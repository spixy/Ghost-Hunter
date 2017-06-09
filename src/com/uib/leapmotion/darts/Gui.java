package com.uib.leapmotion.darts;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Created by spixy on 9.6.2017.
 */
public class Gui extends JFrame {
    private static final long serialVersionUID = 1L;
    private int width = 800;
    private int height = 600;
    private int padding = 50;
    private BufferedImage graphicsContext;
    private JPanel contentPanel = new JPanel();
    private JLabel contextRender;
    private RenderingHints antialiasing;
    private final float dartRadius = 10;
    private final Color dartColor = Color.white;

    private float dartX, dartY, dartZ;

    public void start()
    {
        antialiasing = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphicsContext = new BufferedImage(width + (2 * padding), width + (2 * padding), BufferedImage.TYPE_INT_RGB);
        contextRender = new JLabel(new ImageIcon(graphicsContext));

        contentPanel.add(contextRender);
        contentPanel.setSize(width + padding * 2, height + padding * 2);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setContentPane(contentPanel);
        //take advantage of auto-sizing the window based on the size of its contents
        this.pack();
        this.setLocationRelativeTo(null);

        this.dartX = getWidth() / 2;
        this.dartY = getHeight() / 2;
        this.dartZ = 0;
        this.DrawDart();

        setVisible(true);
    }

    private static float clamp(float val, float min, float max) {
        return Math.max(min, Math.min(max, val));
    }

    public void OnHandChange(float x, float y, float z)
    {
        this.dartX = clamp(this.dartX + x, 0, getWidth());
        this.dartY = clamp(this.dartY + y, 0, getHeight());
        this.dartZ = clamp(this.dartZ + z , 1, 10);

        this.DrawDart();
    }

    private void DrawDart() {
        Graphics2D g2d = graphicsContext.createGraphics();
        g2d.setRenderingHints(antialiasing);

        g2d.clearRect(0, 0, getWidth(), getHeight());

        //Set up the font to print on the circles
        Font font = g2d.getFont();
        font = font.deriveFont(Font.BOLD, 14f);
        g2d.setFont(font);

        float radius = dartRadius * dartZ; // TODO: more dartZ == smaller dart

        //set up the large circle
        Ellipse2D.Double dart = new Ellipse2D.Double(dartX - radius, dartY - radius, 2 * radius, 2 * radius);

        //we save the big circle for last, to cover up any stray marks under the stroke
        //of its perimeter. We also set the clip back to null to prevent the large circle
        //itself from accidentally getting clipped
        g2d.setColor(dartColor);
        g2d.draw(dart);

        g2d.dispose();
        //force the container for the context to re-paint itself
        contextRender.repaint();
    }
}
