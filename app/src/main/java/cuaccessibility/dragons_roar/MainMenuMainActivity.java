package cuaccessibility.dragons_roar;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.speech.RecognizerIntent;


import cuaccessibility.dragons_roar.R;

public class MainMenuMainActivity extends AppCompatActivity {

    private Button mContinueButton;

    private Button mVoiceCommandButton;

    private Button mNewCharacterButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainmenu_activity_main);
        mNewCharacterButton = (Button) findViewById(R.id.newChar);
        mVoiceCommandButton = (Button) findViewById(R.id.voiceCommand);
        mContinueButton = (Button) findViewById(R.id.continueButton);


        mNewCharacterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenuMainActivity.this, voiceButton.class);
                startActivity(intent);
            }
        });

        mVoiceCommandButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenuMainActivity.this, voiceButton.class);
                startActivity(intent);


            }
        });

        mContinueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenuMainActivity.this, voiceButton.class);
                startActivity(intent);

            }

        });

    }
    public void sendMessage(View view) {
        Intent intent = new Intent(MainMenuMainActivity.this, voiceButton.class);
        startActivity(intent);

    }





}
