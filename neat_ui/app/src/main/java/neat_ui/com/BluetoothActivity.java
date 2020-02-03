package neat_ui.com;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Set;
import java.util.UUID;

public class BluetoothActivity extends AppCompatActivity {
    private String TAG = "neat";
    private String mUUID = "00001101-0000-1000-8000-00805F9B34FB";
    private String deviceName = "ev3dev";
    private final static int REQUEST_ENABLE_BT = 1;
    /**
     * Called when the activity is first created.
     *
     * @param savedInstanceState If the activity is being re-initialized after
     *                           previously being shut down then this Bundle contains the data it most
     *                           recently supplied in onSaveInstanceState(Bundle). <b>Note: Otherwise it is null.</b>
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        // Device does not support Bluetooth
        if (mBluetoothAdapter == null) Toast.makeText(getApplicationContext(),"This device doesn’t support Bluetooth",Toast.LENGTH_SHORT).show();
        if (!mBluetoothAdapter.isEnabled()) {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
        }
        BluetoothDevice mmDevice = null;
        Set<BluetoothDevice> pairedDevices = mBluetoothAdapter.getBondedDevices();
        // If there are paired devices
        if (pairedDevices.size() > 0) {
            // Loop through paired devices
            for (BluetoothDevice device : pairedDevices) {
                // Add the name and address to an array adapter to show in a ListView
                if (device.getName().equals(deviceName))
                    mmDevice = device;
            }
        }
        //Creating the socket.
        BluetoothSocket mmSocket;
        BluetoothSocket tmp = null;
        UUID myUUID = UUID.fromString(mUUID);
        try {
            // MY_UUID is the app's UUID string, also used by the server code
            tmp = mmDevice.createRfcommSocketToServiceRecord(myUUID);
        } catch (IOException e) {
            e.printStackTrace();
        }
        mmSocket = tmp;
        //socket created, try to connect
        try {
            mmSocket.connect();
        } catch (IOException e) {
            try {
                mmSocket.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        try {
            InputStream in = mmSocket.getInputStream();
            OutputStream out = mmSocket.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
