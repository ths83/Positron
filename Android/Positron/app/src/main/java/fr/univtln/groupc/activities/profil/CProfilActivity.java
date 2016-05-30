package fr.univtln.groupc.activities.profil;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.pkmmte.view.CircularImageView;

import fr.univtln.groupc.activities.google.CCurrentPlayer;
import fr.univtln.groupc.entities.CPlayerEntity;
import fr.univtln.groupc.rest.CRestPlayer;
import fr.univtln.m1dapm.groupec.tperron710.positron.R;

public class CProfilActivity extends AppCompatActivity {

    CircularImageView mCircularImageView;
    TextView mTextName, mTextLevel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cprofil);
        String mEnergy =null, mEnergyMax=null, mName=null, mXp=null, mTeam=null, mBagSize=null, mLevel = null;


        if (CCurrentPlayer.mPlayer!=null) {
            mEnergy = String.valueOf(CCurrentPlayer.mPlayer.getEnergy());
            mEnergyMax = String.valueOf(CCurrentPlayer.mPlayer.getEnergyMax());
            mName = String.valueOf(CCurrentPlayer.mPlayer.getNickName());
            mXp = String.valueOf(CCurrentPlayer.mPlayer.getXp());
            mTeam = String.valueOf(CCurrentPlayer.mPlayer.getTeam());
            mBagSize = String.valueOf(CCurrentPlayer.mPlayer.getBagSize());
            mLevel = String .valueOf(CCurrentPlayer.mPlayer.getLevel());
        }
        
        mTextName = (TextView) findViewById(R.id.textName);
        if (mTextName != null) {
            mTextName.setText(mName);
        }

        mTextLevel = (TextView) findViewById(R.id.textLevel);
        if (mTextLevel != null) {
            mTextLevel.setText("Level " + mLevel);
        }

        // Récupere et intègre l'image pour la rendre circulaire et l'intégrérer à l'activité
        mCircularImageView = (CircularImageView)findViewById(R.id.yourCircularImageView);
        mCircularImageView.setBorderColor(getResources().getColor(R.color.borderColor));
        mCircularImageView.setBorderWidth(20);
        //mCircularImageView.setSelectorColor(getResources().getColor(R.color.BlueLightTransparent));
        //mCircularImageView.setSelectorStrokeColor(getResources().getColor(R.color.BlueDark));
        mCircularImageView.setSelectorStrokeWidth(10);
        mCircularImageView.addShadow();

        // On configure les ronds à afficher
        TextDrawable drawableEnergy = TextDrawable.builder()
                .beginConfig().textColor(Color.BLACK)
                .endConfig()
                .buildRound(mEnergy, Color.LTGRAY);

        TextDrawable drawableEnergyMax = TextDrawable.builder()
                .beginConfig().textColor(Color.BLACK)
                .endConfig()
                .buildRound(mEnergyMax, Color.LTGRAY);

        TextDrawable drawableXp = TextDrawable.builder()
                .beginConfig().textColor(Color.BLACK)
                .endConfig()
                .buildRound(mXp, Color.LTGRAY);

        TextDrawable drawableBagSize = TextDrawable.builder()
                .beginConfig().textColor(Color.BLACK)
                .endConfig()
                .buildRound(mBagSize, Color.LTGRAY);

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
