package eu.sekercioglu.translator;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import com.google.api.GoogleAPI;
import com.google.cloud.translate.Translate;
import com.google.cloud.translate.TranslateOptions;
import com.google.cloud.translate.Translation;


public class MainActivity extends AppCompatActivity {


    protected static final int RESULT_SPEECH = 1;
    private Button btnSpeak;
    private Button btnTranslate;
    private TextView txtText, txtEnglish;
    private static final String API_KEY = "API-KEY";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtText = (TextView) findViewById(R.id.txtMetin);
        txtEnglish = (TextView) findViewById(R.id.txtEnglish);
        btnTranslate = (Button) findViewById(R.id.btnCevir);

        btnTranslate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputString;
                String outputString = null;
                inputString = txtText.getText().toString();
                final TextView textView = (TextView) findViewById(R.id.txtEnglish);
                final Handler textViewHandler = new Handler();
                new AsyncTask<Void, Void, Void>() {
                    @Override
                    protected Void doInBackground(Void... params) {
                        TranslateOptions options = TranslateOptions.newBuilder()
                                .setApiKey(API_KEY)
                                .build();
                        Translate translate = options.getService();
                        final Translation translation =
                                translate.translate(txtText.getText().toString(),
                                        Translate.TranslateOption.targetLanguage("en"));
                        textViewHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                if (textView != null) {
                                    textView.setText(translation.getTranslatedText());
                                }
                            }
                        });
                        return null;
                    }
                }.execute();

            }
        });






        }
    }
