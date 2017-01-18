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
public class TopFragment extends Fragment implements View.OnClickListener {


    private EditText mEditName;

    public TopFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //設定済みの名前を読み出す
        AppDB db = new AppDB(getContext());
        String name = db.getSetting("USER_NAME","");
        db.close();

        View view =inflater.inflate(R.layout.fragment_top, container, false);
        mEditName = (EditText)view.findViewById(R.id.editName);
        mEditName.setText(name);
        view.findViewById(R.id.imageBack).setOnClickListener(this);
        view.findViewById(R.id.imagesettei).setOnClickListener(this);

        return view;
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imagesettei:
                //名前の保存
                AppDB db = new AppDB(getContext());
                db.setSetting("USER_NAME",mEditName.getText().toString());
                db.close();

                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.fullscreen_content, new TitleFragment());
                ft.commitAllowingStateLoss();
                break;

            case R.id.imageBack:
                FragmentTransaction ft2 = getFragmentManager().beginTransaction();
                ft2.replace(R.id.fullscreen_content,new TitleFragment());
                ft2.commitAllowingStateLoss();
                break;
        }
    }
}