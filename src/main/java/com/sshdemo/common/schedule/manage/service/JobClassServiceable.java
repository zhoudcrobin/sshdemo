/**
 * Copyright (c)2010-2011 Enterprise Website Content Management System(EWCMS), All rights reserved.
 * EWCMS PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * http://www.ewcms.com
 */

package com.sshdemo.common.schedule.manage.service;

import java.util.List;

import com.sshdemo.common.schedule.BaseException;
import com.sshdemo.common.schedule.model.JobClass;

/**
 *
 * @author 吴智俊
 */
public interface JobClassServiceable {
    public Long saveJobClass(JobClass alqcJobClass) throws BaseException;

    public Long updateJobClass(JobClass alqcJobClass) throws BaseException;

    public JobClass findByJobClass(Long id) throws BaseException;

    public List<JobClass> findByAllJobClass() throws BaseException;

    public void deletedJobClass(Long id) throws BaseException;

    public JobClass findByJobClassByClassEntity(String classEntity) throws BaseException;
}
