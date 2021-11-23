package com.example.testbeacon1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.RemoteException;
import android.util.Log;
import android.util.Range;
import android.widget.TextView;

//import com.aprilbrother.aprilbrothersdk.Beacon;
//import com.aprilbrother.aprilbrothersdk.connection.AprilBeaconConnection;

import org.altbeacon.beacon.Beacon;
import org.altbeacon.beacon.BeaconConsumer;
import org.altbeacon.beacon.BeaconManager;
import org.altbeacon.beacon.BeaconParser;
import org.altbeacon.beacon.BleNotAvailableException;
import org.altbeacon.beacon.RangeNotifier;
import org.altbeacon.beacon.Region;

import java.util.Collection;
import java.util.List;

import static android.content.ContentValues.TAG;

public class MainActivity extends AppCompatActivity {
    private static final int TAG_CODE_PERMISSION_LOCATION = 2;
    //BeaconListAdapter beaconListAdapter;
    private BeaconManager beaconManager;
    //private BeaconListAdapter beaconListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView txt = (TextView)findViewById(R.id.TxtView1);
        txt.setText("ssssd");
        //RangingActivity rangingActivity = new RangingActivity();


        //rangingActivity.onCreate();
        //Beacon (параметры конструктора - три стринга, четыре инта) - зачем?? Смотри в классе в библиотеке
        //BeaconManager
        //AprilBeaconConnection

        //read = new AprilBeaconCharacteristics(this, beacon);
        //read.connectGattToRead(new MyReadCallBack()
        System.out.println("******************************HELLO*****************************");

        /*if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) ==
                PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) ==
                        PackageManager.PERMISSION_GRANTED) {
            // {beacon code}
        } else {
            ActivityCompat.requestPermissions(this, new String[] {
                            Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_COARSE_LOCATION },
                    TAG_CODE_PERMISSION_LOCATION);
        }*/

        //beaconManager = BeaconManager.getInstanceForApplication(this);

        BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (bluetoothAdapter.isEnabled()) {
            System.out.println("BLUETOOTH ENABLED");
            Intent intent = new Intent(MainActivity.this,RangingActivity.class);
            startActivity(intent);
        }


        // To detect proprietary beacons, you must add a line like below corresponding to your beacon
        // type.  Do a web search for "setBeaconLayout" to get the proper expression.
       /*beaconManager.getBeaconParsers().add(new BeaconParser().setBeaconLayout("m:2-3=0215,i:4-19,i:20-21,i:22-23,p:24-24"));

        beaconManager.setForegroundScanPeriod(5000l);
        beaconManager.setBackgroundScanPeriod(5000l);
        beaconManager.setForegroundBetweenScanPeriod(1100l);
        beaconManager.setBackgroundBetweenScanPeriod(1100l);
        BeaconConsumer beaconConsumer = new BeaconConsumer() {
            @Override
            public void onBeaconServiceConnect() {
                beaconManager.setRangeNotifier(new RangeNotifier() {
                    @Override
                    public void didRangeBeaconsInRegion(Collection<Beacon> collection, Region region) {
                        if(collection.size() > 0){
                            MainActivity.this.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    beaconListAdapter.setNewData((List<Beacon>) collection);
                                }
                            });
                            Log.i(TAG, "The first beacon I see is about "+collection.iterator().next().getDistance()+" meters away.");}

                    }
                });
            }

            @Override
            public Context getApplicationContext() {
                return null;
            }

            @Override
            public void unbindService(ServiceConnection serviceConnection) {

            }

            @Override
            public boolean bindService(Intent intent, ServiceConnection serviceConnection, int i) {
                return false;
            }
        }

        beaconManager.bind(beaconConsumer);

        beaconManager.addRangeNotifier(new RangeNotifier() {
            @Override
            public void didRangeBeaconsInRegion(Collection<Beacon> beacons, Region region) {
                System.out.println("The first beacon I see is about "+beacons.iterator().next().getDistance()+" meters away.");
            }
        });

        Region region = new Region("myRangingUniqueId", null, null, null);
        try {
            beaconManager.startRangingBeaconsInRegion(region);
        } catch (RemoteException e) {
            e.printStackTrace();
        }


        // To detect proprietary beacons, you must add a line like below corresponding to your beacon
        // type.  Do a web search for "setBeaconLayout" to get the proper expression.
        // beaconManager.getBeaconParsers().add(new BeaconParser().
        //        setBeaconLayout("m:2-3=beac,i:4-19,i:20-21,i:22-23,p:24-24,d:25-25"));
*/
    }


}