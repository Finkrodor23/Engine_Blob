package com.corporation.bufra.blob_engine.OpenGL;

import android.graphics.Rect;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView.Renderer;

import com.corporation.bufra.blob_engine.OpenGL.shapes.Rectangle;
import com.corporation.bufra.blob_engine.OpenGL.shapes.Triangle;
import com.corporation.bufra.blob_engine.OpenGL.shapes.figures.MainFigure;


import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created by Marc on 31.07.2015.
 */
public class MainRenderer implements Renderer, SensorEventListener {

   static  boolean show_triangle = false;
    public static void setTriangle(boolean set_Triangle){
        show_triangle = set_Triangle;
    }

    Triangle triangle;
    Rectangle[] rectangles;
    MainFigure mainChar;

    private float Ax = 0;

    public void setAy(float ay) {
        Ay = ay;
    }

    private float Ay = 0;

    public void setAx(float ax) {
        Ax = ax;
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        mainChar = new MainFigure(0.5f, 0.5f, -0.5f, -0.5f) ;
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {

    }

    @Override
    public void onDrawFrame(GL10 gl) {
        GLES20.glClearColor(0.8f, 0.0f, 0.0f, 1.0f);
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT);

        mainChar.setValues(rectangles, 0.001f*Ax, 0.001f*Ay);

       mainChar.draw();
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
