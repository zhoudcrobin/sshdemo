/**
 * Copyright (c)2010-2011 Enterprise Website Content Management System(EWCMS), All rights reserved.
 * EWCMS PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * http://www.ewcms.com
 */
package com.sshdemo.common.extendds.manage.dao;

import org.springframework.stereotype.Repository;

import com.sshdemo.common.dao.JpaDAO;
import com.sshdemo.common.extendds.model.BaseDS;

/**
 * 
 * @author wu_zhijun
 *
 */
@Repository
public class BaseDSDAO extends JpaDAO<Long, BaseDS> {

}
