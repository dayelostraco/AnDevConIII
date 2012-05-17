package com.sparcedge.andevconiii;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.sparcedge.andevconIII.R;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * User: dayelostraco
 * Date: 5/16/12
 * Time: 1:47 PM
 */
public class SpeechRecognitionExampleActivity extends Activity implements TextToSpeech.OnInitListener {

    public static final int REQUEST_CODE = 2;
    private static final String WOLFRAM_ALPHA_APP_ID = "4Y754K-7RJ4J58PYV";

    private TextToSpeech mTts;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.speech_demo);

        mTts = new TextToSpeech(this, this);

        PackageManager pm = getPackageManager();
        List<ResolveInfo> activities = pm.queryIntentActivities(new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH), 0);
        if (activities.size() == 0) {
            Toast toast = Toast.makeText(this, "No Speech Recognition on this device!", 1000);
            toast.show();

        } else {
            inflateButtons();
        }
    }

    private void inflateButtons() {
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

    private void speak(String textToSpeak) {
        mTts.speak(textToSpeak, TextToSpeech.QUEUE_FLUSH, null);
    }

    private String getAnswer(String question) {

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("http://api.wolframalpha.com/v2/query?input=");
        stringBuilder.append(question.replaceAll(" ", "+"));
        stringBuilder.append("&appid=");
        stringBuilder.append(WOLFRAM_ALPHA_APP_ID);

        try{
            HttpClient client = new DefaultHttpClient();
            HttpGet request = new HttpGet(stringBuilder.toString());
            HttpResponse response = client.execute(request);

            StatusLine status = response.getStatusLine();
            if (status.getStatusCode() != 200) {
                Log.d("SpeechRecognitionExampleActivity", "HTTP error, invalid server status code: " + response.getStatusLine());
            }

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(response.getEntity().getContent());

            XPathFactory xPathfactory = XPathFactory.newInstance();
            XPath xpath = xPathfactory.newXPath();
            XPathExpression expr = xpath.compile("/queryresult/pod[@title='Result']/subpod[@title='']/plaintext/text()");
            Object result = expr.evaluate(doc, XPathConstants.STRING);

            return result.toString();

        } catch (Exception e){
            Log.d("SpeechRecognitionExampleActivity", "Could not parse Wolfram Alpha response.");
        }

        return "Could not find an answer";
    }

    @Override
    protected void onActivityResult(int requestCode,
                                    int resultCode, Intent data) {

        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            ArrayList<String> matches = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);

            if (matches.size() > 0) {
                String bestResult = matches.get(0);
                String answer = getAnswer(bestResult);
                Toast toast = Toast.makeText(this, answer, 100000);
                toast.show();
                speak(answer);
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
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
                Log.e("SpeechRecognitionExampleActivity", "Language is not available.");
            }

        } else {
            Log.e("SpeechRecognitionExampleActivity", "Could not initialize TextToSpeech.");
        }
    }
}