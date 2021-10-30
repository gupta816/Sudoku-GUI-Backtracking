package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.Border;
import java.util.*;

class Draw extends JPanel {
    Hangman controller;
    public Draw(Hangman controller) {
        this.controller = controller;
        Border raisedBevel = BorderFactory.createRaisedBevelBorder();
        Border loweredBevel = BorderFactory.createLoweredBevelBorder();
        Border compound = BorderFactory.createCompoundBorder
                ( raisedBevel, loweredBevel );
        setBorder( compound );
    }

    // method: paintComponenet
    // purpose: this method gives the specifics to draw the man that is commonly known as hangman
    public void paintComponent( Graphics g ) {
        super.paintComponent( g );

        //Draw the gallows
        g.setColor(Color.black);
        g.drawRect(120,100, 10, 180);
        g.drawRect(120, 100, 100, 10);
        g.drawRect(40, 250, 250, 5);

        g.setColor(Color.black);
        g.fillRect( 121, 100, 10, 180);
        g.fillRect(121,100, 100, 10);
        g.fillRect(41,250, 250, 5);

        //Draw the head and nose
        if( controller.headDrawn ){
            g.setColor(Color.black);
            g.fillRect(215,110,6,15);
            g.setColor(Color.black);
            g.fillOval(200, 120, 30, 30);
        }

        //Draw the body
        if( controller.bodyDrawn ){
            g.setColor(Color.black);
            g.drawRect(214, 144, 5, 70);
            g.fillRect(215, 145, 5, 70);
        }

        //Draw the left arm
        if( controller.leftArmDrawn ){
            g.setColor(Color.black);
            g.drawRect(214, 150, 10, 40);
            g.fillRect(215, 150, 10, 40);

        }

        //Draw the right arm
        if( controller.rightArmDrawn ){
            g.setColor(Color.black);
            g.drawRect(209, 150, 10, 40);
            g.fillRect(210, 150, 10, 40);
        }

        //Draw the left leg
        if( controller.leftLegDrawn ){
            g.setColor(Color.black);
            g.drawRect(209, 170, 10, 50);
            g.fillRect(210, 170, 10, 50);
        }

        //Draw the right leg
        if( controller.rightLegDrawn ){
            g.setColor(Color.black);
            g.drawRect(214, 170, 10, 50);
            g.fillRect(215, 170, 10, 50);
        }
    }
}