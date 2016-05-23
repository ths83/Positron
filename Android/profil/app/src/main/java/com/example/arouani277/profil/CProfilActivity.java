package com.example.arouani277.profil;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.example.arouani277.profil.R;
import com.pkmmte.view.CircularImageView;

public class CProfilActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Récupere et intègre l'image pour la rendre circulaire et l'intégrérer à l'activité
        CircularImageView circularImageView = (CircularImageView)findViewById(R.id.yourCircularImageView);
        circularImageView.setBorderColor(getResources().getColor(R.color.borderColor));
        circularImageView.setBorderWidth(20);
        //circularImageView.setSelectorColor(getResources().getColor(R.color.BlueLightTransparent));
        //circularImageView.setSelectorStrokeColor(getResources().getColor(R.color.BlueDark));
        circularImageView.setSelectorStrokeWidth(10);
        circularImageView.addShadow();

        // On configure les ronds à afficher
        TextDrawable drawable1 = TextDrawable.builder()
                .beginConfig().textColor(Color.BLACK)
                .endConfig()
                .buildRound("5", Color.LTGRAY);

        TextDrawable drawable2 = TextDrawable.builder()
                .beginConfig().textColor(Color.BLACK)
                .endConfig()
                .buildRound("0", Color.LTGRAY);

        TextDrawable drawable3 = TextDrawable.builder()
                .beginConfig().textColor(Color.BLACK)
                .endConfig()
                .buildRound("50", Color.LTGRAY);

        TextDrawable drawable4 = TextDrawable.builder()
                .beginConfig().textColor(Color.BLACK)
                .endConfig()
                .buildRound("3", Color.LTGRAY);

        // On affiche les ronds dans l'image_view
        ImageView image1 = (ImageView) findViewById(R.id.image_view1);
        image1.setImageDrawable(drawable1);

        ImageView image2 = (ImageView) findViewById(R.id.image_view2);
        image2.setImageDrawable(drawable2);

        ImageView image3 = (ImageView) findViewById(R.id.image_view3);
        image3.setImageDrawable(drawable3);

        ImageView image4 = (ImageView) findViewById(R.id.image_view4);
        image4.setImageDrawable(drawable4);
    }

}
