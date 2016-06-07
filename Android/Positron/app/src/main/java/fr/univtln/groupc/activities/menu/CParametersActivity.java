package fr.univtln.groupc.activities.menu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import fr.univtln.m1dapm.groupec.tperron710.positron.R;

public class CParametersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cparameters);
    }

    /**
     * Acceder aux parametres des langues
     * -----
     * Set languages parameters
     * @param pView
     */
    public void setLanguagesActivity(View pView){
        Intent lLanguagesIntent = new Intent(this, CLanguagesActivity.class);
        startActivity(lLanguagesIntent);
    }
}
