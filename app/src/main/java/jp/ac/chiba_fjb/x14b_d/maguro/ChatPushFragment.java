package jp.ac.chiba_fjb.x14b_d.maguro;


import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import jp.ac.chiba_fjb.x14b_d.maguro.Lib.AppDB;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChatPushFragment extends Fragment implements View.OnClickListener {
    Handler mHandler = new Handler();
    TextView mTeikei1;
    TextView mTeikei2;
    TextView mTeikei3;
    TextView mTeikei4;
    TextView mTeikei5;

    public ChatPushFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_teikei_list, container, false);
        view.findViewById(R.id.imageBack).setOnClickListener(this);
        // Inflate the layout for this fragment
        AppDB db = new AppDB(getContext());
        String sTeikei1 = db.getSetting("USER_TEIKEI1","null");
        String sTeikei2 = db.getSetting("USER_TEIKEI2","null");
        String sTeikei3 = db.getSetting("USER_TEIKEI3","null");
        String sTeikei4 = db.getSetting("USER_TEIKEI4","null");
        String sTeikei5 = db.getSetting("USER_TEIKEI5","null");
        db.close();
        mTeikei1 = (TextView) view.findViewById(R.id.teikei1);
        mTeikei2 = (TextView) view.findViewById(R.id.teikei2);
        mTeikei3 = (TextView) view.findViewById(R.id.teikei3);
        mTeikei4 = (TextView) view.findViewById(R.id.teikei4);
        mTeikei5 = (TextView) view.findViewById(R.id.teikei5);
        setTeikei(sTeikei1, sTeikei2, sTeikei3, sTeikei4, sTeikei5);

        mTeikei1.setOnClickListener(this);
        mTeikei2.setOnClickListener(this);
        mTeikei3.setOnClickListener(this);
        mTeikei4.setOnClickListener(this);
        mTeikei5.setOnClickListener(this);
        return view;
    }

    void setTeikei(final String rTeikei1, final String rTeikei2, final String rTeikei3, final String rTeikei4, final String rTeikei5){
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                mTeikei1.setText(rTeikei1);
                mTeikei2.setText(rTeikei2);
                mTeikei3.setText(rTeikei3);
                mTeikei4.setText(rTeikei4);
                mTeikei5.setText(rTeikei5);
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imageBack:
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.fullscreen_content, new CameraFragment());
                ft.commitAllowingStateLoss();
                break;
            case R.id.teikei1:
                FragmentTransaction ft2 = getFragmentManager().beginTransaction();
                ft2.replace(R.id.fullscreen_content, new CameraFragment());
                ft2.commitAllowingStateLoss();
                break;
            case R.id.teikei2:
                FragmentTransaction ft3 = getFragmentManager().beginTransaction();
                ft3.replace(R.id.fullscreen_content, new CameraFragment());
                ft3.commitAllowingStateLoss();
                break;
            case R.id.teikei3:
                FragmentTransaction ft4 = getFragmentManager().beginTransaction();
                ft4.replace(R.id.fullscreen_content, new CameraFragment());
                ft4.commitAllowingStateLoss();
                break;
            case R.id.teikei4:
                FragmentTransaction ft5 = getFragmentManager().beginTransaction();
                ft5.replace(R.id.fullscreen_content, new CameraFragment());
                ft5.commitAllowingStateLoss();
                break;
            case R.id.teikei5:
                FragmentTransaction ft6 = getFragmentManager().beginTransaction();
                ft6.replace(R.id.fullscreen_content, new CameraFragment());
                ft6.commitAllowingStateLoss();
                break;

        }

    }
}

