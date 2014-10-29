/**
 * Copyright (c)2010-2011 Enterprise Website Content Management System(EWCMS), All rights reserved.
 * EWCMS PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * http://www.ewcms.com
 */

package com.sshdemo.common.web.taglib.date.view;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.views.TagLibrary;

import com.opensymphony.xwork2.util.ValueStack;
import com.sshdemo.common.web.taglib.date.view.freemark.BodyModel;

/**
 *功能说明

 * @author 周冬初
 *
 */
public class BodyTagLibrary implements TagLibrary {
    public Object getFreemarkerModels(ValueStack stack, HttpServletRequest req, HttpServletResponse res) {
        return new BodyModel(stack,req,res);
    }

	@SuppressWarnings("rawtypes")
	public List<Class> getVelocityDirectiveClasses() {
        return new ArrayList<Class>();
    }
}
