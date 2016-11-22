package jp.ac.chiba_fjb.x14b_d.maguro;

import android.Manifest;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class FullscreenActivity extends AppCompatActivity {

    private Permission mPermission;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_camera);



        View view = this.getWindow().getDecorView();
        view.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LOW_PROFILE
               | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                );


        view.requestFocus();

        mPermission = new Permission();
        mPermission.setOnResultListener(new Permission.ResultListener() {
            @Override
            public void onResult() {
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.fullscreen_content,new CameraFragment());
                ft.commitAllowingStateLoss();
            }
        });
        mPermission.addPermission( Manifest.permission.CAMERA);
        mPermission.addPermission( Manifest.permission.WRITE_EXTERNAL_STORAGE);
        mPermission.requestPermissions(this);

    }
//
//    @Override
//    public boolean dispatchKeyEvent(KeyEvent event) {
//        if (event.getAction() == KeyEvent.KEYCODE_VOLUME_UP) {
//            mCamera.zoom(4);
//
//        }
//        if (event.getAction() == KeyEvent.KEYCODE_VOLUME_DOWN){
//            mCamera.zoom(-4);
//        }
//        return super.dispatchKeyEvent(e);
//
//    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        mPermission.onRequestPermissionsResult(requestCode,permissions,grantResults);
    }
    TextView timer;
    Button start;

}
