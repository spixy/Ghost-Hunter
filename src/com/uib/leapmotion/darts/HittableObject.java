package com.uib.leapmotion.darts;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

/**
 * Created by spixy on 9.6.2017.
 */
public class HittableObject {

    public void Draw(Graphics2D g2d)
    {
        //float radius = dartRadius * dartZ;

        //set up the large circle
        //Ellipse2D.Double dart = new Ellipse2D.Double(dartX - radius, dartY - radius, 2 * radius, 2 * radius);

        //Rectangle2D

        //we save the big circle for last, to cover up any stray marks under the stroke
        //of its perimeter. We also set the clip back to null to prevent the large circle
        //itself from accidentally getting clipped
        //g2d.setColor(dartColor);
        //g2d.draw(dart);

        //g2d.dispose();
    }
}
