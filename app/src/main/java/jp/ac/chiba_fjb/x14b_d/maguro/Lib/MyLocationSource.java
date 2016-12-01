package jp.ac.chiba_fjb.x14b_d.maguro.Lib;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;

/**
 * Created by oikawa on 2016/11/18.
 */

public class MyLocationSource implements android.location.LocationListener {
	public interface OnLocationListener {
		public void onLocation(Location location);
	}

	public void setOnLocationListener(OnLocationListener listener) {
		mListener = listener;
	}

	private OnLocationListener mListener;
	private final static int GPS_TIME = 5 * 10000;    //5秒
	private final static int NET_TIME = 10 * 10000;    //10秒
	private Context mContext;
	private LocationManager mLocationManager;
	private Location mLastLocation;

	public MyLocationSource(Context context) {
		mContext = context;
		mLocationManager = (LocationManager) mContext.getSystemService(Context.LOCATION_SERVICE);

		LocationProvider gpsProvider = mLocationManager.getProvider(LocationManager.GPS_PROVIDER);
		if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
			return;
		}
		mLocationManager.requestLocationUpdates(gpsProvider.getName(), GPS_TIME, 10, this);
		LocationProvider networkProvider = mLocationManager.getProvider(LocationManager.NETWORK_PROVIDER);
		mLocationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, NET_TIME, 0, this);
	}

	public void deactivate() {
		//警告は無視
		if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
			return;
		}
		mLocationManager.removeUpdates(this);
	}

	@Override
	public void onLocationChanged(Location location) {
		if(mListener != null)
			mListener.onLocation(location);
	}

	@Override
	public void onStatusChanged(String s, int i, Bundle bundle) {

	}

	@Override
	public void onProviderEnabled(String s) {

	}

	@Override
	public void onProviderDisabled(String s) {

	}
}