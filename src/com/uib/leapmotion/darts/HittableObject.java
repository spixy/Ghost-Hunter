package com.uib.leapmotion.darts;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.Random;

/**
 * Created by spixy on 9.6.2017.
 */
public class HittableObject {
    private static final int width = 50;
    private static final int height = 50;
    private double x;
    private double y;
    private Random rn = new Random();

    public void Draw(Graphics2D g2d)
    {
        //float radius = dartRadius * dartZ;
        //set up the large circle
        //Ellipse2D.Double dart = new Ellipse2D.Double(dartX - radius, dartY - radius, 2 * radius, 2 * radius);
        int x = rn.nextInt(Gui.WINDOW_WIDTH);
        int y = rn.nextInt(Gui.WINDOW_HEIGHT);

        BufferedImage img;
        try {
            URL url = getClass().getResource("/enemy.gif");
            img = ImageIO.read(url);
            g2d.drawImage(img, x,y,width,height,null);
        } catch (Exception e) {
            e.printStackTrace();
            g2d.setColor(Color.BLUE);
            g2d.drawOval(x, y, width,height);
        }

        //we save the big circle for last, to cover up any stray marks under the stroke
        //of its perimeter. We also set the clip back to null to prevent the large circle
        //itself from accidentally getting clipped
        //g2d.setColor(dartColor);
        //g2d.draw(dart);

        //g2d.dispose();
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}
