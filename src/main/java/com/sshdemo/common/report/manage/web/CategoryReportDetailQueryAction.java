/**
 * Copyright (c)2010-2011 Enterprise Website Content Management System(EWCMS), All rights reserved.
 * EWCMS PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * http://www.ewcms.com
 */
package com.sshdemo.common.report.manage.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import com.sshdemo.common.query.Resultable;
import com.sshdemo.common.query.jpa.QueryFactory;
import com.sshdemo.common.report.manage.ReportFacable;
import com.sshdemo.common.report.model.CategoryReport;
import com.sshdemo.common.report.model.ChartReport;
import com.sshdemo.common.report.model.TextReport;
import com.sshdemo.common.web.QueryBaseAction;
import com.sshdemo.common.web.util.JSONUtil;
import com.sshdemo.common.web.util.Struts2Util;
import com.sshdemo.common.web.vo.DataGrid;
import com.sshdemo.common.web.vo.PropertyGrid;

/**
 * 
 * @author wuzhijun
 * 
 */
public class CategoryReportDetailQueryAction extends QueryBaseAction {

	private static final long serialVersionUID = 1324307014782116124L;

	private static final String TEXT_GROUP_TITLE = "文字报表";
	private static final String CHART_GROUP_TITLE = "图型报表";

	@Autowired
	private ReportFacable reportFac;

	private Long categoryId;

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	@Override
	protected Resultable queryResult(QueryFactory queryFactory,
			String cacheKey, int rows, int page, Order order) {
		return null;
	}

	@Override
	protected Resultable querySelectionsResult(QueryFactory queryFactory,
			int rows, int page, String[] selections, Order order) {
		return null;
	}

	public String query() {
		List<PropertyGrid> list = new ArrayList<PropertyGrid>();
		CategoryReport categoryReport = reportFac.findCategoryReportById(getCategoryId());
		Set<TextReport> texts = categoryReport.getTexts();
		for (TextReport text : texts) {
			PropertyGrid grid = new PropertyGrid();
			grid.setName(text.getName());
			grid.setValue(text.getRemarks());
			grid.setGroup(TEXT_GROUP_TITLE);
			list.add(grid);
		}

		Set<ChartReport> charts = categoryReport.getCharts();
		for (ChartReport chart : charts) {
			PropertyGrid grid = new PropertyGrid();
			grid.setName(chart.getName());
			grid.setValue(chart.getRemarks());
			grid.setGroup(CHART_GROUP_TITLE);
			list.add(grid);
		}
		DataGrid data = new DataGrid(list.size(), list);
		Struts2Util.renderJson(JSONUtil.toJSON(data));
		return NONE;
	}
}
