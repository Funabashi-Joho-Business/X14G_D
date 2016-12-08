package jp.ac.chiba_fjb.x14b_d.maguro.Lib;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.view.Display;
import android.view.Surface;

import static android.content.Context.SENSOR_SERVICE;

public class Compass implements SensorEventListener {
    private static final int MATRIX_SIZE = 16;
    private static final int DIMENSION = 4;
    private static final String TAG = "TEST";
    private float[] magneticValues;
    private float[] accelerometerValues;
    private SensorManager mSensor;
    private double mDirection;
    private Activity mActivity;

    public interface OnSensorListener{
        public void onChange(double direction);
    }
    private OnSensorListener mListener;
    public Compass(Activity con,OnSensorListener listener){
        mActivity = con;
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

            Display d = mActivity.getWindowManager().getDefaultDisplay();
            int dr = d.getRotation();

            float[] rotationMatrix = new float[MATRIX_SIZE];
            float[] inclinationMatrix = new float[MATRIX_SIZE];
            float[] remapedMatrix = new float[MATRIX_SIZE];

            float[] orientationValues = new float[DIMENSION];

            // 加速度センサと地磁気センサから回転行列を取得
            SensorManager.getRotationMatrix(rotationMatrix, inclinationMatrix, accelerometerValues, magneticValues);

            if (dr == Surface.ROTATION_0) {
                remapedMatrix = rotationMatrix;
            } else {
                // 回転あり
                float[] outR = new float[16];

                if (dr == Surface.ROTATION_90) {
                    SensorManager.remapCoordinateSystem(rotationMatrix,
                        SensorManager.AXIS_Y, SensorManager.AXIS_MINUS_X, remapedMatrix);

                } else if (dr == Surface.ROTATION_180) {
                    float[] outR2 = new float[16];
                    SensorManager.remapCoordinateSystem(rotationMatrix,
                        SensorManager.AXIS_Y, SensorManager.AXIS_MINUS_X, remapedMatrix);
                    SensorManager.remapCoordinateSystem(remapedMatrix,
                        SensorManager.AXIS_Y, SensorManager.AXIS_MINUS_X, remapedMatrix);

                } else if (dr == Surface.ROTATION_270) {
                    SensorManager.remapCoordinateSystem(rotationMatrix,
                        SensorManager.AXIS_MINUS_Y, SensorManager.AXIS_MINUS_X, remapedMatrix);

                }
            }

            //SensorManager.remapCoordinateSystem(rotationMatrix, SensorManager.AXIS_Z, SensorManager.AXIS_X, remapedMatrix);
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
