package ru.lemmikki.tama;

import java.util.TimerTask;


public class TimerTaskMain extends TimerTask {
    private final MainActivity act;

    TimerTaskMain(MainActivity act) {
        this.act = act;
    }

    @Override
    public void run() {
        act.CurFood = act.CurFood - act.Loss;
        if (act.CurFood < 0) {
            act.CurFood = 0;
        }
        act.CurPet = act.CurPet - act.Loss;
        if (act.CurPet < 0) {
            act.CurPet = 0;
        }
        act.CurWash = act.CurWash - act.Loss;
        if (act.CurWash < 0) {
            act.CurWash = 0;
        }
        if (act.BackGroundColorCurr == 0) {
            if (act.CurSleep < (act.MaxFood - 4 * act.Loss)) {
                act.CurSleep += 4 * act.Loss;
            } else {
                act.CurSleep = act.MaxSleep;
            }
            act.BaseTimerCycle = 6000;
        } else {
            act.CurSleep = act.CurSleep - act.Loss;
            if (act.CurSleep < 0) {
                act.CurSleep = 0;
            }
            act.BaseTimerCycle = 3000;
        }
        act.Happiness = (act.CurPet * act.CurPet + act.CurWash * act.CurWash + act.CurPet * act.CurPet + act.CurSleep * act.CurSleep) / 4000;
        if (act.Happiness > 600) {
            act.CurExp++;
        }
        if (act.CurExp >= act.NextLevelExp) {
            act.CurExp = 0;
            act.CurLevel++;
        }
        if (act.CurLevel == 3) {
            act.CurLevel = 2;
        }
        act.updateTextViews();
    }
}