package jp.ac.chiba_fjb.x14b_d.maguro;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;


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
        View view =  inflater.inflate(R.layout.fragment_camera, container, false);

        view.findViewById(R.id.imageZoomIn).setOnClickListener(this);
        view.findViewById(R.id.imageZoomOut).setOnClickListener(this);
        view.findViewById(R.id.imageBack).setOnClickListener(this);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mCamera = new CameraPreview();
        TextureView textureView = (TextureView)getView().findViewById(R.id.textureView);
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
        switch(v.getId()){
            case R.id.imageZoomIn:
                mCamera.zoom(1);
                break;
            case R.id.imageZoomOut:
                mCamera.zoom(-1);
                break;
            case R.id.imageBack:
                getActivity().onBackPressed();
                break;
        }
    }
}

