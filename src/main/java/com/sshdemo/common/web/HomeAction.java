/**
 * Copyright (c)2010-2011 Enterprise Website Content Management System(EWCMS), All rights reserved.
 * EWCMS PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * http://www.ewcms.com
 */

package com.sshdemo.common.web;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.sshdemo.common.report.manage.ReportFacable;
import com.sshdemo.common.report.model.CategoryReport;
import com.sshdemo.common.security.manage.SecurityFacable;

/**
 * 首页Action
 *
 * @author 周冬初
 */
@Controller("home")
public class HomeAction extends BaseAction {
    
	private static final long serialVersionUID = -1585813168152878468L;

	private Integer siteId;
    private String siteName;
    private String realName;
    private String userName;
    private boolean hasSite = true;
    private Integer yearCreate = Calendar.getInstance().get(Calendar.YEAR);

	@Autowired
	private SecurityFacable securityFac;
	
	@Autowired
	private ReportFacable reportFac;
	public String execute() {
		realName = securityFac.getCurrentUserInfo().getName();
		userName = securityFac.getCurrentUserInfo().getUsername();
		
		return SUCCESS;
	}
	
	public Integer getSiteId() {
		return siteId;
	}

	public void setSiteId(Integer siteId) {
		this.siteId = siteId;
	}
	
    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }
    
    public String getRealName(){
        return realName;
    }
    
    public Boolean getHasSite(){
        return hasSite;
    }
    
    public String getUserName(){
    	return userName;
    }
    
    public Integer getYearCreate() {
		return yearCreate;
	}

	public void setYearCreate(Integer yearCreate) {
		this.yearCreate = yearCreate;
	}

	public List<Integer> getYears(){
		List<Integer> years = new ArrayList<Integer>();
		for (int i = 2004; i <= yearCreate; i++){
			years.add(i);
		}
		return years;
	}
	
	public List<CategoryReport> getCategoryReportList(){
		return reportFac.findAllCategoryReport();
	}
}
