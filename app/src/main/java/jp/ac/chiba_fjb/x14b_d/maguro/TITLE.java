package jp.ac.chiba_fjb.x14b_d.maguro;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

public class TITLE extends AppCompatActivity implements View.OnClickListener {

//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        View view =  inflater.inflate(R.layout.activity_title, container, false);
//
//        view.findViewById(R.id.imageTim).setOnClickListener(this);
//        view.findViewById(R.id.imageS).setOnClickListener(this);
//        view.findViewById(R.id.imageR).setOnClickListener(this);
//        return view;
//    }
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_title);
        View view = this.getWindow().getDecorView();
        view.findViewById(R.id.imageTim).setOnClickListener(this);
        view.findViewById(R.id.imageS).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imageS:
                Intent intent = new Intent(TITLE.this,FullscreenActivity.class);
                startActivity(intent);

//                FragmentTransaction is = getSupportFragmentManager().beginTransaction();
//                is.add(R.id.fullscreen_content, new CameraFragment());
//                is.commit();
                break;
            case R.id.imageTim:
//                Intent intent2 = new Intent(TITLE.this, TeamFragment.class);
//                startActivity(intent2);

//                FragmentTransaction it = getSupportFragmentManager().beginTransaction();
//                it.add(R.id.fullscreen_content,new TeamFragment());
                break;

        }
    }
}
