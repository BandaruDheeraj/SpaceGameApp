package com.example.dheeraj.spacegameapp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Context;
import android.graphics.*;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Switch;

import android.graphics.Paint;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends Activity implements View.OnTouchListener {
    CollisionEx v;
    Bitmap craft;
    //float x,y;
    Sprite sprite;
    Bitmap alien;
    Craft craft2;
    Calendar c = Calendar.getInstance();
    int abc = Calendar.SECOND;
    int numAliens = 0 ;
    int score = 0;
    long start = System.currentTimeMillis()/1000;
    long newTime;
    int prevScore = score;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        craft = BitmapFactory.decodeResource(getResources(),R.drawable.craft);
        alien = BitmapFactory.decodeResource(getResources(),R.drawable.alien);
       // x = y = 0;
        v = new CollisionEx(this);
        v.setOnTouchListener(this);

        setContentView(v);

    }


    @Override
    protected void onPause(){
        super.onPause();
        v.pause();
    }
    @Override
    protected void onResume(){
        super.onResume();
        v.resume();
    }
    public class CollisionEx extends SurfaceView implements Runnable {

        Thread t = null;
        SurfaceHolder holder;
        boolean isItOk = false;
        //Sprite sprite;
        //Sprite sprite2;
        ArrayList<Sprite> aliens = new ArrayList<Sprite>();
        boolean isSpriteLoaded = false;

        public CollisionEx(Context context) {
            super(context);
            holder = getHolder();
        }



        @Override
        public void run() {
            while (isItOk) {

                //perform canvas drawing
                if (!holder.getSurface().isValid()) {
                    continue;
                }
                if (!isSpriteLoaded){

                    for(int z = 0 ; z<= 6 ; z++) {
                        aliens.add(new Sprite(this, alien));
                    }

                    craft2 = new Craft(this, craft);
                isSpriteLoaded =true;
                }
                Canvas c = holder.lockCanvas();
                onDraw(c);
                holder.unlockCanvasAndPost(c);

                newTime = System.currentTimeMillis()/1000;
                score = (int)(newTime-start);
                //if(score != prevScore)
                score++;
                prevScore = score;
            }
        }


        protected void onDraw(Canvas canvas) {
            Paint p = new Paint();
            Paint m = new Paint();
            m.setColor(Color.BLACK);
            m.setTextSize(150);

            p.setColor(Color.WHITE);
            p.setTextSize(50);
            canvas.drawARGB(255, 0, 0, 0);
            canvas.drawText("Score: "+score,0,50,p);

            craft2.onDraw(canvas);

            for(Sprite s: aliens)
                s.onDraw(canvas);

            for(Sprite s: aliens) {


                if (s.checkCollision(s, craft2)) {
                    //pause();
                    Intent i = new Intent(MainActivity.this, GameOver.class);
                    startActivity(i);
                }


            }
        }

        public void pause() {
            isItOk = false;
            while (true) {

                try {
                    t.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                break;

            }

            t = null;
        }


        public void resume() {
            isItOk = true;
            t = new Thread(this);
            t.start();
        }
    }


    @Override
    public boolean onTouch(View v, MotionEvent me) {

        switch(me.getAction()) {
            case MotionEvent.ACTION_MOVE:
                craft2.setX( (int) me.getX());
                craft2.setY((int)me.getY());
                break;
            case MotionEvent.ACTION_DOWN:
                craft2.setX( (int) me.getX());
                craft2.setY((int)me.getY());
                break;

        }

    return true;
    }




}


