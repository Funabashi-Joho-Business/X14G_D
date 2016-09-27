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
public class CameraFragment extends Fragment {
    private CameraPreview mCamera;

    public CameraFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_camera, container, false);
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
    public void onPause() {
        mCamera.close();
        super.onPause();
    }
}
