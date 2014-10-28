/**
 * Copyright (c)2010-2011 Enterprise Website Content Management System(EWCMS), All rights reserved.
 * EWCMS PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * http://www.ewcms.com
 */

package com.sshdemo.common.schedule.manage.web;

import static com.sshdemo.common.lang.EmptyUtil.isNotNull;
import static com.sshdemo.common.lang.EmptyUtil.isStringNotEmpty;

import com.sshdemo.common.query.Resultable;
import com.sshdemo.common.query.jpa.EntityQueryable;
import com.sshdemo.common.query.jpa.QueryFactory;
import com.sshdemo.common.schedule.model.JobClass;
import com.sshdemo.common.web.QueryBaseAction;
/**
 *
 * @author 吴智俊
 */
public class JobClassQueryAction extends QueryBaseAction {

	private static final long serialVersionUID = -8882837349113907705L;
	
    @Override
    protected Resultable queryResult(QueryFactory queryFactory, String cacheKey, int rows, int page, Order order) {
    	EntityQueryable query =  queryFactory.createEntityQuery(JobClass.class).setPage(page).setRow(rows).orderAsc("id");
		
        Integer id = getParameterValue(Integer.class,"id", "查询编号错误，应该是整型");
        if (isNotNull(id)) query.eq("id", id);
        
        String className = getParameterValue(String.class, "className");
        if (isStringNotEmpty(className)) query.likeAnywhere("className", className);
        
        String classEntity = getParameterValue(String.class, "classEntity");
        if (isStringNotEmpty(classEntity)) query.likeAnywhere("classEntity", classEntity);
        
        entityOrder(query, order);
        return query.queryCacheResult(cacheKey);
    }

    @Override
    protected Resultable querySelectionsResult(QueryFactory queryFactory, int rows, int page, String[] selections, Order order) {
    	EntityQueryable query =  queryFactory.createEntityQuery(JobClass.class).setPage(page).setRow(rows).orderAsc("id");
        
        query.in("id", getIds(Integer.class));
        
        return query.queryResult();    
     }
}
