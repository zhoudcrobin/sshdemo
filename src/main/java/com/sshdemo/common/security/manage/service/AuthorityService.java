/**
 * Copyright (c)2010-2011 Enterprise Website Content Management System(EWCMS), All rights reserved.
 * EWCMS PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * http://www.ewcms.com
 */

package com.sshdemo.common.security.manage.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sshdemo.common.security.manage.dao.AuthorityDAOable;


/**
 *  实现通用权限服务
 *  
 * @author wangwei
 */
@Service
public class AuthorityService implements AuthorityServiceable{

    @Autowired
    private AuthorityDAOable authorityDao; 
    
    @Override
    public boolean hasAuthorityname(String name) {
        return authorityDao.get(name) != null;
    }

    public void setAuthorityDao(AuthorityDAOable authorityDao) {
        this.authorityDao = authorityDao;
    }

}
