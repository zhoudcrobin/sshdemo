/**
 * Copyright (c)2010-2011 Enterprise Website Content Management System(EWCMS), All rights reserved.
 * EWCMS PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * http://www.ewcms.com
 */

package com.sshdemo.common.security.manage.service;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.core.userdetails.UserCache;
import org.springframework.security.core.userdetails.cache.NullUserCache;

import com.sshdemo.common.lang.EwcmsMessageSource;
import com.sshdemo.common.security.core.session.EwcmsSessionRegistry;
import com.sshdemo.common.security.manage.dao.AuthorityDAOable;
import com.sshdemo.common.security.manage.dao.GroupDAOable;
import com.sshdemo.common.security.manage.dao.UserDAOable;
import com.sshdemo.common.security.manage.model.Authority;
import com.sshdemo.common.security.manage.model.Group;
import com.sshdemo.common.security.manage.model.User;

/**
 * 权限服务管理实现
 * 
 * @author wangwei
 */
abstract class AbstractService implements MessageSourceAware {
    
    private static final Logger logger = LoggerFactory.getLogger(AbstractService.class);
    
    protected MessageSourceAccessor messages = EwcmsMessageSource.getAccessor();
    @Autowired
    protected AuthorityDAOable authorityDao;
    @Autowired
    protected GroupDAOable groupDao;
    @Autowired
    protected UserDAOable userDao;
    @Autowired(required = false)
    protected UserCache userCache = new NullUserCache();
    @Autowired(required = false)
    protected EwcmsSessionRegistry sessionRegistry;
    
    protected Set<User> getUsersByNames(final Set<String> names)throws UserServiceException{
        Set<User> users = new HashSet<User>();
        for(Iterator<String> iterator = names.iterator();iterator.hasNext();){
            String name = iterator.next();
            User user = getUserByUsername(name);
            users.add(user);
        }
        return users;
    }
    
    protected User getUserByUsername(final String name){
        User user = userDao.get(name);
        if(user == null){
            throw new UserServiceException(
                    messages.getMessage("AbstractService.userNotFound",new Object[]{name},"Can't found  "+ name + " user"));
        }
        return user;
    }
    
    protected Set<Authority> getAuthoritiesByNames(final Set<String> names){
        Set<Authority> authorities = new HashSet<Authority>();
        for(Iterator<String> iterator = names.iterator(); iterator.hasNext();){
            String name = iterator.next();
            Authority authority = getAuthorityByName(name);
            authorities.add(authority);
        }
        return authorities;
    }
    
    protected Authority getAuthorityByName(final String name){
        Authority authority = authorityDao.get(name);
        if(authority == null){
            throw new UserServiceException(messages.getMessage(
                    "AbstractService.authorityNotFound",new Object[]{name},"Can't found  "+ name + " authority"));
        }
        return authority;
    }
    
    protected Set<Group> getGroupsByNames(final Set<String> names){
        Set<Group> groups = new HashSet<Group>();
        for(Iterator<String> iterator = names.iterator(); iterator.hasNext();){
            String name = iterator.next();
            Group group = getGroupByName(name);
            groups.add(group);
        }
        return groups;
    }
    
    protected Group getGroupByName(final String name){
        Group group = groupDao.get(name);
        if(group == null){
            throw new UserServiceException(messages.getMessage(
                    "AbstractService.groupNotFound",new Object[]{name},"Can't found  "+ name + " group"));
        }
        return group;
    }
    
    @Override
    public void setMessageSource(MessageSource messageSource) {
        messages = new MessageSourceAccessor(messageSource);
    }

    public void setAuthorityDao(AuthorityDAOable dao) {
        authorityDao = dao;
    }

    public void setGroupDao(GroupDAOable dao) {
        this.groupDao = dao;
    }

    public void setUserDao(UserDAOable dao) {
        this.userDao = dao;
    }

    public void setUserCache(UserCache cache) {
        this.userCache = cache;
    }

    public void setSessionRegistry(EwcmsSessionRegistry sessionRegistry) {
        this.sessionRegistry = sessionRegistry;
    }

    /**
     * 从系统环境中移除过时的用户
     * 
     * 用户的属性经过重新设置后，而系统环境中保存的用户信息已经过时，所以需要移除cache和session中的信息，保持信息同步。
     * 
     * @param name 用户名
     */
    protected void removeExpiredUserByUsername(final String name) {
        userCache.removeUserFromCache(name);
        if(sessionRegistry == null){
            if(logger.isDebugEnabled()){
                logger.debug("Can't remove user in session,because sessionRegistry is null");
            }
            return ;
        }
        sessionRegistry.removeSessionInformationByUsername(name);
    }
}
