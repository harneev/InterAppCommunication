package singh.harneev.atc.connection.ipc;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import singh.harneev.atc.contentprovider.dbhelper.DBDispenser;
import singh.harneev.atcsdk.IDBCallback;
import singh.harneev.atcsdk.IDBService;

/**
 * Created by harneev on 22/06/17.
 */

public class DBService extends Service {

    private static final String TAG = DBService.class.getSimpleName();

    public static final int CB_SCHOOL_DETAIL = 53;
    public static final int CB_ALL_SCHOOLS_DETAIL = 54;

    private ServiceHandler mHandler;

    //Thread independently handling the background operations.
    private HandlerThread mHandlerThread = new HandlerThread("BackgroundThread");

    private List<IDBCallback> mCallbackList = new ArrayList<>();

    @Override
    public void onCreate() {
        super.onCreate();

        // Handler Thread handling all call back methods
        mHandlerThread.start();
        mHandler = new ServiceHandler(mHandlerThread.getLooper());
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    /**
     * Stub Methods implementation of AIDL Service
     */
    IDBService.Stub mBinder = new IDBService.Stub() {

        @Override
        public void getSchoolDetails(IDBCallback callback) throws RemoteException {

            sendMsgToHandler(callback, CB_ALL_SCHOOLS_DETAIL, null);
        }
    };

    /**
     * Handler class to evaluate results and reply back
     * to the caller
     */
    private class ServiceHandler extends Handler {

        ServiceHandler(Looper looper) {
            super(looper);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            try {
                int callbackIndex = msg.arg1;
                Bundle bundle = msg.getData();

                switch (msg.what) {

                    case CB_ALL_SCHOOLS_DETAIL:

                        mCallbackList.get(callbackIndex).handleSchoolDetail(DBDispenser.getSchoolList());

                        break;
                }
            } catch (Exception e) {
                Log.w(TAG, e);
            }
        }
    }

    /**
     * Create and Send message to handler
     *
     * @param callback
     * @param flag
     * @param bundle
     */
    void sendMsgToHandler(IDBCallback callback, int flag, Bundle bundle) {

        //mCallbackList.register(callback);
        // get array size
        int position = 0;

        if (callback != null) {
            mCallbackList.add(callback);
        }

        position = (mCallbackList.size() > 0 ? mCallbackList.size() - 1 : 0);

        Message message = mHandler.obtainMessage();
        message.arg1 = position;

        if (bundle != null)
            message.setData(bundle);

        message.what = flag;
        mHandler.sendMessage(message);
    }
}
