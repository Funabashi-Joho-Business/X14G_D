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

import static android.R.attr.name;

import jp.ac.chiba_fjb.x14b_d.maguro.Lib.AppDB;
import jp.ac.chiba_fjb.x14b_d.maguro.Lib.TeamOperation;

/**
 * A simple {@link Fragment} subclass.
 */
public class TeikeiListFragment extends Fragment implements View.OnClickListener {

<<<<<<< HEAD:app/src/main/java/jp/ac/chiba_fjb/x14b_d/maguro/teikeiFragment.java
    private EditText mEditteikei;
    public teikeiFragment() {
=======

    public TeikeiListFragment() {
>>>>>>> 96547077665a5edc861c09fdc33f2af3ef2c1500:app/src/main/java/jp/ac/chiba_fjb/x14b_d/maguro/TeikeiListFragment.java
        // Required empty public constructor
    }


    @Override
<<<<<<< HEAD:app/src/main/java/jp/ac/chiba_fjb/x14b_d/maguro/teikeiFragment.java
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        AppDB db = new AppDB(getContext());
        String name = db.getSetting("TEIKEI","");
        db.close();

        View view = inflater.inflate(R.layout.fragment_teikei, container, false);
        view.findViewById(R.id.itiran).setOnClickListener(this);
        mEditteikei = (EditText)view.findViewById(R.id.itiran);
        mEditteikei.setText(name);
=======
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_teikei_list, container, false);
        view.findViewById(R.id.imageBack).setOnClickListener(this);
>>>>>>> 96547077665a5edc861c09fdc33f2af3ef2c1500:app/src/main/java/jp/ac/chiba_fjb/x14b_d/maguro/TeikeiListFragment.java
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imageBack:
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.fullscreen_content,new TeikeiCreateFragment());
                ft.commitAllowingStateLoss();
                break;
        }

    }
}
