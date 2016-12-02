package jp.ac.chiba_fjb.x14b_d.maguro;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class CameraFragment2 extends Fragment implements View.OnClickListener {

    public View onCreateView(LayoutInflater inflater, ViewGroup container) {
        View view =  inflater.inflate(R.layout.fragment_zeroin, container, false);

        view.findViewById(R.id.imageTateUp).setOnClickListener(this);
        view.findViewById(R.id.imageTateDown).setOnClickListener(this);
        view.findViewById(R.id.imageYokoUp).setOnClickListener(this);
        view.findViewById(R.id.imageYokoDown).setOnClickListener(this);
        view.findViewById(R.id.imageBack).setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imageTateUp:
                break;
            case R.id.imageTateDown:
                break;
            case R.id.imageYokoUp:
                break;
            case R.id.imageYokoDown:
                break;
            case R.id.imageBack:
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.fullscreen_content,new CameraFragment());
                ft.commitAllowingStateLoss();
                break;
        }
    }
}
