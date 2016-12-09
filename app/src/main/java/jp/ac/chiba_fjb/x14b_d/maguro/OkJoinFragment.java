package jp.ac.chiba_fjb.x14b_d.maguro;


import android.os.Bundle;

import android.os.Handler;
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

//    Handler mHandler = new Handler();
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
        Bundle bundle = getArguments();
        mTeamName = bundle.getString("teamName2");
        view.findViewById(R.id.imageBack).setOnClickListener(this);

        AppDB db = new AppDB(getContext());
        db.close();

        mTextTeamname = (TextView)view.findViewById(R.id.textTeamname);
//        setText(mTeamName);
        if(mTeamName.length() > 0) {
            TeamOperation.joinTeam(mTeamName,"0",0,"0","0",0,0,this);
        }

    return view;
    }

//    void setText(final String TeamName){
//        mHandler.post(new Runnable() {
//            @Override
//            public void run() {
//                mTextTeamname.setText(TeamName);
//            }
//        });
//    }

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
