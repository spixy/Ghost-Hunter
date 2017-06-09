package com.uib.leapmotion.darts.movements;

import java.util.Random;

/**
 * Created by stefa on 09.06.2017.
 */
public abstract class BaseStrategy implements MovementStrategy {
    protected Random random;
    protected int widthArea;
    protected int heightArea;
    protected int x;
    protected int y;

    public BaseStrategy(int widthArea, int heightArea){
        this.widthArea = widthArea;
        this.heightArea = heightArea;
        this.random = new Random();
        generatePosition();
    }

    protected void generatePosition(){
        x = random.nextInt(widthArea);
        y = random.nextInt(heightArea);
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
