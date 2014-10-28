/**
 * Copyright (c)2010-2011 Enterprise Website Content Management System(EWCMS), All rights reserved.
 * EWCMS PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * http://www.ewcms.com
 */

package com.sshdemo.common.security.manage.web.group;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.sshdemo.common.security.manage.SecurityFacable;
import com.sshdemo.common.security.manage.model.Authority;
import com.sshdemo.common.security.manage.model.Group;
import com.sshdemo.common.security.manage.model.User;
import com.sshdemo.common.security.manage.service.UserServiceException;
import com.sshdemo.common.web.JsonBaseAction;
import com.sshdemo.common.web.vo.DataGrid;

/**
 * 用户组明细Action
 * 
 * @author wangwei
 */
@Controller("security.group.detail.action")
public class DetailAction extends ActionSupport{
    
	private static final long serialVersionUID = -7312151991988099806L;

	private static final Logger logger = LoggerFactory.getLogger(DetailAction.class);
    private static final String Authority_Group_Title = "权限";
    private static final String User_Group_Title = "用户";
    
    private String name;
    private Set<String> authNames;
    private Set<String> usernames;
    private Boolean showTitle = Boolean.TRUE;
    
    @Autowired
    private SecurityFacable fac;
    
    @Override
    public String execute(){
        return SUCCESS;
    }
    
    public void query(){
        Group group = fac.getGroup(name);
        
        JsonBaseAction json = new JsonBaseAction();
        
        if(group == null){
            logger.warn("Group name is {} but get is null",name);
            json.renderObject(new DataGrid(0,Collections.EMPTY_LIST));
            return ;
        }
        
        List<PropertyGridItem> items = new ArrayList<PropertyGridItem>();
        items.addAll(getItems(group.getAuthorities(),new ConvertToPropertyGridItem (){
            @Override
            public PropertyGridItem convert(Object value) {
                Authority authority = (Authority)value;
                return new PropertyGridItem(authority.getName(),authority.getRemark(),Authority_Group_Title);
            }
        }));
        items.addAll(getItems(group.getUsers(),new ConvertToPropertyGridItem (){
            @Override
            public PropertyGridItem convert(Object value) {
                User user = (User)value;
                return new PropertyGridItem(user.getUsername(),user.getUserInfo().getName(),User_Group_Title);
            }
        }));
            
        json.renderObject(new DataGrid(items.size(),items));
    }
    
    private List<PropertyGridItem> getItems(Collection<?> values,ConvertToPropertyGridItem convert){
        List<PropertyGridItem> items = new ArrayList<PropertyGridItem>();
        for(Object value : values){
            items.add(convert.convert(value));
        }
        return items;
    }

    /**
     * 添加权限和用户
     */
    public void addAuthsAndUsers(){
        JsonBaseAction json = new JsonBaseAction();
        try{
            fac.addAuthsAndUsersToGroup(name, authNames, usernames);
            json.renderSuccess();    
        }catch(UserServiceException e){
            json.renderError(e.getMessage());
        }
    }
    
    /**
     * 删除权限和用户
     */
    public void removeAuthsAndUsers(){
        JsonBaseAction json = new JsonBaseAction();
        try{
            fac.removeAuthsAndUsersInGroup(name, authNames, usernames);
            json.renderSuccess();    
        }catch(UserServiceException e){
            json.renderError(e.getMessage());
        }
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public Boolean getShowTitle() {
        return showTitle;
    }

    public void setShowTitle(Boolean showTitle) {
        this.showTitle = showTitle;
    }

    public Set<String> getAuthNames() {
        return authNames;
    }

    public void setAuthNames(Set<String> authNames) {
        this.authNames = authNames;
    }

    public Set<String> getUsernames() {
        return usernames;
    }

    public void setUsernames(Set<String> usernames) {
        this.usernames = usernames;
    }
    
    /**
     * jquery easyui propertygrid 显示行对象
     */
    public static class PropertyGridItem {

        private Object name;
        private Object value;
        private String group;
        
        public PropertyGridItem(Object name,Object value,String group){
            this.name = name;
            this.value = value;
            this.group = group;
        }
        
        public Object getName() {
            return name;
        }
        
        public Object getValue() {
            return value;
        }
        
        public String getGroup() {
            return group;
        }
    }
    
    /**
     * 转换对象成PropertyGridItem
     */
    private interface ConvertToPropertyGridItem{
        
        PropertyGridItem convert(Object value);
    }
}
