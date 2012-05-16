package com.sparcedge.andevconiii;

import android.app.Activity;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import com.sparcedge.andevconIII.R;

import java.util.Locale;
import java.util.Random;

public class TextToSpeechExampleActivity extends Activity implements TextToSpeech.OnInitListener {

    private static final String TAG = "TextToSpeechDemo";
    public static final int REQUEST_CODE = 6;

    private TextToSpeech mTts;
    private Button speakTtsButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tts_demo);

        mTts = new TextToSpeech(this, this);

        speakTtsButton = (Button) findViewById(R.id.speakTtsButton1);
        speakTtsButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                sayHello();
            }
        });
    }

    @Override
    public void onDestroy() {
        if (mTts != null) {
            mTts.stop();
            mTts.shutdown();
        }

        super.onDestroy();
    }

    @Override
    public void onInit(int status) {

        if (status == TextToSpeech.SUCCESS) {

            int result = mTts.setLanguage(Locale.US);

            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e(TAG, "Language is not available.");
            } else {

                speakTtsButton.setEnabled(true);
                sayHello();
            }
        } else {
            Log.e(TAG, "Could not initialize TextToSpeech.");
        }
    }

    private static final Random RANDOM = new Random();

    private static final String[] HELLOS = {
            "Hello",
            "Salutations",
            "Greetings",
            "Howdy",
            "What's crack-a-lackin?",
            "That explains the stench!"
    };

    private void sayHello() {
        int helloLength = HELLOS.length;
        String hello = HELLOS[RANDOM.nextInt(helloLength)];
        mTts.speak(hello, TextToSpeech.QUEUE_FLUSH, null);
    }
}