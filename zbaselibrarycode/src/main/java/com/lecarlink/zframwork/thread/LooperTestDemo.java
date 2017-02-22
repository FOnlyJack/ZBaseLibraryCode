package com.lecarlink.zframwork.thread;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Log;

import java.lang.ref.WeakReference;

public class LooperTestDemo {
    private WorkerThread mWorkerThread;

    /**
     * Instances of static inner classes do not hold an implicit
     * reference to their outer class.
     */
    public static class MyHandler extends Handler {
        private final WeakReference<LooperTestDemo> mActivity;

        public MyHandler(LooperTestDemo activity) {
            mActivity = new WeakReference<LooperTestDemo>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            String text = (String) msg.obj;
            if (TextUtils.isEmpty(text)) {

                new AsyncTask<Void, Void, Void>() {

                    @Override
                    protected void onPreExecute() {
                    }

                    @Override
                    protected Void doInBackground(Void... args) {
                        return null;
                    }

                    @Override
                    protected void onPostExecute(Void arg) {
                    }
                }.execute();


                return;
            }
        }
    }

    /**
     * Instances of anonymous classes do not hold an implicit
     * reference to their outer class when they are "static".
     */
    private static final Runnable sRunnable = new Runnable() {
        @Override
        public void run() { /* ... */ }
    };

    public MyHandler getHandler() {
        return mMainHandler;
    }

    private final MyHandler mMainHandler = new MyHandler(this);

    public void start() {
        mMainHandler.postDelayed(sRunnable, 1000 * 60 * 10);
        mWorkerThread = new WorkerThread(this);
        mWorkerThread.executeTask("please do me a favor");
        mWorkerThread.exit();
    }

    public void stop() {
        mWorkerThread.exit();
        mWorkerThread = null;
    }

    private static class WorkerThread extends Thread {
        protected static final String TAG = "WorkerThread";
        private Handler mHandler;
        private Looper mLooper;
        private final WeakReference<LooperTestDemo> mActivity;
        ;

        public WorkerThread(LooperTestDemo activity) {
            mActivity = new WeakReference<LooperTestDemo>(activity);
            start();
        }

        public void run() {
            // Attention: if you obtain looper before Looper#prepare(), you can still use the looper
            // to process message even after you call Looper#quit(), which means the looper does not 
            //really quit.
            Looper.prepare();
            // So we should call Looper#myLooper() after Looper#prepare(). Anyway, we should put all stuff between Looper#prepare()
            // and Looper#loop().
            // In this case, you will receive "Handler{4051e4a0} sending message to a Handler on a dead thread
            // 05-09 08:37:52.118: W/MessageQueue(436): java.lang.RuntimeException: Handler{4051e4a0} sending message 
            // to a Handler on a dead thread", when try to send a message to a looper which Looper#quit() had called,
            // because the thread attaching the Looper and Handler dies once Looper#quit() gets called.
            mLooper = Looper.myLooper();
            // either new Handler() and new Handler(mLooper) will work
            mHandler = new Handler(mLooper) {
                @Override
                public void handleMessage(Message msg) {
                    LooperTestDemo activity = mActivity.get();
                    if (activity == null) {
                        return;
                    }
                    /*
                     * Attention: object Message is not reusable, you must obtain a new one for each time you want to use it.
                     * Otherwise you got "android.util.AndroidRuntimeException: { what=1000 when=-15ms obj=it is my please
                     * to serve you, please be patient to wait!........ } This message is already in use."
                     */
                    //      Message newMsg = Message.obtain();
                    StringBuilder sb = new StringBuilder();
                    sb.append("it is my please to serve you, please be patient to wait!\n");
                    Log.e(TAG, "workerthread, it is my please to serve you, please be patient to wait!");
                    for (int i = 1; i < 100; i++) {
                        sb.append(".");
                        Message newMsg = Message.obtain();
                        newMsg.obj = sb.toString();
                        activity.getHandler().sendMessage(newMsg);
                        Log.e(TAG, "workthread, working" + sb.toString());
                        SystemClock.sleep(100);
                    }
                    Log.e(TAG, "workerthread, your work is done.");
                    sb.append("\nyour work is done");
                    Message newMsg = Message.obtain();
                    newMsg.obj = sb.toString();
                    final Object a = new Object();
                    //“当前线程”在调用wait()时，必须拥有该对象的同步锁。该线程调用wait()之后，会释放该锁；然后一直等待直到“其它线程”调用对象的同步锁的notify()或notifyAll()方法
                    synchronized (a) {
                        activity.getHandler().sendMessage(newMsg);
                        activity.getHandler().postAtFrontOfQueue(new Runnable() {
                            @Override
                            public void run() {
                                synchronized (a) {
                                    a.notify();
                                }
                            }
                        });
                        try {
                            a.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } finally {

                        }
                    }
                }
            };
            Looper.loop();
        }

        public void exit() {
            if (mLooper != null) {
                mLooper.quit();
                mLooper = null;
            }
        }

        // This method returns immediately, it just push an Message into Thread's MessageQueue.
        // You can also call this method continuously, the task will be executed one by one in the
        // order of which they are pushed into MessageQueue(they are called).
        public void executeTask(String text) {
            if (mLooper == null || mHandler == null) {
                Message msg = Message.obtain();
                msg.obj = "Sorry man, it is out of service";
                mActivity.get().getHandler().sendMessage(msg);
                return;
            }
            Message msg = Message.obtain();
            msg.obj = text;
            mHandler.sendMessage(msg);
        }
    }
}
