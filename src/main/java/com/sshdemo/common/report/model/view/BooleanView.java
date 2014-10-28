/**
 * Copyright (c)2010-2011 Enterprise Website Content Management System(EWCMS), All rights reserved.
 * EWCMS PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * http://www.ewcms.com
 */
package com.sshdemo.common.report.model.view;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * 布尔视图
 * 
 * <ul>
 * </ul>
 * 
 * @author 吴智俊
 */
@Entity
@Table(name = "plugin_report_view_boolean")
@PrimaryKeyJoinColumn(name = "view_id")
public class BooleanView extends ComponentView {

    private static final long serialVersionUID = 3751586581957193886L;
}
