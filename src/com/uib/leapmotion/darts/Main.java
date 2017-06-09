package com.uib.leapmotion.darts;

import com.leapmotion.leap.*;
import com.leapmotion.leap.Controller.PolicyFlag;

import java.io.IOException;

/**
 * Created by spixy on 9.6.2017.
 */
public class Main {
    public static void main(String[] args) {
        Controller controller = new Controller();
        controller.setPolicyFlags(PolicyFlag.POLICY_BACKGROUND_FRAMES);
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
