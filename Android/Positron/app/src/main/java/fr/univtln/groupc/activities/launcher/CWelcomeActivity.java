package fr.univtln.groupc.activities.launcher;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import fr.univtln.groupc.signin.google.CSignInActivity;
import fr.univtln.m1dapm.groupec.tperron710.positron.R;

public class CWelcomeActivity extends AppCompatActivity {

    /** Duration of wait **/
    private final int SPLASH_DISPLAY_LENGTH = 3000;

    private Button mSignInButton;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.activity_cwelcome);

        mSignInButton = (Button) findViewById(R.id.sign_in_button);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mSignInButton.setVisibility(View.VISIBLE);
                mSignInButton.setEnabled(true);
                mSignInButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(CWelcomeActivity.this,CSelectTeamActivity.class));
                    }
                });

            }
        }, SPLASH_DISPLAY_LENGTH);

    }
}
