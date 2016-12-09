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
public class TeikeiListFragment extends Fragment implements View.OnClickListener {


    private EditText mEditteikei;
    public TeikeiListFragment() {

        // Required empty public constructor
    }


    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        AppDB db = new AppDB(getContext());
        String name = db.getSetting("TEIKEI","");
        db.close();

        View view = inflater.inflate(R.layout.fragment_teikei_list, container, false);
        view.findViewById(R.id.itiran).setOnClickListener(this);
        mEditteikei = (EditText)view.findViewById(R.id.itiran);
        mEditteikei.setText(name);
        view.findViewById(R.id.imageBack).setOnClickListener(this);

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
