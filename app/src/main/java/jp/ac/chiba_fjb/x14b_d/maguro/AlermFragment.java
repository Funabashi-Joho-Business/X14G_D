package jp.ac.chiba_fjb.x14b_d.maguro;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import static android.R.id.edit;



/**
 * A simple {@link Fragment} subclass.
 */
public class AlermFragment extends Fragment implements View.OnClickListener {

    private EditText mEditTimer;


    public AlermFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.fragment_alarm, container, false);


        mEditTimer = (EditText)view.findViewById(R.id.editTimer);
        view.findViewById(R.id.start).setOnClickListener(this);


        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.start:
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.fullscreen_content,new CameraFragment());
                ft.commitAllowingStateLoss();
                break;

<<<<<<< HEAD
=======
            case R.id.imageBack:
                FragmentTransaction ft2 = getFragmentManager().beginTransaction();
                ft2.replace(R.id.fullscreen_content,new CameraFragment());
                ft2.commitAllowingStateLoss();
                break;
>>>>>>> 48a4b94ab748e6d5355240b2a89aa2a2680ee82d
        }
    }
}
