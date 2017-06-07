package com.example.user.lemmikki;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.support.v7.app.AppCompatActivity;
import android.widget.RelativeLayout;
import android.widget.TextView;

import static com.example.user.lemmikki.R.id.EditText1;
import static com.example.user.lemmikki.R.id.button6;
import static com.example.user.lemmikki.R.id.main_container;

public class MainActivity extends AppCompatActivity {

    Handler handler = new Handler();
    int BackGroundColorCurr;
    int CheckerEditText1 = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final ImageView imageView = (ImageView) findViewById(R.id.egg);


        Button button1 = (Button) findViewById(R.id.button1);
        View.OnClickListener listener1 = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (BackGroundColorCurr == 0) {
                    imageView.setImageResource(R.drawable.stars);
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            imageView.setImageResource(R.drawable.egg);
                        }
                    }, 1000);
                }
            }
        };
        button1.setOnClickListener(listener1);

        Button button2 = (Button) findViewById(R.id.button2);
        View.OnClickListener listener2 = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (BackGroundColorCurr == 0) {
                    imageView.setImageResource(R.drawable.washable);
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            imageView.setImageResource(R.drawable.egg);
                        }
                    }, 1000);
                }
            }
        };
        button2.setOnClickListener(listener2);

        Button button3 = (Button) findViewById(R.id.button3);
        View.OnClickListener listener3 = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (BackGroundColorCurr==0) {
                    imageView.setImageResource(R.drawable.lovable);
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            imageView.setImageResource(R.drawable.egg);
                        }
                    }, 1000);
                }
            }

        };
        button3.setOnClickListener(listener3);

        Button button4 = (Button) findViewById(R.id.button4);
        View.OnClickListener listener4 = new View.OnClickListener() {
            @Override
            public void onClick(View v){
                if (BackGroundColorCurr==0) {
                    BackGroundColorCurr = 1;
                    RelativeLayout bgElement = (RelativeLayout) findViewById(R.id.main_container);
                    bgElement.setBackgroundColor(Color.BLACK);
                    imageView.setImageResource(R.drawable.sleepable);
                } else {
                    BackGroundColorCurr=0;
                    RelativeLayout bgElement = (RelativeLayout) findViewById(R.id.main_container);
                    bgElement.setBackgroundColor(getResources().getColor(R.color.LightPink));
                    imageView.setImageResource(R.drawable.egg);
                }
            }

        };
        button4.setOnClickListener(listener4);

        final Button button6 = (Button) findViewById(R.id.button6);
        View.OnClickListener listener6 = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView textView3 = (TextView) findViewById(R.id.TextView3);
                EditText editText1 = (EditText) findViewById(R.id.EditText1);
                switch (CheckerEditText1) {
                    case 0:
                        button6.setText("OK");
                        textView3.setVisibility(View.INVISIBLE);
                        editText1.setVisibility(View.VISIBLE);
                        CheckerEditText1 = 1;
                        break;
                    case 1:
                        button6.setText("Change name");
                        textView3.setText(editText1.getText().toString());
                        editText1.setVisibility(View.INVISIBLE);
                        textView3.setVisibility(View.VISIBLE);
                        CheckerEditText1 = 0;
                        break;
                    }
                }
        };
        button6.setOnClickListener(listener6);
    }

}


