package jp.ac.chiba_fjb.x14b_d.maguro;

import android.content.Context;
import android.os.Bundle;
import android.os.PowerManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;


/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class FullscreenActivity extends AppCompatActivity {

    private Permission mPermission;
    private PowerManager.WakeLock mLock;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullscreen);





        mPermission = new Permission();
        mPermission.setOnResultListener(new Permission.ResultListener() {
            @Override
            public void onResult() {
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.fullscreen_content,new TitleFragment());
                ft.commitAllowingStateLoss();
            }
        });
        mPermission.requestPermissions(this);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        mPermission.onRequestPermissionsResult(requestCode,permissions,grantResults);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //画面を常時ON
        PowerManager pm = (PowerManager)getSystemService(Context.POWER_SERVICE);
        mLock = pm.newWakeLock(PowerManager.SCREEN_BRIGHT_WAKE_LOCK, "My tag");
        mLock.acquire();

    }

    @Override
    protected void onPause() {
        //常時ONを戻す
        mLock.release();
        super.onPause();
    }
}
