/**
 * Copyright (c)2010-2011 Enterprise Website Content Management System(EWCMS), All rights reserved.
 * EWCMS PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * http://www.ewcms.com
 */

package com.sshdemo.common.schedule.manage.dao;

import org.springframework.stereotype.Repository;

import com.sshdemo.common.dao.JpaDAO;
import com.sshdemo.common.schedule.model.JobInfo;

/**
 * 定时任务DAO
 * 
 * @author 吴智俊
 */
@Repository
public class JobInfoDAO extends JpaDAO<Long, JobInfo>{
}
