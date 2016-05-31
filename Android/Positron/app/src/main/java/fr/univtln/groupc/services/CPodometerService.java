package fr.univtln.groupc.services;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import fr.univtln.m1dapm.groupec.tperron710.positron.R;

public class CPodometerService extends Activity implements SensorEventListener {

    private final static String PODOMETER_SENSOR_NOT_PRESENT_FRENCH = "Impossible de compter les pas ! ";

    private SensorManager mSensorManager;
    private TextView mStepNumber;
    boolean mActivityIsRunning;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mStepNumber = (TextView) findViewById(R.id.affichage);
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mActivityIsRunning = true;
        Sensor lCountSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        if (lCountSensor != null) {
            mSensorManager.registerListener(this, lCountSensor, SensorManager.SENSOR_DELAY_UI);
        } else {
            Toast.makeText(this,PODOMETER_SENSOR_NOT_PRESENT_FRENCH, Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (mActivityIsRunning) {
            mStepNumber.setText(String.valueOf(event.values[0]));
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

}