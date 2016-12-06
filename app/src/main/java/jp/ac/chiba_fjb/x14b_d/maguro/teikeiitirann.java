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
public class teikeiitirann extends Fragment implements View.OnClickListener {


    public teikeiitirann() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_teikeiitiran, container, false);
        view.findViewById(R.id.imageBack).setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imageBack:
                if(mLayoutNormal.getVisibility() == View.GONE){
                    mLayoutNormal.setVisibility(View.VISIBLE);
                    mLayoutPosition.setVisibility(View.GONE);
                }else{
                    FragmentTransaction ft = getFragmentManager().beginTransaction();
                    ft.replace(R.id.fullscreen_content,new TitleFragment());
                    ft.commit();
                }


        }
    }
}
