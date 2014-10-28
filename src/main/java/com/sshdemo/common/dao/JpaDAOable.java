/**
 * Copyright (c)2010-2011 Enterprise Website Content Management System(EWCMS), All rights reserved.
 * EWCMS PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * http://www.ewcms.com
 */

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sshdemo.common.dao;

import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author wangwei
 */
public interface JpaDAOable<K, E> {

    public void persist(E entity);

    public void remove(E entity);

    public void removeByPK(K pk);

    public E merge(E entity);

    public void refresh(E entity);

    public E get(K pk);

    public E getRefresh(K pk);

    public E flush(E entity);

    public List<E> findAll();

    public Integer removeAll();

    public EntityManager getEntityManager();
}
