package fr.univtln.groupc.activities.profil;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import fr.univtln.groupc.activities.map.CMapsActivity;
import fr.univtln.groupc.activities.menu.CManualActivity;
import fr.univtln.groupc.activities.menu.CParametersActivity;
import fr.univtln.m1dapm.groupec.tperron710.positron.R;

public class CChoiceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cchoice);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_cchoice, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * acces a l'arbre de competences du joueur
     * -----
     * competences tree access
     * @param pView
     */
    public void setSkillTreeActivity(View pView){
        Intent lCompetencesTreeIntent = new Intent(this,CSkillTree.class);
        startActivity(lCompetencesTreeIntent);
    }

    /**
     * acces au profil du joueur
     * -----
     * player profile access
     * @param pView
     */
    public void setPlayerProfilActivity(View pView){
        Intent lProfilIntent = new Intent(this, CProfilActivity.class);
        startActivity(lProfilIntent);
    }

    /**
     * acces aux parametres de Positron
     * -----
     * set Positron Parameters
     * @param pView
     */
    //TODO parameters
    public void setPositronParameters(View pView){
        Intent lParametersIntent = new Intent(this, CParametersActivity.class);
        startActivity(lParametersIntent);
    }

    /**
     * acces au manuel de Positron
     * -----
     * set Positron manual
     * @param pView
     */
    //TODO manual
    public void setPositronManual(View pView){
        Intent lManualIntent = new Intent(this, CManualActivity.class);
        startActivity(lManualIntent);
    }

    /**
     * retourner sur la carte du jeu
     * ------
     * returns to Positron Map
     * @param pView
     */
    //TODO return on map
    public void setPositronMap(View pView){
        Intent lMap = new Intent(this, CMapsActivity.class);
        startActivity(lMap);
    }
}
