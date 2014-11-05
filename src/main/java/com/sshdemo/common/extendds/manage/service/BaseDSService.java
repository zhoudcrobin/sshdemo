/**
 * Copyright (c)2010-2011 Enterprise Website Content Management System(EWCMS), All rights reserved.
 * EWCMS PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * http://www.ewcms.com
 */
package com.sshdemo.common.extendds.manage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sshdemo.common.extendds.manage.dao.BaseDSDAO;
import com.sshdemo.common.extendds.model.BaseDS;


@Service
public class BaseDSService implements BaseDSServiceable {
	
	@Autowired
	private BaseDSDAO baseDSDAO;

	public void setBaseDSDAO(BaseDSDAO baseDSDAO){
		this.baseDSDAO = baseDSDAO;
	}
	
	@Override
	public Long saveOrUpdateBaseDS(BaseDS baseDS) {
		if(baseDS.getId()==null){
			baseDSDAO.persist(baseDS);
		}else{
			baseDSDAO.merge(baseDS);
		}
		return baseDS.getId();
	}

	@Override
	public BaseDS findByBaseDS(Long baseDSId) {
		return baseDSDAO.get(baseDSId);
	}

	@Override
	public List<BaseDS> findAllBaseDS() {
		return (List<BaseDS>)baseDSDAO.findAll();
	}

	@Override
	public void deletedBaseDS(Long baseDSId) {
		baseDSDAO.removeByPK(baseDSId);
	}
}
