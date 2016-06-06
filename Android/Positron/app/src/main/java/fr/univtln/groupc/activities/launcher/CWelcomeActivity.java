package fr.univtln.groupc.activities.launcher;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import fr.univtln.groupc.signin.google.CSignInActivity;
import fr.univtln.m1dapm.groupec.tperron710.positron.R;

public class CWelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cwelcome);

        try {
            wait(3000);
            Intent lSign = new Intent(this, CSignInActivity.class);
            startActivity(lSign);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
