/**
 * Copyright (c)2010-2011 Enterprise Website Content Management System(EWCMS), All rights reserved.
 * EWCMS PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * http://www.ewcms.com
 */

package com.sshdemo.common.schedule.generate.job;

import com.sshdemo.common.schedule.generate.job.report.EwcmsExecutionReportJob;


/**
 * 执行定时工作任务实体类路径
 * 
 * @author 吴智俊
 */
public final class JobClassEntity {
	//报表执行JOB
	public static final String JOB_REPORT = EwcmsExecutionReportJob.class.getName().toString();
}
