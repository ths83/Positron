package fr.univtln.groupc.test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import fr.univtln.groupc.rest.CRestDelete;
import fr.univtln.m1dapm.groupec.tperron710.positron.R;

public class testActivity extends AppCompatActivity {

    private Button mTestButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        mTestButton = (Button) findViewById(R.id.testButton);
        mTestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new CRestDelete().onDeleteLink(1);
            }
        });
    }
}
