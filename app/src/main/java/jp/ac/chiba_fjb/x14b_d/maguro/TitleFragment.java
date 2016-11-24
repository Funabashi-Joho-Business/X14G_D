package jp.ac.chiba_fjb.x14b_d.maguro;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A simple {@link Fragment} subclass.
 */
public class TitleFragment extends Fragment implements View.OnClickListener {

<<<<<<< HEAD
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view =  inflater.inflate(R.layout.activity_fullscreen, container, false);

        view.findViewById(R.id.imageTim).setOnClickListener(this);
        view.findViewById(R.id.imageS).setOnClickListener(this);
        view.findViewById(R.id.imagescop).setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
//            case R.id.imageS:
//                FragmentTransaction ft = getActivity().beginTransaction();
//                ft.replace(R.id.fullscreen_content,new CameraFragment());
//                ft.commitAllowingStateLoss();
//                break;
//            case R.id.imageTim:
//                break;
=======
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_title, container, false);

        view.findViewById(R.id.imageTim).setOnClickListener(this);
        view.findViewById(R.id.imageS).setOnClickListener(this);
        return view;
    }

>>>>>>> c4a9a93f0b3c626b6122bcb7e278a61c4df47789

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
                        ft2.replace(R.id.fullscreen_content,new TeamFragment());
                        ft2.commitAllowingStateLoss();
                        break;
        }
    }
}
