package game;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.border.EmptyBorder;


public class Sudoku extends JPanel{
    public void run() throws IOException{
        r.setSize(30, 30);
        newGame();
    }
    static JPanel panel;
    static JPanel mainPanel;
    static JPanel clockPanel;
    static JPanel scorePanel;
    static JButton checkSolution;
    static JButton end;
    static int score = Colors.score;
    static JButton quit;
    static JFrame frame = new JFrame("Game");
    static int numberOfRounds;
    String ans;
    static JLabel scores = new JLabel("Total Score");
    static JLabel totalScore = null;
    static String scoreString;
    BufferedWriter writer = null;
    Clock c = new Clock();
    Rectangle r = new Rectangle();
    int count = 0;
    JFormattedTextField[] textFields = new JFormattedTextField[81]; //array of textfields
    int[] solution = {8, 3, 5, 4, 1, 6, 9, 2, 7, 2, 9, 6, 8, 5, 7, 4, 3, 1, 4,
            1, 7, 2, 9, 3, 6, 5, 8, 5, 6, 9, 1, 3, 4, 7, 8, 2, 1, 2,
            3, 6, 7, 8, 5, 4, 9, 7, 4, 8, 5, 2, 9, 1, 6, 3, 6, 5, 2,
            7, 8, 1, 3, 9, 4, 9, 8, 1, 3, 4, 5, 2, 7, 6, 3, 7, 4, 9,
            6, 2, 8, 1, 5}; //answers of the board

    public void newGame() throws IOException{
        writer = new BufferedWriter( new FileWriter( "Scores.txt", true));
        Font s = new Font("Serif", Font.BOLD, 14);
        UIManager.put("Button.font", s);
        numberOfRounds = 0;
        panel = new JPanel();
        mainPanel = new JPanel(new BorderLayout(3,3));
        clockPanel = new JPanel();
        scorePanel = new JPanel();
        checkSolution = new JButton("Check Solution");
        quit = new JButton("Quit");
        end = new JButton("End");
        checkSolution.setToolTipText("Click to check solution");
        quit.setToolTipText("Click to go end game");
        end.setToolTipText("Go Back to menu");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600,400);
        frame.setVisible(true);
        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        frame.setBounds((screenSize.width-600)/2, (screenSize.height-400)/2, 600, 400);
        frame.getContentPane().setBackground(Color.WHITE);
        scorePanel.setLayout(null);
        scorePanel.setBackground(Color.WHITE);
        scorePanel.add(scores);
        scores.setFont( new Font("Helvetica", Font.PLAIN, 28) );
        scores.setBounds(220,5,300,100);
        scores.setForeground(Color.blue);
        mainPanel.setBackground(Color.BLACK);
        mainPanel.setBorder(new EmptyBorder(1,1,1,1));
        panel.setPreferredSize(new Dimension(600,380));
        clockPanel.setPreferredSize(new Dimension(600,20));
        panel.setLayout(null);
        clockPanel.setBackground(Color.WHITE);
        panel.setBackground(Color.WHITE);
        panel.add(quit);
        panel.add(checkSolution);
        quit.setBounds(10,300,130,30);
        quit.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                panel.setVisible(false);
                scorePanel.setVisible(true);
                mainPanel.add(scorePanel, BorderLayout.CENTER);
                scoreString = String.valueOf(score);
                totalScore = new JLabel (scoreString);
                totalScore.setFont( new Font("Helvetica", Font.PLAIN, 28) );
                totalScore.setBounds(220,50,300,100);
                totalScore.setForeground(Color.blue);
                scorePanel.add(totalScore);
                end.setBounds(10,300,130,30);
                scorePanel.add(end);
                end.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                        repaint();
                        revalidate();
                        frame.dispose();
                        Main.menu();
                    }
                });
                ans = JOptionPane.showInputDialog(null, "Name");
                try {
                    writer.append( ans + " ");
                    writer.append(scoreString + "\n");
                    writer.close();
                } catch (IOException ex) {
                    Logger.getLogger(Colors.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

        });
        checkSolution.setBounds(10,270,130,30);
        checkSolution.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                numberOfRounds++;
                if(checkBoard() || numberOfRounds == 5){
                    panel.setVisible(false);
                    scorePanel.setVisible(true);
                    mainPanel.add(scorePanel, BorderLayout.CENTER);
                    scoreString = String.valueOf(score);
                    totalScore = new JLabel (scoreString);
                    totalScore.setFont( new Font("Helvetica", Font.PLAIN, 28) );
                    totalScore.setBounds(220,50,300,100);
                    totalScore.setForeground(Color.blue);
                    scorePanel.add(totalScore);
                    end.setBounds(10,300,130,30);
                    scorePanel.add(end);
                    end.addActionListener(new ActionListener(){
                        public void actionPerformed(ActionEvent e){
                            repaint();
                            revalidate();
                            frame.dispose();
                            Main.menu();
                        }
                    });
                    ans = JOptionPane.showInputDialog(null, "Name");
                    try {
                        writer.append( ans + " ");
                        writer.append(scoreString + "\n");
                        writer.close();
                    } catch (IOException ex) {
                        Logger.getLogger(Colors.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    repaint();
                    revalidate();
                }else{
                    JFrame frame = new JFrame();
                    JOptionPane.showMessageDialog(frame,
                            "Try again or press quit to end game.",
                            "Incorrect Solution",
                            JOptionPane.PLAIN_MESSAGE);
                }
            }
        });


        clockPanel.add(c.time);
        c.start();
        textArrayInitializor();
        boardPainter();


        panel.setVisible(true);
        clockPanel.setVisible(true);
        mainPanel.add(panel,BorderLayout.CENTER);
        mainPanel.add(clockPanel, BorderLayout.NORTH);
        frame.add(mainPanel);
        mainPanel.getInputMap().put(KeyStroke.getKeyStroke("F1"),
                "doSomething");
        mainPanel.getActionMap().put("doSomething",
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
        mainPanel.getInputMap().put(KeyStroke.getKeyStroke("ESCAPE"),
                "Escape");
        mainPanel.getActionMap().put("Escape",
                new AbstractAction() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        System.exit(0);
                    }
                });
    }
    public void textArrayInitializor(){
        NumberFormat f = NumberFormat.getNumberInstance();
        f.setMaximumIntegerDigits(1);
        for(int i = 0; i < 81; i++){
            JFormattedTextField textTemp = new JFormattedTextField(f);
            textTemp.setBounds(r);
            textFields[i] = textTemp;
        }
    }

    public void boardPainter(){
        int x = 170; int y = 60;
        int counter = 0;
        int[] presetInts = {8, 4, 6, 7, 4, 1, 6, 5, 5, 9, 3, 7, 8,
                7, 4, 8, 2, 1, 3, 5, 2, 9, 1, 3, 9, 2, 5};
        int[] presetLoc = {0, 3, 5, 8, 15, 19, 24, 25, 27, 29, 31, 33, 34, 40,
                46, 47, 49, 51, 53, 55, 56, 61, 65, 72, 75, 77, 80};
        int presetCounter = 0;
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                if(counter == presetLoc[presetCounter]){
                    textFields[counter].setLocation(x, y);
                    textFields[counter].setText(Integer.toString(presetInts[presetCounter]));
                    textFields[counter].setEditable(false);
                    panel.add(textFields[counter]);
                    textFields[counter].setVisible(true);
                    x+=30;
                    presetCounter++;
                    counter++;
                }else{
                    textFields[counter].setLocation(x, y);
                    panel.add(textFields[counter]);
                    textFields[counter].setVisible(true);
                    x+=30;
                    counter++;
                }
            }
            y+=30;
            x = 170;
        }

    }
    public boolean checkBoard(){
        int incorrect = 0;
        boolean result = true;
        for(int i = 0; i < textFields.length; i++){
            if(textFields[i].getText().equals("") ||solution[i] == Integer.parseInt(textFields[i].getText())){

            }
            else{
                incorrect+= 10;
                result = false;
            }
        }if(result){
            score+= 540;
            return result;
        }else{
            score-=incorrect;
            if(score <= 0){
                score = 0;
            }
            return result; }

    }



}