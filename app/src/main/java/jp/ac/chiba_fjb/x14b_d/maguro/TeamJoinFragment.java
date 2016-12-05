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
    private String mTeamName;
    private String mUserPass;

    public TeamJoinFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.fragment_join, container, false);
        Bundle bundle = getArguments();
        mTeamName = bundle.getString("teamName");
        mEditPass = (EditText)view.findViewById(R.id.editPass);
        view.findViewById(R.id.imageSetuzoku).setOnClickListener(this);
        view.findViewById(R.id.imageBack).setOnClickListener(this);

        AppDB db = new AppDB(getContext());
        mUserName = db.getSetting("USER_NAME","");
        mUserId = db.getSetting("USER_ID",0);
        mUserPass = db.getSetting("USER_PASS","");
        db.close();



        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imageSetuzoku:
                Snackbar.make(getView(), "チーム参加中", Snackbar.LENGTH_SHORT).show();
                TeamOperation.joinTeam(mTeamName,mEditPass.getText().toString(),mUserId,mUserName,mUserPass,0,0,this);

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
            db.setSetting("TEAM_NAME",mTeamName);
            db.setSetting("USER_ID",recvData.userId);
            db.setSetting("USER_PASS",recvData.userPass);
            db.setSetting("TEAM_PASS",mEditPass.getText().toString());
            db.close();

            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.fullscreen_content,new OkJoinFragment());
            ft.commitAllowingStateLoss();


        }else{
            Snackbar.make(getView(), "参加失敗", Snackbar.LENGTH_SHORT).show();
        }

    }
}
