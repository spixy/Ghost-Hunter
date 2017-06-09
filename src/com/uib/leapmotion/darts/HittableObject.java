package com.uib.leapmotion.darts;

import com.uib.leapmotion.darts.Movements.MovementStrategy;
import com.uib.leapmotion.darts.Movements.TeleportStrategy;
import com.uib.leapmotion.darts.Movements.WalkingStrategy;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class HittableObject {
    private static final int width = 50;
    private static final int height = 50;

    private Image image;
    private MovementStrategy strategy;


    public HittableObject(boolean isWalking) {
        if(isWalking){
            strategy = new WalkingStrategy();
        }else{
            strategy = new TeleportStrategy();
        }

        try {
            image = ImageIO.read(getClass().getResource("/enemy.gif"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void Draw(Graphics2D g2d) {
        strategy.move();

        System.out.println(strategy.getX()+ " " + strategy.getY());
        if (image != null) {
            g2d.drawImage(image, strategy.getX(), strategy.getY(), width, height, null);
        } else {
            g2d.setColor(Color.BLUE);
            g2d.drawOval(strategy.getX(), strategy.getY(), width, height);
        }
    }
}
