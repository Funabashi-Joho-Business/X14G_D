package jp.ac.chiba_fjb.x14b_d.maguro;
import android.location.Location;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

import static android.content.pm.ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;
import static android.content.pm.ActivityInfo.SCREEN_ORIENTATION_REVERSE_LANDSCAPE;


/**
 * A simple {@link Fragment} subclass.
 */
public class CameraFragment extends Fragment implements View.OnClickListener, MyLocationSource.OnLocationListener {

    private MyLocationSource mLocation;
    private CameraPreview mCamera;
    private int mVisibilty;

    public CameraFragment() {
        mCamera = new CameraPreview();
    }
    public boolean mRotation = true;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_camera, container, false);

        TextureView textureView = (TextureView) view.findViewById(R.id.textureView);
        mCamera.setTextureView(textureView);

        view.findViewById(R.id.imageZoomIn).setOnClickListener(this);
        view.findViewById(R.id.imageZoomOut).setOnClickListener(this);
        view.findViewById(R.id.imageBack).setOnClickListener(this);
        view.findViewById(R.id.imageREC).setOnClickListener(this);
        view.findViewById(R.id.imageTimer).setOnClickListener(this);
        view.findViewById(R.id.imageriv).setOnClickListener(this);
        view.findViewById(R.id.imageMember).setOnClickListener(this);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLocation = new MyLocationSource(getContext());
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mVisibilty = view.getSystemUiVisibility();
        view.setSystemUiVisibility(
            View.SYSTEM_UI_FLAG_LOW_PROFILE
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
        );


        view.requestFocus();
    }


    @Override
    public void onDestroyView() {
        getView().setSystemUiVisibility(mVisibilty);
        super.onDestroyView();
    }



    @Override
    public void onResume() {
        super.onResume();
        mCamera.open(0);
        mCamera.startPreview();

        mLocation.setOnLocationListener(this);
    }

    @Override
    public void onPause() {
        mLocation.setOnLocationListener(null);
        mCamera.close();
        super.onPause();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imageZoomIn:
                mCamera.zoom(4);
                break;
            case R.id.imageZoomOut:
                mCamera.zoom(-4);
                break;
            case R.id.imageBack:
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.fullscreen_content,new TitleFragment());
                ft.commit();
//                getActivity().onBackPressed();
                break;
            case R.id.imageREC:
                if(!mCamera.isRecording()) {
                    Toast.makeText(getContext(), "録画開始", Toast.LENGTH_SHORT).show();
                    Date date = new Date();
                    SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String fileName = String.format("%s/%s.mp4",Environment.getExternalStorageDirectory().toString(),sdf1.format(date));
                    mCamera.startRecording(fileName);
                }
                else {
                    Toast.makeText(getContext(), "録画停止", Toast.LENGTH_SHORT).show();
                    mCamera.stopRecording();
                }
                break;
            case R.id.imageTimer:

                FragmentTransaction ft2 = getFragmentManager().beginTransaction();
                ft2.replace(R.id.fullscreen_content,new AlermFragment());
                ft2.commit();

                break;
            case R.id.imageriv:
                if(mRotation)
                    getActivity().setRequestedOrientation(SCREEN_ORIENTATION_REVERSE_LANDSCAPE);
                else
                    getActivity().setRequestedOrientation(SCREEN_ORIENTATION_LANDSCAPE);
                mRotation = !mRotation;
                mCamera.setRotation(180.0f);
                break;
            case R.id.imageMember:
                FragmentTransaction ft4 = getFragmentManager().beginTransaction();
                ft4.replace(R.id.fullscreen_content,new list());
                ft4.commitAllowingStateLoss();
                break;
        }
    }

    @Override
    public void onLocation(Location location) {
        TextView textView = (TextView)getView().findViewById(R.id.textGPS);
        textView.setText(String.format("東経%.2f 北緯%.2f",location.getLongitude(),location.getLatitude()));
    }
}

