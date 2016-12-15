package jp.ac.chiba_fjb.x14b_d.maguro;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import jp.ac.chiba_fjb.x14b_d.maguro.Lib.AppDB;
import jp.ac.chiba_fjb.x14b_d.maguro.Lib.TeamOperation;
import jp.ac.chiba_fjb.x14b_d.maguro.Lib.TeikeiOperation;

import static jp.ac.chiba_fjb.x14b_d.maguro.R.id.editTeikei1;
import static jp.ac.chiba_fjb.x14b_d.maguro.R.id.editTeikei2;
import static jp.ac.chiba_fjb.x14b_d.maguro.R.id.editTeikei3;
import static jp.ac.chiba_fjb.x14b_d.maguro.R.id.editTeikei4;
import static jp.ac.chiba_fjb.x14b_d.maguro.R.id.editTeikei5;


public class TeikeiCreateFragment extends Fragment implements View.OnClickListener{

    private EditText teikei1;
    private EditText teikei2;
    private EditText teikei3;
    private EditText teikei4;
    private EditText teikei5;

    public TeikeiCreateFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_teikei_create, container, false);
        teikei1=(EditText)view.findViewById(editTeikei1);
        teikei2=(EditText)view.findViewById(editTeikei2);
        teikei3=(EditText)view.findViewById(editTeikei3);
        teikei4=(EditText)view.findViewById(editTeikei4);
        teikei5=(EditText)view.findViewById(editTeikei5);
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
                ft.replace(R.id.fullscreen_content, new TeikeiListFragment());
                ft.commitAllowingStateLoss();
                break;
            case R.id.imageBack:
                FragmentTransaction ft2 = getFragmentManager().beginTransaction();
                ft2.replace(R.id.fullscreen_content, new TitleFragment());
                ft2.commitAllowingStateLoss();
                break;
            case R.id.imagesetteing:
                AppDB db = new AppDB(getContext());
                db.setSetting("USER_TEIKEI1",teikei1.getText().toString());
                db.setSetting("USER_TEIKEI2",teikei2.getText().toString());
                db.setSetting("USER_TEIKEI3",teikei3.getText().toString());
                db.setSetting("USER_TEIKEI4",teikei4.getText().toString());
                db.setSetting("USER_TEIKEI5",teikei5.getText().toString());
                db.close();

                FragmentTransaction ft3 = getFragmentManager().beginTransaction();
                ft3.replace(R.id.fullscreen_content,new  TeikeiListFragment());
                ft3.commitAllowingStateLoss();
                break;
        }
    }
}
