package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;

public class HighScores extends JComponent{
    // method: paintComponent
    // purpose: changes the way the highScore menu looks like
    public void paintComponent(Graphics g) {
        if(g instanceof Graphics2D)
        {
            Graphics2D g2 = (Graphics2D)g;
            Font font = new Font("Arial", Font.BOLD, 72);
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(Color.WHITE);
            g2.setFont(font);
            g2.drawString("High Scores",90,60);
        }
    }
}