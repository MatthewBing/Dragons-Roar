package cuaccessibility.dragons_roar;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;


/*
    This activity is the main usage of this app.

    On this screen, the user can:
    1: Press the button to activate voice command recognition.
    2: Hit either the back button or the Up button to return to the main menu.
 */
public class voiceButton extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voice_button);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.voiceButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("button working");
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

}
