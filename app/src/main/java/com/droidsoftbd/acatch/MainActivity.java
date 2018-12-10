package com.droidsoftbd.acatch;

import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    //Frame
    private FrameLayout gameFrame;
    private int frameheight, frameWidth, initialFrameWidth;
    private LinearLayout startLayout;

    //Image
    private ImageView box, black, orange, pink;
    private Drawable imageBoxRight, imageBoxLeft;

    //Size
    private int boxSize;

    //Position
    private float boxX, boxY;
    private float blackX, blackY;
    private float orangeX, orangeY;
    private float pinkX, pinkY;

    //Score
    private TextView scoreLebel, highScoreLebel;
    private int score, highScore, timeCount;

    //Class
    private Timer timer;
    private Handler handler;

    //Status
    private boolean start_flag;
    private boolean action_flag;
    private boolean pink_flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        handler = new Handler();
        start_flag = false;
        action_flag = false;
        pink_flag = false;

        gameFrame= findViewById(R.id.gameFrame);
        startLayout = findViewById(R.id.startLayout);
        box =findViewById(R.id.box);
        black= findViewById(R.id.pink);
        orange = findViewById(R.id.orange);
        pink =findViewById(R.id.pink);
        scoreLebel= findViewById(R.id.scoreLebel);
        highScoreLebel = findViewById(R.id.highScoreLebel);

        imageBoxLeft = getResources().getDrawable(R.drawable.box_left);
        imageBoxRight = getResources().getDrawable(R.drawable.box_right);

    }

    public void startGame(View view) {

        start_flag = true;
        startLayout.setVisibility(View.INVISIBLE);

        if (frameheight == 0) {
            frameheight = gameFrame.getHeight();
            frameWidth = gameFrame.getWidth();
            initialFrameWidth = frameWidth;

            boxSize = box.getHeight();
            boxX = box.getX();
            boxY = box.getY();
        }

        box.setX(0.0f);
        black.setY(3000.0f);
        orange.setY(3000.0f);
        pink.setY(3000.0f);

        blackY =  black.getY();
        orangeY = orange.getY();
        pinkY = pink.getY();

        box.setVisibility(View.VISIBLE);
        black.setVisibility(View.VISIBLE);
        orange.setVisibility(View.VISIBLE);
        pink.setVisibility(View.VISIBLE);

        timeCount = 0;
        score = 0;
        scoreLebel.setText(" Score : 0");

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                changePos();
            }
        },0,20);

    }

    public void quitGame(View view) {
    }

    private void changePos() {

        //Move Box
        if (action_flag) {
            //Touching
            boxX +=14;
            box.setImageDrawable(imageBoxRight);

        } else {
            //Releasing
            boxX -= 14;
            box.setImageDrawable(imageBoxLeft);
        }

        //Check box position
        if (boxX < 0){
            boxX = 0;
            box.setImageDrawable(imageBoxRight);
        }

        if (frameWidth - boxSize < boxX) {
            boxX = frameWidth - boxSize;
            box.setImageDrawable(imageBoxLeft);
        }

        box.setX(boxX);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (start_flag) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                action_flag = true;
            } else if (event.getAction() == MotionEvent.ACTION_UP) {
                action_flag = false;
            }
        }
        return true;
    }
}
