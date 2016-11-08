package jp.ac.chiba_fjb.x14b_d.maguro;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class FullscreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullscreen);

        View view = this.getWindow().getDecorView();
        view.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LOW_PROFILE
               | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                );

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

        ft.add(R.id.fullscreen_content,new CameraFragment());
        ft.commit();

        view.requestFocus();

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
}
