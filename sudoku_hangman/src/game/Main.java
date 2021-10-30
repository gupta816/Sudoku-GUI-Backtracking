package game;

import java.awt.*;

import javax.swing.SwingUtilities;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;


public class Main {
    //method: main
    //purpose: this main method runs the program
    static JFrame f = null;
    public static void main(String[] args){

        SwingUtilities.invokeLater(new Runnable(){
            public void run(){
                createAndShowGUI();

            }
        });
    }
    // method: createAndShowGUI
    // purpose: this method creates the title menu and the game menu
    public static void createAndShowGUI(){


        f = new JFrame("CS 245");
        final JPanel panel = new JPanel();
        final JPanel cpanel = new JPanel();
        System.out.println("CS 245"+ SwingUtilities.isEventDispatchThread());

        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(600,400);
        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        f.setBounds((screenSize.width-600)/2, (screenSize.height-400)/2, 600, 400);
        f.setVisible(true);
        f.getContentPane().setBackground(Color.BLACK);
        UIManager.put("Button.background", Color.black);
        UIManager.put("Button.foreground", Color.white);
        Font s = new Font("Serif", Font.BOLD, 14);
        UIManager.put("Button.font", s);
        final Container cp = f.getContentPane();
        final Canvas t = new Canvas();
        cp.add(t);
        panel.getInputMap().put(KeyStroke.getKeyStroke("F1"),
                "doSomething");
        panel.getActionMap().put("doSomething",
                new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JFrame frame = new JFrame();
                        JOptionPane.showMessageDialog(frame,
                                "Candy Jimenez - Bronco ID: 007728123\nYvonne Hong - Bronco ID: \n" +
                                        "Point and Clicker\nWinter 2013 ",
                                "Info",
                                JOptionPane.PLAIN_MESSAGE);
                    }
                });
        panel.getInputMap().put(KeyStroke.getKeyStroke("ESCAPE"),
                "Escape");
        panel.getActionMap().put("Escape",
                new AbstractAction() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        System.exit(0);
                    }
                });

        ActionListener timer = new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                t.setVisible(false);
                panel.setLayout(null);
                JButton play = new JButton("Play");
                play.setBounds(470,200,110,30);
                play.setToolTipText("Start Game");
                panel.add(play);
                ActionListener listener = new ActionListener(){
                    final Container cp = f.getContentPane();
                    final Hangman b = new Hangman();
                    final Component contents = b.createComponents();
                    public void actionPerformed(ActionEvent arg0)  {

                        cp.add(b);
                        f.getContentPane().add(contents);
                        b.setVisible(true);
                        panel.setVisible(false);
                    }
                };
                play.addActionListener(listener);
                JButton highScores = new JButton("High Scores");
                highScores.setBounds(470,240,110,30);
                highScores.setToolTipText("View High Scores");
                highScores.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                        final Container cp = f.getContentPane();
                        final HighScores c = new HighScores();
                        panel.setVisible(false);
                        cpanel.setVisible(true);
                        cpanel.setLayout(null);
                        cp.add(c);
                        JButton back = new JButton("Back");
                        back.setBounds(30,325,110,30);
                        back.setToolTipText("Go Back to menu");
                        c.add(back);
                        back.addActionListener(new ActionListener(){
                            public void actionPerformed(ActionEvent e){
                                panel.setVisible(true);
                                c.setVisible(false);

                            }
                        });
                    }
                });
                panel.add(highScores);
                JButton credits = new JButton("Credits");
                credits.setBounds(470,280,110,30);
                credits.setToolTipText("View who made this Game");
                panel.add(credits);
                ImageIcon ii = new ImageIcon("src\\hangman6.gif");
                JLabel label = new JLabel(ii);
                label.setBounds(75,100,300,200);
                panel.add(label);
                f.add(panel);
                panel.setBackground(Color.BLACK);
                credits.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e)
                    {
                        final Container cp = f.getContentPane();
                        final Credits c = new Credits();

                        panel.setVisible(false);
                        cpanel.setVisible(true);
                        cpanel.setLayout(null);
                        cp.add(c);
                        JButton back = new JButton("Back");
                        back.setBounds(30,325,110,30);
                        back.setToolTipText("Go Back to menu");
                        c.add(back);
                        back.addActionListener(new ActionListener(){
                            public void actionPerformed(ActionEvent e){
                                panel.setVisible(true);
                                c.setVisible(false);
                            }
                        });
                    }
                });


            }
        };
        new Timer(3000, timer).start();

    }
    public static void menu(){
        f = new JFrame("CS 245");
        final JPanel panel = new JPanel();
        final JPanel cpanel = new JPanel();
        System.out.println("CS 245"+ SwingUtilities.isEventDispatchThread());


        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(600,400);
        f.setVisible(true);
        f.getContentPane().setBackground(Color.BLACK);
        UIManager.put("Button.background", Color.black);
        UIManager.put("Button.foreground", Color.white);
        Font s = new Font("Serif", Font.BOLD, 14);
        UIManager.put("Button.font", s);
        final Container cp = f.getContentPane();
        final Canvas t = new Canvas();
        cp.add(t);

        t.setVisible(false);
        panel.setLayout(null);

        JButton play = new JButton("Play");
        play.setBounds(470,200,110,30);
        panel.add(play);
        ActionListener listener = new ActionListener(){
            final Container cp = f.getContentPane();
            final Hangman b = new Hangman();
            final Component contents = b.createComponents();
            public void actionPerformed(ActionEvent arg0)  {
                cp.add(b);
                f.getContentPane().add(contents);
                b.setVisible(true);
                panel.setVisible(false);
            }
        };
        play.addActionListener(listener);
        JButton highScores = new JButton("High Scores");
        highScores.setBounds(470,240,110,30);
        highScores.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                final Container cp = f.getContentPane();
                final HighScores c = new HighScores();
                panel.setVisible(false);
                cpanel.setVisible(true);
                cpanel.setLayout(null);
                cp.add(c);
                JButton back = new JButton("Back");
                back.setBounds(30,325,110,30);
                c.add(back);
                back.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                        panel.setVisible(true);
                        c.setVisible(false);

                    }
                });
            }
        });
        panel.add(highScores);
        JButton credits = new JButton("Credits");
        credits.setBounds(470,280,110,30);
        panel.add(credits);
        ImageIcon ii = new ImageIcon("src\\hangman6.gif");
        JLabel label = new JLabel(ii);
        label.setBounds(75,100,300,200);
        panel.add(label);
        f.add(panel);
        panel.setBackground(Color.BLACK);
        credits.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                final Container cp = f.getContentPane();
                final Credits c = new Credits();

                panel.setVisible(false);
                cpanel.setVisible(true);
                cpanel.setLayout(null);
                cp.add(c);
                JButton back = new JButton("Back");
                back.setBounds(30,325,110,30);
                c.add(back);
                back.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                        panel.setVisible(true);
                        c.setVisible(false);
                    }
                });
            }
        });

    }
    public static void dispose(){
        f.dispose();
    }





}