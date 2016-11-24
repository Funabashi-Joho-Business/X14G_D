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
public class CameraFragment2 extends Fragment implements View.OnClickListener{

    private MyLocationSource mLocation;
    public boolean mRotation = true;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_camera2, container, false);

        view.findViewById(R.id.imageScope).setOnClickListener(this);
        view.findViewById(R.id.imageriv).setOnClickListener(this);
        return view;
    }


    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case  R.id.imageScope:
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.add(R.id.fullscreen_content,new CameraFragment());
                ft.commit();
                break;

            case R.id.imageriv:
                if(mRotation)
                    getActivity().setRequestedOrientation(SCREEN_ORIENTATION_REVERSE_LANDSCAPE);
                else
                    getActivity().setRequestedOrientation(SCREEN_ORIENTATION_LANDSCAPE);
                break;

        }
    }

}

