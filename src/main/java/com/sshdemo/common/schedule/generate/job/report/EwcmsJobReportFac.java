/**
 * Copyright (c)2010-2011 Enterprise Website Content Management System(EWCMS), All rights reserved.
 * EWCMS PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * http://www.ewcms.com
 */
package com.sshdemo.common.schedule.generate.job.report;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sshdemo.common.schedule.BaseException;
import com.sshdemo.common.schedule.generate.job.report.model.EwcmsJobParameter;
import com.sshdemo.common.schedule.generate.job.report.model.EwcmsJobReport;
import com.sshdemo.common.schedule.generate.job.report.service.EwcmsJobReportServiceable;
import com.sshdemo.common.schedule.manage.vo.PageDisplayVO;

/**
 * 
 * @author wu_zhijun
 *
 */
@Service
public class EwcmsJobReportFac implements EwcmsJobReportFacable {

	@Autowired
	private EwcmsJobReportServiceable ewcmsJobReportService;
	
	@Override
	public Long saveOrUpdateJobReport(Long reportId, PageDisplayVO vo,
			String reportType, Set<EwcmsJobParameter> ewcmsJobParameters) throws BaseException {
		return ewcmsJobReportService.saveOrUpdateJobReport(reportId, vo, reportType, ewcmsJobParameters);
	}

	@Override
	public EwcmsJobReport getScheduledJobReport(Long jobId) {
		return ewcmsJobReportService.getScheduledJobReport(jobId);
	}

	@Override
	public EwcmsJobReport getSchedulingByReportId(Long reportId,
			String reportType) {
		return ewcmsJobReportService.getSchedulingByReportId(reportId, reportType);
	}

	@Override
	public List<EwcmsJobParameter> findByJobReportParameterById(Long jobReportId) {
		return ewcmsJobReportService.findByJobReportParameterById(jobReportId);
	}

}
