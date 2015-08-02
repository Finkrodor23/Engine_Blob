package com.corporation.bufra.blob_engine.OpenGL.shapes;

import android.opengl.GLES20;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

/**
 * Created by Marc on 31.07.2015.
 */
public class Triangle extends Shape {

    private FloatBuffer vertexBuffer;

    public float[] getVerticles() {
        return verticles;
    }

    private float verticles[] = {
             -0.3f,  0.0f, 0.0f,
              0.3f,  0.0f, 0.0f,
              0.0f,  0.3f, 0.0f
    };
    private float color[] = new float[] { 0.0f, 0.0f, 1.0f, 1.0f};

    private final String vertexShaderCode =
            "attribute vec4 vPosition;" +
            "void main() {" +
            "gl_Position = vPosition;" +
            "}";
    private final String fragmentShaderCode =
            "precision mediump float;" +
            "uniform vec4 vColor;" +
            "void main() {" +
            " gl_FragColor = vColor;" +
            "}";
    private int shaderProgram;

    public static int loadShader(int type, String shaderCode) {
        int shader = GLES20.glCreateShader(type);
        GLES20.glShaderSource(shader, shaderCode);
        GLES20.glCompileShader(shader);
        return shader;
    }

    public Triangle() {
        ByteBuffer bb = ByteBuffer.allocateDirect(verticles.length * 4);
        bb.order(ByteOrder.nativeOrder());

        vertexBuffer = bb.asFloatBuffer();
        vertexBuffer.put(verticles);
        vertexBuffer.position(0);

        int vertexShader = loadShader(GLES20.GL_VERTEX_SHADER, vertexShaderCode);
        int fragmentShader = loadShader(GLES20.GL_FRAGMENT_SHADER, fragmentShaderCode);

        shaderProgram = GLES20.glCreateProgram();
        GLES20.glAttachShader(shaderProgram, vertexShader);
        GLES20.glAttachShader(shaderProgram, fragmentShader);
        GLES20.glLinkProgram(shaderProgram);



    }

    public Triangle( float x1, float x2, float y1, float y2, float z1, float z2){
        this();
        verticles[0] =  x1;
        verticles[1] =  x2;

        verticles[3] =  y1;
        verticles[4] =  y2;

        verticles[6] =  z1;
        verticles[7] =  z2;

    }

    public void draw(){
        GLES20.glUseProgram(shaderProgram);

        int positionAttrib = GLES20.glGetAttribLocation(shaderProgram, "vPosition");
        GLES20.glEnableVertexAttribArray(positionAttrib);

        GLES20.glVertexAttribPointer(positionAttrib, 3, GLES20.GL_FLOAT, false, 0, vertexBuffer);

        int colorUniform = GLES20.glGetUniformLocation(shaderProgram, "Color");

        GLES20.glUniform4fv(colorUniform, 1, color, 0);
        GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, verticles.length / 3);
        GLES20.glDisableVertexAttribArray(positionAttrib);
    }

    public void setValues( float x2, float y2){
        //Check if triangle is out of x Bounds
        if(verticles[0] - x2 > -1f && verticles[0] - x2 < 1f
        && verticles[3] - x2 > -1f && verticles[3] - x2 < 1f
        && verticles[6] - x2 > -1f && verticles[6] - x2 < 1f) {
            verticles[0] -= x2;
            verticles[3] -= x2;
            verticles[6] -= x2;
        }

        //Check if triangle is out of y Bounds
        if(verticles[1] - y2 > -1f && verticles[1] - y2 < 1f
        && verticles[4] - y2 > -1f && verticles[4] - y2 < 1f
        && verticles[7] - y2 > -1f && verticles[7] - y2 < 1f) {
            verticles[1] -= y2;
            verticles[4] -= y2;
            verticles[7] -= y2;
        }
        //verticles[5] += y2;



        ByteBuffer bb = ByteBuffer.allocateDirect(verticles.length * 4);
        bb.order(ByteOrder.nativeOrder());

        vertexBuffer = bb.asFloatBuffer();
        vertexBuffer.put(verticles);
        vertexBuffer.position(0);

    }
    public void outOfBounds(){
        return;
    }
}
