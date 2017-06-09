package com.example.user.lemmikki;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.support.v7.app.AppCompatActivity;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

import static com.example.user.lemmikki.R.id.EditTextName;
import static com.example.user.lemmikki.R.id.TextViewFood;
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

    public final int MaxFood = 1000;
    public final int MaxWash = 1000;
    public final int MaxPet = 1000;
    public final int MaxSleep = 1000;
    public int TimerCycle;
    public int BaseTimerCycle;
    public int Happiness;
    public int CurExp;
    public int NextLevelExp;
    public int CurLevel;
    public int CurFood;
    public int CurWash;
    public int CurPet;
    public int CurSleep;
    public int Loss;
    public int Gain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Loss=1;
        Gain=100;
        BaseTimerCycle = 3000;
        NextLevelExp = 1000;
//first time drawing interface
        final TextView TextViewFood = (TextView) findViewById(R.id.TextViewFood);
        final TextView TextViewWash = (TextView) findViewById(R.id.TextViewWash);
        final TextView TextViewPet = (TextView) findViewById(R.id.TextViewPet);
        final TextView TextViewSleep = (TextView) findViewById(R.id.TextViewSleep);
        final TextView textViewName = (TextView) findViewById(R.id.TextViewName);
        final TextView TextViewExp = (TextView) findViewById(R.id.TextViewExp);
        final TextView TextViewHappinnes = (TextView) findViewById(R.id.TextViewHappinnes);
        final TextView TextViewLevel = (TextView) findViewById(R.id.TextViewLevel);
        final EditText editTextName = (EditText) findViewById(R.id.EditTextName);

//      Restore preferences
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(this);
//      settings=null;
        if (settings!=null) {
            textViewName.setText(settings.getString("Name","Unnamed"));
            CurFood = (settings.getInt("CurFood", 1000));
            CurWash = (settings.getInt("CurWash", 1000));
            CurPet = (settings.getInt("CurPet", 1000));
            CurSleep = (settings.getInt("CurSleep", 1000));
            CurExp = (settings.getInt("CurExp", 0));
            CurLevel = (settings.getInt("CurLevel", 1));
            BackGroundColorCurr = (settings.getInt("BackGroundColor", 1));
        }

        final ImageView imageView = (ImageView) findViewById(R.id.egg);

        if (BackGroundColorCurr==0) {
            RelativeLayout bgElement = (RelativeLayout) findViewById(R.id.main_container);
            bgElement.setBackgroundColor(getResources().getColor(R.color.LightPink));
            textViewName.setTextColor(getResources().getColor(R.color.Black));
            TextViewFood.setTextColor(getResources().getColor(R.color.Black));
            TextViewWash.setTextColor(getResources().getColor(R.color.Black));
            TextViewPet.setTextColor(getResources().getColor(R.color.Black));
            TextViewSleep.setTextColor(getResources().getColor(R.color.Black));
            TextViewLevel.setTextColor(getResources().getColor(R.color.Black));
            TextViewExp.setTextColor(getResources().getColor(R.color.Black));
            TextViewHappinnes.setTextColor(getResources().getColor(R.color.Black));
            editTextName.setTextColor(getResources().getColor(R.color.Black));
            imageView.setImageResource(R.drawable.egg);
        } else {
            RelativeLayout bgElement = (RelativeLayout) findViewById(R.id.main_container);
            bgElement.setBackgroundColor(getResources().getColor(R.color.Black));
            textViewName.setTextColor(getResources().getColor(R.color.White));
            TextViewFood.setTextColor(getResources().getColor(R.color.White));
            TextViewWash.setTextColor(getResources().getColor(R.color.White));
            TextViewPet.setTextColor(getResources().getColor(R.color.White));
            TextViewSleep.setTextColor(getResources().getColor(R.color.White));
            TextViewLevel.setTextColor(getResources().getColor(R.color.White));
            TextViewExp.setTextColor(getResources().getColor(R.color.White));
            TextViewHappinnes.setTextColor(getResources().getColor(R.color.White));
            editTextName.setTextColor(getResources().getColor(R.color.White));
            imageView.setImageResource(R.drawable.sleepable);
        }


        TextViewFood.setText("Food: "+CurFood + "/" + MaxFood);
        TextViewWash.setText("Wash: "+CurWash + "/" + MaxWash);
        TextViewPet.setText("Pet: "+CurPet + "/" + MaxPet);
        TextViewSleep.setText("Sleep: "+CurSleep + "/" + MaxSleep);
//buttons
        ImageButton ImageButtonFeed = (ImageButton) findViewById(R.id.ImageButtonFeed);
        View.OnClickListener listenerFeed = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (BackGroundColorCurr == 0) {
                    imageView.setImageResource(R.drawable.stars);
                    if (CurFood<(MaxFood-Gain)) {
                        CurFood = CurFood + Gain;
                    } else {
                        CurFood = MaxFood;
                    }
                    TextViewFood.setText("Food: " + CurFood + "/" + MaxFood);
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            imageView.setImageResource(R.drawable.egg);
                        }
                    }, 1000);
                }
            }
        };
        ImageButtonFeed.setOnClickListener(listenerFeed);

        ImageButton ImageButtonWash = (ImageButton) findViewById(R.id.ImageButtonWash);
        View.OnClickListener listenerWash = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (BackGroundColorCurr == 0) {
                    imageView.setImageResource(R.drawable.washable);
                    if (CurWash<(MaxWash-Gain)) {
                        CurWash = CurWash + Gain;
                    } else {
                        CurWash = MaxWash;
                    }
                    TextViewWash.setText("Wash: " + CurWash + "/" + MaxWash);
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            imageView.setImageResource(R.drawable.egg);
                        }
                    }, 1000);
                }
            }
        };
        ImageButtonWash.setOnClickListener(listenerWash);

        ImageButton ImageButtonPet = (ImageButton) findViewById(R.id.ImageButtonPet);
        View.OnClickListener listenerPet = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (BackGroundColorCurr==0) {
                    imageView.setImageResource(R.drawable.lovable);
                    if (CurPet<(MaxPet-Gain)) {
                        CurPet = CurPet + Gain;
                    } else {
                        CurPet = MaxPet;
                    }
                    TextViewPet.setText("Pet: " + CurPet + "/" + MaxPet);
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            imageView.setImageResource(R.drawable.egg);
                        }
                    }, 1000);
                }
            }

        };
        ImageButtonPet.setOnClickListener(listenerPet);

        ImageButton ImageButtonSleep = (ImageButton) findViewById(R.id.ImageButtonSleep);
        View.OnClickListener listenerSleep = new View.OnClickListener() {
            @Override
            public void onClick(View v){
                if (BackGroundColorCurr==0) {
                    BackGroundColorCurr = 1;
                    RelativeLayout bgElement = (RelativeLayout) findViewById(R.id.main_container);
                    bgElement.setBackgroundColor(getResources().getColor(R.color.Black));
                    bgElement.setBackgroundColor(getResources().getColor(R.color.Black));
                    TextViewFood.setTextColor(getResources().getColor(R.color.White));
                    TextViewWash.setTextColor(getResources().getColor(R.color.White));
                    TextViewPet.setTextColor(getResources().getColor(R.color.White));
                    TextViewSleep.setTextColor(getResources().getColor(R.color.White));
                    textViewName.setTextColor(getResources().getColor(R.color.White));
                    editTextName.setTextColor(getResources().getColor(R.color.White));
                    TextViewLevel.setTextColor(getResources().getColor(R.color.White));
                    TextViewExp.setTextColor(getResources().getColor(R.color.White));
                    TextViewHappinnes.setTextColor(getResources().getColor(R.color.White));
                    imageView.setImageResource(R.drawable.sleepable);
                } else {
                    BackGroundColorCurr=0;
                    RelativeLayout bgElement = (RelativeLayout) findViewById(R.id.main_container);
                    bgElement.setBackgroundColor(getResources().getColor(R.color.LightPink));
                    TextViewFood.setTextColor(getResources().getColor(R.color.Black));
                    TextViewWash.setTextColor(getResources().getColor(R.color.Black));
                    TextViewPet.setTextColor(getResources().getColor(R.color.Black));
                    TextViewSleep.setTextColor(getResources().getColor(R.color.Black));
                    textViewName.setTextColor(getResources().getColor(R.color.Black));
                    editTextName.setTextColor(getResources().getColor(R.color.Black));
                    TextViewLevel.setTextColor(getResources().getColor(R.color.Black));
                    TextViewExp.setTextColor(getResources().getColor(R.color.Black));
                    TextViewHappinnes.setTextColor(getResources().getColor(R.color.Black));
                    imageView.setImageResource(R.drawable.egg);
                }
            }

        };
        ImageButtonSleep.setOnClickListener(listenerSleep);

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

//timer
        class TimerTaskMain extends TimerTask {
            @Override
            public void run() {
                switch (BackGroundColorCurr){
                    case 0:
                        CurFood = CurFood - Loss;
                        CurPet = CurPet - Loss;
                        CurSleep = CurSleep - Loss;
                        CurWash = CurWash - Loss;
                        Happiness = (CurPet*CurPet+CurWash*CurWash+CurPet*CurPet+CurSleep*CurSleep)/4000;
                        if (Happiness > 600){
                            CurExp++;
                        }
                        while (CurExp>=NextLevelExp) {
                            CurExp = 0;
                            CurLevel++;
                        }
//                        TimerCycle = BaseTimerCycle;
                        BaseTimerCycle = 3000;
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                TextViewFood.setText("Food: " + CurFood + "/" + MaxFood);
                                TextViewWash.setText("Wash: " + CurWash + "/" + MaxWash);
                                TextViewPet.setText("Pet: " + CurPet + "/" + MaxPet);
                                TextViewSleep.setText("Sleep: " + CurSleep + "/" + MaxSleep);
                                TextViewLevel.setText("Level: " + CurLevel);
                                TextViewExp.setText("Exp: " + CurExp + "/" + NextLevelExp);
                                TextViewHappinnes.setText("Happiness: " + Happiness);
                            }
                        });
                        break;
                    case 1:
                        CurFood = CurFood - Loss;
                        CurPet = CurPet - Loss;
                        CurWash = CurWash - Loss;
                        if (CurSleep<(MaxFood-4*Loss)) {
                            CurSleep = CurSleep + 4*Loss;
                        } else {
                            CurSleep = MaxSleep;
                        }
                        Happiness = (CurPet*CurPet+CurWash*CurWash+CurPet*CurPet+CurSleep*CurSleep)/4000;
                        if (Happiness>600){
                            CurExp++;
                        }
                        while (CurExp>=NextLevelExp) {
                            CurExp = 0;
                            CurLevel++;
                        }
//                        TimerCycle = BaseTimerCycle*2;
                        BaseTimerCycle = 6000;
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                TextViewFood.setText("Food: " + CurFood + "/" + MaxFood);
                                TextViewWash.setText("Wash: " + CurWash + "/" + MaxWash);
                                TextViewPet.setText("Pet: " + CurPet + "/" + MaxPet);
                                TextViewSleep.setText("Sleep: " + CurSleep + "/" + MaxSleep);
                                TextViewLevel.setText("Level: " + CurLevel);
                                TextViewExp.setText("Exp: " + CurExp + "/" + NextLevelExp);
                                TextViewHappinnes.setText("Happiness: " + Happiness);
                            }
                        });
                        break;
                }
            }
        }

        Timer timer=new Timer();
        TimerTask task=new TimerTaskMain();
        timer.scheduleAtFixedRate(task, 1, BaseTimerCycle);
//        timer.scheduleAtFixedRate(task, 1, TimerCycle);

    }

    @Override
    protected void onPause(){
        super.onPause();
        AppOpenedBefore = 1;
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = settings.edit();
        TextView nameTextView = (TextView) findViewById(R.id.TextViewName);
        editor.putString("Name", nameTextView.getText().toString());
        editor.putInt("CurFood", CurFood);
        editor.putInt("CurWash", CurWash);
        editor.putInt("CurPet", CurPet);
        editor.putInt("CurSleep", CurSleep);
        editor.putInt("CurExp", CurExp);
        editor.putInt("CurLevel", CurLevel);
        editor.putInt("BackGroundColor", BackGroundColorCurr);
        // Commit the edits!
        editor.apply();
    }



}



