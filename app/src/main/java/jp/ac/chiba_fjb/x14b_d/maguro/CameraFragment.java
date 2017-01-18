package jp.ac.chiba_fjb.x14b_d.maguro;

import android.location.Location;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import jp.ac.chiba_fjb.x14b_d.maguro.Lib.AppDB;
import jp.ac.chiba_fjb.x14b_d.maguro.Lib.Compass;
import jp.ac.chiba_fjb.x14b_d.maguro.Lib.TeamOperation;
import jp.ac.chiba_fjb.x14b_d.maguro.Lib.TeikeiOperation;

import static android.R.attr.x;
import static android.R.attr.y;
import static android.content.pm.ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;
import static android.content.pm.ActivityInfo.SCREEN_ORIENTATION_REVERSE_LANDSCAPE;


/**
 * A simple {@link Fragment} subclass.
 */
public class CameraFragment extends Fragment implements View.OnClickListener, TeamOperation.OnTeamListener, Compass.OnSensorListener, TeikeiOperation.OnTeikeiListener {

    private  TextView mTextChat;
    private CameraPreview mCamera;
    private int mVisibilty;
    private View mLayoutPosition;
    private View mLayoutNormal;
    private float mPosX = 0.0f;
    private float mPosY = 0.0f;
    private float mScale = 1.0f;
    private Timer mTimer;
    private String mTeamName;
    private String mTeamPass;
    private Compass mCompass;

    private CompassView mImageCompass;
    Handler mHandler = new Handler();



    public CameraFragment() {
        mCamera = new CameraPreview();
    }
    public boolean mRotation = true;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_camera, container, false);
        TextureView textureView = (TextureView) view.findViewById(R.id.textureView);
        mCamera.setTextureView(textureView);

        mTextChat = (TextView)view.findViewById(R.id.textChat);
        view.findViewById(R.id.imageZoomin).setOnClickListener(this);
        view.findViewById(R.id.imageZoomout).setOnClickListener(this);
        view.findViewById(R.id.imageBack).setOnClickListener(this);
        view.findViewById(R.id.imageREC).setOnClickListener(this);
        view.findViewById(R.id.imageTimer).setOnClickListener(this);
        view.findViewById(R.id.imageMember).setOnClickListener(this);
        view.findViewById(R.id.imageZeroin).setOnClickListener(this);
        view.findViewById(R.id.imageChat).setOnClickListener(this);
        mLayoutNormal = view.findViewById(R.id.layoutNormal);
        mLayoutPosition = inflater.inflate(R.layout.fragment_zeroin, container, false);
        mLayoutPosition.findViewById(R.id.imageTateUp).setOnClickListener(this);
        mLayoutPosition.findViewById(R.id.imageTateDown).setOnClickListener(this);
        mLayoutPosition.findViewById(R.id.imageYokoUp).setOnClickListener(this);
        mLayoutPosition.findViewById(R.id.imageYokoDown).setOnClickListener(this);
        mLayoutPosition.findViewById(R.id.imageBack).setOnClickListener(this);
        mLayoutPosition.findViewById(R.id.imageriv).setOnClickListener(this);
        ((FrameLayout)view.findViewById(R.id.frameCamera)).addView(mLayoutPosition);
        mLayoutPosition.setVisibility(View.GONE);
        mTextChat.setOnClickListener(this);

        mImageCompass = (CompassView) view.findViewById(R.id.imageCompas);
        mCompass = new Compass(getActivity(),this);

        AppDB db = new AppDB(getContext());
        mTeamName = db.getSetting("TEAM_NAME","");
        mTeamPass = db.getSetting("TEAM_PASS","");
        db.close();

//        if(mTeamName.length() > 0) {

        recvTeikei(mTeamName,mTeamPass);
//            TeikeiOperation.getTeikei(mTeamName,mTeamPass,this);
//        }


        return view;

    }

    public void recvTeikei(String teamName, String teamPass){
        TeikeiOperation.getTeikei(teamName,teamPass,this);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //タイマー処理
        mTimer = new Timer(true);
        mTimer.schedule(new TimerTask(){
            @Override
            public void run() {
                mHandler.post( new Runnable() {
                    public void run() {
                        recvTeikei(mTeamName,mTeamPass);
                    }
                });
            }
        },1000,3000); //1秒後から3秒間隔で実行
    }

    @Override
    public void onTeikei(TeikeiOperation.RecvData recvData) {
        if (recvData != null && recvData.result) {
            setText(recvData.rUserName + ":" + recvData.rChat);
        } else
            setText("受信失敗");
    }

    public void setText(final String chat){
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                mTextChat.setText(chat);
            }
        });
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mVisibilty = view.getSystemUiVisibility();
        view.setSystemUiVisibility(
            View.SYSTEM_UI_FLAG_LOW_PROFILE
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
        );
        view.requestFocus();
        mCamera.setScale(1.1f);
    }


    @Override
    public void onDestroyView() {
        getView().setSystemUiVisibility(mVisibilty);
        super.onDestroyView();
    }



    @Override
    public void onResume() {
        super.onResume();
        mCamera.open(0);
        mCamera.startPreview();

        mTimer = new Timer();
        mTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                AppDB db = new AppDB(getContext());
                String teamName = db.getSetting("TEAM_NAME","");
                String teamPass = db.getSetting("TEAM_PASS","");
                db.close();
                if(teamName.length()>0 && teamPass.length()>0)
                    TeamOperation.getMember(teamName,teamPass,CameraFragment.this);
            }
        },0,30*1000);
        mCompass.start();
    }

    @Override
    public void onPause() {
        mCompass.stop();
        mTimer.cancel();
        mCamera.close();
        super.onPause();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imageZoomin:
                mScale += 0.1f;
                mCamera.setScale(mScale);
                break;
            case R.id.imageZoomout:
                mScale -= 0.1f;
                if(mScale < 1.0f)
                    mScale = 1.0f;
                mCamera.setScale(mScale);
                break;
            case R.id.imageBack:
                if(mLayoutNormal.getVisibility() == View.GONE){
                    mLayoutNormal.setVisibility(View.VISIBLE);
                    mLayoutPosition.setVisibility(View.GONE);
                }else{
                    FragmentTransaction ft = getFragmentManager().beginTransaction();
                    ft.replace(R.id.fullscreen_content,new TitleFragment());
                    ft.commit();
                }
                break;
            case R.id.imageREC:
                if(!mCamera.isRecording()) {
                    Toast.makeText(getContext(), "録画開始", Toast.LENGTH_SHORT).show();
                    Date date = new Date();
                    SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String fileName = String.format("%s/%s.mp4",Environment.getExternalStorageDirectory().toString(),sdf1.format(date));
                    mCamera.startRecording(fileName);
                }
                else {
                    Toast.makeText(getContext(), "録画停止", Toast.LENGTH_SHORT).show();
                    mCamera.stopRecording();
                }
                break;
            case R.id.imageTimer:

                FragmentTransaction ft2 = getFragmentManager().beginTransaction();
                ft2.replace(R.id.fullscreen_content,new AlermFragment());
                ft2.commit();

                break;


            case R.id.imageMember:
                FragmentTransaction ft4 = getFragmentManager().beginTransaction();
                ft4.replace(R.id.fullscreen_content,new MemberListFragment());
                ft4.commitAllowingStateLoss();
                break;
            case R.id.imageZeroin:
                mLayoutNormal.setVisibility(View.GONE);
                mLayoutPosition.setVisibility(View.VISIBLE);
                break;

            case R.id.imageTateUp:
                mPosY -= 3;
                mCamera.setPosition(mPosX,mPosY);
                break;
            case R.id.imageTateDown:
                mPosY += 3;
                mCamera.setPosition(mPosX,mPosY);
                break;

            case R.id.imageYokoUp:
                mPosX -= 3;
                mCamera.setPosition(mPosX,mPosY);
                break;
            case R.id.imageYokoDown:
                mPosX += 3;
                mCamera.setPosition(mPosX,mPosY);
                break;
            case R.id.imageriv:
                if(mRotation) {
                    getActivity().setRequestedOrientation(SCREEN_ORIENTATION_REVERSE_LANDSCAPE);
                    mCamera.setRotation(180.0f);
                }
                else {
                    getActivity().setRequestedOrientation(SCREEN_ORIENTATION_LANDSCAPE);
                    mCamera.setRotation(0.0f);
                }
                mRotation = !mRotation;
                break;
            case R.id.imageChat:
                FragmentTransaction ft5 = getFragmentManager().beginTransaction();
                ft5.replace(R.id.fullscreen_content,new ChatPushFragment());
                ft5.commitAllowingStateLoss();
                break;
            case R.id.textChat:
                FragmentTransaction ft6 = getFragmentManager().beginTransaction();
                ft6.replace(R.id.fullscreen_content,new ChatPushFragment());
                ft6.commitAllowingStateLoss();
                break;
        }
    }


    @Override
    public void onTeam(final TeamOperation.RecvData recvData) {
        if(getActivity() == null)
            return;
        final Location location = ((FullscreenActivity)getActivity()).getLocation();

        if (recvData != null && recvData.result) {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if(location != null){
                        mImageCompass.clearPoint();
                        for (TeamOperation.UserData m : recvData.members) {
                            float[] results = new float[3];
                            Location.distanceBetween(location.getLatitude(), location.getLongitude(), y, x, results);
                            mImageCompass.addPoint(results);
                        }
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                mImageCompass.invalidate();
                            }
                        });
                    }

//                    StringBuilder sb = new StringBuilder();
//                    for (TeamOperation.UserData m : recvData.members) {
//                        String msg = String.format("%s (%f,%f)\n", m.userName, m.locationX, m.locationY);
//                        sb.append(msg);
//                    }
                    //mTextDebug.setText(sb.toString());
                }
            });
        }
    }

    //@Override
    public void onChange(double direction) {
        mImageCompass.setRotation(-(float)direction);

    }

}
