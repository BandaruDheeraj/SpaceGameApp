package com.example.dheeraj.spacegameapp;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

/**
 * Created by Dheeraj on 6/13/2016.
 */
public class Craft  extends Sprite{
    Bitmap crafts;
    Rect dst;
    int z;
    int a;
    public Craft(MainActivity.CollisionEx collisionEx, Bitmap craft){
        super(collisionEx, craft);
        crafts = craft;
    }

    public void setX(int bNX){
        x = bNX;
        z= bNX;
    }

    public void setY(int bNY){
        y = bNY;
        a = bNY;
    }
    public int getZ(){return z;}
    public int getA(){return a;}

    public Rect getDST(){
        return dst;
    }

    @Override
    public void onDraw(Canvas canvas){
        Rect src = new Rect(0,0,width,height);
        dst = new Rect(x,y,x+width,y+height);
        canvas.drawBitmap(crafts, null,dst,null);
    }

    public void endGame(){

    }
}
