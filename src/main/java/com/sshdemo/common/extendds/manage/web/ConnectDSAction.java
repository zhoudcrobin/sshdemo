/**
 * Copyright (c)2010-2011 Enterprise Website Content Management System(EWCMS), All rights reserved.
 * EWCMS PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * http://www.ewcms.com
 */
package com.sshdemo.common.extendds.manage.web;

import java.sql.Connection;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
import com.sshdemo.common.extendds.generate.factory.DataSourceFactoryable;
import com.sshdemo.common.extendds.generate.factory.init.EwcmsDataSourceFactoryable;
import com.sshdemo.common.extendds.generate.service.EwcmsDataSourceServiceable;
import com.sshdemo.common.extendds.manage.BaseDSFacable;
import com.sshdemo.common.extendds.model.BaseDS;
import com.sshdemo.common.web.util.Struts2Util;

/**
 * 
 * @author wuzhijun
 *
 */
public class ConnectDSAction extends ActionSupport{

	private static final long serialVersionUID = -8419791411417225073L;
	
	@Autowired
	private BaseDSFacable baseDSFac;
	@Autowired
	private EwcmsDataSourceFactoryable ewcmsDataSourceFactory;
	
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String test() {
		EwcmsDataSourceServiceable service = null;
		Connection con = null;
		String testResults = "测试数据库连接失败,请确认填写的内容正确!";
		try{
			BaseDS alqcDataSource = baseDSFac.findByBaseDS(id);

			DataSourceFactoryable factory = (DataSourceFactoryable) ewcmsDataSourceFactory.getBean(alqcDataSource.getClass());
			service = factory.createService(alqcDataSource);
			con = service.openConnection();
			
			if (!con.isClosed())
				testResults = "测试数据库连接正确,您可以在以后的程序中使用!";
		}catch(Exception e){
		}finally{
			try{
				if (con != null){
					con.close();
					con = null;
				}
			}catch(Exception e){
			}
			try{
				if (service != null){
					service.closeConnection();
					service = null;
				}
			}catch(Exception e){
			}
		}
		Struts2Util.renderText(testResults);
		return NONE;
	}
}
