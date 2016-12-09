package jp.ac.chiba_fjb.x14b_d.maguro;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import jp.ac.chiba_fjb.x14b_d.maguro.Lib.TeamOperation;


class TeamAdapter extends ArrayAdapter<TeamOperation.TeamData> {
    public TeamAdapter(Context context) {
        super(context,R.layout.team_item);

    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
	    if (convertView == null) {
		    convertView = LayoutInflater.from(getContext()).inflate(R.layout.team_item, null);
	    }
		TeamOperation.TeamData value = getItem(position);
	    TextView textView = (TextView)convertView.findViewById(R.id.textTeamName);
	    textView.setText((String)value.teamName);
	    TextView textView2 = (TextView)convertView.findViewById(R.id.textMembers);
	    textView2.setText(String.valueOf(value.teamCount));
        return convertView;

    }
}

/**
 * A simple {@link Fragment} subclass.
 */
public class TeamListFragment extends Fragment implements View.OnClickListener, TeamOperation.OnTeamListener, AdapterView.OnItemClickListener {


    private ListView mTeamList;
	private TeamAdapter mAdapter;

	public TeamListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_team_list, container, false);

        view.findViewById(R.id.imageSakusei).setOnClickListener(this);
        view.findViewById(R.id.imageBack).setOnClickListener(this);
	    view.findViewById(R.id.imageUpdate).setOnClickListener(this);

	    mAdapter = new TeamAdapter(getContext());
        mTeamList = (ListView)view.findViewById(R.id.listTeam);
	    mTeamList.setAdapter(mAdapter);
	    mTeamList.setOnItemClickListener(this);

        return view;
    }

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		update();
	}

	@Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imageSakusei:
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.fullscreen_content,new TeamCreateFragment());
                ft.commitAllowingStateLoss();
                break;
            case R.id.imageBack:
                FragmentTransaction ft3 = getFragmentManager().beginTransaction();
                ft3.replace(R.id.fullscreen_content,new TitleFragment());
                ft3.commitAllowingStateLoss();
                break;
	        case R.id.imageUpdate:
		        update();
		        break;
        }
    }
    void update(){
	    ((TextView)getView().findViewById(R.id.textUpdate)).setText("リスト更新中");
        TeamOperation.getTeam(this);
    }

    @Override
    public void onTeam(final TeamOperation.RecvData recvData) {

	    if( getActivity() != null) {
		    getActivity().runOnUiThread(new Runnable() {
			    @Override
			    public void run() {
				    if (recvData != null && recvData.values != null && getActivity() != null) {
					    mAdapter.clear();
					    ((TextView) getView().findViewById(R.id.textUpdate)).setText(recvData.values.length + "件");
					    for (TeamOperation.TeamData value : recvData.values) {
						    mAdapter.add(value);
					    }
					    mAdapter.notifyDataSetChanged();
				    } else {
					    ((TextView) getView().findViewById(R.id.textUpdate)).setText("更新失敗");
				    }


			    }
		    });
	    }

    }

	@Override
	public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
		TeamOperation.TeamData value = mAdapter.getItem(i);
		Bundle bundle = new Bundle();
		bundle.putString("teamName",value.teamName);

		Fragment f = new TeamJoinFragment();
		f.setArguments(bundle);

		FragmentTransaction ft2 = getFragmentManager().beginTransaction();
		ft2.replace(R.id.fullscreen_content,f);
		ft2.commitAllowingStateLoss();
	}
}
