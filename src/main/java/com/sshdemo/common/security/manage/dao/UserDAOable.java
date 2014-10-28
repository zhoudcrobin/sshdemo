/**
 * Copyright (c)2010-2011 Enterprise Website Content Management System(EWCMS), All rights reserved.
 * EWCMS PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * http://www.ewcms.com
 */

package com.sshdemo.common.security.manage.dao;

import com.sshdemo.common.dao.JpaDAOable;
import com.sshdemo.common.security.manage.model.User;

public interface UserDAOable extends JpaDAOable<String,User>{

    /**
     * 更新用户密码
     * 
     * @param username 用户名
     * @param password 用户密码
     */
    void updatePassword(String username,String password);
}
