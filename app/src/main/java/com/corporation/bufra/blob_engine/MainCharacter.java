package com.corporation.bufra.blob_engine;

/**
 * Created by Clemens on 29.07.2015.
 */
public class MainCharacter {
    float SPx, SPy; //Screenposition:
    float Px, Py;     //Real Position

    public MainCharacter() {
        Px = 0f;
        Py = 0f;
    }

    public void update_Position(float x_Ang, float y_Ang) {
        //Updating x Position
        if( x_Ang - 3.5  >= BlobActivity.getAx() ) {
            Px -= 0.02;        //Increment Position for negative slope
        }
        else if( x_Ang + 3.5 <= BlobActivity.getAx() ){
            Px += 0.02;         //Decrement Position for positive slope
        }
        else if( x_Ang - 0.75 >= BlobActivity.getAx() ){
            Px -= 0.01;         //Increment Position for negative slope
        }
        else if ( x_Ang + 0.75 <= BlobActivity.getAx() ){
            Px += 0.01;         //Decrement Position for positive slope
        }
        else{
            // Balanced test
        }

        if( y_Ang - 3.5  >= BlobActivity.getAy() ) {
            Py += 0.02;        //Increment Position for negative slope
        }
        else if( y_Ang + 3.5 <= BlobActivity.getAy() ){
            Py -= 0.02;         //Decrement Position for positive slope
        }
        else if( y_Ang - 0.75 >= BlobActivity.getAy() ){
            Py += 0.01;         //Increment Position for negative slope
        }
        else if ( y_Ang + 0.75 <= BlobActivity.getAy() ){
            Py -= 0.01;         //Decrement Position for positive slope
        }
        else{
            // Balanced
        }

        if(Px < 0){
            Px = 0;
        } else if(Px > 1) {
            Px = 1;
        }

        if(Py < 0) {
            Py = 0;
        } else if(Py > 1) {
            Py = 1;
        }
    }

    public float getPy() {
        return Py;
    }
    public float getPx() {
        return Px;
    }
}

