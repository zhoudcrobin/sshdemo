/**
 * Copyright (c)2010-2011 Enterprise Website Content Management System(EWCMS), All rights reserved.
 * EWCMS PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * http://www.ewcms.com
 */

package com.sshdemo.common.query.jpa;

import java.util.Collection;

public interface Predicatesable {
    
    /**
     * 添加"="条件
     *
     * @param name 属�?�?     * @param value �?     * @return this
     */
    Predicatesable eq(String name, Object value);

    /**
     * 添加"!="条件
     *
     * @param name 属�?�?     * @param value �?     * @return this
     */
    Predicatesable notEq(String name, Object value);

    /**
     * 添加">"条件
     *
     * @param name 属�?�?     * @param value �?     * @return this
     */
    Predicatesable gt(String name, Number value);

    /**
     * 添加">="条件
     *
     * @param name 属�?�?     * @param value �?     * @return this
     */
    Predicatesable ge(String name, Number value);

    /**
     * 添加"<"条件
     *
     * @param name 属�?�?     * @param value �?     * @return this
     */
    Predicatesable lt(String name, Number value);

    /**
     * 添加"<="条件
     *
     * @param name 属�?�?     * @param value �?     * @return this
     */
    Predicatesable le(String name, Number value);

    /**
     * 添加"Like"条件,Like '%xx'
     *
     * @param name 属�?�?     * @param value �?     * @return this
     */
    Predicatesable likeStart(String name, String value);

    /**
     * 添加"Like"条件,Like '%xx%'
     *
     * @param name 属�?�?     * @param value �?     * @return this
     */
    Predicatesable likeAnywhere(String name, String value);

    /**
     * 添加"Like"条件,Like 'xx%'
     *
     * @param name 属�?�?     * @param value �?     * @return this
     */
    Predicatesable likeEnd(String name, String value);

    /**
     * 添加"between"条件
     *
     * @param name 属�?�?     * @param lo 下�?
     * @param hi 上�?
     * @return this
     */
    <Y extends Comparable<? super Y>> Predicatesable between(String name, Y lo, Y hi);

    /**
     * 添加"in"条件
     *
     * @param name 属�?�?     * @param value �?     * @return this
     */
    Predicatesable in(String name, Collection<?> value);

    /**
     * 添加"in"条件
     *
     * @param name 属�?�?     * @param value �?     * @return this
     */
    Predicatesable in(String name, Object[] value);

    /**
     * 添加"null"条件
     *
     * @param name 属�?�?     * @return this
     */
    Predicatesable isNull(String name);

    /**
     * 添加"notNull"条件
     *
     * @param name 属�?�?     * @return this
     */
    Predicatesable isNotNull(String name);

    /**
     * 查询条件and关系
     * 
     * @return this
     */
    Predicatesable and();
    
    /**
     * 输入的条件为and关系
     * 
     * @param x this
     * @return this
     */
    Predicatesable and(Predicatesable x);

    /**
     * 查询条件or关系
     * 
     * @return this
     */
    Predicatesable or();

    /**
     * 输入的条件为or关系
     * 
     * @param x this
     * @return this
     */
    Predicatesable or(Predicatesable x);

    /**
     * 输入的条件为not关系
     * 
     * @param x this
     * @return this
     */
    Predicatesable not(Predicatesable x);
}
