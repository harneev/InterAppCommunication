package singh.harneev.atc.connection.ipc

import android.app.Service
import singh.harneev.atc.contentprovider.dbhelper.DBDispenser.schoolList
import singh.harneev.atcsdk.IDBCallback
import android.content.Intent
import android.os.*
import android.util.Log
import singh.harneev.atcsdk.IDBService
import java.lang.Exception
import java.util.ArrayList

/**
 * Created by harneev on 22/06/17.
 */
class DBService : Service() {

    private var mHandler: ServiceHandler? = null

    //Thread independently handling the background operations.
    private val mHandlerThread = HandlerThread("BackgroundThread")
    private val mCallbackList: MutableList<IDBCallback> = ArrayList()

    /**
     * Stub Methods implementation of AIDL Service
     */
    private val mBinder: IDBService.Stub = object : IDBService.Stub() {

        override fun getSchoolDetails(callback: IDBCallback) {
            sendMsgToHandler(callback, CB_ALL_SCHOOLS_DETAIL, null)
        }
    }

    override fun onCreate() {
        super.onCreate()

        // Handler Thread handling all call back methods
        mHandlerThread.start()
        mHandler = ServiceHandler(mHandlerThread.looper)
    }

    override fun onBind(intent: Intent): IBinder = mBinder

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int = START_STICKY

    /**
     * Handler class to evaluate results and reply back
     * to the caller
     */
    private inner class ServiceHandler constructor(looper: Looper) : Handler(looper) {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            try {
                val callbackIndex = msg.arg1
                val bundle = msg.data
                when (msg.what) {
                    CB_ALL_SCHOOLS_DETAIL -> mCallbackList[callbackIndex].handleSchoolDetail(
                        schoolList
                    )
                }
            } catch (e: Exception) {
                Log.w(TAG, e)
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
    fun sendMsgToHandler(callback: IDBCallback, flag: Int, bundle: Bundle?) {

        //mCallbackList.register(callback);
        // get array size
        var position = 0

        mCallbackList.add(callback)

        position = if (mCallbackList.size > 0) mCallbackList.size - 1 else 0

        mHandler?.let { handler ->
            val message = handler.obtainMessage().apply {
                arg1 = position
                bundle?.let {
                    data = it
                }
                what = flag
            }
            handler.sendMessage(message)
        }
    }

    companion object {
        private val TAG = DBService::class.java.simpleName
        const val CB_SCHOOL_DETAIL = 53
        const val CB_ALL_SCHOOLS_DETAIL = 54
    }
}