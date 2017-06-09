package com.uib.leapmotion.darts.Movements;

import com.uib.leapmotion.darts.Gui;

import java.util.Random;

/**
 * Created by stefa on 09.06.2017.
 */
public abstract class BaseStrategy implements MovementStrategy {
    protected Random random;
    protected int x;
    protected int y;

    public BaseStrategy(){
        random = new Random();
        generatePosition();
    }

    protected void generatePosition(){
        x = random.nextInt(Gui.WINDOW_WIDTH);
        y = random.nextInt(Gui.WINDOW_HEIGHT);
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }
}
