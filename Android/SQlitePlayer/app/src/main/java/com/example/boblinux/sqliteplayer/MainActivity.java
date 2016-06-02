package com.example.boblinux.sqliteplayer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import fr.univtln.groupc.entities.CPlayerEntity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView textView = (TextView) findViewById(R.id.text);
        CPlayerDAO cPlayerDAO = new CPlayerDAO(this);
        CPlayerEntity lPlayerEntity = new CPlayerEntity.CPlayerBuilder(444).bagSize(5).email("azdaz")
                .energy(4).energyMax(5).latitude(4.7448).longitude(454.1564).nickname("ùadza").xp(4).build();

        cPlayerDAO.open();
        Log.i("sqlite","DAO opened");

        try {
            cPlayerDAO.persist(lPlayerEntity);
            Log.i("sqlite","entity persisted " + lPlayerEntity.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }

        CPlayerEntity playerFromBdd = null;

        try {
            playerFromBdd = cPlayerDAO.find(lPlayerEntity.getEmail());
            Log.i("sqlite", "entity found " + playerFromBdd.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (playerFromBdd!=null) {
            Toast.makeText(this, playerFromBdd.toString(), Toast.LENGTH_LONG).show();
            assert textView != null;
            textView.setText(playerFromBdd.toString());
        }


    }
}
