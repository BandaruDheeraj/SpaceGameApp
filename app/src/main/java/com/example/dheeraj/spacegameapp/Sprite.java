package com.example.dheeraj.spacegameapp;

/**
 * Created by Dheeraj on 5/30/2016.
 */

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;



public class Sprite {

    int x,y;
    int xSpeed, ySpeed;
    int height , width;
    Bitmap aliens;
    MainActivity.CollisionEx ce;
    Rect r;
    boolean first = true;

    public Sprite(MainActivity.CollisionEx collisionEx, Bitmap alien) {
        aliens = alien;
        ce = collisionEx;
        height = alien.getHeight();
        width = alien.getWidth();
        x = (int) (Math.random()*ce.getWidth());
        y = (int) (Math.random()*ce.getHeight());
        xSpeed = (int)((Math.random()*10)+1);
        ySpeed = (int)((Math.random()*10)+1);
    }




    public void update(){
        if(x>= ce.getWidth()-width-xSpeed) {
            xSpeed = (int) (-(Math.random() * 10)+1);
            ySpeed = (int) (-(Math.random() * 10) +1);
        }
        if(y> ce.getHeight()-height-ySpeed){
            ySpeed =(int) (-(Math.random() * 10)+1);
            xSpeed = (int) (-(Math.random() * 10)+1);
        }
        if(x + xSpeed <= 0) {
            x = 0;
            ySpeed = (int) (-(Math.random() * 10) +1);
            xSpeed = (int) (-(Math.random() * 10)+1);
        }
        if(y +ySpeed<= 0){
            y = 0;
            ySpeed = (int) (-(Math.random() * 10) +1);
            xSpeed = (int) (-(Math.random() * 10)+1);
        }
        if(x + xSpeed <= 0 && y +ySpeed<= 0){
            x = (int) (Math.random()*(ce.getWidth())+1);
            y = (int) (Math.random()*(ce.getHeight())+1);
        }

        x += xSpeed;
        y += ySpeed;


    }
    public void onDraw(Canvas canvas) {

            update();

        Rect src = new Rect(0,0,width,height);
        Rect dst = new Rect(x,y,x+width,y+height);
        canvas.drawBitmap(aliens,null,dst,null);
    }
    public Rect getBounds(){
        r= new Rect(x, y, x+width, y+height);
        return r;
    }

    public boolean checkCollision(Sprite s, Craft t) {
        Rect mySprite = s.getBounds();
        return mySprite.intersect(t.getDST());
    }
}