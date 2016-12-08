package jp.ac.chiba_fjb.x14b_d.maguro;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import jp.ac.chiba_fjb.x14b_d.maguro.Lib.AppDB;
import jp.ac.chiba_fjb.x14b_d.maguro.Lib.TeamOperation;


/**
 * A simple {@link Fragment} subclass.
 */
public class OkJoinFragment extends Fragment implements View.OnClickListener, TeamOperation.OnTeamListener {

    private String mTeamName;
    private TextView mTextTeamname;


    public OkJoinFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_team4, container, false);
        view.findViewById(R.id.imageBack).setOnClickListener(this);

        AppDB db = new AppDB(getContext());
        mTeamName = db.getSetting("TEAM_NAME","");
        db.close();

        mTextTeamname = (TextView)view.findViewById(R.id.textTeamname);
        if(mTeamName.length() > 0) {
            TeamOperation.joinTeam(mTeamName,"0",0,"0","0",0,0,this);
        }

    return view;
    }

    @Override
    public void onClick(View view) {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.fullscreen_content,new TitleFragment());
        ft.commitAllowingStateLoss();
    }

    @Override
    public void onTeam(final TeamOperation.RecvData recvData) {
        if(getActivity() == null)
            return;
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if(mTeamName!=null){
                    mTextTeamname.setText(mTeamName);
                }
                else{
                    mTextTeamname.setText("---");
                }
            }
        });
    }
}
