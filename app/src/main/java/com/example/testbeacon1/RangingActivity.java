package com.example.testbeacon1;

import android.app.Activity;
import android.os.Bundle;
import android.os.RemoteException;
import android.util.Log;
import android.widget.Toast;

import org.altbeacon.beacon.Beacon;
import org.altbeacon.beacon.BeaconConsumer;
import org.altbeacon.beacon.BeaconManager;
import org.altbeacon.beacon.BeaconParser;
import org.altbeacon.beacon.Identifier;
import org.altbeacon.beacon.RangeNotifier;
import org.altbeacon.beacon.Region;

import java.util.ArrayList;
import java.util.Collection;

public class RangingActivity extends Activity implements BeaconConsumer, RangeNotifier {
    protected static final String TAG = "RangingActivity";
    private static final long DEFAULT_SCAN_PERIOD_MS = 6000l;
    private BeaconManager mBeaconManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranging);
        mBeaconManager = BeaconManager.getInstanceForApplication(this);
        mBeaconManager.setForegroundScanPeriod(DEFAULT_SCAN_PERIOD_MS);
        // En este ejemplo vamos a usar el protocolo Eddystone, así que tenemos que definirlo aquí
        mBeaconManager.getBeaconParsers().add(new BeaconParser().
                //setBeaconLayout("m:2-3=beac,i:4-19,i:20-21,i:22-23,p:24-24,d:25-25")); // ALTBEACON
                //setBeaconLayout("m:2-3=0215,i:4-19,i:20-21,i:22-23,p:24-24")); // IBEACON
                setBeaconLayout("s:0-1=feaa,m:2-2=00,p:3-3:-41,i:4-13,i:14-19")); // EDDY-UID
                 //setBeaconLayout("s:0-1=feaa,m:2-2=10,p:3-3:-41,i:4-20v")); // EDDY-URL
        // Bindea esta actividad al BeaconService
        mBeaconManager.bind(this);
    }

    @Override
    public void onBeaconServiceConnect() {
        // Encapsula un identificador de un beacon de una longitud arbitraria de bytes
        ArrayList<Identifier> identifiers = new ArrayList<>();

        // Asignar null para indicar que queremos buscar cualquier beacon
        //identifiers.add(null);
        // Representa un criterio de campos utilizados para buscar beacons
        Region region = new Region("AllBeaconsRegion", identifiers);
        try {
            // Ordena al BeaconService empezar a buscar beacons que coincida con el objeto Region pasado
            System.out.println("************ STARTING TO PARSE ************");
            mBeaconManager.startRangingBeaconsInRegion(region);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        // Especifica una clase que debería ser llamada cada vez que BeaconsService obtiene datos, una vez por segundo por defecto
        mBeaconManager.addRangeNotifier(this);
    }

    @Override
    public void didRangeBeaconsInRegion(Collection<Beacon> beacons, Region region) {
        //System.out.println("************************ IN RANGING ************************");
        if (beacons.size() == 0) {
            System.out.println("There are no any beacons");
            showToast("still searching");
        }
        if (beacons.size() > 0) {
            showToast("FOUND");
            System.out.println("The first beacon I see is about "+beacons.iterator().next().getDistance()+" meters.");
            //Log.i(TAG, "The first beacon I see is about "+beacons.iterator().next().getDistance()+" meters.");
        }
    }

    public void showToast(String s) {
        Toast toast = Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT);
        toast.show();
    }
}