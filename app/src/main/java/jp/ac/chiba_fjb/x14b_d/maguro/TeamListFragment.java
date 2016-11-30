package jp.ac.chiba_fjb.x14b_d.maguro;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
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


class TeamAdapter extends ArrayAdapter<Object[]> {
    public TeamAdapter(Context context) {
        super(context,R.layout.team_item);

    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
	    if (convertView == null) {
		    convertView = LayoutInflater.from(getContext()).inflate(R.layout.team_item, null);
	    }
		Object[] value = getItem(position);
	    TextView textView = (TextView)convertView.findViewById(R.id.textTeamName);
	    textView.setText((String)value[2]);
	    TextView textView2 = (TextView)convertView.findViewById(R.id.textMembers);
	    textView2.setText(String.valueOf(value[3]));
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
        view.findViewById(R.id.imageSetuzoku).setOnClickListener(this);
        view.findViewById(R.id.imageBack).setOnClickListener(this);

	    mAdapter = new TeamAdapter(getContext());
        mTeamList = (ListView)view.findViewById(R.id.listTeam);
	    mTeamList.setAdapter(mAdapter);
	    mTeamList.setOnItemClickListener(this);

        update();

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imageSakusei:
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.fullscreen_content,new TeamCreateFragment());
                ft.commitAllowingStateLoss();
                break;
            case R.id.imageSetuzoku:
                FragmentTransaction ft2 = getFragmentManager().beginTransaction();
                ft2.replace(R.id.fullscreen_content,new TeamJoinFragment());
                ft2.commitAllowingStateLoss();
                break;
            case R.id.imageBack:
                FragmentTransaction ft3 = getFragmentManager().beginTransaction();
                ft3.replace(R.id.fullscreen_content,new TitleFragment());
                ft3.commitAllowingStateLoss();
                break;
        }
    }
    void update(){
        TeamOperation.getTeam(this);
    }

    @Override
    public void onTeam(final TeamOperation.RecvData recvData) {
        if(recvData!=null && recvData.values!=null && getActivity()!=null){
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
	                mAdapter.clear();
                    for(Object[] value : recvData.values){
	                    mAdapter.add(value);
                    }
	                mAdapter.notifyDataSetChanged();
                }
            });

        }
    }

	@Override
	public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
		Object[] value = mAdapter.getItem(i);
		Bundle bundle = new Bundle();
		bundle.putInt("teamId",(int)value[0]);
		bundle.putString("teamName",(String)value[2]);

		Fragment f = new TeamJoinFragment();
		f.setArguments(bundle);

		FragmentTransaction ft2 = getFragmentManager().beginTransaction();
		ft2.replace(R.id.fullscreen_content,f);
		ft2.commitAllowingStateLoss();
	}
}
