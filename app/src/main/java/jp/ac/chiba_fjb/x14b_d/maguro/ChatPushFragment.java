package jp.ac.chiba_fjb.x14b_d.maguro;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import jp.ac.chiba_fjb.x14b_d.maguro.Lib.AppDB;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChatPushFragment extends Fragment implements View.OnClickListener {

    public ChatPushFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
<<<<<<< HEAD
        View view= inflater.inflate(R.layout.fragment_teikei_list, container, false);
        view.findViewById(R.id.imageBack).setOnClickListener(this);
        AppDB db = new AppDB(getContext());
        String teikei1 = db.getSetting("USER_TEIKEI1","");
        String teikei2 = db.getSetting("USER_TEIKEI2","");
        String teikei3 = db.getSetting("USER_TEIKEI3","");
        String teikei4 = db.getSetting("USER_TEIKEI4","");
        String teikei5 = db.getSetting("USER_TEIKEI5","");
        db.close();
=======
        View view = inflater.inflate(R.layout.fragment_teikei_list, container, false);
        view.findViewById(R.id.imageBack).setOnClickListener(this);
>>>>>>> 41a85c77c46072be098a86a880cde1e5fd0d33e3


        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imageBack:
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.fullscreen_content, new CameraFragment());
                ft.commitAllowingStateLoss();
                break;

        }


            getActivity().runOnUiThread(new Runnable() {
                public void run() {
                        AppDB db = new AppDB(getContext());
                        String teikei1 = db.getSetting("USER_TEIKEI1", "");
                        String teikei2 = db.getSetting("USER_TEIKEI2", "");
                        String teikei3 = db.getSetting("USER_TEIKEI3", "");
                        String teikei4 = db.getSetting("USER_TEIKEI4", "");
                        String teikei5 = db.getSetting("USER_TEIKEI5", "");
                        db.close();

                    }

            });

        }
    }

