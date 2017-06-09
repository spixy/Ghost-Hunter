package com.uib.leapmotion.darts.movements;

/**
 * Created by stefa on 09.06.2017.
 */
public class WalkingStrategy extends BaseStrategy {
    private static final int SPEED = 5;
    private int diffX;
    private int diffY;

    public WalkingStrategy(int widthArea, int heightArea) {
        super(widthArea, heightArea);

        diffX = random.nextInt() % SPEED;
        diffY = random.nextInt() % SPEED;
    }

    @Override
    public void move() {
        int tempX = x + diffX;
        int tempY = y + diffY;

        if (tempX >= widthArea || tempX <= 0) {
            diffX = -diffX;
        }

        if (tempY >= heightArea || tempY <= 0) {
            diffY = -diffY;
        }

        x += diffX;
        y += diffY;
    }
}
