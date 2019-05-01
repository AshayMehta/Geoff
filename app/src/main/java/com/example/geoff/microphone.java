package com.example.geoff;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class microphone extends AppCompatActivity {

    private static final int REQUEST_CODE_SPEECH_INPUT = 1000;

    private ImageButton button;
    private ImageButton mic;
    private TextView text;
    private String input;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_microphone);

        button = (ImageButton) findViewById(R.id.backButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMain();
            }
        });


        text = findViewById(R.id.textview);
        mic = findViewById((R.id.microphone));

        mic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speak();
            }
        });
    }

    private void speak() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "");


        try {
            startActivityForResult(intent, REQUEST_CODE_SPEECH_INPUT);
        } catch(ActivityNotFoundException e) {
            String appPackageName = "com.google.android.googlequicksearchbox";
            try {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
            } catch (android.content.ActivityNotFoundException anfe) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQUEST_CODE_SPEECH_INPUT: {
                if (resultCode == RESULT_OK && null != data) {
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    text.setText(result.get(0));
                    ArrayList<String> res = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    input = res.get(0);
                    checkKeyWord(input);
                }
                break;
            }
        }

    }

    public void checkKeyWord(String input) {
        if (input.equals("who are you")) {
            //load activity for who are you
            Intent intent = new Intent(this, WhoAreYou.class);
            startActivity(intent);
        } else if (input.equals("teach me how to Boogie")) {
            //load youtube video of someone boogie-ing
            Intent intent = new Intent(this, HowToBoogie.class);
            startActivity(intent);
        } else if (input.equals("show me the Italian restaurants on campus")) {
            //load google search results for italian restaurants nearby
            Intent intent = new Intent(this, ItalianRestaurants.class);
            startActivity(intent);
        } else if (input.equals("please give me your music playlist")) {
            //load link to geoff's music playlist
            Intent intent = new Intent(this, MusicPlaylist.class);
            startActivity(intent);
        } else if (input.equals("what's the weather")) {
            //load link to weather
            Intent intent = new Intent(this, Weather.class);
            startActivity(intent);
        } else {
            //load error case activity
            Intent intent = new Intent(this, Default.class);
            startActivity(intent);
        }
    }


    public void openMain() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}
