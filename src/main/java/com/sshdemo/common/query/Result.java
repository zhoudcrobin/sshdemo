/**
 * Copyright (c)2010-2011 Enterprise Website Content Management System(EWCMS), All rights reserved.
 * EWCMS PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * http://www.ewcms.com
 */

package com.sshdemo.common.query;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.Assert;

public class Result implements Resultable{
    
    private int row ;
    private int count ;
    private List<Object> resultList = new ArrayList<Object>();
    private List<Object> extList;
    
    public Result(int row,int count,List<Object> resultList,List<Object> extList){
        Assert.isTrue(count >= 0 ,"count < 0");
        Assert.isTrue(row > 0 , "row <= 0");
        this.count = count;
        this.row = row;
        this.resultList = resultList;
        this.extList = extList;
    }
            
    @Override
    public int getCount() {
        return count ;
    }
    
    public Result setCount(int count){
        Assert.isTrue(count >= 0,"count is not < 0");
        this.count = count;
        return this;
    }

    @Override
    public int getPageCount() {
        return (getCount() + row - 1) / row;
    }
    
    @Override
    public List<Object> getResultList() {
        return resultList;
    }
  
    @Override
    public List<Object> getExtList() {
        return extList;
    }
    
}
