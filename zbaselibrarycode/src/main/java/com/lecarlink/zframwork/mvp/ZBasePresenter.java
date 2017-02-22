package com.lecarlink.zframwork.mvp;

import com.lecarlink.zframwork.services.CancelableTask;
import com.lecarlink.zframwork.thread.Runtask;
import com.lecarlink.zframwork.thread.ThreadPool;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * MVP的Presenter层，作为沟通View和Model的桥梁，它从Model层检索数据后，返回给View层，它也可以决定与View层的交互操作。
 * 它包含一个View层的引用和一个Model层的引用
 */
public class ZBasePresenter<V extends ZActivityViewer, I extends ZInteractor> implements ZBaseTaskManager {

    private static final String TAG = ZBasePresenter.class.getSimpleName();

    protected V viewer;
    protected I interactor;

    protected ZBasePresenter() {
        cancelableTaskList = new ArrayList<>();
    }

    private List<CancelableTask> cancelableTaskList;

    @Override
    public void addCancelableTask(CancelableTask cancelableTask) {
        cancelableTaskList.add(cancelableTask);
    }

    @Override
    public void removeCancelableTask(CancelableTask cancelableTask) {
        Logger.i(TAG, "removeCancelableTask: " + cancelableTask);
        cancelableTaskList.remove(cancelableTask);
    }

    @Override
    public void closeAllTask() {
        for (CancelableTask task : cancelableTaskList) {
            Logger.i(TAG, "closeAllTask: " + task);
            task.cancel(true);
        }
        cancelableTaskList.clear();
    }

    public <U, R> void goRuntask(Runtask<U, R> runtask) {
        addCancelableTask(runtask);
        ThreadPool.go(runtask);
    }

//    public void addHttpRequest(HippoRequest request) {
//        addCancelableTask(request);
//        HttpApplicationController.getInstance().addToRequestQueue(request);
//    }

    public V getViewer() {
        return viewer;
    }

    public void setViewer(V viewer) {
        this.viewer = viewer;
    }

    public I getInteractor() {
        return interactor;
    }

    public void setInteractor(I interactor) {
        this.interactor = interactor;
    }

}
