package jp.ac.chiba_fjb.x14b_d.maguro;


import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */

public class AlermFragment extends DialogFragment {
    TextView mTextView;


    public AlermFragment() {
        // Required empty public constructor
    }

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.setTitle("時間を設定してください");
        return dialog;

    }

//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.activity_fullscreen, container, false);
//        return view;
//        //インスタンスの取得
//        mTextView = (TextView)findViewById(R.id.Timer);
//        //カウンタのクリア
//        mCount = 600;
//        //タイマー処理の作成
//        TimerTask timerTask = new TimerTask() {
//            @Override
//            public void run() {
//                mCount--;
//                //UI関係の処理をサブスレッドで処理するとエラー
//                mTextView.setText(String.valueOf(mCount));
//                //mTextView.setText(""+mCount);
//            }
//        };
//        //タイマー開始処理
//        Timer timer = new Timer();
//        timer.schedule(timerTask,600); //1000ms後にイベントを発生させる
//
//    }
//

}
