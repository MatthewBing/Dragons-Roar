package cuaccessibility.dragons_roar;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.speech.tts.TextToSpeech;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

import ai.api.android.AIConfiguration;
import ai.api.android.GsonFactory;
import ai.api.model.AIError;
import ai.api.model.AIResponse;
import ai.api.model.Metadata;
import ai.api.model.Result;
import ai.api.model.Status;
import ai.api.ui.AIButton;

import static java.lang.Integer.valueOf;
/*
    This activity is the main usage of this app.

    On this screen, the user can:
    1: Press the button to activate voice command recognition.
    2: Hit either the back button or the Up button to return to the main menu.
 */




public class voiceButton extends AppCompatActivity implements AIButton.AIButtonListener  {

    public static final String TAG = voiceButton.class.getName();

    private AIButton aiButton;
    private TextToSpeech tts;
    private TextView resultTextView;
    private static final int REQUEST_AUDIO_PERMISSIONS_ID = 33;
    private Gson gson = GsonFactory.getGson();

    @Override
    protected void onCreate(final Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aibutton_sample);

        checkAudioRecordPermission();

        resultTextView = (TextView) findViewById(R.id.resultTextView);
        aiButton = (AIButton) findViewById(R.id.micButton);

        final AIConfiguration config = new AIConfiguration("7fc1ffac2bb24b6f9e2f5d6b7c587015",
                AIConfiguration.SupportedLanguages.English,
                AIConfiguration.RecognitionEngine.System);

        aiButton.initialize(config);
        aiButton.setResultsListener(this);


    }
    protected void checkAudioRecordPermission() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.RECORD_AUDIO)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.RECORD_AUDIO)) {

                // Show an expanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

            } else {

                // No explanation needed, we can request the permission.

                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.RECORD_AUDIO},
                        REQUEST_AUDIO_PERMISSIONS_ID);

            }
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_AUDIO_PERMISSIONS_ID: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }
        }
    }
    @Override
    protected void onPause() {
        super.onPause();

        // use this method to disconnect from speech recognition service
        // Not destroying the SpeechRecognition object in onPause method would block other apps from using SpeechRecognition service
        aiButton.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();

        // use this method to reinit connection to recognition service
        aiButton.resume();
    }


    @Override
    public void onResult(final AIResponse response) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG, "onResult");

                resultTextView.setText(gson.toJson(response));

                Log.i(TAG, "Received success response");

                // this is example how to get different parts of result object
                final Status status = response.getStatus();
                Log.i(TAG, "Status code: " + status.getCode());
                Log.i(TAG, "Status type: " + status.getErrorType());

                final Result result = response.getResult();
                Log.i(TAG, "Resolved query: " + result.getResolvedQuery());

                Log.i(TAG, "Action: " + result.getAction());
                final String speech = result.getFulfillment().getSpeech();
                Log.i(TAG, "Speech: " + speech);
                //TTS.speak(speech);



                int numOfDice = 0;
                int numOfSides = 0;
                String diceRollString = "";

                final HashMap<String, JsonElement> params = result.getParameters();
                if (params != null && !params.isEmpty()) {
                    Log.i(TAG, "Parameters: ");
                    for (final Map.Entry<String, JsonElement> entry : params.entrySet()) {
                        Log.i(TAG, String.format("%s: %s", entry.getKey(), entry.getValue().toString()));
                        if(entry.getKey() == "Dice"){
                            numOfDice = Integer.valueOf(entry.getValue().toString());
                        }
                        else if(entry.getKey() == "number"){
                            numOfSides = Integer.valueOf(entry.getValue().toString());
                        }

                    }
                }

                final Metadata metadata = result.getMetadata();
                if (metadata != null) {
                    Log.i(TAG, "Intent id: " + metadata.getIntentId());
                    Log.i(TAG, "Intent name: " + metadata.getIntentName());
                    Random r = new Random();
                    int diceRollTotal = 0;
                    while (numOfDice > 0){
                        diceRollTotal += r.nextInt(valueOf(numOfSides) + 1);
                        numOfDice = numOfDice - 1;
                    }


                    diceRollString = String.valueOf(diceRollTotal);
                    Snackbar mySnackbar = Snackbar.make(findViewById(R.id.CoordLayout), diceRollString, Snackbar.LENGTH_SHORT);
                    mySnackbar.show();

                }

                //TTS handled below
                tts = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener(){
                    @Override
                    @SuppressWarnings("deprecation")
                    public void onInit(int status){
                        if (status == TextToSpeech.SUCCESS) {
                            tts.setLanguage(Locale.US);
                            Log.i(TAG + " TTS SYSTEM", "TTS initialized.");

                            //Handle the different intents back from api.ai
                            if (metadata.getIntentName().equals("aboutMe")) {
                                String message = ""; //placeholder
                                tts.speak(message, TextToSpeech.QUEUE_FLUSH, null);
                                Log.i(TAG + " TTS SYSTEM", "Message: " + message );

                            } else if (metadata.getIntentName().equals("Roll Dice")) {
                                tts.speak(speech, TextToSpeech.QUEUE_FLUSH, null);
                                Log.i(TAG + " TTS SYSTEM", "Message: " + speech);

                            } else {
                                Log.e(TAG + "TTS SYSTEM", "Unhandled intent!");
                                tts.speak(speech, TextToSpeech.QUEUE_FLUSH, null);
                            }

                        } else {
                            Log.e(TAG + " TTS SYSTEM", "TTS initialization failed! Check the device.");
                        }
                    }
                });

            }

        });
    }

    @Override
    public void onError(final AIError error) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG, "onError");
                resultTextView.setText(error.toString());
            }
        });
    }

    @Override
    public void onCancelled() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG, "onCancelled");
                resultTextView.setText("");
            }
        });
    }
}