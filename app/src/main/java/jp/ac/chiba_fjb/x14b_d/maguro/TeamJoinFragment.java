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


/**
 * A simple {@link Fragment} subclass.
 */
public class TeamJoinFragment extends Fragment implements View.OnClickListener, TeamOperation.OnTeamListener {


    private EditText mEditPass;
    private int mTeamId;
    private String mUserName;
    private int mUserId;

    public TeamJoinFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.fragment_join, container, false);

        mEditPass = (EditText)view.findViewById(R.id.editPass);
        view.findViewById(R.id.imageSetuzoku).setOnClickListener(this);
        view.findViewById(R.id.imageBack).setOnClickListener(this);

        AppDB db = new AppDB(getContext());
        mUserName = db.getSetting("USER_NAME","");
        mUserId = db.getSetting("USER_ID",0);
        db.close();



        Bundle bundle = getArguments();
        if(bundle != null){
            mTeamId = bundle.getInt("teamId");
            System.out.println(mTeamId);
            System.out.println(bundle.getString("teamName"));
        }


        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imageSetuzoku:
                TeamOperation.joinTeam(mTeamId,mEditPass.getText().toString(),mUserId,mUserName,this);
                break;
            case R.id.imageBack:
                FragmentTransaction ft2 = getFragmentManager().beginTransaction();
                ft2.replace(R.id.fullscreen_content,new TeamListFragment());
                ft2.commitAllowingStateLoss();
                break;
        }
    }

    @Override
    public void onTeam(TeamOperation.RecvData recvData) {
        if(recvData != null && recvData.result){
            AppDB db = new AppDB(getContext());
            db.setSetting("TEAM_ID",recvData.teamId);
            db.setSetting("USER_ID",recvData.userId);
            db.setSetting("TEAM_PASS",mEditPass.getText().toString());
            db.close();

            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.fullscreen_content,new team4());
            ft.commitAllowingStateLoss();


        }else{
            Snackbar.make(getView(), "加入失敗", Snackbar.LENGTH_SHORT).show();
        }

    }
}
