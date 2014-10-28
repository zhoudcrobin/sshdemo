/**
 * Copyright (c)2010-2011 Enterprise Website Content Management System(EWCMS), All rights reserved.
 * EWCMS PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * http://www.ewcms.com
 */

package com.sshdemo.common.query.jpa;

import java.util.Collection;

import com.sshdemo.common.query.Queryable;

/**
 * 实体查询接口，简化CriteriaQuery查询�? * 
 * @author wangwei
 */
public interface EntityQueryable extends Predicatesable,Queryable{
    
    @Override
    EntityQueryable setRow(int row);
    
    @Override
    EntityQueryable setPage(int page);
    
    /**
     * 添加"="条件
     *
     * @param name 属�?�?     * @param value �?     * @return this
     */
    @Override
    EntityQueryable eq(String name, Object value);

    /**
     * 添加"!="条件
     *
     * @param name 属�?�?     * @param value �?     * @return this
     */
    @Override
    EntityQueryable notEq(String name, Object value);

    /**
     * 添加">"条件
     *
     * @param name 属�?�?     * @param value �?     * @return this
     */
    @Override
    EntityQueryable gt(String name, Number value);

    /**
     * 添加">="条件
     *
     * @param name 属�?�?     * @param value �?     * @return this
     */
    @Override
    EntityQueryable ge(String name, Number value);

    /**
     * 添加"<"条件
     *
     * @param name 属�?�?     * @param value �?     * @return this
     */
    @Override
    EntityQueryable lt(String name, Number value);

    /**
     * 添加"<="条件
     *
     * @param name 属�?�?     * @param value �?     * @return this
     */
    @Override
    EntityQueryable le(String name, Number value);

    /**
     * 添加"Like"条件,Like '%xx'
     *
     * @param name 属�?�?     * @param value �?     * @return this
     */
    @Override
    EntityQueryable likeStart(String name, String value);

    /**
     * 添加"Like"条件,Like '%xx%'
     *
     * @param name 属�?�?     * @param value �?     * @return this
     */
    @Override
    EntityQueryable likeAnywhere(String name, String value);

    /**
     * 添加"Like"条件,Like 'xx%'
     *
     * @param name 属�?�?     * @param value �?     * @return this
     */
    @Override
    EntityQueryable likeEnd(String name, String value);

    /**
     * 添加"between"条件
     *
     * @param name 属�?�?     * @param lo 下�?
     * @param hi 上�?
     * @return this
     */
    @Override
    <Y extends Comparable<? super Y>> EntityQueryable between(String name, Y lo, Y hi);

    /**
     * 添加"in"条件
     *
     * @param name 属�?�?     * @param value �?     * @return this
     */
    @Override
    EntityQueryable in(String name, Collection<?> value);

    /**
     * 添加"in"条件
     *
     * @param name 属�?�?     * @param value �?     * @return this
     */
    @Override
    EntityQueryable in(String name, Object[] value);

    /**
     * 添加"null"条件
     *
     * @param name 属�?�?     * @return this
     */
    EntityQueryable isNull(String name);

    /**
     * 添加"notNull"条件
     *
     * @param name 属�?�?     * @return this
     */
    @Override
    EntityQueryable isNotNull(String name);

    /**
     * 查询条件and关系
     * 
     * @return this
     */
    @Override
    EntityQueryable and();
    
    /**
     * 输入的条件为and关系
     * 
     * @param x this
     * @return this
     */
    @Override
    EntityQueryable and(Predicatesable x);

    /**
     * 查询条件or关系
     * 
     * @return this
     */
    EntityQueryable or();

    /**
     * 输入的条件为or关系
     * 
     * @param x this
     * @return this
     */
    @Override
    EntityQueryable or(Predicatesable x);

    /**
     * 输入的条件为not关系
     * 
     * @param x this
     * @return this
     */
    @Override
    EntityQueryable not(Predicatesable x);

    /**
     * 递增排序
     *
     * @param name
     * @return this
     */
    EntityQueryable orderAsc(String name);

    /**
     * 递减排序
     *
     * @param name
     * @return this
     */
    EntityQueryable orderDesc(String name);
    
}
