package de.jonas.viergewinnt;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ThreadLocalRandom;

public class Bot {

    private static final int TIMER_PERIOD = 100;

    public void start() {
        final Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (!FieldState.getState().equals(FieldState.COMPUTER)) {
                    return;
                }
                placeRandom();
                FieldState.setState(FieldState.USER);
                System.out.println("------------------- Set State -------------------");
            }
        }, 0, TIMER_PERIOD);
    }

    private void placeRandom() {
        Data.Column column = Data.COLUMNS[getRandom()];
        while (true) {
            for (final int field : column.getColumn()) {
                if (!Data.CIRCLE_LOCATIONS[field].getState().equals(FieldState.NONE)) {
                    continue;
                }
                Data.CIRCLE_LOCATIONS[field].setState(FieldState.COMPUTER);
                new WinHandler().checkWin();
                FieldState.setState(FieldState.USER);
                return;
            }
            column = Data.COLUMNS[getRandom()];
        }
    }

    private int getRandom() {
        return ThreadLocalRandom.current().nextInt(0, Data.COLUMNS.length);
    }

}
