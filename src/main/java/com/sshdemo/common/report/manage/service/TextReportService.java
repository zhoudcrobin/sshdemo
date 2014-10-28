/**
 * Copyright (c)2010-2011 Enterprise Website Content Management System(EWCMS), All rights reserved.
 * EWCMS PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * http://www.ewcms.com
 */
package com.sshdemo.common.report.manage.service;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import net.sf.jasperreports.engine.JRParameter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.sshdemo.common.BaseException;
import com.sshdemo.common.report.manage.dao.CategoryReportDAO;
import com.sshdemo.common.report.manage.dao.TextReportDAO;
import com.sshdemo.common.report.manage.util.ParameterSetValueUtil;
import com.sshdemo.common.report.manage.util.TextDesignUtil;
import com.sshdemo.common.report.model.CategoryReport;
import com.sshdemo.common.report.model.Parameter;
import com.sshdemo.common.report.model.TextReport;
import com.sshdemo.common.schedule.generate.job.report.dao.EwcmsJobReportDAO;
import com.sshdemo.common.schedule.generate.job.report.model.EwcmsJobReport;
/**
 * 
 * @author wu_zhijun
 *
 */
@Service
public class TextReportService implements TextReportServiceable {

	@Autowired
	private TextReportDAO textReportDAO;
	@Autowired
	private CategoryReportDAO categorReportDAO;
	@Autowired
	private EwcmsJobReportDAO ewcmsJobReportDAO;
	
	@Override
	public Long addTextReport(TextReport textReport) throws BaseException {
		byte[] reportFile = textReport.getTextEntity();

		if (reportFile != null && reportFile.length > 0) {
			InputStream in = new ByteArrayInputStream(reportFile);
			TextDesignUtil rd = new TextDesignUtil(in);
			List<JRParameter> paramList = rd.getParameters();
			
			Set<Parameter> icSet = new LinkedHashSet<Parameter>();
			if (!paramList.isEmpty()) {
				for (JRParameter param : paramList) {
					Parameter ic = getParameterValue(param);
					icSet.add(ic);
				}
				textReport.setParameters(icSet);
			}
		}
		textReportDAO.persist(textReport);
		return textReport.getId();
	}

	@Override
	public Long updTextReport(TextReport textReport) throws BaseException {
		TextReport entity = textReportDAO.get(textReport.getId());
		
		entity.setBaseDS(textReport.getBaseDS());
		entity.setName(textReport.getName());
		entity.setHidden(textReport.getHidden());
		entity.setRemarks(textReport.getRemarks());
		
		entity.setUpdateDate(new Date(Calendar.getInstance().getTime().getTime()));
		
		byte[] reportFile = textReport.getTextEntity();
		if (reportFile != null && reportFile.length > 0) {
			entity.setTextEntity(textReport.getTextEntity());
			
			InputStream in = new ByteArrayInputStream(reportFile);
			TextDesignUtil rd = new TextDesignUtil(in);

			List<JRParameter> paramList = rd.getParameters();
			Set<Parameter> icSet = entity.getParameters();

			Set<Parameter> icNewList = new LinkedHashSet<Parameter>();
			for (JRParameter param : paramList) {
				Parameter ic = findListEntity(icSet, param);
				if (ic == null) {
					ic = getParameterValue(param);
				}
				icNewList.add(ic);
			}
			entity.setParameters(icNewList);
		}
		textReportDAO.merge(entity);
		return entity.getId();
	}

	@Override
	public void delTextReport(Long textReportId){
		TextReport textReport = textReportDAO.get(textReportId);
		Assert.notNull(textReport);
		List<CategoryReport> categories = textReportDAO.findCategoryReportByTextReportId(textReportId);
		if (categories != null && !categories.isEmpty()){
			for (CategoryReport categoryReport : categories){
				Set<TextReport> textReports = categoryReport.getTexts();
				if (textReports.isEmpty()) continue;
				textReports.remove(textReport);
				categoryReport.setTexts(textReports);
				categorReportDAO.merge(categoryReport);
			}
		}
		List<EwcmsJobReport> ewcmsJobReports = textReportDAO.findEwcmsJobReportByTextReportId(textReportId);
		if (ewcmsJobReports != null && !ewcmsJobReports.isEmpty()){
			for (EwcmsJobReport ewcmsJobReport : ewcmsJobReports){
				if (ewcmsJobReport.getChartReport() == null){
					ewcmsJobReportDAO.remove(ewcmsJobReport);
				}else{
					ewcmsJobReport.setTextReport(null);
					ewcmsJobReportDAO.merge(ewcmsJobReport);
				}
			}
		}
		textReportDAO.removeByPK(textReportId);
	}

	@Override
	public TextReport findTextReportById(Long textReportId){
		return textReportDAO.get(textReportId);
	}

	@Override
	public List<TextReport> findAllTextReport() {
		return textReportDAO.findAll();
	}
	
	@Override
	public Long updTextReportParameter(Long textReportId, Parameter parameter) throws BaseException {
		if (textReportId == null || textReportId.intValue() == 0)
			throw new BaseException("", "报表编号不存在，请重新选择！");
		TextReport text = textReportDAO.get(textReportId);
		if (text == null)
			throw new BaseException("", "报表不存在，请重新选择！");
		
		parameter = ParameterSetValueUtil.setParametersValue(parameter);
		
		Set<Parameter> parameters = text.getParameters();
		parameters.remove(parameter);
		parameters.add(parameter);
		text.setParameters(parameters);
		
		textReportDAO.merge(text);
		
		return parameter.getId();
	}
	
	/**
	 * 把报表文件里的参数转换数据参数
	 * 
	 * @param jrParameter
	 *            报表参数对象
	 * @return Parameters
	 */
	private Parameter getParameterValue(JRParameter jrParameter) {
		Parameter ic = new Parameter();

		ic.setEnName(jrParameter.getName());
		ic.setClassName(jrParameter.getValueClassName());
		if (jrParameter.getDefaultValueExpression() == null){
			ic.setDefaultValue("");
		}else{
			ic.setDefaultValue(jrParameter.getDefaultValueExpression().getText());
		}
		ic.setDescription(jrParameter.getDescription());
		ic.setType(Conversion(jrParameter.getValueClassName()));

		return ic;
	}

	/**
	 * 根据报表参数名查询数据库中的报表参数集合
	 * 
	 * @param icSet
	 *            数据库中的报表参数集合
	 * @param JRParameter
	 *            报表参数
	 * @return ReportParameter
	 */
	private Parameter findListEntity(Set<Parameter> icSet, JRParameter param) {
		for (Parameter ic : icSet) {
			String rpEnName = ic.getEnName();
			String jrEnName = param.getName();
			if (jrEnName.trim().equals(rpEnName.trim())) {
				return ic;
			}
		}
		return null;
	}

	/**
	 * 把类型名转换成枚举
	 * 
	 * @param className
	 *            类型名
	 * @return InputControlEnum 枚举
	 */
	private Parameter.Type Conversion(String className) {
		if (className.toLowerCase().indexOf("boolean") > -1) {
			return Parameter.Type.BOOLEAN;
		}
		return Parameter.Type.TEXT;
	}

}
