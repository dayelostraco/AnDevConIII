package com.sparcedge;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.immersion.uhl.Launcher;
import com.sparcedge.andevconIII.R;

public class HapticActivity extends Activity {

    private Launcher viblauncher;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.haptic_demo);

        try {
            viblauncher = new Launcher(this);

            setButtons();

        } catch (RuntimeException e){
            e.printStackTrace();
        }
    }

    private void setButtons(){
        Button hapticButton1 = (Button) findViewById(R.id.hapticButton1);
        hapticButton1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                viblauncher.play(Launcher.BOUNCE_33);
            }
        });

        Button hapticButton2 = (Button) findViewById(R.id.hapticButton2);
        hapticButton2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                viblauncher.play(Launcher.BOUNCE_66);
            }
        });

        Button hapticButton3 = (Button) findViewById(R.id.hapticButton3);
        hapticButton3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                viblauncher.play(Launcher.BOUNCE_100);
            }
        });

        Button hapticButton4 = (Button) findViewById(R.id.hapticButton4);
        hapticButton4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                viblauncher.play(Launcher.DOUBLE_SHARP_CLICK_100);
            }
        });

        Button hapticButton5 = (Button) findViewById(R.id.hapticButton5);
        hapticButton5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                viblauncher.play(Launcher.ALERT9);
            }
        });
    }

    @Override
    public void onPause(){
        try{
            viblauncher.stop();
        } catch (RuntimeException e){
            e.printStackTrace();
        }
    }
}
