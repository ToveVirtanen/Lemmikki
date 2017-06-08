package com.example.user.lemmikki;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.support.v7.app.AppCompatActivity;
import android.widget.RelativeLayout;
import android.widget.TextView;

import static com.example.user.lemmikki.R.id.EditTextName;
import static com.example.user.lemmikki.R.id.TextViewName;
import static com.example.user.lemmikki.R.id.buttonFeed;
import static com.example.user.lemmikki.R.id.buttonName;
import static com.example.user.lemmikki.R.id.buttonPet;
import static com.example.user.lemmikki.R.id.buttonSleep;
import static com.example.user.lemmikki.R.id.buttonWash;
import static com.example.user.lemmikki.R.id.main_container;

public class MainActivity extends AppCompatActivity {

    Handler handler = new Handler();
    int BackGroundColorCurr = 0;
    int CheckerEditTextName = 0;
    int AppOpenedBefore = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//      Restore preferences
        TextView textViewName = (TextView) findViewById(R.id.TextViewName);
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(this);
//      settings=null;
        if (settings!=null) {
            textViewName.setText(settings.getString("name","unnamed"));
        }



        final ImageView imageView = (ImageView) findViewById(R.id.egg);

        Button buttonFeed = (Button) findViewById(R.id.buttonFeed);
        View.OnClickListener listenerFeed = new View.OnClickListener() {
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
        buttonFeed.setOnClickListener(listenerFeed);

        Button buttonWash = (Button) findViewById(R.id.buttonWash);
        View.OnClickListener listenerWash = new View.OnClickListener() {
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
        buttonWash.setOnClickListener(listenerWash);

        Button buttonPet = (Button) findViewById(R.id.buttonPet);
        View.OnClickListener listenerPet = new View.OnClickListener() {
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
        buttonPet.setOnClickListener(listenerPet);

        Button buttonSleep = (Button) findViewById(R.id.buttonSleep);
        View.OnClickListener listenerSleep = new View.OnClickListener() {
            @Override
            public void onClick(View v){
                if (BackGroundColorCurr==0) {
                    BackGroundColorCurr = 1;
                    RelativeLayout bgElement = (RelativeLayout) findViewById(R.id.main_container);
                    bgElement.setBackgroundColor(getResources().getColor(R.color.navy));
                    imageView.setImageResource(R.drawable.sleepable);
                } else {
                    BackGroundColorCurr=0;
                    RelativeLayout bgElement = (RelativeLayout) findViewById(R.id.main_container);
                    bgElement.setBackgroundColor(getResources().getColor(R.color.LightPink));
                    imageView.setImageResource(R.drawable.egg);
                }
            }

        };
        buttonSleep.setOnClickListener(listenerSleep);

        final Button buttonName = (Button) findViewById(R.id.buttonName);
        View.OnClickListener listenerName = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView TextViewName = (TextView) findViewById(R.id.TextViewName);
                EditText editTextName = (EditText) findViewById(R.id.EditTextName);
                switch (CheckerEditTextName) {
                    case 0:
                        buttonName.setText("Ok");
                        TextViewName.setVisibility(View.INVISIBLE);
                        editTextName.setVisibility(View.VISIBLE);
                        CheckerEditTextName = 1;
                        break;
                    case 1:
                        buttonName.setText("Change name");
                        TextViewName.setText(editTextName.getText().toString());
                        editTextName.setVisibility(View.INVISIBLE);
                        TextViewName.setVisibility(View.VISIBLE);
                        CheckerEditTextName = 0;
                        break;
                    }
                }
        };
        buttonName.setOnClickListener(listenerName);

//        final Button buttonDelSet = (Button) findViewById(R.id.buttonDelSet);
//        View.OnClickListener listenerDelSet = new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                settings=null;
//            }
//        };
//        buttonDelSet.setOnClickListener(listenerDelSet);
    }

    @Override
    protected void onPause(){
        super.onPause();
        AppOpenedBefore = 1;
        // We need an Editor object to make preference changes.
        // All objects are from android.context.Context
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = settings.edit();
        TextView nameTextView = (TextView) findViewById(R.id.TextViewName);
        editor.putString("name", nameTextView.getText().toString());
        // Commit the edits!
        editor.apply();
    }



}



