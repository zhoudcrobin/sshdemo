/**
 * Copyright (c)2010-2011 Enterprise Website Content Management System(EWCMS), All rights reserved.
 * EWCMS PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * http://www.ewcms.com
 */

package com.sshdemo.common.security.manage.web.user;

import static com.sshdemo.common.lang.EmptyUtil.isStringNotEmpty;

import java.util.List;

import org.springframework.stereotype.Controller;

import com.sshdemo.common.query.Resultable;
import com.sshdemo.common.query.jpa.EntityQueryable;
import com.sshdemo.common.query.jpa.QueryFactory;
import com.sshdemo.common.security.manage.model.User;
import com.sshdemo.common.web.QueryBaseAction;

/**
 * 用户列表查询
 * 
 * @author wangwei
 */
@Controller("security.user.query.action")
public class QueryAction extends QueryBaseAction {

	private static final long serialVersionUID = -8736899399025749992L;

	@Override
    protected Resultable queryResult(QueryFactory queryFactory,
            String cacheKey, int rows, int page, Order order) {

        EntityQueryable query = 
            queryFactory.createEntityQuery(User.class)
            .setPage(page)
            .setRow(rows);

        String username = getParameterValue(String.class, "username");
        if (isStringNotEmpty(username)) query.likeAnywhere("username", username);
        String name = getParameterValue(String.class,"name");
        if (isStringNotEmpty(name)) query.likeAnywhere("userInfo.name", name);
        
        if(order.hasOrder()){
            entityOrder(query, order);
        }else{
            query.orderAsc("username");
        }

        return query.queryCacheResult(cacheKey);
    }

    @Override
    protected Resultable querySelectionsResult(QueryFactory queryFactory,
            int rows, int page, String[] selections, Order order) {
        EntityQueryable query = 
            queryFactory.createEntityQuery(User.class)
            .setPage(page)
            .setRow(rows);
        
        List<String> usernames = getIds(String.class);
        query.in("username", usernames);
        
        return query.queryResult();
    }
}