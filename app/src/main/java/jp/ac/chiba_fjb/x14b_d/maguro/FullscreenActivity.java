package jp.ac.chiba_fjb.x14b_d.maguro;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.PowerManager;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Timer;
import java.util.TimerTask;

import jp.ac.chiba_fjb.x14b_d.maguro.Lib.AppDB;
import jp.ac.chiba_fjb.x14b_d.maguro.Lib.MyLocationSource;
import jp.ac.chiba_fjb.x14b_d.maguro.Lib.Permission;
import jp.ac.chiba_fjb.x14b_d.maguro.Lib.TeamOperation;


/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class FullscreenActivity extends AppCompatActivity implements MyLocationSource.OnLocationListener, TeamOperation.OnTeamListener {
    private MyLocationSource mLocationSource;
    private Permission mPermission;
    private PowerManager.WakeLock mLock;
    private Location mLocation;
    private Timer mTimer;
    int timer;
    int timerOk;
    private TextView mTextTimer;
    Handler mHandler = new Handler();
    public CountDownTimer cdt;


    public void onOkClicked(int i) {
        mTextTimer = (TextView)findViewById(R.id.textView6);
        if(i != 0) {
            if(i != -1) {
                if(timerOk == 1) {
                    cdt.cancel();
                }
                timerOk = 1;
                timer = i;
                timer = timer * 60000;
                cdt = new CountDownTimer(timer, 1000) {
                    public void onTick(long millisUntilFinished) {
                        setText(Long.toString(millisUntilFinished));
                    }
                    public void onFinish() {
                        mTextTimer.setText("終了");
                    }
                }.start();

            }else{
                timerOk = 0;
                cdt.cancel();
                mTextTimer.setText("");
            }
        }else{
            timerOk = 0;
        }
    }

    void setText(final String number){
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                int i = Integer.parseInt(number);
                int mm = i / 1000 / 60;
                int ss = i / 1000 % 60;
                mTextTimer.setText(String.format("%1$02d:%2$02d", mm, ss));
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullscreen);

        mPermission = new Permission();
        mPermission.setOnResultListener(new Permission.ResultListener() {
            @Override
            public void onResult() {
                //設定済みの名前を読み出す
                AppDB db = new AppDB(FullscreenActivity.this);
                String name = db.getSetting("USER_NAME","");
                db.close();

                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                if(name.length() == 0)
                    ft.replace(R.id.fullscreen_content,new TopFragment());
                else
                    ft.replace(R.id.fullscreen_content,new TitleFragment());
                ft.commitAllowingStateLoss();
            }
        });
        mPermission.requestPermissions(this);
        mLocationSource = new MyLocationSource(this);
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
        mLocationSource.setOnLocationListener(this);

        mTimer = new Timer();
        mTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                AppDB db = new AppDB(FullscreenActivity.this);
                String teamName = db.getSetting("TEAM_NAME","");
                String teamPass = db.getSetting("TEAM_PASS","");
                String userName = db.getSetting("USER_NAME","");
                int userId = db.getSetting("USER_ID",0);
                String userPass = db.getSetting("USER_PASS","");
                db.close();

                if(teamName.length()>0 && teamPass.length()>0 && userId>0 && userPass.length()>0){
                    double x = 0.0;
                    double y = 0.0;
                    if(mLocation != null) {
                        x = mLocation.getLongitude();
                        y = mLocation.getLatitude();
                    }

                    TeamOperation.joinTeam(teamName,teamPass,userId,userName,userPass,x,y,FullscreenActivity.this);
                }

            }
        },0,30*1000);

    }

    @Override
    protected void onPause() {
        mTimer.cancel();
        mLocationSource.setOnLocationListener(null);
        //常時ONを戻す
        mLock.release();
        super.onPause();
    }


    @Override
    public void onLocation(Location location) {
        mLocation = location;
    }

    @Override
    public void onTeam(TeamOperation.RecvData recvData) {
        if(recvData != null && recvData.result) {
            AppDB db = new AppDB(this);
            db.setSetting("USER_PASS", recvData.userPass);
            db.close();
        }
    }
}
