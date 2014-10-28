/**
 * Copyright (c)2010-2011 Enterprise Website Content Management System(EWCMS), All rights reserved.
 * EWCMS PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * http://www.ewcms.com
 */
package com.sshdemo.common.report.manage.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.sshdemo.common.report.manage.ReportFacable;
import com.sshdemo.common.report.model.CategoryReport;
import com.sshdemo.common.web.CrudBaseAction;
/**
 * 
 * @author wuzhijun
 * 
 */
public class CategoryReportAction extends CrudBaseAction<CategoryReport, Long> {

	private static final long serialVersionUID = -877919389056277148L;
	private Long categoryId;
	@Autowired
	private ReportFacable reportFac;

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public List<Long> getSelections() {
		return super.getOperatorPK();
	}

	public void setSelections(List<Long> selections) {
		super.setOperatorPK(selections);
	}

	public CategoryReport getCategoryReportVo() {
		return super.getVo();
	}

	public void setCategoryReportVo(CategoryReport categoryReport) {
		super.setVo(categoryReport);
	}

	@Override
	protected Long getPK(CategoryReport vo) {
		return vo.getId();
	}

	@Override
	protected CategoryReport getOperator(Long pk) {
		return reportFac.findCategoryReportById(pk);
	}

	@Override
	protected void deleteOperator(Long pk) {
		reportFac.delCategoryReport(pk);
	}

	@Override
	protected Long saveOperator(CategoryReport vo, boolean isUpdate) {
		return reportFac.addOrUpdCategoryReport(vo);
	}

	@Override
	protected CategoryReport createEmptyVo() {
		return new CategoryReport();
	}
	
	public String categoryReport(){
		setCategoryReportVo(reportFac.findCategoryReportById(categoryId));
		return SUCCESS;
	}
}
