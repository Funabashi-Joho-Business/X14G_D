package jp.ac.chiba_fjb.x14b_d.maguro.Lib;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import static android.content.Context.SENSOR_SERVICE;

public class Compass implements SensorEventListener {
    private float[] mAccell;
    private float[] mMagnetic;

    public interface OnSensorListener{
        public void onChange(double direction);
    }
    private SensorManager mSensor;
    private double mDirection;
    private OnSensorListener mListener;

    public Compass(Context con,OnSensorListener listener){
        mSensor = (SensorManager) con.getSystemService(SENSOR_SERVICE);
        mListener = listener;
    }

    public void start(){
        // センサーイベントの登録

        mSensor.registerListener(this, mSensor.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD), SensorManager.SENSOR_DELAY_UI);
        mSensor.registerListener(this, mSensor.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_UI);
    }
    public void stop(){
        // センサーイベントを削除
        mSensor.unregisterListener(this);
    }
    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        // センサの取得値をそれぞれ保存しておく
        switch( sensorEvent.sensor.getType()) {
            case Sensor.TYPE_ACCELEROMETER:
                mAccell = sensorEvent.values.clone();
                break;
            case Sensor.TYPE_MAGNETIC_FIELD:
                mMagnetic = sensorEvent.values.clone();
                break;
        }

        // fAccell と fMagnetic から傾きと方位角を計算する
        if( mAccell != null && mMagnetic != null ) {
            // 回転行列を得る
            float[] inR = new float[16];
            SensorManager.getRotationMatrix(
                inR,
                null,
                mAccell,
                mMagnetic );
            // ワールド座標とデバイス座標のマッピングを変換する

            // 姿勢を得る
            float[] fAttitude = new float[3];
            SensorManager.getOrientation(
                inR,
                fAttitude );

            mDirection = Math.toDegrees(fAttitude[0]);
            mListener.onChange(mDirection);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    public double getDirection(){
        return mDirection;
    }


}
