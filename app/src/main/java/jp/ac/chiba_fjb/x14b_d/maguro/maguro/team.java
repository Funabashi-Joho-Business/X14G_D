package jp.ac.chiba_fjb.x14b_d.maguro.maguro;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import jp.ac.chiba_fjb.x14b_d.maguro.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class team extends Fragment {


    public team() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_team, container, false);
        return view;
    }

}
