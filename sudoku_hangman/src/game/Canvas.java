package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JComponent;

public class Canvas extends JComponent
{
    // method: paintComponent
    // purpose: changes the way the title menu looks like
    public void paintComponent(Graphics g)
    {
        if(g instanceof Graphics2D){
            Graphics2D g2 = (Graphics2D)g;
            Font font = new Font("Arial", Font.BOLD, 72);
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(Color.WHITE);
            g2.setFont(font);
            g2.drawString("HangMan",130,60);
            Font font2 = new Font("Arial", Font.BOLD, 40);
            g2.setFont(font2);
            g2.setColor(Color.RED);
            g2.drawString("S.Y.C",250,300);
        }
    }

}