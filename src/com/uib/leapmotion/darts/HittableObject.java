package com.uib.leapmotion.darts;

import com.uib.leapmotion.darts.movements.MovementStrategy;
import com.uib.leapmotion.darts.movements.TeleportStrategy;
import com.uib.leapmotion.darts.movements.WalkingStrategy;

import javax.swing.*;
import java.awt.*;

public class HittableObject {
    private int imageWidth;
    private int imageHeight;
    private Image image;
    private MovementStrategy strategy;

    public HittableObject(boolean isWalking) {
        if(isWalking){
            imageWidth = 50;
            imageHeight = 50;
            strategy = new WalkingStrategy(Gui.WINDOW_WIDTH + imageWidth,
                    Gui.WINDOW_HEIGHT + imageHeight);
            image = new ImageIcon(getClass().getResource("/enemy.gif")).getImage();
        }else{
            imageWidth = 80;
            imageHeight = 80;
            strategy = new TeleportStrategy(Gui.WINDOW_WIDTH, Gui.WINDOW_HEIGHT);
            image = new ImageIcon(getClass().getResource("/enemy2.gif")).getImage();
        }
    }

    public void Draw(Graphics2D g2d) {
        strategy.move();

        System.out.println(strategy.getX()+ " " + strategy.getY());
        if (image != null) {
            g2d.drawImage(image, strategy.getX(), strategy.getY(), imageWidth, imageHeight, null);
        } else {
            g2d.setColor(Color.BLUE);
            g2d.drawOval(strategy.getX(), strategy.getY(), imageWidth, imageHeight);
        }
    }

    public boolean Overlap(float x, float y, float radius)
    {
        float x1 = strategy.getX() + imageWidth / 2;
        float y1 = strategy.getY() + imageHeight / 2;
        float x2 = x + radius;
        float y2 = y + radius;

        double distance = Math.sqrt( ((x2 - x1) * (x2 - x1)) + ((y2 - y1) * (y2 - y1)) );
        double maxDistance = (radius + Math.max(imageWidth, imageHeight)) / 2;

        return distance < maxDistance;
    }
}
