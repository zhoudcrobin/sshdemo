/**
 * Copyright (c)2010-2011 Enterprise Website Content Management System(EWCMS), All rights reserved.
 * EWCMS PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * http://www.ewcms.com
 */
package com.sshdemo.common.extendds.manage.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.sshdemo.common.extendds.manage.BaseDSFacable;
import com.sshdemo.common.extendds.model.BaseDS;
import com.sshdemo.common.extendds.model.JdbcDS;
import com.sshdemo.common.web.CrudBaseAction;
/**
 * 
 * @author wuzhijun
 * 
 */
public class JdbcDSAction extends CrudBaseAction<JdbcDS, Long> {

	private static final long serialVersionUID = -538592880660461507L;

	@Autowired
	private BaseDSFacable baseDSFac;

	public List<Long> getSelections() {
		return super.getOperatorPK();
	}

	public void setSelections(List<Long> selections) {
		super.setOperatorPK(selections);
	}

	public JdbcDS getJdbcDSVo() {
		return super.getVo();
	}

	public void setJdbcDSVo(JdbcDS jdbcDS) {
		super.setVo(jdbcDS);
	}

	@Override
	protected Long getPK(JdbcDS vo) {
		return vo.getId();
	}

	@Override
	protected JdbcDS getOperator(Long pk) {
		BaseDS baseDS = baseDSFac.findByBaseDS(pk);
		if (baseDS instanceof JdbcDS) {
			return (JdbcDS) baseDS;
		}
		return null;
	}

	@Override
	protected void deleteOperator(Long pk) {
		baseDSFac.deletedBaseDS(pk);
	}

	@Override
	protected Long saveOperator(JdbcDS vo, boolean isUpdate) {
		return baseDSFac.saveOrUpdateBaseDS(vo);
	}

	@Override
	protected JdbcDS createEmptyVo() {
		return new JdbcDS();
	}

}
