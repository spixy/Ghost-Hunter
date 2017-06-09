package uib.leapmotion.darts;

import com.leapmotion.leap.Controller;
import com.leapmotion.leap.Gesture;

import java.io.IOException;

class Main {
    public static void main(String[] args)
    {
        Controller controller = new Controller();
        controller.setPolicyFlags(Controller.PolicyFlag.POLICY_BACKGROUND_FRAMES);
        SampleListener listener = new SampleListener();
        controller.addListener(listener);
        // controller.enableGesture(Gesture.Type.TYPE_SCREEN_TAP);
        // controller.enableGesture(Gesture.Type.TYPE_SWIPE);
        controller.enableGesture(Gesture.Type.TYPE_CIRCLE);
        System.out.println("Press Enter to quit...");
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
        controller.removeListener(listener);
    }
}