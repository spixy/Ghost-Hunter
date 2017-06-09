package com.uib.leapmotion.darts.movements;

/**
 * Created by stefa on 09.06.2017.
 */
public class TeleportStrategy extends BaseStrategy{
    private static final int TELEPORT_INTERVAL = 150;
    private int count;

    public TeleportStrategy(int widthArea, int heightArea) {
        super(widthArea, heightArea);
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
