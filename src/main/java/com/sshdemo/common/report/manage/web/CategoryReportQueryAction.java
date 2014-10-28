/**
 * Copyright (c)2010-2011 Enterprise Website Content Management System(EWCMS), All rights reserved.
 * EWCMS PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * http://www.ewcms.com
 */
package com.sshdemo.common.report.manage.web;

import static com.sshdemo.common.lang.EmptyUtil.isNotNull;
import static com.sshdemo.common.lang.EmptyUtil.isStringNotEmpty;

import com.sshdemo.common.query.Resultable;
import com.sshdemo.common.query.jpa.EntityQueryable;
import com.sshdemo.common.query.jpa.QueryFactory;
import com.sshdemo.common.report.model.CategoryReport;
import com.sshdemo.common.web.QueryBaseAction;
/**
 * 
 * @author wuzhijun
 *
 */
public class CategoryReportQueryAction extends QueryBaseAction{

	private static final long serialVersionUID = -1268766311745288459L;

	@Override
	protected Resultable queryResult(QueryFactory queryFactory,
			String cacheKey, int rows, int page, Order order) {
		EntityQueryable query = queryFactory.createEntityQuery(CategoryReport.class).setPage(page).setRow(rows).orderDesc("id");
    	
		Long id = getParameterValue(Long.class,"id", "查询编号错误，应该是整型");
    	if (isNotNull(id)) query.eq("id", id);
    	
        String name = getParameterValue(String.class, "name", "");
        if (isStringNotEmpty(name)) query.likeAnywhere("name", name);
        
    	entityOrder(query, order);
    	return query.queryCacheResult(cacheKey);
	}

	@Override
	protected Resultable querySelectionsResult(QueryFactory queryFactory, int rows, int page, String[] selections, Order order) {
    	EntityQueryable query =  queryFactory.createEntityQuery(CategoryReport.class).setPage(page).setRow(rows).orderAsc("id");
        
        query.in("id", getIds(Long.class));
        
        return query.queryResult();    
	}

}
