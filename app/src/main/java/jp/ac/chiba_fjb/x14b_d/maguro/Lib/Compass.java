package jp.ac.chiba_fjb.x14b_d.maguro.Lib;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import static android.content.Context.SENSOR_SERVICE;

public class Compass implements SensorEventListener {
    public interface OnSensorListener{
        public void onChange(double direction);
    }

    private static final int MATRIX_SIZE = 16;
    private static final int DIMENSION = 4;
    private static final String TAG = "TEST";
    private float[] magneticValues;
    private float[] accelerometerValues;
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
        if (sensorEvent.accuracy == SensorManager.SENSOR_STATUS_UNRELIABLE)
            return;

        switch (sensorEvent.sensor.getType()) {
            case Sensor.TYPE_MAGNETIC_FIELD: // 地磁気センサ
                magneticValues = sensorEvent.values.clone();
                break;
            case Sensor.TYPE_ACCELEROMETER:  // 加速度センサ
                accelerometerValues = sensorEvent.values.clone();
                break;
        }

        if (magneticValues != null && accelerometerValues != null) {
            float[] rotationMatrix = new float[MATRIX_SIZE];
            float[] inclinationMatrix = new float[MATRIX_SIZE];
            float[] remapedMatrix = new float[MATRIX_SIZE];

            float[] orientationValues = new float[DIMENSION];

            // 加速度センサと地磁気センサから回転行列を取得
            SensorManager.getRotationMatrix(rotationMatrix, inclinationMatrix, accelerometerValues, magneticValues);
            SensorManager.remapCoordinateSystem(rotationMatrix, SensorManager.AXIS_X, SensorManager.AXIS_Z, remapedMatrix);
            SensorManager.getOrientation(remapedMatrix, orientationValues);

            // 方位を取得する
            float angrad = orientationValues[0];
            mDirection = Math.floor(angrad >= 0 ? Math.toDegrees(angrad) : 360.0f + Math.toDegrees(angrad));

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
