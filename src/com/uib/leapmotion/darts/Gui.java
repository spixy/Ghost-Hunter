package com.uib.leapmotion.darts;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Created by spixy on 9.6.2017.
 */
public class Gui extends JFrame {
    public static final int WINDOW_WIDTH = 800;
    public static final int WINDOW_HEIGHT = 600;
    private int padding = 50;
    private BufferedImage graphicsContext;
    private JPanel contentPanel = new JPanel();
    private JLabel contextRender;
    private RenderingHints antialiasing;
    private final float dartRadius = 10;
    private final Color dartColor = Color.white;
    private final float enemySpawnProbability = 0.1f;
    private final int MaxEnemyCount = 5;
    private float dartX, dartY, dartZ;
    private ArrayList<HittableObject> enemies;
    private BufferedImage background;
    private int enemyHolder;

    private int score = 0;

    private final String[] bgImageNames = new String[]
    {
        "/bg1.jpg",
        "/bg2.jpg",
        "/bg3.jpg",
    };

    private Random random = new Random();

    public void start(boolean useTimer)
    {
        enemies = new ArrayList<>();

        antialiasing = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphicsContext = new BufferedImage(WINDOW_WIDTH + (2 * padding), WINDOW_HEIGHT + (2 * padding), BufferedImage.TYPE_INT_RGB);
        contextRender = new JLabel(new ImageIcon(graphicsContext));

        contentPanel.add(contextRender);
        contentPanel.setSize(WINDOW_WIDTH + padding * 2, WINDOW_HEIGHT + padding * 2);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setContentPane(contentPanel);
        //take advantage of auto-sizing the window based on the size of its contents
        this.pack();
        this.setLocationRelativeTo(null);

        try {
            int index = random.nextInt(bgImageNames.length);
            URL url = getClass().getResource(bgImageNames[index]);
            background = ImageIO.read(url);
        } catch (IOException e) {
            background = null;
        }

        this.dartX = getWidth() / 2;
        this.dartY = getHeight() / 2;
        this.dartZ = 1;
        this.DrawUpdate();

        setVisible(true);

        if (useTimer)
        {
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                        Point mouseLoc = MouseInfo.getPointerInfo().getLocation();
                        dartX = mouseLoc.x;
                        dartY = mouseLoc.y;
                    DrawUpdate();
                }
            }, 15, 15);
        }
    }

    private static float clamp(float val, float min, float max) {
        return Math.max(min, Math.min(max, val));
    }

    public void OnHandChange(float x, float y, float z)
    {
        this.dartX = clamp(this.dartX + x, 0, getWidth());
        this.dartY = clamp(this.dartY + y, 0, getHeight());
        this.dartZ = clamp(this.dartZ + z , 3, 5);

        this.DrawUpdate();
    }

    private void DrawUpdate() {
        Graphics2D g2d = graphicsContext.createGraphics();
        g2d.setRenderingHints(antialiasing);

        if (background != null)
            g2d.drawImage(background, 0, 0, null);
        else
            g2d.clearRect(0, 0, getWidth(), getHeight());

        // hit some enemy?
        if (this.dartZ == 5)
        {
            for (int i = enemies.size() - 1; i >= 0; --i)
            {
                if (enemies.get(i).Overlap(dartX, dartY, GetDartRadius()))
                {
                    enemies.remove(i);
                    score++;
                }
            }
        }

        // spawn new enemy
        if (enemies.size() < MaxEnemyCount && random.nextFloat() < enemySpawnProbability)
        {
            enemies.add(new HittableObject(enemyHolder % 2 == 0));
            enemyHolder++;
        }

        // draw enemies
        for (HittableObject enemy : enemies)
        {
            enemy.Draw(g2d);
        }

        this.DrawDart(g2d);
        this.DrawUI(g2d);

        g2d.dispose();
        //force the container for the context to re-paint itself
        contextRender.repaint();
    }

    private float GetDartRadius()
    {
        return dartRadius * dartZ;
    }

    private void DrawDart(Graphics2D g2d) {
        float radius = GetDartRadius();
        //set up the large circle
        Ellipse2D.Double dart = new Ellipse2D.Double(dartX - radius, dartY - radius, 2 * radius, 2 * radius);
        g2d.setColor(dartColor);
        g2d.draw(dart);
    }

    private void DrawUI(Graphics2D g2d) {
        //Set up the font to print on the circles
        Font font = g2d.getFont();
        font = font.deriveFont(Font.BOLD, 14f);
        g2d.setFont(font);

        g2d.setColor(Color.WHITE);
        g2d.drawString("Score: " + score, 5, 15);
    }

}
