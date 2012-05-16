package com.sparcedge.andevconiii;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.sparcedge.andevconIII.R;

/**
 * User: dayelostraco
 * Date: 5/16/12
 * Time: 3:56 PM
 */
public class AnDevConIIIActivity extends Activity {

    public static final int REQUEST_CODE = 1;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.android_dev_con);

        inflateButtons();
    }

    private void inflateButtons(){

        Button speechRecDemoButton = (Button) findViewById(R.id.speechRecDemoButton);
        speechRecDemoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), SpeechRecognitionExampleActivity.class);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });

        Button ttsDemoButton = (Button) findViewById(R.id.ttsDemoButton);
        ttsDemoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), TextToSpeechExampleActivity.class);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });

        Button hapticDemoButton = (Button) findViewById(R.id.hapticDemoButton);
        hapticDemoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), HapticExampleActivity.class);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });

        Button arDemoButton = (Button) findViewById(R.id.arDemoButton);
        arDemoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), AugmentedRealityExampleActivity.class);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });
    }
}