package com.kaltsit.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kaltsit.entity.MenuEntity;
import com.kaltsit.mapper.MenuMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, MenuEntity> {

    /**
     * 递归-根据更节点获取菜单对象
     * @param rootId 根目录
     * @return 菜单
     */
    public MenuEntity getMenuTree(String rootId) {
        //根据rootId获取节点对象(SELECT * FROM menu t WHERE t.id = ?)
        MenuEntity menu = this.getBaseMapper().selectById(rootId);
        //查询rootId下的所有子节点(SELECT * FROM menu WHERE parent_id = ?)
        LambdaQueryWrapper<MenuEntity> lqw = new LambdaQueryWrapper<MenuEntity>().eq(MenuEntity::getParentId, rootId);
        List<MenuEntity> childTreeNodes = this.baseMapper.selectList(lqw);
        //遍历子节点
        for (MenuEntity child : childTreeNodes) {
            //递归
            MenuEntity n = getMenuTree(child.getId());
            menu.getChildren().add(n);
        }
        return menu;
    }

    //建立树形结构
    public List<MenuEntity> buildTree(List<MenuEntity> menuList) {
        List<MenuEntity> treeMenus = new ArrayList<>();
        for (MenuEntity menuNode : getRootNode(menuList)) {
            buildChildTree(menuNode, menuList);
            treeMenus.add(menuNode);
        }
        return treeMenus;
    }

    //递归，建立子树形结构
    private MenuEntity buildChildTree(MenuEntity pNode, List<MenuEntity> menuList) {
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
    private List<MenuEntity> getRootNode(List<MenuEntity> menuList) {
        List<MenuEntity> rootMenuLists = new ArrayList<>();
        for (MenuEntity menuNode : menuList) {
            if (menuNode.getParentId().equals("0")) {
                rootMenuLists.add(menuNode);
            }
        }
        return rootMenuLists;
    }

}
