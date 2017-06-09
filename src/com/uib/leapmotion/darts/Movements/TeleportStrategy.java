package com.uib.leapmotion.darts.Movements;

import com.uib.leapmotion.darts.Gui;

import java.util.Random;

/**
 * Created by stefa on 09.06.2017.
 */
public class TeleportStrategy extends BaseStrategy{
    private static final int TELEPORT_INTERVAL = 150;
    private int count;

    public TeleportStrategy() {
        super();
    }

    @Override
    public void move() {
        if(count == TELEPORT_INTERVAL){
            generatePosition();
            count = 0;
        }else {
            count++;
        }
    }
}
