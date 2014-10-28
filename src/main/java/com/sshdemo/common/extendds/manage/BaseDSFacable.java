/**
 * Copyright (c)2010-2011 Enterprise Website Content Management System(EWCMS), All rights reserved.
 * EWCMS PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * http://www.ewcms.com
 */
package com.sshdemo.common.extendds.manage;

import java.util.List;

import com.sshdemo.common.extendds.model.BaseDS;

public interface BaseDSFacable {
	/**
	 * 新增或修改数据源
	 * 
	 * @param baseDS ReportDataSource对象
	 * @return Long 数据源对象编号
	 */
	public Long saveOrUpdateBaseDS(BaseDS baseDS);

	/**
	 * 查询数据源对象
	 * 
	 * @param baseDSId 数据源编号
	 * @return ReportDataSource对象
	 */
	public BaseDS findByBaseDS(Long baseDSId);

	/**
	 * 查询所有数据源对象
	 * 
	 * @return List对象
	 */
	public List<BaseDS> findAllBaseDS();

	/**
	 * 删除数据源对象
	 * 
	 * @param baseDSId 数据源编号
	 */
	public void deletedBaseDS(Long baseDSId);
}
