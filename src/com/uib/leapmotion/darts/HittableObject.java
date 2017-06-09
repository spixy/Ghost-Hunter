package com.uib.leapmotion.darts;

import com.uib.leapmotion.darts.movements.MovementStrategy;
import com.uib.leapmotion.darts.movements.TeleportStrategy;
import com.uib.leapmotion.darts.movements.WalkingStrategy;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class HittableObject {
    public static final int IMAGE_WIDTH = 50;
    public static final int IMAGE_HEIGHT = 50;

    private Image image;
    private MovementStrategy strategy;

    public HittableObject(boolean isWalking) {
        int widthArea = Gui.WINDOW_WIDTH + IMAGE_WIDTH;
        int heightArea = Gui.WINDOW_HEIGHT + IMAGE_HEIGHT;

        if(isWalking){
            strategy = new WalkingStrategy(widthArea, heightArea);
        }else{
            strategy = new TeleportStrategy(widthArea, heightArea);
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
            g2d.drawImage(image, strategy.getX(), strategy.getY(), IMAGE_WIDTH, IMAGE_HEIGHT, null);
        } else {
            g2d.setColor(Color.BLUE);
            g2d.drawOval(strategy.getX(), strategy.getY(), IMAGE_WIDTH, IMAGE_HEIGHT);
        }
    }
}
