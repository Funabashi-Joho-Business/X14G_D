package jp.ac.chiba_fjb.x14b_d.maguro;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import jp.ac.chiba_fjb.x14b_d.maguro.Lib.AppDB;


/**
 * A simple {@link Fragment} subclass.
 */
public class TeikeiCreateFragment extends Fragment implements View.OnClickListener {
    private EditText editTeikei1;
    private EditText editTeikei2;
    private EditText editTeikei3;
    private EditText editTeikei4;
    private EditText editTeikei5;
    public TeikeiCreateFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_teikei_create, container, false);
        view.findViewById(R.id.editTeikei1).setOnClickListener(this);
        view.findViewById(R.id.editTeikei2).setOnClickListener(this);
        view.findViewById(R.id.editTeikei3).setOnClickListener(this);
        view.findViewById(R.id.editTeikei4).setOnClickListener(this);
        view.findViewById(R.id.editTeikei5).setOnClickListener(this);
        view.findViewById(R.id.imageBack).setOnClickListener(this);
        view.findViewById(R.id.imageTeikeiList).setOnClickListener(this);
        view.findViewById(R.id.imagesetteing).setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imageTeikeiList:
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.fullscreen_content,new TeikeiListFragment());
                ft.commitAllowingStateLoss();
                break;
            case R.id.imageBack:
                FragmentTransaction ft2 = getFragmentManager().beginTransaction();
                ft2.replace(R.id.fullscreen_content,new TitleFragment());
                ft2.commitAllowingStateLoss();
                break;
            case R.id.imagesetteing:
                save();
                get();
                break;
        }
    }

    private void save() {
        AppDB db = new AppDB(getContext());
        db.setSetting("URSE_TEIKEI1",editTeikei1.getText().toString());
        db.setSetting("URSE_TEIKEI2",editTeikei2.getText().toString());
        db.setSetting("URSE_TEIKEI3",editTeikei3.getText().toString());
        db.setSetting("URSE_TEIKEI4",editTeikei4.getText().toString());
        db.setSetting("URSE_TEIKEI5",editTeikei5.getText().toString());
        db.close();
    }
    private void get(){
        AppDB db = new AppDB(getContext());
        db.getSetting("URSE_TEIKEI1","");
        db.getSetting("URSE_TEIKEI2","");
        db.getSetting("URSE_TEIKEI3","");
        db.getSetting("URSE_TEIKEI4","");
        db.getSetting("URSE_TEIKEI5","");
        db.close();
    }


}
