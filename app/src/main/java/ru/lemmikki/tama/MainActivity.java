package ru.lemmikki.tama;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.support.v7.app.AppCompatActivity;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

import ru.lemmikki.tama.R;

public class MainActivity extends AppCompatActivity {

    Handler handler = new Handler();
    int BackGroundColorCurr = 1;
    int CheckerEditTextName = 0;
    int AppOpenedBefore = 0;

    public final int MaxFood = 100;
    public final int MaxWash = 100;
    public final int MaxPet = 100;
    public final int MaxSleep = 100;
    public int CurLevel = 0;
    public int CurExp = 0;

    public int TimerCycle;
    public int BaseTimerCycle;
    public long Happiness;
    public long MillsOnExit;

    public int NextLevelExp;
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
    int egg[] = new int[]{R.drawable.egg, R.drawable.egg,R.drawable.egg,R.drawable.dog, R.drawable.dog,R.drawable.dog,R.drawable.rabbit,R.drawable.rabbit,R.drawable.rabbit};
    int washable[] = new int[]{R.drawable.washable, R.drawable.washable,R.drawable.washable,R.drawable.dogwash,R.drawable.dogwash,R.drawable.dogwash, R.drawable.rabbitwashable, R.drawable.rabbitwashable, R.drawable.rabbitwashable};
    int eatable[] = new int[]{R.drawable.stars,R.drawable.stars,R.drawable.stars, R.drawable.dogeating,R.drawable.dogeating,R.drawable.dogeating, R.drawable.rabbiteatable, R.drawable.rabbiteatable, R.drawable.rabbiteatable};
    int sleepable[] = new int[]{R.drawable.sleepable, R.drawable.sleepable,R.drawable.sleepable,R.drawable.dogsleep,R.drawable.dogsleep,R.drawable.dogsleep, R.drawable.rabbitsleepable, R.drawable.rabbitsleepable, R.drawable.rabbitsleepable};
    int lovable[] = new int[]{R.drawable.lovable, R.drawable.lovable,R.drawable.lovable,R.drawable.doglove,R.drawable.doglove,R.drawable.doglove, R.drawable.rabbitlovable, R.drawable.rabbitlovable, R.drawable.rabbitlovable};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Loss=1;
        Gain=10;
        BaseTimerCycle = 3000;
        NextLevelExp = 100;
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
            CurFood = (settings.getLong("CurFood", 100));
            CurWash = (settings.getLong("CurWash", 100));
            CurPet = (settings.getLong("CurPet", 100));
            CurSleep = (settings.getLong("CurSleep", 100));
            CurExp = (settings.getInt("CurExp", 0));
            CurLevel = (settings.getInt("CurLevel", 0));
            BackGroundColorCurr = (settings.getInt("BackGroundColor", 1));
            MillsOnExit = (settings.getLong("MilsOnExit", 1));
        }

        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss Z", new Locale("en"));
        MyMills=MillsOnExit;
        currentMils=System.currentTimeMillis();
        doIt=currentMils-MyMills;
        System.out.print(doIt);

//        switch (BackGroundColorCurr){
//            case 0:
//                CurFood = CurFood - (doIt*Loss)/1000;
//                if (CurFood<0){
//                    CurFood = 0;
//                }
//                CurWash = CurWash - (doIt*Loss)/1000;
//                if (CurWash<0){
//                    CurWash = 0;
//                }
//                CurPet = CurPet - (doIt*Loss)/1000;
//                if (CurPet<0){
//                    CurPet = 0;
//                }
//                CurSleep = CurSleep - (doIt*Loss)/1000;
//                if (CurSleep<0){
//                    CurSleep = 0;
//                }
//                break;
//            case 1:
//                CurFood = CurFood - (doIt*Loss)/1000;
//                if (CurFood<0){
//                    CurFood = 0;
//                }
//                CurWash = CurWash - (doIt*Loss)/1000;
//                if (CurWash<0){
//                    CurWash = 0;
//                }
//                CurPet = CurPet - (doIt*Loss)/1000;
//                if (CurPet<0){
//                    CurPet = 0;
//                }
//                CurSleep = CurSleep + (doIt*Loss)/4000;
//                if (CurSleep>MaxSleep){
//                    CurSleep = 1000;
//                }
//                break;
//        }

        final ImageView imageView = (ImageView) findViewById(R.id.egg);
        imageView.setImageResource(egg[CurLevel]);

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
//            imageView.setImageResource(R.drawable.egg);
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
//            imageView.setImageResource(R.drawable.sleepable);
        }


        TextViewFood.setText("Food: "+CurFood + "/" + MaxFood);
        TextViewWash.setText("Wash: "+CurWash + "/" + MaxWash);
        TextViewPet.setText("Pet: "+CurPet + "/" + MaxPet);
        TextViewSleep.setText("Sleep: "+CurSleep + "/" + MaxSleep);
        TextViewLevel.setText("Level: " + CurLevel);
        TextViewExp.setText("Exp: " + CurExp + "/" + NextLevelExp);
        TextViewHappinnes.setText("Happiness: " + Happiness);
//buttons
        ImageButton ImageButtonFeed = (ImageButton) findViewById(R.id.ImageButtonFeed);
        View.OnClickListener listenerFeed = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (BackGroundColorCurr == 0) {
                    imageView.setImageResource(eatable[CurLevel]);
                    if (CurFood<(MaxFood-Gain)) {
                        CurFood = CurFood + Gain;
                    } else {
                        CurFood = MaxFood;
                    }
                    TextViewFood.setText("Food: " + CurFood + "/" + MaxFood);
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            imageView.setImageResource(egg[CurLevel]);
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
                    imageView.setImageResource(washable[CurLevel]);
                    if (CurWash<(MaxWash-Gain)) {
                        CurWash = CurWash + Gain;
                    } else {
                        CurWash = MaxWash;
                    }
                    TextViewWash.setText("Wash: " + CurWash + "/" + MaxWash);
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            imageView.setImageResource(egg[CurLevel]);
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
                    imageView.setImageResource(lovable[CurLevel]);
                    if (CurPet<(MaxPet-Gain)) {
                        CurPet = CurPet + Gain;
                    } else {
                        CurPet = MaxPet;
                    }
                    TextViewPet.setText("Pet: " + CurPet + "/" + MaxPet);
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            imageView.setImageResource(egg[CurLevel]);
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
                    imageView.setImageResource(sleepable[CurLevel]);
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
                    imageView.setImageResource(egg[CurLevel]);
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
//        buttonDelSet.setOnClickListener(listenerDelSet)

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
                        Happiness = (CurPet+CurWash+CurPet+CurSleep)/4;
                        if (Happiness > 50){
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
                        Happiness = (CurPet+CurWash+CurFood+CurSleep)/4;
                        if (Happiness > 50){
                            CurExp++;
                        }
                        while (CurExp>=NextLevelExp) {
                            CurExp = 0;
                            CurLevel++;
                        }
                        if (CurSleep>=1000) {
                            CurSleep=MaxSleep;
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
        editor.apply();
    }

}



