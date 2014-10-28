/**
 * Copyright (c)2010-2011 Enterprise Website Content Management System(EWCMS), All rights reserved.
 * EWCMS PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * http://www.ewcms.com
 */

package com.sshdemo.common.security.manage.dao;

import org.springframework.stereotype.Repository;

import com.sshdemo.common.dao.JpaDAO;
import com.sshdemo.common.security.manage.model.Authority;

/**
 * 通用权限数据操作
 * 
 * @author wangwei
 */
@Repository
public class AuthorityDAO extends JpaDAO<String,Authority> implements AuthorityDAOable{
    
}
