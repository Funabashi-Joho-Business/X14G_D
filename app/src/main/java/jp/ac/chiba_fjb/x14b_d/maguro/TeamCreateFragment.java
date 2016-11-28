package jp.ac.chiba_fjb.x14b_d.maguro;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;


public class TeamCreateFragment extends Fragment implements View.OnClickListener, TeamOperation.OnCreateListener {

    private EditText mEditName;
    private EditText mEditPass;

    public TeamCreateFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_team_create, container, false);

        mEditName = (EditText)view.findViewById(R.id.editName);
        mEditPass = (EditText)view.findViewById(R.id.editPass);
        view.findViewById(R.id.imageSetuzoku).setOnClickListener(this);
        view.findViewById(R.id.imageBack).setOnClickListener(this);


        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imageSetuzoku:
                Snackbar.make(getView(), "チーム作成", Snackbar.LENGTH_SHORT).show();
                TeamOperation.createTeam(mEditName.getText().toString(),mEditPass.getText().toString(),this);
                break;
            case R.id.imageBack:
                FragmentTransaction ft2 = getFragmentManager().beginTransaction();
                ft2.replace(R.id.fullscreen_content,new TeamListFragment());
                ft2.commitAllowingStateLoss();
                break;
        }
    }

    @Override
    public void onTeamCreated(final boolean flag) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if(flag) {
                    Snackbar.make(getView(), "チーム作成完了", Snackbar.LENGTH_SHORT).show();
                    FragmentTransaction ft = getFragmentManager().beginTransaction();
                    ft.replace(R.id.fullscreen_content,new TeamListFragment());
                    ft.commitAllowingStateLoss();
                }
                else
                    Snackbar.make(getView(), "チーム作成失敗", Snackbar.LENGTH_SHORT).show();
            }
        });


    }

    //テキストビューに他のＩＤを表示　ＩＤを元に接続する
}
