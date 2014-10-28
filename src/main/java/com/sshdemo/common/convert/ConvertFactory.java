/**
 * Copyright (c)2010-2011 Enterprise Website Content Management System(EWCMS), All rights reserved.
 * EWCMS PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * http://www.ewcms.com
 */

package com.sshdemo.common.convert;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

/**
 * 转换数据对象的工厂封�?
 * 
 * @author WangWei
 */
public enum ConvertFactory {

    instance;
    
    private Map<Class<?>, Convertable<?>> converts;

    private ConvertFactory() {
        converts = initConverts();
    }

    /**
     * 根据Class类型 返回转换类型操作
     * 
     * @param clazz 数据类型�?
     * @return ParseTypeHandler
     */
    @SuppressWarnings("unchecked")
    public <I> Convertable<I> convertHandler(Class<? super I> clazz) {
        
        Convertable<?> convert = converts.get(clazz);
        
        if(convert == null){
            throw new IllegalStateException(clazz.getName()+" type cant not convert");
        }
        
        return (Convertable<I>)convert;
    }

    private Map<Class<?>, Convertable<?>> initConverts() {
        Map<Class<?>, Convertable<?>> map = new HashMap<Class<?>, Convertable<?>>();

        map.put(BigDecimal.class, new BigDecimalConvert());
        map.put(BigInteger.class, new BigIntegerConvert());
        map.put(Boolean.class, new BooleanConvert());
        map.put(Byte.class, new ByteConvert());
        map.put(java.util.Date.class, new DateConvert());
        map.put(Double.class, new DoubleConvert());
        map.put(Float.class, new FloatConvert());
        map.put(Integer.class, new IntegerConvert());
        map.put(Long.class, new LongConvert());
        map.put(Short.class, new ShortConvert());
        map.put(java.sql.Date.class, new SqlDateConvert());
        map.put(java.sql.Timestamp.class, new SqlTimestampConvert());
        map.put(java.sql.Time.class, new SqlTimeConvert());
        map.put(String.class, new StringConvert());

        return map;
    }
}
