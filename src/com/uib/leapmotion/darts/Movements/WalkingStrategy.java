package com.uib.leapmotion.darts.Movements;

import com.uib.leapmotion.darts.Gui;

/**
 * Created by stefa on 09.06.2017.
 */
public class WalkingStrategy extends BaseStrategy {
    private static final int SPEED = 5;
    private int diffX;
    private int diffY;


    public WalkingStrategy() {
        super();

        diffX = random.nextInt() % SPEED;
        diffY = random.nextInt() % SPEED;
    }

    @Override
    public void move() {
        int tempX = x + diffX;
        int tempY = y + diffY;

        if (tempX >= Gui.WINDOW_WIDTH || tempX <= 0) {
            diffX = -diffX;
        }

        if (tempY >= Gui.WINDOW_HEIGHT || tempY <= 0) {
            diffY = -diffY;
        }

        x += diffX;
        y += diffY;
    }
}
