package cuaccessibility.dragons_roar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.speech.RecognizerIntent;
import android.view.View;



public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /** This is called from the LOAD VOICE BUTTON button on the main screen */
    public void sendMessage(View view) {
        Intent intent = new Intent(this, voiceButton.class);
        startActivity(intent);
    }

    //
}
