package com.lecarlink.zframwork.mvp;

import com.lecarlink.zframwork.services.CancelableTask;

/**
 * 业务处理基类
 */
public interface ZBaseTaskManager {

    /**
     * 添加可cancel任务
     *
     * @param cancelableTask
     */
    void addCancelableTask(CancelableTask cancelableTask);

    /**
     * 移除可cancel任务（task完成后调用此方法）
     *
     * @param cancelableTask
     */
    void removeCancelableTask(CancelableTask cancelableTask);

    /**
     * 关闭所有任务
     */
    void closeAllTask();
}
