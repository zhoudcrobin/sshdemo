/**
 * Copyright (c)2010-2011 Enterprise Website Content Management System(EWCMS), All rights reserved.
 * EWCMS PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * http://www.ewcms.com
 */

package com.sshdemo.common.convert;

/**
 *  转换成布尔型boolean
 *  
 *  if(value = "true") //忽略大小�?
 *       return true;
 *  else
 *       return false;
 *       
 *  @author WangWei
 */
class BooleanConvert implements Convertable<Boolean> {

    @Override
    public Boolean parse(String value)throws ConvertException {
    	if (value == null || value.equals("")){
    		return false;
    	}
        return Boolean.valueOf(value);
    }

    @Override
    public String parseString(Boolean value) {
        return value ? "true":"false";
    }

}
