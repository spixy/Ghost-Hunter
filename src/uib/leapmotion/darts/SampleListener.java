package uib.leapmotion.darts;

import com.leapmotion.leap.*;
import java.awt.*;

class SampleListener extends Listener {
    boolean readyForControl = false;
    int screenWidth;
    int screenHeight;
    boolean iBoxGet = false;
    InteractionBox iBox = null;
    Robot robot;
    boolean isMoving = false;
    boolean unGrip = false;
    boolean wasFacingDown = true;
    boolean wasInTabState = false;
    boolean wasTabbing = false;
    boolean justCircleGestured = false;
    boolean isResizing = false;

    public void onConnect(Controller controller) {
        System.out.println("Connected");
        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment()
                .getDefaultScreenDevice();
        screenWidth = gd.getDisplayMode().getWidth();
        screenHeight = gd.getDisplayMode().getHeight();
        System.out.println("Screen Resolution: X: " + screenWidth + ", H: "
                + screenHeight);
        readyForControl = true;
        try {
            robot = new Robot();
            robot.setAutoDelay(5);
        } catch (AWTException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}