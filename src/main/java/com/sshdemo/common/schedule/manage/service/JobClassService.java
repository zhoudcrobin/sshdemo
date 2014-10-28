/**
 * Copyright (c)2010-2011 Enterprise Website Content Management System(EWCMS), All rights reserved.
 * EWCMS PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * http://www.ewcms.com
 */

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sshdemo.common.schedule.manage.service;

import java.lang.Class;
import java.util.List;

import org.quartz.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sshdemo.common.schedule.BaseException;
import com.sshdemo.common.schedule.manage.dao.JobClassDAO;
import com.sshdemo.common.schedule.model.JobClass;

/**
 * 
 * @author 吴智俊
 */
@Service
public class JobClassService implements JobClassServiceable {

	@Autowired
	private JobClassDAO jobClassDAO;

	@Override
	public Long saveJobClass(JobClass alqcJobClass) throws BaseException {
		if (validator(alqcJobClass)) {
			jobClassDAO.persist(alqcJobClass);
			return alqcJobClass.getId();
		}
		return null;
	}

	@Override
	public Long updateJobClass(JobClass alqcJobClass) throws BaseException {
		jobClassDAO.merge(alqcJobClass);
		return alqcJobClass.getId();
	}

	@Override
	public JobClass findByJobClass(Long id) throws BaseException {
		return (JobClass) jobClassDAO.get(id);
	}

	@Override
	public List<JobClass> findByAllJobClass() throws BaseException {
		return (List<JobClass>) jobClassDAO.findAll();
	}

	@Override
	public void deletedJobClass(Long id) throws BaseException {
		jobClassDAO.removeByPK(id);
	}

	protected Boolean validator(JobClass jobClass) throws BaseException {
		String jobClassEntity = jobClass.getClassEntity().trim();
		if (jobClassEntity != null && jobClassEntity.length() > 0) {
			try {
				Class<?> classEntity = Class.forName(jobClassEntity);
				if (Job.class.isAssignableFrom(classEntity)) {
					return true;
				} else {
					throw new BaseException("不是一个有效的作业", "不是一个有效的作业");
				}
			} catch (ClassNotFoundException ex) {
				throw new BaseException("未找到执行的作业", "未找到执行的作业");
			}
		} else {
			throw new BaseException("未设置作业", "未设置作业");
		}

	}

	@Override
	public JobClass findByJobClassByClassEntity(String classEntity) throws BaseException {
		return jobClassDAO.findByJobClassByClassEntity(classEntity);
	}

}
