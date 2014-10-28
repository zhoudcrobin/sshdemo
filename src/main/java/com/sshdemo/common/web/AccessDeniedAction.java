/**
 * Copyright (c)2010-2011 Enterprise Website Content Management System(EWCMS), All rights reserved.
 * EWCMS PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * http://www.ewcms.com
 */

package com.sshdemo.common.web;

import com.opensymphony.xwork2.ActionSupport;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Controller;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.WebAttributes;

/**
 *
 * @author wangwei
 */
@Controller
public class AccessDeniedAction extends ActionSupport{

	private static final long serialVersionUID = -4986067005613090040L;

	private String errorDetails;
    private String errorTrace;

    public String getErrorDetails(){
        return this.errorDetails;
    }

    public String getErrorTrace(){
        return this.errorTrace;
    }

    @Override
    public String execute(){
        
        HttpServletRequest request = ServletActionContext.getRequest();
        AccessDeniedException exception =(AccessDeniedException)
                request.getAttribute(WebAttributes.ACCESS_DENIED_403);

        this.errorDetails = exception.getMessage();
        this.errorTrace = exception.toString();
        
        return SUCCESS;
    }
}
