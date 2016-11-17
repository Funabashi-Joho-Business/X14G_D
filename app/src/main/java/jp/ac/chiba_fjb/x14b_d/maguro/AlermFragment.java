package jp.ac.chiba_fjb.x14b_d.maguro;


import android.app.Dialog;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;


/**
 * A simple {@link Fragment} subclass.
 */

public class AlermFragment extends DialogFragment {
    Timer mTimer;
    public int mCount;
    TextView mTextView;

    public AlermFragment() {
        // Required empty public constructor
    }

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.setTitle("時間を設定してください");
        return dialog;

    }}

//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.activity_fullscreen, container, false);
//        return view;
//        Handler  mHandler;
//
//        if(mTimer == null){
//            TimerTask timerTask = new TimerTask() {
//                @Override
//                public void run() {
//                    mCount--;
//                    //UI関係の処理をサブスレッドで処理するとエラー
//                    mHandler.post(new Runnable() {
//                        @Override
//                        public void run() {
//                            mTextView.setText(String.valueOf(mCount));
//                        }
//                    });
//                }
//            };
//            //タイマーの起動
//            mTimer = new Timer();
//            mTimer.schedule(timerTask,0,0);
//        }
//
//
//}}
