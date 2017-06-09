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
import android.widget.Switch;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
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
    public long Happiness;
    public long MillsOnExit;
    public int CurExp;
    public int NextLevelExp;
    public int CurLevel;
    public long CurFood;
    public long CurWash;
    public long CurPet;
    public long CurSleep;
    public int Loss;
    public int Gain;
    public long doIt;
    public long MyMills;
    public long currentMils;

    String dateStr = new String();


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
            CurFood = (settings.getLong("CurFood", 1000));
            CurWash = (settings.getLong("CurWash", 1000));
            CurPet = (settings.getLong("CurPet", 1000));
            CurSleep = (settings.getLong("CurSleep", 1000));
            CurExp = (settings.getInt("CurExp", 0));
            CurLevel = (settings.getInt("CurLevel", 1));
            BackGroundColorCurr = (settings.getInt("BackGroundColor", 1));
            MillsOnExit = (settings.getLong("MilsOnExit", 1));
        }

        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss Z", new Locale("en"));
        MyMills=MillsOnExit;
        //получаем текущие миллисекунды
        currentMils=System.currentTimeMillis();
        //теперь можно сравнивать
        doIt=currentMils-MyMills;
        System.out.print(doIt);

        switch (BackGroundColorCurr){
            case 0:
                CurFood = CurFood - (doIt*Loss)/1000;
                if (CurFood<0){
                    CurFood = 0;
                }
                CurWash = CurWash - (doIt*Loss)/1000;
                if (CurWash<0){
                    CurWash = 0;
                }
                CurPet = CurPet - (doIt*Loss)/1000;
                if (CurPet<0){
                    CurPet = 0;
                }
                CurSleep = CurSleep - (doIt*Loss)/1000;
                if (CurSleep<0){
                    CurSleep = 0;
                }
                break;
            case 1:
                CurFood = CurFood - (doIt*Loss)/1000;
                if (CurFood<0){
                    CurFood = 0;
                }
                CurWash = CurWash - (doIt*Loss)/1000;
                if (CurWash<0){
                    CurWash = 0;
                }
                CurPet = CurPet - (doIt*Loss)/1000;
                if (CurPet<0){
                    CurPet = 0;
                }
                CurSleep = CurSleep + (doIt*Loss)/4000;
                if (CurSleep>MaxSleep){
                    CurSleep = 1000;
                }
                break;
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
        TextViewLevel.setText("Level: " + CurLevel);
        TextViewExp.setText("Exp: " + CurExp + "/" + NextLevelExp);
        TextViewHappinnes.setText("Happiness: " + Happiness);
//buttons
        Button buttonFeed = (Button) findViewById(R.id.buttonFeed);
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
        buttonFeed.setOnClickListener(listenerFeed);

        Button buttonWash = (Button) findViewById(R.id.buttonWash);
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
        buttonWash.setOnClickListener(listenerWash);

        Button buttonPet = (Button) findViewById(R.id.buttonPet);
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
        buttonPet.setOnClickListener(listenerPet);

        Button buttonSleep = (Button) findViewById(R.id.buttonSleep);
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

//timer
        class TimerTaskMain extends TimerTask {
            @Override
            public void run() {
                switch (BackGroundColorCurr){
                    case 0:
                        CurFood = CurFood - Loss;
                        if (CurFood<0){
                            CurFood=0;
                        }
                        CurPet = CurPet - Loss;
                        if (CurPet<0){
                            CurPet=0;
                        }
                        CurSleep = CurSleep - Loss;
                        if (CurSleep<0){
                            CurSleep=0;
                        }
                        CurWash = CurWash - Loss;
                        if (CurWash<0){
                            CurWash=0;
                        }
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
                        if (CurFood<0){
                            CurFood=0;
                        }
                        CurPet = CurPet - Loss;
                        if (CurPet<0){
                            CurPet=0;
                        }
                        CurWash = CurWash - Loss;
                        if (CurWash<0){
                            CurWash=0;
                        }
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
        timer.scheduleAtFixedRate(task, 3000, BaseTimerCycle);
//        timer.scheduleAtFixedRate(task, 1, TimerCycle);

    }

    @Override
    protected void onPause(){
        super.onPause();
        AppOpenedBefore = 1;
        MillsOnExit=System.currentTimeMillis();
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = settings.edit();
        TextView nameTextView = (TextView) findViewById(R.id.TextViewName);
        editor.putString("Name", nameTextView.getText().toString());
        editor.putLong("CurFood", CurFood);
        editor.putLong("CurWash", CurWash);
        editor.putLong("CurPet", CurPet);
        editor.putLong("CurSleep", CurSleep);
        editor.putLong("MilsOnExit", MillsOnExit);
        editor.putInt("CurExp", CurExp);
        editor.putInt("CurLevel", CurLevel);
        editor.putInt("BackGroundColor", BackGroundColorCurr);
        // Commit the edits!
        editor.apply();
    }

}



