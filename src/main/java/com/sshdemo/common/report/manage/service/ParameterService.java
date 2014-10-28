/**
 * Copyright (c)2010-2011 Enterprise Website Content Management System(EWCMS), All rights reserved.
 * EWCMS PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * http://www.ewcms.com
 */
package com.sshdemo.common.report.manage.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sshdemo.common.report.manage.dao.ParameterDAO;
import com.sshdemo.common.report.model.Parameter;
/**
 * 
 * @author wu_zhijun
 *
 */
@Service
public class ParameterService implements ParameterServiceable {

	@Autowired
	private ParameterDAO parameterDAO;
	
	@Override
	public Parameter findParameterById(Long parameterId) {
		return parameterDAO.get(parameterId);
	}

	@Override
	public Boolean findSessionIsEntityByParameterIdAndUserName(Long parameterId, String userName) {
		return parameterDAO.findSessionIsEntityByParameterIdAndUserName(parameterId, userName);
	}
}
