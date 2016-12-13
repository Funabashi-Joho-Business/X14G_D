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


public abstract class TeikeiCreateFragment extends Fragment implements View.OnClickListener, TeikeiOperation.OnTeikeiListener {

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
        teikei1=(EditText)view.findViewById(R.id.editTeikei1);
        teikei2=(EditText)view.findViewById(R.id.editTeikei2);
        teikei3=(EditText)view.findViewById(R.id.editTeikei3);
        teikei4=(EditText)view.findViewById(R.id.editTeikei4);
        teikei5=(EditText)view.findViewById(R.id.editTeikei5);
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

                FragmentTransaction ft3 = getFragmentManager().beginTransaction();
                ft3.replace(R.id.fullscreen_content,new TeikeiListFragment());
                ft3.commitAllowingStateLoss();
                break;
        }
    }

    private void save() {
//定型文セット処理
        AppDB db = new AppDB(getContext());
        int userId = db.getSetting("USER_ID",0);
        db.setSetting("URSE_TEIKEI1",teikei1.getText().toString());
        db.setSetting("URSE_TEIKEI2",teikei2.getText().toString());
        db.setSetting("URSE_TEIKEI3",teikei3.getText().toString());
        db.setSetting("URSE_TEIKEI4",teikei4.getText().toString());
        db.setSetting("URSE_TEIKEI5",teikei5.getText().toString());
        db.close();
        Snackbar.make(getView(), "定型文作成", Snackbar.LENGTH_SHORT).show();
      //  TeamOperation.joinTeam(editTeikei1.getText().toString(),editTeikei2.getText().toString(),editTeikei3.getText().toString(),editTeikei4.getText().toString(),editTeikei5.getText().toString(),userId,this);
    }

    //定型文呼び出し
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
