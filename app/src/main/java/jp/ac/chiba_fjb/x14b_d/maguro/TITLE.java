package jp.ac.chiba_fjb.x14b_d.maguro;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class TITLE extends AppCompatActivity implements View.OnClickListener {
public  TITLE(){

    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_camera, container, false);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imageS:
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.add(R.id.fullscreen_content, new CameraFragment());
                ft.commit();
                break;
            case R.id.imageTim:
                FragmentTransaction tt = getSupportFragmentManager().beginTransaction();
                tt.add(R.id.fullscreen_content,new team());
                break;
        }
    }
}
