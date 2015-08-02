package com.corporation.bufra.blob_engine.OpenGL.shapes;

/**
 * Created by Marc on 02.08.2015.
 */
public class Rectangle extends Shape {
    Triangle upperleft, downerright;
    public float verticles[] = {
            -0.3f, 0.0f, 0.0f,
            0.3f, 0.3f, 0.0f
    };

    public Rectangle(){
        upperleft = new Triangle(verticles[0], verticles[1], verticles[3], verticles[1], verticles[0], verticles[4] );
        downerright = new Triangle(verticles[3], verticles[4], verticles[3], verticles[1], verticles[0], verticles[4] );
    }

    public Rectangle(float x1, float x2, float y1, float y2) {
        upperleft = new Triangle(x1, x2, y1, x2, x1, y2 );
        downerright = new Triangle(y1, y2, y1, x2, x1, y2 );
    }

    public void draw() {
        upperleft.draw();
        downerright.draw();
    }

    public void setValues( float x2, float y2){
        upperleft.setValues(x2, y2);
        downerright.setValues(x2, y2);
    }
}
