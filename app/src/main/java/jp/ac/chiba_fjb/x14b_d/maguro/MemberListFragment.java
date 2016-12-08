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
public class MemberListFragment extends Fragment implements View.OnClickListener, TeamOperation.OnTeamListener {

    private String mTeamName;
    private TextView mTextTeamname;
    private String mUserPass;
    private String mTeamPass;
    private TextView mTextList;

    public MemberListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_list, container, false);

        view.findViewById(R.id.imageBack).setOnClickListener(this);
        AppDB db = new AppDB(getContext());
        mTeamName = db.getSetting("TEAM_NAME","");
        mTeamPass = db.getSetting("TEAM_PASS","");
        db.close();

        mTextTeamname = (TextView)view.findViewById(R.id.TextTeamname);
        if(mTeamName.length() > 0) {
            mTextTeamname.setText(mTeamName);
            TeamOperation.getMember(mTeamName,mTeamPass,this);
        }

        mTextList = (TextView)view.findViewById(R.id.textList);
        return view;
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imageBack:
                FragmentTransaction ft2 = getFragmentManager().beginTransaction();
                ft2.replace(R.id.fullscreen_content,new CameraFragment());
                ft2.commitAllowingStateLoss();
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
                TeamOperation.TeamData[] list = recvData.values;
                StringBuilder sb = new StringBuilder();
                for (TeamOperation.UserData m : recvData.members) {
                    String msg = String.format("%s\n", m.userName);
                    sb.append(msg);
                }
                mTextList.setText(sb.toString());
            }
        });
    }
}
