package com.example.toms.myapplication;

import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private Locale lLocale;
    private Configuration lConfig;
    private String lLanguageToApply;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
        Toast.makeText(getBaseContext(),getText(R.string.Langues),Toast.LENGTH_LONG).show();
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
        Toast.makeText(getBaseContext(),getText(R.string.Langues),Toast.LENGTH_LONG).show();
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
