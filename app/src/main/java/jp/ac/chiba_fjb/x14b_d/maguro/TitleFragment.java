package jp.ac.chiba_fjb.x14b_d.maguro;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import jp.ac.chiba_fjb.x14b_d.maguro.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class TitleFragment extends Fragment implements View.OnClickListener {

        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_title, container, false);

        view.findViewById(R.id.imageTim).setOnClickListener(this);
        view.findViewById(R.id.imageS).setOnClickListener(this);
        return view;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imageS:
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.fullscreen_content,new CameraFragment());
                ft.commitAllowingStateLoss();
                break;
            case R.id.imageTim:
                break;

        }
    }
}
