/**
 * Copyright (c)2010-2011 Enterprise Website Content Management System(EWCMS), All rights reserved.
 * EWCMS PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * http://www.ewcms.com
 */

package com.sshdemo.common.query.jpa;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.TemporalType;

import com.sshdemo.common.query.Result;
import com.sshdemo.common.query.cache.CacheResultable;
import com.sshdemo.common.query.cache.ResultCacheable;

public class HqlQuery extends AbstractQuery implements HqlQueryable {
    
    private ResultCacheable cache;
    
    private HqlQueryTemplate listTemplate;
    private HqlQueryTemplate countTemplate;
    private HqlQueryTemplate extTemplate;
    
    private int row = DEFAULT_ROW ;
    private int page = DEFAULT_PAGE ;
    
    public HqlQuery(final EntityManager em, final String hql){
    	this(em, hql, null, null);
    }
    
    public HqlQuery(final EntityManager em,final String hql,final String countHql) {
        this(em, hql, countHql, null);
    }

    public HqlQuery(final EntityManager em,final String hql,final String countHql,final String extHql) {
        listTemplate = new HqlQueryTemplate(em,hql);
        if (countHql != null){
        	countTemplate = new HqlQueryTemplate(em,countHql);
        }
        if(extHql != null){
            extTemplate = new HqlQueryTemplate(em,extHql);
        }
    }
    
    @Override
    public Result queryResult(){
        return getResult(countTemplate,listTemplate,extTemplate,row,page);
    }

    @Override
    public CacheResultable queryCacheResult(String cacheKey) {
        return getCacheResult(cache,cacheKey,
                countTemplate,listTemplate,extTemplate,
                row,page);
    }
    
    @Override
    public HqlQueryable setParameter(String name, Object value) {
        listTemplate.setParameter(name, value);
        countTemplate.setParameter(name, value);
        if(extTemplate != null){
            extTemplate.setParameter(name, value);
        }
        return this;
    }

    @Override
    public HqlQueryable setParameter(int position, Object value) {
        listTemplate.setParameter(position, value);
        countTemplate.setParameter(position, value);
        if(extTemplate != null){
            extTemplate.setParameter(position, value);
        }
        return this;
    }

    @Override
    public HqlQueryable setParameter(String name, Date date, TemporalType type) {
        listTemplate.setParameter(name, date,type);
        countTemplate.setParameter(name, date,type);
        if(extTemplate != null){
            extTemplate.setParameter(name, date,type);
        }
        return this;
    }

    @Override
    public HqlQueryable setParameter(int position, Date date, TemporalType type) {
        listTemplate.setParameter(position, date,type);
        countTemplate.setParameter(position, date,type);
        if(extTemplate != null){
            extTemplate.setParameter(position, date,type);
        }
        return this;
    }

    @Override
    public HqlQueryable setParameter(String name, Calendar calendar,TemporalType type) {
        listTemplate.setParameter(name, calendar,type);
        countTemplate.setParameter(name, calendar,type);
        if(extTemplate != null){
            extTemplate.setParameter(name, calendar,type);
        }
        return this;
    }

    @Override
    public HqlQueryable setParameter(int position, Calendar calendar,TemporalType type) {
        listTemplate.setParameter(position, calendar,type);
        countTemplate.setParameter(position, calendar,type);
        if(extTemplate != null){
            extTemplate.setParameter(position, calendar,type);
        }
        return this;
    }
    
    @Override
    public HqlQueryable setCache(ResultCacheable cache){
        this.cache = cache;
        return this;
    }

    @Override
    public HqlQueryable setRow(int row) {
        this.row = row;
        return this;
    }

    @Override
    public HqlQueryable setPage(int page) {
        this.page = page;
        return this;
    }
}
