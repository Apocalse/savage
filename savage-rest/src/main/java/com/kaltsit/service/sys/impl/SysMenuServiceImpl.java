package com.kaltsit.service.sys.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kaltsit.mapper.sys.SysMenuMapper;
import com.kaltsit.abstracts.sys.SysMenuEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenuEntity> {

    /**
     * 递归-根据根节点获取菜单对象
     * @param rootId 根目录
     * @return 菜单
     */
    public SysMenuEntity getMenuTree(String rootId, String status) {
        if(StringUtils.isEmpty(rootId)){
            rootId = "0";
        }
        String[] arr = status.split(",");
        //TODO SELECT * FROM menu t WHERE t.id = ? and t.id in (...)
        //根据rootId获取节点对象(SELECT * FROM menu t WHERE t.id = ?)
        SysMenuEntity menu = this.baseMapper.selectById(rootId);
        //查询rootId下的所有子节点(SELECT * FROM menu WHERE parent_id = ?)
        LambdaQueryWrapper<SysMenuEntity> lqw = new LambdaQueryWrapper<SysMenuEntity>()
                .eq(SysMenuEntity::getParentId, rootId)
                .and(wrapper -> {
                    for (int i = 0; i < arr.length; i++) {
                        wrapper.eq(SysMenuEntity::getStatus, Integer.parseInt(arr[i]));
                        if (i < status.length() - 1) // 只要不是最后一个，则追加一个 or 操作符
                            wrapper.or();
                    }
                })
                .orderByAsc(SysMenuEntity::getOrderNum);
        List<SysMenuEntity> childTreeNodes = this.baseMapper.selectList(lqw);
        //遍历子节点
        for (SysMenuEntity child : childTreeNodes) {
            //递归
            SysMenuEntity n = getMenuTree(child.getId(), status);
            menu.getChildren().add(n);
        }
        return menu;
    }

    /**
     * 传入用户所拥有的所有菜单权限
     * @param menuList 菜单数组
     * @return 菜单
     */
    public List<SysMenuEntity> buildTree(List<SysMenuEntity> menuList) {
        List<SysMenuEntity> treeMenus = new ArrayList<>();
        for (SysMenuEntity menuNode : getRootNode(menuList)) {
            buildChildTree(menuNode, menuList);
            treeMenus.add(menuNode);
        }
        return treeMenus;
    }

    //递归，建立子树形结构
    public SysMenuEntity buildChildTree(SysMenuEntity pNode, List<SysMenuEntity> menuList) {
        List<SysMenuEntity> childMenus = new ArrayList<>();
        for (SysMenuEntity menuNode : menuList) {
            if (menuNode.getParentId().equals(pNode.getId())) {
                childMenus.add(buildChildTree(menuNode, menuList));
            }
        }
        pNode.setChildren(childMenus);
        return pNode;
    }

    //获取根节点
    public List<SysMenuEntity> getRootNode(List<SysMenuEntity> menuList) {
        List<SysMenuEntity> rootMenuLists = new ArrayList<>();
        for (SysMenuEntity menuNode : menuList) {
            if (menuNode.getParentId().equals("0")) {
                rootMenuLists.add(menuNode);
            }
        }
        return rootMenuLists;
    }

    @Transactional
    public void deleteByMenuId(String id){
        SysMenuEntity rootMenu = getMenuTree(id, "1,2,3");
        Set<String> set = new HashSet<>();
        getAllChildrenIds(rootMenu, set);
        set.forEach(e -> {
            this.baseMapper.deleteWithDelToken(this.getById(e));
        });
    }

    public void getAllChildrenIds(SysMenuEntity menu, Set<String> set){
        if(menu.getChildren().size() > 0){
            for (SysMenuEntity m : menu.getChildren()){
                getAllChildrenIds(m, set);
            }
        }
        set.add(menu.getId());
    }

}
