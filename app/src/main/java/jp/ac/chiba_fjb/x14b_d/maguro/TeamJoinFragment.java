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
public class TeamJoinFragment extends Fragment implements View.OnClickListener {


    public TeamJoinFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.fragment_join, container, false);

        view.findViewById(R.id.editPass);
        view.findViewById(R.id.imageSetuzoku).setOnClickListener(this);
        view.findViewById(R.id.imageBack).setOnClickListener(this);


        Bundle bundle = getArguments();
        if(bundle != null){
            System.out.println(bundle.getInt("teamId"));
            System.out.println(bundle.getString("teamName"));
        }


        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imageSetuzoku:
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.fullscreen_content,new OkJoinFragment());
                ft.commitAllowingStateLoss();
                break;
            case R.id.imageBack:
                FragmentTransaction ft2 = getFragmentManager().beginTransaction();
                ft2.replace(R.id.fullscreen_content,new TeamListFragment());
                ft2.commitAllowingStateLoss();
                break;
        }
    }
}
