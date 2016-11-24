package jp.ac.chiba_fjb.x14b_d.maguro;

import android.location.Location;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import static android.content.pm.ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;
import static android.content.pm.ActivityInfo.SCREEN_ORIENTATION_REVERSE_LANDSCAPE;


/**
 * A simple {@link Fragment} subclass.
 */
public class CameraFragment2 extends Fragment implements View.OnClickListener, MyLocationSource.OnLocationListener {

    private MyLocationSource mLocation;
    private CameraPreview mCamera;
    public CameraFragment2() {
        mCamera = new CameraPreview();
    }
    public boolean mRotation = true;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_camera2, container, false);

        TextureView textureView = (TextureView) view.findViewById(R.id.textureView);
        mCamera.setTextureView(textureView);


        view.findViewById(R.id.imageBack).setOnClickListener(this);
      view.findViewById(R.id.imageScope).setOnClickListener(this);
        view.findViewById(R.id.imageriv).setOnClickListener(this);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLocation = new MyLocationSource(getContext());
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
    }

    @Override
    public void onDestroyView() {
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
            case R.id.imageBack:
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.add(R.id.fullscreen_content,new TitleFragment());
                ft.commit();
                break;

            case  R.id.imageScope:
                FragmentTransaction ft2 = getFragmentManager().beginTransaction();
                ft2.add(R.id.fullscreen_content,new CameraFragment());
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

        }
    }

    @Override
    public void onLocation(Location location) {
        TextView textView = (TextView)getView().findViewById(R.id.textGPS);
        textView.setText(String.format("東経%.2f 北緯%.2f",location.getLongitude(),location.getLatitude()));
    }
}

