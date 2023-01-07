package com.kaltsit.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kaltsit.entity.MenuEntity;
import com.kaltsit.mapper.MenuMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, MenuEntity> {

    /**
     * 递归-根据根节点获取菜单对象
     * @param rootId 根目录
     * @return 菜单
     */
    public MenuEntity getMenuTree(String rootId) {
        if(StringUtils.isEmpty(rootId)){
            rootId = "0";
        }
        //TODO SELECT * FROM menu t WHERE t.id = ? and t.id in (...)
        //根据rootId获取节点对象(SELECT * FROM menu t WHERE t.id = ?)
        MenuEntity menu = this.getBaseMapper().selectById(rootId);
        //查询rootId下的所有子节点(SELECT * FROM menu WHERE parent_id = ?)
        LambdaQueryWrapper<MenuEntity> lqw = new LambdaQueryWrapper<MenuEntity>()
                .eq(MenuEntity::getParentId, rootId)
                .orderByAsc(MenuEntity::getOrderNum);
        List<MenuEntity> childTreeNodes = this.baseMapper.selectList(lqw);
        //遍历子节点
        for (MenuEntity child : childTreeNodes) {
            //递归
            MenuEntity n = getMenuTree(child.getId());
            menu.getChildren().add(n);
        }
        return menu;
    }

    /**
     * 传入用户所拥有的所有菜单权限
     * @param menuList 菜单数组
     * @return 菜单
     */
    public List<MenuEntity> buildTree(List<MenuEntity> menuList) {
        List<MenuEntity> treeMenus = new ArrayList<>();
        for (MenuEntity menuNode : getRootNode(menuList)) {
            buildChildTree(menuNode, menuList);
            treeMenus.add(menuNode);
        }
        return treeMenus;
    }

    //递归，建立子树形结构
    public MenuEntity buildChildTree(MenuEntity pNode, List<MenuEntity> menuList) {
        List<MenuEntity> childMenus = new ArrayList<>();
        for (MenuEntity menuNode : menuList) {
            if (menuNode.getParentId().equals(pNode.getId())) {
                childMenus.add(buildChildTree(menuNode, menuList));
            }
        }
        pNode.setChildren(childMenus);
        return pNode;
    }

    //获取根节点
    public List<MenuEntity> getRootNode(List<MenuEntity> menuList) {
        List<MenuEntity> rootMenuLists = new ArrayList<>();
        for (MenuEntity menuNode : menuList) {
            if (menuNode.getParentId().equals("0")) {
                rootMenuLists.add(menuNode);
            }
        }
        return rootMenuLists;
    }

    @Transactional
    public void deleteByMenuId(String id){
        MenuEntity rootMenu = getMenuTree(id);
        Set<String> set = new HashSet<>();
        getAllChildrenIds(rootMenu, set);
        set.forEach(e -> {
            this.getBaseMapper().deleteWithDelToken(this.getById(e));
        });
    }

    public void getAllChildrenIds(MenuEntity menu, Set<String> set){
        if(menu.getChildren().size() > 0){
            for (MenuEntity m : menu.getChildren()){
                getAllChildrenIds(m, set);
            }
        }
        set.add(menu.getId());
    }

}