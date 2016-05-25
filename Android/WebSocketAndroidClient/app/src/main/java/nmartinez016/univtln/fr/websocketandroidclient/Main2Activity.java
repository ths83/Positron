package nmartinez016.univtln.fr.websocketandroidclient;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {

    ProgressBar mHealthBar;
    TextView mText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        mHealthBar = (ProgressBar)findViewById(R.id.healthbar);
        mText = (TextView) findViewById(R.id.text);
        mHealthBar.setProgressTintList(ColorStateList.valueOf(Color.GREEN));

    }

    public void attackButton(View view){
        int lNewHp = mHealthBar.getProgress() - 30;
        mHealthBar.setProgress(lNewHp);
        if (lNewHp > 0){
            mText.setText(lNewHp + "/100");
        }
        else{
            mText.setText("0/100");
        }
    }

    public void healButton(View view){
        int lNewHp = mHealthBar.getProgress() + 15;
        mHealthBar.setProgress(lNewHp);
        if (lNewHp < 100){
            mText.setText(lNewHp + "/100");
        }
        else{
            mText.setText("100/100");
        }
    }

}
