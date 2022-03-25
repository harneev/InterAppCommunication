package singh.harneev.atcclient

import singh.harneev.atcsdk.IDBService
import android.content.ServiceConnection
import android.os.Bundle
import singh.harneev.atcclient.R
import singh.harneev.atcsdk.IDBCallback
import kotlin.Throws
import singh.harneev.atcsdk.school.School
import android.widget.Toast
import android.content.ComponentName
import android.os.IBinder
import android.content.Intent
import android.os.RemoteException
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    private var mIPCService: IDBService? = null

    private var mServiceConnection = object : ServiceConnection {
        override fun onServiceDisconnected(name: ComponentName) {
            mIPCService = null
            Toast.makeText(
                applicationContext, "Service Disconnected",
                Toast.LENGTH_SHORT
            ).show()
        }

        override fun onServiceConnected(name: ComponentName, service: IBinder) {
            mIPCService = IDBService.Stub.asInterface(service)
            Toast.makeText(
                applicationContext, "Service Connected",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private val serviceCallback: IDBCallback.Stub = object : IDBCallback.Stub() {
        @Throws(RemoteException::class)
        override fun handleSchoolDetail(schoolList: List<School>) {
            runOnUiThread {
                Toast.makeText(
                    this@MainActivity,
                    "count: " + schoolList.size,
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()
        // binding to  IPC services

        mIPCService ?: run {
            val intent = Intent(IPC_SERVICE_ACTION).also {
                it.`package` = IPC_SERVICE_PACKAGE
            }

            bindService(intent, mServiceConnection, BIND_AUTO_CREATE)
        }
    }

    fun showAllSchoolList(view: View?) {
        try {
            mIPCService?.getSchoolDetails(serviceCallback)
        } catch (e: RemoteException) {
            e.printStackTrace()
        }
    }

    fun SearchSchoolInfo(view: View?) {}

    companion object {
        private const val IPC_SERVICE_ACTION = "singh.harneev.atc.IDBService"
        private const val IPC_SERVICE_PACKAGE = "singh.harneev.atc"
    }
}