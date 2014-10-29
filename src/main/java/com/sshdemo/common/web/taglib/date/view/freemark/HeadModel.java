/**
 * Copyright (c)2010-2011 Enterprise Website Content Management System(EWCMS), All rights reserved.
 * EWCMS PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * http://www.ewcms.com
 */

package com.sshdemo.common.web.taglib.date.view.freemark;

import com.opensymphony.xwork2.util.ValueStack;
import com.sshdemo.common.web.taglib.date.component.Head;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.components.Component;
import org.apache.struts2.views.freemarker.tags.TagModel;

/**
 *
 * @author 周冬初
 */
public class HeadModel extends TagModel {

    public HeadModel(ValueStack stack, HttpServletRequest req, HttpServletResponse res) {
        super(stack, req, res);
    }

    @Override
    protected Component getBean() {
        return new Head(this.stack, this.req, this.res);
    }
}
