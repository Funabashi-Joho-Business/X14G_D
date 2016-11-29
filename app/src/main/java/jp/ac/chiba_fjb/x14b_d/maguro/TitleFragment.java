package jp.ac.chiba_fjb.x14b_d.maguro;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A simple {@link Fragment} subclass.
 */
public class TitleFragment extends Fragment implements View.OnClickListener {


        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view =  inflater.inflate(R.layout.fragment_title, container, false);

        view.findViewById(R.id.imageTim).setOnClickListener(this);
        view.findViewById(R.id.imageS).setOnClickListener(this);
        view.findViewById(R.id.imageRename).setOnClickListener(this);
        view.findViewById(R.id.imageKaisan).setOnClickListener(this);
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
                    case R.id.imageRename:
                        FragmentTransaction ft3 = getFragmentManager().beginTransaction();
                        ft3.replace(R.id.fullscreen_content,new top());
                        ft3.commitAllowingStateLoss();
                        break;
                    case R.id.imageKaisan:
                        FragmentTransaction ft4 = getFragmentManager().beginTransaction();
                        ft4.replace(R.id.fullscreen_content,new TitleFragment());
                        ft4.commitAllowingStateLoss();
                        break;
        }
    }
}

