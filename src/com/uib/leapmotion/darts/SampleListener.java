package com.uib.leapmotion.darts;
/******************************************************************************
 * \
 * Copyright (C) 2012-2013 Leap Motion, Inc. All rights reserved.               *
 * Leap Motion proprietary and confidential. Not for distribution.              *
 * Use subject to the terms of the Leap Motion SDK Agreement available at       *
 * https://developer.leapmotion.com/sdk_agreement, or another agreement         *
 * between Leap Motion and you, your company or other organization.             *
 * \
 ******************************************************************************/

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.MouseInfo;
import java.awt.Point;

import com.leapmotion.leap.*;

class SampleListener extends Listener {
    boolean readyForControl = false;
    int screenWidth;
    int screenHeight;
    boolean useAllHands = false;
    Gui gui;

    SampleListener(Gui gui) {
        this.gui = gui;
    }

    public void onConnect(Controller controller) {
        System.out.println("Connected");
        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment()
                .getDefaultScreenDevice();
        screenWidth = gd.getDisplayMode().getWidth();
        screenHeight = gd.getDisplayMode().getHeight();
        System.out.println("Screen Resolution: X: " + screenWidth + ", H: "
                + screenHeight);
        readyForControl = true;
    }

    public void onFrame(Controller controller) {
        Frame frame = controller.frame(); // The latest frame

        if (!readyForControl)
            return;

        if (useAllHands)
        {
            for (Hand hand : frame.hands())
            {
                ProcessHand(hand);
            }
        }
        else
        {
            Hand rightHand = frame.hands().rightmost();

            if (rightHand != null)
            {
                ProcessHand(rightHand);
            }
        }
    }

    void ProcessHand(Hand hand)
    {
        if (hand.confidence() > .15)
        {
            Vector velocity = hand.palmVelocity();
            System.out.println("Velocity: " + velocity);

            float xSpeed = (velocity.getX() /  12);
            float ySpeed = (velocity.getY() / -12);
            float zSpeed = (velocity.getZ() / 400);
            gui.OnHandChange(xSpeed, ySpeed, zSpeed);
        }
    }
}

