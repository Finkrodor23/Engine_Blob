package com.corporation.bufra.blob_engine;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ConfigurationInfo;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.corporation.bufra.blob_engine.OpenGL.MainRenderer;
import com.corporation.bufra.blob_engine.OpenGL.MainSurfaceView;

/**
 * Created by Marc on 31.07.2015.
 */
public class MainActivity extends Activity implements SensorEventListener{

    //Create Surface, Renderer and Manager
    private MainSurfaceView mGLView;
    private MainRenderer mGLViewRenderer = new MainRenderer();
    private ActivityManager mainManager;

    /*Sensor Manager*/
    private long lastUpdate;
    private static float Ax, Ay;
    private int frameRate;
    private SensorManager derSensorManager;

    /* Debugging Toasts */
    Toast toast;
    Toast toast_wave;
    int duration = Toast.LENGTH_SHORT;

    @Override
    protected void onCreate(Bundle savedInstanceState ){
        super.onCreate(savedInstanceState);

        //Standartwerte setzen:
        Ax = 0;
        Ay = 6.8f;

        lastUpdate = System.currentTimeMillis();
        frameRate = 25;

        derSensorManager = (SensorManager) this.getSystemService(SENSOR_SERVICE);

        Context context = getApplicationContext();

        mainManager = (ActivityManager)getSystemService(Context.ACTIVITY_SERVICE);
        ConfigurationInfo info = mainManager.getDeviceConfigurationInfo();
        boolean supportES2 = (info.reqGlEsVersion >= 0x20000);

        if(supportES2){
            //Einen Toast aussprechen -> sehr geil!
            toast = Toast.makeText(context, "DEVICE SUPPORTS OPENGL", duration);
            toast.show();


            mGLView = new MainSurfaceView(this);
            mGLView.setEGLContextClientVersion(2);
            mGLView.setRenderer(mGLViewRenderer);
            setContentView(mGLView);
        }
        else {
            Log.e("OpenGLES 2", "Your device doesnt support ES2., (" + info.reqGlEsVersion + ")");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
            startActivity(new Intent(this, EinstellungenActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if(event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            getAccelerometer(event);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    public void getAccelerometer(SensorEvent event) {
        float[]values = event.values;

        float x = values[0];
        float y = values[1];
        float z = values[2];

        long actualTime = event.timestamp;

        Context context = getApplicationContext();

        toast_wave = Toast.makeText(context, "x-K.: " + x + "    x-K.: " + y, duration);
        //toast_wave.show();

        if (actualTime >= lastUpdate + frameRate){
            mGLViewRenderer.setAx(x);
            mGLViewRenderer.setAy(y);
        }

        lastUpdate = actualTime;

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
}
