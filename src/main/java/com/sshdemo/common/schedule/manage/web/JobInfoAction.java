/**
 * Copyright (c)2010-2011 Enterprise Website Content Management System(EWCMS), All rights reserved.
 * EWCMS PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * http://www.ewcms.com
 */

package com.sshdemo.common.schedule.manage.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.sshdemo.common.report.generate.factory.ChartFactoryable;
import com.sshdemo.common.report.generate.factory.TextFactoryable;
import com.sshdemo.common.report.generate.vo.PageShowParam;
import com.sshdemo.common.report.model.ChartReport;
import com.sshdemo.common.report.model.TextReport;
import com.sshdemo.common.schedule.BaseException;
import com.sshdemo.common.schedule.generate.job.JobClassEntity;
import com.sshdemo.common.schedule.generate.job.report.EwcmsJobReportFacable;
import com.sshdemo.common.schedule.generate.job.report.model.EwcmsJobReport;
import com.sshdemo.common.schedule.manage.SchedulingFacable;
import com.sshdemo.common.schedule.manage.util.ConversionUtil;
import com.sshdemo.common.schedule.manage.vo.PageDisplayVO;
import com.sshdemo.common.schedule.model.JobClass;
import com.sshdemo.common.schedule.model.JobInfo;
import com.sshdemo.common.web.CrudBaseAction;
/**
 *
 * @author 吴智俊
 */
public class JobInfoAction extends CrudBaseAction<PageDisplayVO, Long> {

	private static final long serialVersionUID = -7180641001521655948L;
	
	@Autowired
	private SchedulingFacable schedulingFac;
	@Autowired
	private EwcmsJobReportFacable ewcmsJobReportFac;
	@Autowired
	private TextFactoryable textFactory;
	@Autowired
	private ChartFactoryable chartFactory;
	
	private Boolean subChannel;
	private List<PageShowParam> pageShowParams = new ArrayList<PageShowParam>();

	public PageDisplayVO getPageDisplayVo(){
		return super.getVo();
	}
	
	public void setPageDisplayVo(PageDisplayVO pageDisplayVo){
		super.setVo(pageDisplayVo);
	}
	
	public List<Long> getSelections() {
		return super.getOperatorPK();
	}

	public void setSelections(List<Long> selections) {
		super.setOperatorPK(selections);
	}
	
	public Boolean getSubChannel() {
		return subChannel;
	}

	public void setSubChannel(Boolean subChannel) {
		this.subChannel = subChannel;
	}

	public List<PageShowParam> getPageShowParams() {
		return pageShowParams;
	}

	public void setPageShowParams(List<PageShowParam> pageShowParams) {
		this.pageShowParams = pageShowParams;
	}

	@Override
	protected PageDisplayVO createEmptyVo() {
		return new PageDisplayVO();
	}

	@Override
	protected void deleteOperator(Long pk) {
		try {
			schedulingFac.deletedScheduledJob(pk);
		} catch (BaseException e) {
			this.addActionMessage(e.getPageMessage());
		}
	}

	@Override
	protected PageDisplayVO getOperator(Long pk) {
		PageDisplayVO pageDisplayVo = new PageDisplayVO();
		try {
			JobInfo jobInfo = schedulingFac.getScheduledJob(pk);
			pageDisplayVo = ConversionUtil.constructPageVo(jobInfo);
			
			pageDisplayVo.setIsJobChannel(false);
			pageDisplayVo.setIsJobReport(false);
			pageDisplayVo.setIsJobCrawler(false);
			
			if (jobInfo instanceof EwcmsJobReport){
				TextReport textReport = ((EwcmsJobReport) jobInfo).getTextReport();
				ChartReport chartReport = ((EwcmsJobReport) jobInfo).getChartReport();
				pageDisplayVo = ConversionUtil.constructPageVo((EwcmsJobReport) jobInfo);
				if (textReport != null) {
					pageDisplayVo.setReportId(textReport.getId());
					pageDisplayVo.setReportName(textReport.getName());
					pageDisplayVo.setReportType("text");
					pageDisplayVo.setPageShowParams(ConversionUtil.coversionParameterFromPage(ewcmsJobReportFac.findByJobReportParameterById(jobInfo.getId()), textFactory.textParameters(textReport)));
					pageDisplayVo.setOutputFormats(ConversionUtil.stringToArray(((EwcmsJobReport) jobInfo).getOutputFormat()));
				} else if (chartReport != null) {
					pageDisplayVo.setReportId(chartReport.getId());
					pageDisplayVo.setReportName(chartReport.getName());
					pageDisplayVo.setReportType("chart");
					pageDisplayVo.setPageShowParams(ConversionUtil.coversionParameterFromPage(ewcmsJobReportFac.findByJobReportParameterById(jobInfo.getId()), chartFactory.chartParameters(chartReport)));
				}
				pageDisplayVo.setIsJobReport(true);
				
				setPageDisplayVo(pageDisplayVo);
				setPageShowParams(pageDisplayVo.getPageShowParams());
			}
		} catch (BaseException e) {
		}
		return pageDisplayVo;
	}

	@Override
	protected Long getPK(PageDisplayVO vo) {
		return vo.getJobId();
	}

	@Override
	protected Long saveOperator(PageDisplayVO vo, boolean isUpdate) {
//		AlqcJob alqcJob = new AlqcJob();
//		try{
//			if (vo.getJobId() != null && vo.getJobId().intValue() > 0){
//				alqcJob = alqcSchedulingFac.getScheduledJob(vo.getJobId());
//			}
//			if (vo.getJobClassId() != null && vo.getJobClassId().intValue() > 0){
//				AlqcJobClass alqcJobClass = alqcSchedulingFac.findByJobClass(vo.getJobClassId());
//				alqcJob.setJobClass(alqcJobClass);
//			}
//			if (isUpdate) {
//				return alqcSchedulingFac.updateScheduledJob(ConversionUtil.constructAlqcJobVo(alqcJob,vo));
//			}else{
//				return alqcSchedulingFac.saveScheduleJob(ConversionUtil.constructAlqcJobVo(alqcJob, vo));
//			}
//		}catch(BaseException e){
//			this.addActionMessage(e.getPageMessage());
//			return null;
//		}
		return null;
	}
	
	@Override
	public String save() throws Exception{
		JobInfo jobInfo = new JobInfo();
		try{
			if (getPageDisplayVo().getJobId() != null && getPageDisplayVo().getJobId().intValue() > 0){
				jobInfo = schedulingFac.getScheduledJob(getPageDisplayVo().getJobId());
			}
			if (getPageDisplayVo().getJobClassId() != null && getPageDisplayVo().getJobClassId().intValue() > 0){
				JobClass alqcJobClass = schedulingFac.findByJobClass(getPageDisplayVo().getJobClassId());
				jobInfo.setJobClass(alqcJobClass);
			}
			jobInfo = ConversionUtil.constructJobInfoVo(jobInfo, getPageDisplayVo());
			
			if (isUpdateOperator()) {
				operatorState = OperatorState.UPDATE;
			    schedulingFac.updateScheduledJob(jobInfo);
			    operatorPK.remove(0);
			    if (!operatorPK.isEmpty()){
			    	setPageDisplayVo(getOperator(operatorPK.get(0)));
			    }
			} else {
				operatorState = OperatorState.ADD;
				Long id = schedulingFac.saveScheduleJob(jobInfo);
	            operatorPK.add(id);
	            setPageDisplayVo(createEmptyVo());
			}
		}catch(BaseException e){
			addActionMessage(e.getPageMessage());
		}finally{
			getPageDisplayVo().setIsJobChannel(false);
			getPageDisplayVo().setIsJobReport(false);
			getPageDisplayVo().setIsJobCrawler(false);
			if (jobInfo instanceof EwcmsJobReport){
				getPageDisplayVo().setIsJobReport(true);
			}
		}
		return SUCCESS;
	}
	
	public List<JobClass> getAllJobClassList() {
		List<JobClass> jobClasses = new ArrayList<JobClass>();
		try {
			jobClasses = schedulingFac.findByAllJobClass();
			if (!getPageDisplayVo().getIsJobReport()){
				JobClass jobClass = schedulingFac.findByJobClassByClassEntity(JobClassEntity.JOB_REPORT);
				jobClasses.remove(jobClass);
			}
		} catch (BaseException e) {
			e.printStackTrace();
		}
		return jobClasses;
	}
	
	private Long jobId;
	
	public Long getJobId() {
		return jobId;
	}

	public void setJobId(Long jobId) {
		this.jobId = jobId;
	}

	public String pauseJob(){
		try {
			schedulingFac.pauseJob(getJobId());
		} catch (BaseException e) {
			addActionMessage(e.getPageMessage());
		}
		return SUCCESS;
	}
	
	public String resumedJob(){
		try{
			schedulingFac.resumedJob(getJobId());
		}catch(BaseException e){
			addActionMessage(e.getPageMessage());
		}
		return SUCCESS;
	}
}
