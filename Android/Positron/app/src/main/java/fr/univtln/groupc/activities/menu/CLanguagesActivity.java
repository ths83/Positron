package fr.univtln.groupc.activities.menu;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.util.Locale;

import fr.univtln.m1dapm.groupec.tperron710.positron.R;

public class CLanguagesActivity extends AppCompatActivity {

    private Locale lLocale;
    private Configuration lConfig;
    private String lLanguageToApply;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clanguages);
    }

    /**
     * Mettre la langue de Positron en francais
     * -----
     * French language for Positron
     * @param pView
     */
    //TODO test it
    public void setFrenchLanguage(View pView){
        lLanguageToApply = "fr";
        lLocale = new Locale(lLanguageToApply);
        Locale.setDefault(lLocale);
        lConfig = new Configuration();
        lConfig.locale = lLocale;
        getBaseContext().getResources().updateConfiguration(lConfig,
                getBaseContext().getResources().getDisplayMetrics());
    }

    /**
     * Mettre la langue de Positron en anglais
     * -----
     * English language for Positron
     * @param pView
     */
    //TODO test it
    public void setEnglishLanguage(View pView){
        lLanguageToApply = "en";
        lLocale = new Locale(lLanguageToApply);
        Locale.setDefault(lLocale);
        lConfig = new Configuration();
        lConfig.locale = lLocale;
        getBaseContext().getResources().updateConfiguration(lConfig,
                getBaseContext().getResources().getDisplayMetrics());
    }

    /**
     * Mettre la langue de Positron en arabe
     * -----
     * Arabian language for Positron
     * @param pView
     */
    public void setArabianLanguage(View pView){

    }
}
