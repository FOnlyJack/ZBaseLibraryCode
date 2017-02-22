package com.lecarlink.zframwork.services;

import java.util.Collection;

/**
 * @Description 可撤销任务接口
 */
public interface CancelableTask {

    public void addListener(Collection<CancelableTask> cancelableTaskCollection);

    public boolean cancel(boolean mayInterruptIfRunning);

    public void remove();
}
