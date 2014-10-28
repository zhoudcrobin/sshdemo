/**
 * Copyright (c)2010-2011 Enterprise Website Content Management System(EWCMS), All rights reserved.
 * EWCMS PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * http://www.ewcms.com
 */

package com.sshdemo.common.convert;

/**
 * 转换成byte数据类型�?
 * <p>
 * MIN_VALUE <= byte  <= MAX_VALUE 
 * <p>
 * MIN_VALUE = -pow(2,7)
 * <p>
 *  MAX_VALUE=pwo(2,7)-1
 *  
 * @author WangWei
 */
class ByteConvert implements Convertable<Byte> {

    @Override
    public Byte parse(String value) throws ConvertException {
        try {
        	if (value == null || value.equals("")){
        		return 0;
        	}
            return Byte.valueOf(value);
        } catch (NumberFormatException e) {
            throw new ConvertException(e);
        }
    }

    @Override
    public String parseString(Byte value) {
        return String.valueOf(value.byteValue());
    }
}
