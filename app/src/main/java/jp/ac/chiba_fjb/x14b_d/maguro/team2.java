package jp.ac.chiba_fjb.x14b_d.maguro;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by x14g010 on 2016/11/18.
 */

public class team2 extends Fragment implements View.OnClickListener {


    public team2() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_team2, container, false);


        view.findViewById(R.id.imagesetu).setOnClickListener(this);
    return view;
    }

    @Override
    public void onClick(View view) {

    }
    //テキストビューに他のＩＤを表示　ＩＤを元に接続する
}