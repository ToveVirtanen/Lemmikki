package com.example.user.lemmikki;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.support.v7.app.AppCompatActivity;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final ImageView imageView = (ImageView) findViewById(R.id.egg);


        Button button1 = (Button) findViewById(R.id.button1);
        View.OnClickListener listener1 = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageView.setImageResource(R.drawable.stars);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        imageView.setImageResource(R.drawable.egg);
                    }
                }, 1000);
            }

        };
        button1.setOnClickListener(listener1);

        Button button2 = (Button) findViewById(R.id.button2);
        View.OnClickListener listener2 = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageView.setImageResource(R.drawable.washable);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        imageView.setImageResource(R.drawable.egg);
                    }
                }, 1000);
            }

        };
        button2.setOnClickListener(listener2);

        Button button3 = (Button) findViewById(R.id.button3);
        View.OnClickListener listener3 = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageView.setImageResource(R.drawable.lovable);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        imageView.setImageResource(R.drawable.egg);
                    }
                }, 1000);
            }

        };
        button3.setOnClickListener(listener3);


        Button button4 = (Button) findViewById(R.id.button4);
        View.OnClickListener listener4 = new View.OnClickListener() {
            @Override
            public void onClick(View v){
                if (get.RGB==0) {
                    BackGroundColorCurr = 1;
                    RelativeLayout bgElement = (RelativeLayout) findViewById(R.id.main_container);
                    bgElement.setBackgroundColor(Color.BLACK);
                } else {
                    BackGroundColorCurr=0;
                    RelativeLayout bgElement = (RelativeLayout) findViewById(R.id.main_container);
                    bgElement.setBackgroundColor(Color.GRAY);
                }
            }

        };
        button4.setOnClickListener(listener4);

    }
}


