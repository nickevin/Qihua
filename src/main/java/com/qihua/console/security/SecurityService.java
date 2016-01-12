package com.qihua.console.security;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qihua.exception.NullObjectException;

/**
 * Class description goes here.
 * 
 * @author aopfilter@163.com
 * @since 2013-10-11
 * @version 1.0
 * @see
 */
@Service
public class SecurityService {

    @Autowired
    private SecurityDAO securityDAO;

    public List<Role> findRoles() {
        List<Role> list = securityDAO.selectRole();
        if (list.isEmpty()) {
            return Collections.emptyList();
        }

        return list;
    }

    public Role findRoleById(Long id) throws Exception {
        try {
            return securityDAO.selectRole(id);
        } catch (Exception e) {
            throw new NullObjectException();
        }
    }

    public List<Menu> findMenus() throws Exception {
        List<Menu> list = securityDAO.selectMenu();
        for (Menu item : list) {
            item.setItems(getChildren(item, list));
        }

        return filterChildren(list);
    }

    public List<Menu> findMenuByRoleId(Long id) throws Exception {
        List<Menu> list = securityDAO.selectByRoleId(id);
        for (Menu item : list) {
            item.setItems(getChildren(item, list));
        }

        return filterChildren(list);
    }

    public List<Menu> findMenuByRole(Role role) throws Exception {
        return findMenuByRoleId(role.getRoleId());
    }

    private List<Menu> getChildren(Menu parent, List<Menu> list) {
        List<Menu> newList = new ArrayList<Menu>();

        for (Menu item : list) {
            if (item.getParentId().equals(parent.getMenuId())) {
                newList.add(item);
            }
        }

        return newList;
    }

    private List<Menu> filterChildren(List<Menu> list) {
        ArrayList<Menu> newList = new ArrayList<Menu>();
        for (Menu item : list) {
            if (item.getParentId() == 1) {
                newList.add(item);
            }
        }

        return newList;
    }

    @Transactional(rollbackFor = Exception.class)
    public Role save(Role role) throws Exception {
        if (role.getRoleId() == 0) {
            Role newItem = securityDAO.insertRole(role);
            securityDAO.insertRoleMenu(newItem, newItem.getItems());

            return newItem;
        } else {
            Role existed = securityDAO.updateRole(role);
            securityDAO.updateRoleMenu(role, role.getItems());

            return existed;
        }
    }

}
