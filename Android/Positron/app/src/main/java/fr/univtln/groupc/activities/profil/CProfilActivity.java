package fr.univtln.groupc.activities.profil;

import android.annotation.TargetApi;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.pkmmte.view.CircularImageView;

import java.util.Objects;

import fr.univtln.groupc.activities.google.SCurrentPlayer;

import fr.univtln.groupc.rest.CRestPlayer;
import fr.univtln.m1dapm.groupec.tperron710.positron.R;

public class CProfilActivity extends AppCompatActivity {

    CircularImageView mCircularImageView;
    TextView mTextName, mTextLevel;
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cprofil);
        String mEnergy = null, mEnergyMax = null, mName = null, mXp = null, mTeam = null, mBagSize = null, mLevel = null;

        // on met à jour le playeur courant
        SCurrentPlayer.mPlayer = new CRestPlayer().getPlayerByMail(SCurrentPlayer.mMail);

        mCircularImageView = (CircularImageView) findViewById(R.id.yourCircularImageView);
        mCircularImageView.setImageDrawable(getDrawable(R.mipmap.logatom));

        if (SCurrentPlayer.mPlayer != null) {

            mTextName = (TextView) findViewById(R.id.textName);
            if (mTextName != null) {
                mTextName.setText(SCurrentPlayer.mPlayer.getNickName());
            }

            mTextLevel = (TextView) findViewById(R.id.textLevel);
            if (mTextLevel != null) {
                mTextLevel.setText("Level " + SCurrentPlayer.mPlayer.getLevel());
            }

            // Récupere et intègre l'image pour la rendre circulaire et l'intégrérer à l'activité

            // on met le drapeau de la team à laquelle l'user courant appartient


            if (Objects.equals(SCurrentPlayer.mPlayer.getTeam().getColor(), "bleu"))

                mCircularImageView.setImageDrawable(getDrawable(R.mipmap.logatom));
            else
                mCircularImageView.setImageDrawable(getDrawable(R.mipmap.logxenom));


            mCircularImageView.setBorderColor(getResources().getColor(R.color.borderColor));
            mCircularImageView.setBorderWidth(20);
            mCircularImageView.setSelectorStrokeWidth(10);
            mCircularImageView.addShadow();

            // On configure les ronds à afficher
            TextDrawable drawableEnergy = TextDrawable.builder()
                    .beginConfig().textColor(Color.BLACK)
                    .endConfig()
                    .buildRound(String.valueOf(SCurrentPlayer.mPlayer.getEnergy()), Color.LTGRAY);

            TextDrawable drawableEnergyMax = TextDrawable.builder()
                    .beginConfig().textColor(Color.BLACK)
                    .endConfig()
                    .buildRound(String.valueOf(SCurrentPlayer.mPlayer.getEnergyMax()), Color.LTGRAY);

            TextDrawable drawableXp = TextDrawable.builder()
                    .beginConfig().textColor(Color.BLACK)
                    .endConfig()
                    .buildRound(String.valueOf(SCurrentPlayer.mPlayer.getXp()), Color.LTGRAY);

            TextDrawable drawableBagSize = TextDrawable.builder()
                    .beginConfig().textColor(Color.BLACK)
                    .endConfig()
                    .buildRound(String.valueOf(SCurrentPlayer.mPlayer.getBagSize()), Color.LTGRAY);

            // On affiche les ronds dans l'image_view
            ImageView image1 = (ImageView) findViewById(R.id.image_view1);
            if (image1 != null) {
                image1.setImageDrawable(drawableEnergy);
            }

            ImageView image2 = (ImageView) findViewById(R.id.image_view2);
            if (image2 != null) {
                image2.setImageDrawable(drawableEnergyMax);
            }

            ImageView image3 = (ImageView) findViewById(R.id.image_view3);
            if (image3 != null) {
                image3.setImageDrawable(drawableXp);
            }

            ImageView image4 = (ImageView) findViewById(R.id.image_view4);
            if (image4 != null) {
                image4.setImageDrawable(drawableBagSize);
            }
        }
    }

}
