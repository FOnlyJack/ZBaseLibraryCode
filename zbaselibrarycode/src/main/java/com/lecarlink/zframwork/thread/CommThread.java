package com.lecarlink.zframwork.thread;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class CommThread extends HandlerThread {

    private final static String TAG = CommThread.class.getCanonicalName();

    private final static int READ_DELAY_MS = 500;

    private final static int MSG_INIT_SOCKET = 0;
    private final static int MSG_READ_SOCKET = 1;
    private final static int MSG_WRITE_TO_SOCKET = 2;
    private final static int MSG_SHUTDOWN = 3;
    Handler mResponseHandler;
    private Handler mHandler;
    private CommListener mListener;

    private Runnable mScheduledReadRunnable = new Runnable() {
        @Override
        public void run() {
            sendMessageToRead();
        }
    };

    // socket related
    private Socket mSocket;
    private DataInputStream mInputStream;
    private DataOutputStream mOutputStream;
    private InetAddress mSocketAddress;
    private int mPort;


    public CommThread(Handler responseHandler) {
        super(TAG);
        mResponseHandler = responseHandler;
        mInputStream = null;
        mOutputStream = null;
    }

    public void initSocket(final InetAddress serverAddress, int port) {
        mSocketAddress = serverAddress;
        mPort = port;

        mHandler = new Handler(getLooper()) {
            @Override
            public void handleMessage(Message msg) {
                try {
                    if (msg.what == MSG_INIT_SOCKET) {
                        initSocketAndStreams();
                    } else if (msg.what == MSG_READ_SOCKET) {
                        processReadMessage();
                    } else if (msg.what == MSG_WRITE_TO_SOCKET) {
                        processSendMessage(msg);
                    } else if (msg.what == MSG_SHUTDOWN) {
                        quit();
                    }

                } catch (IOException e) {
                    Log.e(TAG, e.getMessage());
                    deviceConnectionClosed();

                    shutDownSocketAndStreams();

                }
            }
        };

        // post a runnable to init streams from the thread
        mHandler.sendEmptyMessageDelayed(MSG_INIT_SOCKET, 100);
    }

    public void setListener(CommListener listener) {
        this.mListener = listener;
    }

    private void initSocketAndStreams() throws IOException {

        mSocket = new Socket(mSocketAddress, mPort);
        mSocket.setKeepAlive(true);
        mSocket.setSoTimeout(1);
        mInputStream = new DataInputStream(new BufferedInputStream(mSocket.getInputStream()));
        mOutputStream = new DataOutputStream(mSocket.getOutputStream());

        mResponseHandler.post(new Runnable() {
            @Override
            public void run() {
                if (mListener != null) {
                    mListener.onConnectionSetupComplete();
                }
            }
        });

        scheduleReadInterval();
    }

    private void shutDownSocketAndStreams() {
        Log.d(TAG, "Shutting down connection to the stat");
        try {
            if (mOutputStream != null) {
                mOutputStream.close();
            }

            if (mInputStream != null) {
                mInputStream.close();
            }

            if (mSocket != null) {
                mSocket.close();
            }
        } catch (IOException ex) {
            Log.e(TAG, ex.toString());
        }
    }

    private void processSendMessage(Message msg) throws IOException {
//        if (mSocket.isConnected()) {
//            Log.d(TAG, "Sending message to the stat");
//            if (msg.obj != null && msg.obj instanceof Message) {
//                Message msgToSend = (Message) msg.obj;
//                byte[] toSend = msgToSend.toByteArray();
//                int size = msgToSend.getByteArraySize();
//                mOutputStream.write(toSend, 0, size);
//                mOutputStream.flush();
//            }
//        } else {
//            throw new IOException("error sending message, socket is disconnected");
//        }
    }

    private void processReadMessage() throws IOException {

//        if (mSocket.isConnected()) {
//            // read message
//            int bytesAvail = mInputStream.available();
//            if (bytesAvail > 0) {
//                Log.d(TAG, "Reading message from the stat");
//                byte[] recvBuffer = new byte[bytesAvail];
//                int readCount = mInputStream.read(recvBuffer);
//                if (readCount > 0) {
//                    int payload_size = ((recvBuffer[1] & 0xFF) << 8) | (recvBuffer[2] & 0xFF);
//                    byte[] payload = new byte[payload_size];
//                    System.arraycopy(recvBuffer, Message.DEVICE_MESSAGE_HEADER_SIZE, payload, 0, payload_size);
//                    final Message rcv_msg = new Message(recvBuffer[0], recvBuffer[3], payload_size, payload);
//
//					if(rcv_msg.isValid()) {
//                     //otherwise call the callback
//                        mResponseHandler.post(new Runnable() {
//                            @Override
//                            public void run() {
//                                if (mListener != null) {
//                                    mListener.onDataSendSuccess(rcv_msg);
//                                }
//                            }
//                        });
//                    } else {
//                        // or post failure
//                        //otherwise call the callback
//                        mResponseHandler.post(new Runnable() {
//                            @Override
//                            public void run() {
//                                if (mListener != null) {
//                                    mListener.onDataSendFailed(rcv_msg);
//                                }
//                            }
//                        });
//                    }
//                } else {
//                    throw new IOException("Socket read error");
//                }
//            }
//
//            scheduleReadInterval();
//        } else {
//            throw new IOException("error reading message, socket is disconnected");
//        }

    }

    private void deviceConnectionClosed() {
        if (mListener != null) {
            mListener.onDeviceConnectionClosed();
        }
    }

    private void scheduleReadInterval() {
        // schedule to be read again in xx interval
        mHandler.postDelayed(mScheduledReadRunnable, READ_DELAY_MS);
    }

    // use these to send message
    public void sendMessageToWrite(Message msg) {
        mHandler.obtainMessage(MSG_WRITE_TO_SOCKET, msg).sendToTarget();
    }

    public void sendMessageToRead() {
        mHandler.obtainMessage(MSG_READ_SOCKET).sendToTarget();
    }

    public void sendMessageToShutdown() {
        Log.d(TAG, "Trigger shut down");
        mHandler.obtainMessage(MSG_SHUTDOWN).sendToTarget();
    }

    @Override
    public boolean quit() {
        Log.d(TAG, "Remove messages and shutdown");
        mHandler.removeMessages(MSG_READ_SOCKET);
        mHandler.removeCallbacks(mScheduledReadRunnable);
        shutDownSocketAndStreams();
        return super.quit();
    }

    public interface CommListener {

        void onConnectionSetupComplete();

        void onDeviceConnectionClosed();

        void onMesssageReceived(Message message);

        void onDataSendSuccess(Message msg);

        void onDataSendFailed(Message msg);
    }
}
