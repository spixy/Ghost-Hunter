package com.uib.leapmotion.darts.Movements;

/**
 * Created by stefa on 09.06.2017.
 */
public interface MovementStrategy {
    int getX();
    int getY();
    void move();
}
