/**
 * Copyright (c)2010-2011 Enterprise Website Content Management System(EWCMS), All rights reserved.
 * EWCMS PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * http://www.ewcms.com
 */

package com.sshdemo.common.schedule.manage.web;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.sshdemo.common.query.Resultable;
import com.sshdemo.common.query.jpa.QueryFactory;
import com.sshdemo.common.schedule.BaseException;
import com.sshdemo.common.schedule.generate.quartz.JobsQuartzSchedulerable;
import com.sshdemo.common.schedule.manage.SchedulingFacable;
import com.sshdemo.common.schedule.model.JobInfo;
import com.sshdemo.common.web.QueryBaseAction;
import com.sshdemo.common.web.util.JSONUtil;
import com.sshdemo.common.web.util.Struts2Util;
import com.sshdemo.common.web.vo.DataGrid;

/**
 *
 * @author 吴智俊
 */
public class JobInfoQueryAction extends QueryBaseAction {
	
	private static final long serialVersionUID = -8882837349113907705L;
	
	protected static final Logger logger = LoggerFactory.getLogger(JobInfoQueryAction.class);
	
	private DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	@Autowired
	private JobsQuartzSchedulerable jobsQuartzScheduler;
	@Autowired
	private SchedulingFacable schedulingFac;

    @Override
    protected Resultable queryResult(QueryFactory queryFactory, String cacheKey, int rows, int page, Order order) {
        return null;
    }

    @Override
    protected Resultable querySelectionsResult(QueryFactory queryFactory, int rows, int page, String[] selections, Order order) {
        return null;
    }
    
	@Override
	public String query() {
		List<JobInfo> alqcJobs = new ArrayList<JobInfo>();
		try{
			alqcJobs = schedulingFac.getScheduledJobs();
			alqcJobs = jobsQuartzScheduler.getJobsRuntimeInformation(alqcJobs);
		}catch(BaseException e){
			logger.error(e.toString());
		}
		DataGrid data = new DataGrid(alqcJobs.size(), alqcJobs);
		Struts2Util.renderJson(JSONUtil.toJSON(data, DATE_FORMAT));
		
		return NONE;
	}

}
