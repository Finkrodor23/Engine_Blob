package com.corporation.bufra.blob_engine;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class BlobActivity extends Activity implements SensorEventListener{
    private long lastUpdate;
    private static float Ax, Ay;
    private int frameRate;
    private SensorManager derSensorManager;
    private String oben, unten, rechts, links;
    private TextView TVoben, TVunten;
    private MainCharacter mainCh = new MainCharacter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blob);

        //Strings und Standardwerte setzen
        Ax = 0;
        Ay = 6.8f;
        
        //Hi Github push                                ~ from AS
        //Hi Android Studio does this work now?         ~ from Github
        //Hi Github this is really amazing isnt it?     ~ from AS
        //I still cant believe it but however what bout going to bed its soo late.. good night ~ from github
        //Thats ok just let me push this message one last time to wish you a good night ~ from AS
        //Update

        lastUpdate = System.currentTimeMillis();
        frameRate = 25;

        //TextViews reinholen:
        TVoben = (TextView)findViewById(R.id.tvOben);
        TVunten = (TextView)findViewById(R.id.tvUnten);


        derSensorManager = (SensorManager) this.getSystemService(SENSOR_SERVICE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_blob, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //Accelerometer overrides
    @Override
    public void onSensorChanged(SensorEvent event) {
        if(event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            getAccelerometer(event);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        // register this class as a listener for the orientation and
        // accelerometer sensors
        derSensorManager.registerListener(this,
                derSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        // unregister listener
        super.onPause();
        derSensorManager.unregisterListener(this);
    }
    //Accelerometer scheiß: Bitte diese Kraftausdrücke unterlassen, danke
    private void getAccelerometer(SensorEvent event) {
        float[]values = event.values;

        float x = values[0];
        float y = values[1];
        float z = values[2];

        long actualTime = event.timestamp;

        if (actualTime >= lastUpdate + frameRate){
            mainCh.update_Position(x, y);
        }

        lastUpdate = actualTime;
        float Px = mainCh.getPx() + 0.01f;
        float Py = mainCh.getPy();
        TVoben.setText( Px + "" );
        TVunten.setText(Py + "");
    }
//test

    public static float getAx() {
        return Ax;
    }

    public static void setAx(float ax) {
        Ax = ax;
    }

    public static float getAy() {
        return Ay;
    }

    public static void setAy(float ay) {
        Ay = ay;
    }
}
