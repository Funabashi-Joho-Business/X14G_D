package jp.ac.chiba_fjb.x14b_d.maguro;
/**
 * Created by x14g010 on 2016/11/01.
 */

import android.app.Activity;
import android.content.Context;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;

public class GPS extends Activity {
    // GPS用
    private LocationManager mLocationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        // GPS
        mLocationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        boolean gpsFlg = mLocationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        Log.d("GPS Enabled", gpsFlg?"OK":"NG");
    }

    // GPSボタン

    }
