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
public class TitleFragment extends Fragment implements View.OnClickListener, TeamOperation.OnTeamListener {


    private TextView mTextTeam;
    private String mUserName;
    private String mUserId;
    private String mTeamPass;
    private String mTeamId;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view =  inflater.inflate(R.layout.fragment_title, container, false);

        view.findViewById(R.id.imageTim).setOnClickListener(this);
        view.findViewById(R.id.imageS).setOnClickListener(this);
        view.findViewById(R.id.imageRena).setOnClickListener(this);
        view.findViewById(R.id.imageKaisan).setOnClickListener(this);

        //設定済みの名前を読み出す
        //設定済みの名前を読み出す
        AppDB db = new AppDB(getContext());
        mUserName = db.getSetting("USER_NAME", "");
        mUserId = db.getSetting("USER_ID", "");
        mTeamPass = db.getSetting("TEAM_PASS","");
        mTeamId = db.getSetting("TEAM_ID","");
        db.close();

        TextView textView = (TextView)view.findViewById(R.id.textUserName);
        textView.setText(mUserName);

        mTextTeam = (TextView)view.findViewById(R.id.textTeamName);
        if(mTeamId.length() > 0) {
            mTextTeam.setText("確認中");
            TeamOperation.joinTeam(Integer.parseInt(mTeamId),mTeamPass,Integer.parseInt(mUserId),mUserName,this);
        }

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imageS:
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.fullscreen_content,new CameraFragment());
                ft.commitAllowingStateLoss();
                break;
            case R.id.imageTim:
                FragmentTransaction ft2 = getFragmentManager().beginTransaction();
                ft2.replace(R.id.fullscreen_content,new TeamListFragment());
                ft2.commitAllowingStateLoss();
                break;
            case R.id.imageRena:
                FragmentTransaction ft3 = getFragmentManager().beginTransaction();
                ft3.replace(R.id.fullscreen_content,new TopFragment());
                ft3.commitAllowingStateLoss();
                break;
            case R.id.imageKaisan:
                mTextTeam.setText("離脱中");
                TeamOperation.removeTeam(Integer.parseInt(mTeamId), mTeamPass, Integer.parseInt(mUserId), new TeamOperation.OnTeamListener() {
                    @Override
                    public void onTeam(TeamOperation.RecvData recvData) {
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                mTextTeam.setText("---");
                                AppDB db = new AppDB(getContext());
                                db.setSetting("USER_ID", "");
                                db.setSetting("TEAM_ID", "");
                                db.setSetting("TEAM_PASS", "");
                                db.close();
                            }
                        });
                    }
                });
                break;
        }
    }

    @Override
    public void onTeam(final TeamOperation.RecvData recvData) {
        if(getActivity() == null)
            return;
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if(recvData!=null && recvData.result){
                    mTextTeam.setText(recvData.teamName);
                }
                else{
                    mTextTeam.setText("---");
                }
            }
        });

    }
}

