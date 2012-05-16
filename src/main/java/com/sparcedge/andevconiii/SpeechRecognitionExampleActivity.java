package com.sparcedge.andevconiii;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.sparcedge.andevconIII.R;

import java.util.ArrayList;
import java.util.List;

/**
 * User: dayelostraco
 * Date: 5/16/12
 * Time: 1:47 PM
 */
public class SpeechRecognitionExampleActivity extends Activity {

    public static final int REQUEST_CODE = 2;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.speech_demo);

        PackageManager pm = getPackageManager();
        List<ResolveInfo> activities = pm.queryIntentActivities(new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH), 0);
        if (activities.size() == 0) {
            Toast toast = Toast.makeText(this, "No Speech Recognition on this device!", 1000);
            toast.show();

        } else {
            setButtonActions();
        }
    }

    private void setButtonActions(){
        Button speakButton = (Button) findViewById(R.id.speakButton1);
        speakButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Speech Recognition in 3,2,1...");
                startActivityForResult(intent, REQUEST_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode,
                                    int resultCode, Intent data) {

        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            ArrayList<String> matches = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);

            if(matches.size()>0){
                String bestResult = matches.get(0);
                Toast toast = Toast.makeText(this, bestResult, 2000);
                toast.show();
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}