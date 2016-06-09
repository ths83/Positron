package fr.univtln.groupc.activities.launcher;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import fr.univtln.groupc.activities.map.CMapsActivity;
import fr.univtln.m1dapm.groupec.tperron710.positron.R;

public class CSelectTeamActivity extends AppCompatActivity {

    private Button mMapButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cselect_team2);

        mMapButton = (Button) findViewById(R.id.map);
        mMapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CSelectTeamActivity.this, CMapsActivity.class));
            }
        });
    }

}
