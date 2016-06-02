package fr.univtln.groupc.services;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import fr.univtln.m1dapm.groupec.tperron710.positron.R;

public class CPodometer extends Activity implements SensorEventListener{

    private SensorManager mSensorManager;
    private float mStepNumber;
    boolean mActivityIsRunning;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mActivityIsRunning = true;
        Sensor lCountSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        if (lCountSensor != null) {
            mSensorManager.registerListener(this, lCountSensor, SensorManager.SENSOR_DELAY_UI);
        } else {
            Toast.makeText(this,getText(R.string.podo_sensor_not_present), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (mActivityIsRunning) {
            mStepNumber = (event.values[0]);
            Log.d("test","step -> " + mStepNumber);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    /**
     * retourne le nb de pas effectue par le joueur
     * -----
     * number of player steps
     * @return
     */
    public float getStepNumber() {
        return mStepNumber;
    }
}