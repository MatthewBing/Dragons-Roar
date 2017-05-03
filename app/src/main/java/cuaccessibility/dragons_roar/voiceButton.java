package cuaccessibility.dragons_roar;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

import org.json.JSONException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import ai.api.AIServiceException;
import ai.api.android.AIConfiguration;
import ai.api.android.AIDataService;
import ai.api.android.AIService;
import ai.api.android.GsonFactory;
import ai.api.model.AIError;
import ai.api.model.AIRequest;
import ai.api.model.AIResponse;
import ai.api.model.Metadata;
import ai.api.model.Result;
import ai.api.model.Status;
import ai.api.ui.AIButton;
/*
    This activity is the main usage of this app.

    On this screen, the user can:
    1: Press the button to activate voice command recognition.
    2: Hit either the back button or the Up button to return to the main menu.
 */




public class voiceButton extends AppCompatActivity implements AIButton.AIButtonListener  {

    public static final String TAG = voiceButton.class.getName();

    private AIButton aiButton;

    private AIService service;

    private TextView queryEditText;

    private CheckBox eventCheckBox;
    private Spinner eventSpinner;
    private AIDataService aiDataService;
    private EditText contextEditText;
    private TextToSpeech tts;


    private TextView resultTextView;
    String resultsLog;

    private static final int REQUEST_AUDIO_PERMISSIONS_ID = 33;
    private Gson gson = GsonFactory.getGson();

    //Added by Matt: This resultHandler is what will be used to load the character.
    //After we implement character loading/saving, it should take a CharacterSheet as an arg.
    resultHandler thisQuery;

    public voiceButton() throws FileNotFoundException, JSONException {
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {


        try {
            thisQuery = new resultHandler(voiceButton.this);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aibutton_sample);

        checkAudioRecordPermission();


        Context currentContext = voiceButton.this;
        resultTextView = (TextView) findViewById(R.id.resultTextView);
        resultTextView.setSingleLine(false);
        resultsLog = "";

        queryEditText = (EditText) findViewById(R.id.textQuery);

        aiButton = (AIButton) findViewById(R.id.micButton);

        service = aiButton.getAIService();



        //Added by Matt: This resultHandler is what will be used to load the character.
        //After we implement character loading/saving, it should take a CharacterSheet as an arg.


        final AIConfiguration config = new AIConfiguration("a7df5dcce21542b8b625fc643b561311",
                AIConfiguration.SupportedLanguages.English,
                AIConfiguration.RecognitionEngine.System);

        aiButton.initialize(config);
        aiButton.setResultsListener(this);
        aiDataService = new AIDataService(this, config);


        //Text To Speech Initialization
        tts = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int statusCode) {
                if (statusCode == TextToSpeech.SUCCESS) {
                    tts.setLanguage(Locale.US);
                    Log.i(TAG, "TTS has been initialized");
                } else {
                    Log.i(TAG, "TTS could not be initialized");
                    //What else should we do in this case?
                }
            }
        });


    }


    public Context getContext(voiceButton voiceButton){
        return voiceButton.this;
    }


    private void sendRequest() {

        final String queryString = String.valueOf(queryEditText.getText());
        //final String eventString = eventSpinner.isEnabled() ? String.valueOf(String.valueOf(eventSpinner.getSelectedItem())) : null;
        //final String contextString = String.valueOf(contextEditText.getText());

        //if (TextUtils.isEmpty(queryString) && TextUtils.isEmpty(eventString)) {
        //onError(new AIError(getString(R.string.non_empty_query)));
        //return;
        //}

        final AsyncTask<String, Void, AIResponse> task = new AsyncTask<String, Void, AIResponse>() {

            private AIError aiError;

            @Override
            protected AIResponse doInBackground(final String... params) {
                final AIRequest request = new AIRequest();
                String query = params[0];
                //String event = params[1];

                if (!TextUtils.isEmpty(query))
                    request.setQuery(query);
                //if (!TextUtils.isEmpty(event))
                //request.setEvent(new AIEvent(event));
                //final String contextString = params[2];
                //RequestExtras requestExtras = null;
                //if (!TextUtils.isEmpty(contextString)) {
                //final List<AIContext> contexts = Collections.singletonList(new AIContext(contextString));
                //requestExtras = new RequestExtras(contexts, null);
                //}

                try {
                    return aiDataService.request(request);
                } catch (final AIServiceException e) {
                    aiError = new AIError(e);
                    return null;
                }
            }

            @Override
            protected void onPostExecute(final AIResponse response) {
                if (response != null) {
                    onResult(response);
                } else {
                    onError(aiError);
                }
            }
        };

        task.execute(queryString);
    }


    public void buttonSend(View view){
        sendRequest();
    }

    public void buttonClear(View view){
        queryEditText.setText("");
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
                //Log.d(TAG, "onResult");

                //resultTextView.setText(gson.toJson(response));

                //Log.i(TAG, "Received success response");

                // this is example how to get different parts of result object
                final Status status = response.getStatus();
                //Log.i(TAG, "Status code: " + status.getCode());
                //Log.i(TAG, "Status type: " + status.getErrorType());

                final Result result = response.getResult();
                //Log.i(TAG, "Resolved query: " + result.getResolvedQuery());

                //Log.i(TAG, "Action: " + result.getAction());
                //final String speech = result.getFulfillment().getSpeech();
                //Log.i(TAG, "Speech: " + speech);
                //TTS.speak(speech);

                final HashMap<String, JsonElement> params = result.getParameters();
                final Metadata metadata = result.getMetadata();


                if (params != null && !params.isEmpty()) {
                    Log.i(TAG, "Parameters: ");
                    for (final Map.Entry<String, JsonElement> entry : params.entrySet()) {
                        Log.i(TAG, String.format("%s: %s", entry.getKey(), entry.getValue().toString()));
                    }
                }

                String fullResponseForUser = null;
                try {
                    fullResponseForUser = thisQuery.getResponse(params, metadata);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                resultsLog = fullResponseForUser + " \n" + resultsLog;
                tts.speak(fullResponseForUser, TextToSpeech.QUEUE_FLUSH, null);
                resultTextView.setText(resultsLog);
                //Snackbar mySnackbar = Snackbar.make(findViewById(R.id.CoordLayout), fullResponseForUser, Snackbar.LENGTH_INDEFINITE);
                //mySnackbar.show();

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