package jp.ac.chiba_fjb.x14b_d.maguro;

import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

import jp.ac.chiba_fjb.x14b_d.maguro.CameraPreview;

import static android.content.pm.ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;
import static android.content.pm.ActivityInfo.SCREEN_ORIENTATION_REVERSE_LANDSCAPE;


/**
 * A simple {@link Fragment} subclass.
 */
public class CameraFragment extends Fragment implements View.OnClickListener {
    private CameraPreview mCamera;
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
        return view;
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
    }

    @Override
    public void onPause() {
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
                getActivity().onBackPressed();
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

                DialogFragment newFragment = new AlermFragment();
                newFragment.show(getFragmentManager(),null);

                break;
            case R.id.imageriv:
                if(mRotation)
                    getActivity().setRequestedOrientation(SCREEN_ORIENTATION_REVERSE_LANDSCAPE);
                else
                    getActivity().setRequestedOrientation(SCREEN_ORIENTATION_LANDSCAPE);
                mRotation = !mRotation;
                break;
//            case  R.id.imageR:
//               FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
//                ft.add(R.id.layout,);
//                ft.commit();
//                break;

        }
    }
}

