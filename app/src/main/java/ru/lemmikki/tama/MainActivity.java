package ru.lemmikki.tama;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends AppCompatActivity {

    public final int MaxFood = 1000;
    public final int MaxWash = 1000;
    public final int MaxPet = 1000;
    public final int MaxSleep = 1000;
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
    Handler handler = new Handler();
    int BackGroundColorCurr = 0;
    boolean CheckerEditTextName = false;

    TextView TextViewFood;
    TextView TextViewWash;
    TextView TextViewPet;
    TextView TextViewSleep;
    TextView textViewName;
    TextView TextViewExp;
    TextView TextViewHappiness;
    TextView TextViewLevel;
    EditText editTextName;
    ImageView imageView;

    int egg[] = new int[]{0, R.drawable.egg, R.drawable.dog, R.drawable.rabbit};
    int washable[] = new int[]{0, R.drawable.washable, R.drawable.dogwash, R.drawable.rabbitwashable};
    int eatable[] = new int[]{0, R.drawable.stars, R.drawable.dogeating, R.drawable.rabbiteating};
    int sleepable[] = new int[]{0, R.drawable.sleepable, R.drawable.dogsleep, R.drawable.rabbitsleepable};
    int lovable[] = new int[]{0, R.drawable.lovable, R.drawable.doglove, R.drawable.rabbitlovable};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Loss = 1;
        Gain = 100;
        BaseTimerCycle = 3000;
        NextLevelExp = 1000;

        TextViewFood = (TextView) findViewById(R.id.TextViewFood);
        TextViewWash = (TextView) findViewById(R.id.TextViewWash);
        TextViewPet = (TextView) findViewById(R.id.TextViewPet);
        TextViewSleep = (TextView) findViewById(R.id.TextViewSleep);
        textViewName = (TextView) findViewById(R.id.TextViewName);
        TextViewExp = (TextView) findViewById(R.id.TextViewExp);
        TextViewHappiness = (TextView) findViewById(R.id.TextViewHappiness);
        TextViewLevel = (TextView) findViewById(R.id.TextViewLevel);
        editTextName = (EditText) findViewById(R.id.EditTextName);

        imageView = (ImageView) findViewById(R.id.egg);

        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(this);
        if (settings != null) {
            textViewName.setText(settings.getString("Name", "Unnamed"));
            CurFood = (settings.getLong("CurFood", 1000));
            CurWash = (settings.getLong("CurWash", 1000));
            CurPet = (settings.getLong("CurPet", 1000));
            CurSleep = (settings.getLong("CurSleep", 1000));
            CurExp = (settings.getInt("CurExp", 0));
            CurLevel = (settings.getInt("CurLevel", 1));
            BackGroundColorCurr = (settings.getInt("BackGroundColor", 1));
            MillsOnExit = (settings.getLong("MilsOnExit", 1));
        }

        MyMills = MillsOnExit;
        currentMils = System.currentTimeMillis();
        doIt = currentMils - MyMills;

        CurFood = CurFood - (doIt * Loss) / 3000;
        if (CurFood < 0) {
            CurFood = 0;
        }
        CurWash = CurWash - (doIt * Loss) / 3000;
        if (CurWash < 0) {
            CurWash = 0;
        }
        CurPet = CurPet - (doIt * Loss) / 3000;
        if (CurPet < 0) {
            CurPet = 0;
        }
        CurSleep = CurSleep - (doIt * Loss) / 3000;
        if (BackGroundColorCurr == 0 && CurSleep < 0) {
            CurSleep = 0;
        }
        if (BackGroundColorCurr == 1 && CurSleep > MaxSleep) {
            CurSleep = 1000;
        }

//buttons
        ImageButton ImageButtonFeed = (ImageButton) findViewById(R.id.ImageButtonFeed);
        View.OnClickListener listenerFeed = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (BackGroundColorCurr == 0)
                    return;
                imageView.setImageResource(eatable[CurLevel]);
                if (CurFood < (MaxFood - Gain)) {
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
        };
        ImageButtonFeed.setOnClickListener(listenerFeed);

        ImageButton ImageButtonWash = (ImageButton) findViewById(R.id.ImageButtonWash);
        View.OnClickListener listenerWash = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (BackGroundColorCurr == 0)
                    return;
                imageView.setImageResource(washable[CurLevel]);
                if (CurWash < (MaxWash - Gain)) {
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
        };
        ImageButtonWash.setOnClickListener(listenerWash);

        ImageButton ImageButtonPet = (ImageButton) findViewById(R.id.ImageButtonPet);
        View.OnClickListener listenerPet = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (BackGroundColorCurr == 0)
                    return;
                imageView.setImageResource(lovable[CurLevel]);
                if (CurPet < (MaxPet - Gain)) {
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

        };
        ImageButtonPet.setOnClickListener(listenerPet);

        ImageButton ImageButtonSleep = (ImageButton) findViewById(R.id.ImageButtonSleep);
        View.OnClickListener listenerSleep = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeBackground();
            }
        };
        ImageButtonSleep.setOnClickListener(listenerSleep);

        final Button buttonName = (Button) findViewById(R.id.buttonName);
        View.OnClickListener listenerName = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView TextViewName = (TextView) findViewById(R.id.TextViewName);
                if (CheckerEditTextName) {
                    buttonName.setText("Change name");
                    TextViewName.setText(editTextName.getText().toString());
                    TextViewName.setVisibility(View.VISIBLE);
                    editTextName.setVisibility(View.INVISIBLE);
                } else {
                    buttonName.setText("Ok");
                    TextViewName.setVisibility(View.INVISIBLE);
                    editTextName.setVisibility(View.VISIBLE);
                }
                CheckerEditTextName = !CheckerEditTextName;
            }
        };
        buttonName.setOnClickListener(listenerName);

    }

    private void changeBackground() {
        if (BackGroundColorCurr == 0) {
            RelativeLayout bgElement = (RelativeLayout) findViewById(R.id.main_container);
            bgElement.setBackgroundColor(getResources().getColor(R.color.LightPink));
            textViewName.setTextColor(getResources().getColor(R.color.Black));
            TextViewFood.setTextColor(getResources().getColor(R.color.Black));
            TextViewWash.setTextColor(getResources().getColor(R.color.Black));
            TextViewPet.setTextColor(getResources().getColor(R.color.Black));
            TextViewSleep.setTextColor(getResources().getColor(R.color.Black));
            TextViewLevel.setTextColor(getResources().getColor(R.color.Black));
            TextViewExp.setTextColor(getResources().getColor(R.color.Black));
            TextViewHappiness.setTextColor(getResources().getColor(R.color.Black));
            editTextName.setTextColor(getResources().getColor(R.color.Black));
            imageView.setImageResource(egg[CurLevel]);
            BackGroundColorCurr = 1;
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
            TextViewHappiness.setTextColor(getResources().getColor(R.color.White));
            editTextName.setTextColor(getResources().getColor(R.color.White));
            imageView.setImageResource(sleepable[CurLevel]);
            BackGroundColorCurr = 0;
        }
    }

    public void updateTextViews() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                TextViewFood.setText("Food: " + CurFood + "/" + MaxFood);
                TextViewWash.setText("Wash: " + CurWash + "/" + MaxWash);
                TextViewPet.setText("Pet: " + CurPet + "/" + MaxPet);
                TextViewSleep.setText("Sleep: " + CurSleep + "/" + MaxSleep);
                TextViewLevel.setText("Level: " + CurLevel);
                TextViewExp.setText("Exp: " + CurExp + "/" + NextLevelExp);
                TextViewHappiness.setText("Happiness: " + Happiness);
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("Name", textViewName.getText().toString());
        editor.putLong("CurFood", CurFood);
        editor.putLong("CurWash", CurWash);
        editor.putLong("CurPet", CurPet);
        editor.putLong("CurSleep", CurSleep);
        editor.putLong("MilsOnExit", System.currentTimeMillis());
        editor.putInt("CurExp", CurExp);
        editor.putInt("CurLevel", CurLevel);
        editor.putInt("BackGroundColor", BackGroundColorCurr);
        editor.apply();
    }

    @Override
    protected void onResume() {
        super.onResume();

        changeBackground();
        updateTextViews();

        Timer timer = new Timer();
        TimerTask task = new TimerTaskMain(this);
        timer.scheduleAtFixedRate(task, 3000, BaseTimerCycle);
    }
}