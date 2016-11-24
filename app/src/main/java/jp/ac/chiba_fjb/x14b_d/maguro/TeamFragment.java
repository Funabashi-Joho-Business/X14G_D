package jp.ac.chiba_fjb.x14b_d.maguro;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class TeamFragment extends Fragment implements View.OnClickListener {


    public TeamFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_team, container, false);

        view.findViewById(R.id.imageSakusei).setOnClickListener(this);
        view.findViewById(R.id.imageSetuzoku).setOnClickListener(this);
        view.findViewById(R.id.imageBack).setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imageSakusei:
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.fullscreen_content,new team3());
                ft.commitAllowingStateLoss();
                break;
            case R.id.imageSetuzoku:
                FragmentTransaction ft2 = getFragmentManager().beginTransaction();
                ft2.replace(R.id.fullscreen_content,new team2());
                ft2.commitAllowingStateLoss();
                break;
            case R.id.imageBack:
                FragmentTransaction ft3 = getFragmentManager().beginTransaction();
                ft3.replace(R.id.fullscreen_content,new TitleFragment());
                ft3.commitAllowingStateLoss();
                break;
        }
    }

}
