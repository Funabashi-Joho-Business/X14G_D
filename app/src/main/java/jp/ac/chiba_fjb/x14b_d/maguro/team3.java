package jp.ac.chiba_fjb.x14b_d.maguro;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class team3 extends Fragment implements View.OnClickListener {


    public team3() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_team3, container, false);
        view.findViewById(R.id.imageB).setOnClickListener(this);
    return view;
    }

    @Override
    public void onClick(View view) {

    }
}
