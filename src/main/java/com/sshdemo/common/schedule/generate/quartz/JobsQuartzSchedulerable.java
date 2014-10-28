/**
 * Copyright (c)2010-2011 Enterprise Website Content Management System(EWCMS), All rights reserved.
 * EWCMS PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * http://www.ewcms.com
 */

package com.sshdemo.common.schedule.generate.quartz;

import java.util.List;

import com.sshdemo.common.schedule.BaseException;
import com.sshdemo.common.schedule.generate.common.ValidationErrorsable;
import com.sshdemo.common.schedule.model.JobInfo;

/**
 * 
 * @author 吴智俊
 */
public interface JobsQuartzSchedulerable {

    /**
     * 新建调度任务
     *
     * @param job 调度器任务
     * @throws BaseException
     */
    public void scheduleJob(JobInfo job) throws BaseException;

    /**
     * 修改调度任务
     *
     * @param job 调度器任务
     * @throws BaseException
     */
    public void rescheduleJob(JobInfo job) throws BaseException;

    /**
     * 删除调度任务
     *
     * @param jobId 调度任务编号
     * @throws BaseException
     */
    public void removeScheduledJob(Long jobId) throws BaseException;
    
    /**
     * 暂停调度任务
     * 
     * @param jobId 调度任务编号
     * @throws BaseException
     */
    public void pauseJob(Long jobId) throws BaseException;
    
    /**
     * 从暂停恢复调度任务
     * @param jobId 调度任务编号
     * @throws BaseExcepiton
     */
    public void resumedJob(Long jobId) throws BaseException;

    /**
     * 新增调度器监听接口
     *
     * @param listener 监听接口
     * @throws BaseException
     */
    public void addSchedulerListener(SchedulerListenerable listener) throws BaseException;

    /**
     * 删除调度器监听接口
     *
     * @param listener 监听接口
     * @throws BaseException
     */
    public void removeSchedulerListener(SchedulerListenerable listener) throws BaseException;

    /**
     * 查询调度器任务状态
     *
     * @param jobInfos 调度器任务集合
     * @return List
     * @throws BaseException
     */
    public List<JobInfo> getJobsRuntimeInformation(List<JobInfo> jobInfos) throws BaseException;

    /**
     * 校验调度器任务
     *
     * @param job 调度器任务
     * @param errors 错误信息
     * @throws BaseException
     */
    public void validate(JobInfo job, ValidationErrorsable errors) throws BaseException;
}
