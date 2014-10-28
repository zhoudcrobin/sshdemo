/**
 * Copyright (c)2010-2011 Enterprise Website Content Management System(EWCMS), All rights reserved.
 * EWCMS PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * http://www.ewcms.com
 */

package com.sshdemo.common.schedule.manage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sshdemo.common.schedule.manage.dao.JobInfoDAO;
import com.sshdemo.common.schedule.manage.dao.JobTriggerDAO;
import com.sshdemo.common.schedule.model.JobInfo;

/**
 * @author 吴智俊
 */
@Service
public class JobInfoService implements JobInfoServiceable {
	
	@Autowired
	private JobInfoDAO jobInfoDAO;
	@Autowired
	private JobTriggerDAO jobTriggerDAO;
	
	@Override
	public JobInfo saveJob(JobInfo jobInfo) {
		jobInfoDAO.persist(jobInfo);
		jobInfoDAO.flush(jobInfo);
		return jobInfo;
	}

	@Override
	public JobInfo updateJob(JobInfo jobInfo) {
		if (jobInfo.getTrigger() != null){
			Long triggerId = jobInfo.getTrigger().getId();
			if (triggerId != null){
				jobTriggerDAO.removeByPK(triggerId);
				jobInfo.getTrigger().setId(null);
				jobInfo.getTrigger().setVersion(-1);
			}
		}
		jobInfoDAO.merge(jobInfo);
		jobInfoDAO.flush(jobInfo);
		jobInfo = jobInfoDAO.get(jobInfo.getId());
		return jobInfo;
	}

	@Override
	public JobInfo findByJob(Long id)  {
		return jobInfoDAO.get(id);
	}
	
	@Override
	public void deletedJob(Long id)  {
		jobInfoDAO.removeByPK(id);
	}
	
	@Override
	public List<JobInfo> findByAllJob() {
		return (List<JobInfo>)jobInfoDAO.findAll();
	}
}
