package jp.ac.chiba_fjb.x14b_d.maguro.maguro;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;

import jp.ac.chiba_fjb.x14b_d.maguro.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class CameraFragment extends Fragment implements View.OnClickListener {
    private CameraPreview mCamera;

    public CameraFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_camera, container, false);

        view.findViewById(R.id.imageZoomIn).setOnClickListener(this);
        view.findViewById(R.id.imageZoomOut).setOnClickListener(this);
        view.findViewById(R.id.imageBack).setOnClickListener(this);
        view.findViewById(R.id.imageREC).setOnClickListener(this);
        view.findViewById(R.id.imageTimer).setOnClickListener(this);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mCamera = new CameraPreview();
        TextureView textureView = (TextureView) getView().findViewById(R.id.textureView);
        mCamera.setTextureView(textureView);
        mCamera.open(0);
        mCamera.startPreview();
        //mCamera.setSaveListener(this);
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
       //         FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
     //           ft.add(R.id.layout,new REC());
      //          ft.commit();
                break;
            case R.id.imageTimer:
     //           FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
     //           ft.add(R.id.layout,new ALARM());
     //           ft.commit();
                break;
            case  R.id.imageR:
  //              FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
       //         ft.add(R.id.layout,new GPS());
    //            ft.commit();
                break;

        }
    }
}

