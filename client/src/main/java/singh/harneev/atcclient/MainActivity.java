package singh.harneev.atcclient;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.List;

import singh.harneev.atcsdk.IDBCallback;
import singh.harneev.atcsdk.IDBService;
import singh.harneev.atcsdk.school.School;

public class MainActivity extends AppCompatActivity {

    private static final String IPC_SERVICE_ACTION = "singh.harneev.atc.IDBService";
    private static final String IPC_SERVICE_PACKAGE = "singh.harneev.atc";

    private IDBService mIPCService;
    private ServiceConnection mServiceConnection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // binding to  IPC services
        initConnection();
    }

    public void showAllSchoolList(View view) {

        try {
            mIPCService.getSchoolDetails(serviceCallback);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void SearchSchoolInfo(View view) {

    }

    IDBCallback.Stub serviceCallback = new IDBCallback.Stub() {

        @Override
        public void handleSchoolDetail(final List<School> schoolList) throws RemoteException {

            runOnUiThread(new Runnable() {
                @Override
                public void run() {

                    Toast.makeText(MainActivity.this, "count: "+ schoolList.size(), Toast.LENGTH_LONG).show();
                }
            });
        }
    };

    /**
     * Bind to IPC Services
     */
    private void initConnection() {

        mServiceConnection = new ServiceConnection() {

            @Override
            public void onServiceDisconnected(ComponentName name) {
                mIPCService = null;
                Toast.makeText(getApplicationContext(), "Service Disconnected",
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                try {
                    mIPCService = IDBService.Stub.asInterface((IBinder) service);

                    Toast.makeText(getApplicationContext(), "Service Connected",
                            Toast.LENGTH_SHORT).show();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        // First time initialization
        if (mIPCService == null) {
            Intent it = new Intent();
            it.setAction(IPC_SERVICE_ACTION);
            it.setPackage(IPC_SERVICE_PACKAGE);

            // binding to remote service
            bindService(it, mServiceConnection, Service.BIND_AUTO_CREATE);
        }
    }
}
