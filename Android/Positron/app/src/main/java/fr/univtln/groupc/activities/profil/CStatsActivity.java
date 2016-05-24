package fr.univtln.groupc.activities.profil;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.amulyakhare.textdrawable.TextDrawable;

import fr.univtln.m1dapm.groupec.tperron710.positron.R;

public class CStatsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cstats);

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

        TextDrawable drawable5 = TextDrawable.builder()
                .beginConfig().textColor(Color.BLACK)
                .endConfig()
                .buildRound("41", Color.LTGRAY);

        // On affiche les ronds dans l'image_view
        ImageView image1 = (ImageView) findViewById(R.id.image_view1);
        image1.setImageDrawable(drawable1);

        ImageView image2 = (ImageView) findViewById(R.id.image_view2);
        image2.setImageDrawable(drawable2);

        ImageView image3 = (ImageView) findViewById(R.id.image_view3);
        image3.setImageDrawable(drawable3);

        ImageView image4 = (ImageView) findViewById(R.id.image_view4);
        image4.setImageDrawable(drawable4);

        ImageView image5 = (ImageView) findViewById(R.id.image_view5);
        image5.setImageDrawable(drawable5);
    }
}
