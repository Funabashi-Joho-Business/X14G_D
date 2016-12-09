package jp.ac.chiba_fjb.x14b_d.maguro;


import android.os.Bundle;
import android.support.design.widget.Snackbar;
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
    private int mUserId;
    private String mTeamPass;
    private String mTeamName;
    private String mUserPass;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view =  inflater.inflate(R.layout.fragment_title, container, false);

        view.findViewById(R.id.imageTim).setOnClickListener(this);
        view.findViewById(R.id.imageS).setOnClickListener(this);
        view.findViewById(R.id.imageRena).setOnClickListener(this);
        view.findViewById(R.id.imageKaisan).setOnClickListener(this);
        view.findViewById(R.id.teikeibun).setOnClickListener(this);
        //設定済みの名前を読み出す
        AppDB db = new AppDB(getContext());
        mUserId = db.getSetting("USER_ID", 0);
        mUserName = db.getSetting("USER_NAME", "");
        mUserPass = db.getSetting("USER_PASS", "");
        mTeamName = db.getSetting("TEAM_NAME","");
        mTeamPass = db.getSetting("TEAM_PASS","");
        db.close();

        TextView textView = (TextView)view.findViewById(R.id.textUserName);
        textView.setText(mUserName);

        mTextTeam = (TextView)view.findViewById(R.id.textTeamName);
        mTextTeam.setText("---");
        if(mTeamName.length() > 0) {
            TeamOperation.joinTeam(mTeamName,mTeamPass,mUserId,mUserName,mUserPass,0,0,this);
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
                Snackbar.make(getView(), "チーム離脱中", Snackbar.LENGTH_SHORT).show();
                TeamOperation.removeTeam(mUserId,mUserPass, new TeamOperation.OnTeamListener() {
                    @Override
                    public void onTeam(final TeamOperation.RecvData recvData) {
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if(recvData!=null && recvData.result) {
                                    Snackbar.make(getView(), "チーム離脱成功", Snackbar.LENGTH_SHORT).show();
                                    mTextTeam.setText("---");
                                    AppDB db = new AppDB(getContext());
                                    db.setSetting("USER_ID", 0);
                                    db.setSetting("TEAM_NAME", "");
                                    db.setSetting("TEAM_PASS", "");
                                    db.close();
                                    mUserId = 0;
                                }
                                else
                                    Snackbar.make(getView(), "チーム離脱失敗", Snackbar.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
                break;
            case R.id.teikeibun:
                FragmentTransaction ft4 = getFragmentManager().beginTransaction();
                ft4.replace(R.id.fullscreen_content,new TeikeiCreateFragment());
                ft4.commitAllowingStateLoss();
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
                    mTextTeam.setText(mTeamName);
                    AppDB db = new AppDB(getContext());
                    db.setSetting("USER_ID", recvData.userId);
                    db.setSetting("USER_PASS", recvData.userPass );
                    db.close();
                }
                else{
                    mTextTeam.setText("---");
                }
            }
        });

    }
}

