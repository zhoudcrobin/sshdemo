/**
 * Copyright (c)2010-2011 Enterprise Website Content Management System(EWCMS), All rights reserved.
 * EWCMS PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * http://www.ewcms.com
 */

package com.sshdemo.common.query.jpa;

import javax.persistence.EntityManager;

public interface QueryTemplateCallback<T> {

    public T doInQuery(EntityManager em);
}
