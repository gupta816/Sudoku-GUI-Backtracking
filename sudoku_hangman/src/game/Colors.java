package game;


import java.awt.*;
import java.util.Random;
import javax.swing.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.border.EmptyBorder;


public class Colors extends JPanel{
    // method: run
    // purpose: to execute the program
    public void run(){
        SwingUtilities.invokeLater(new Runnable(){
            public void run(){

                newGame();

            }
        });
    }

    int numberOfRounds;
    static int score = Hangman.score;


    static JPanel panel;
    static JPanel mainPanel;
    static JPanel clockPanel;
    static JPanel sudokuPanel;

    static ImageIcon green = new ImageIcon("src\\green.png");
    static JButton greenButton = new JButton(green);
    static ImageIcon purple = new ImageIcon("src\\purple.png");
    static JButton purpleButton = new JButton(purple);
    static ImageIcon blue = new ImageIcon("src\\blue.png");
    static JButton blueButton = new JButton(blue);
    static ImageIcon red = new ImageIcon("src\\red.png");
    static JButton redButton = new JButton(red);
    static ImageIcon yellow = new ImageIcon("src\\yellow.png");
    static JButton yellowButton = new JButton(yellow);
    static JLabel word = null;
    int [] setWordColor = {1,2,3,4,5};
    static String color;
    static JFrame frame = new JFrame("Game");
    static JButton end = new JButton("End");
    String ans;
    Clock c = new Clock();







    // method: newGame
    // purpose: create a new Game
    public void newGame()
    {
        numberOfRounds = 0;
        panel = new JPanel();
        mainPanel = new JPanel(new BorderLayout(3,3));
        clockPanel = new JPanel();
        sudokuPanel = new JPanel();


        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600,400);
        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        frame.setBounds((screenSize.width-600)/2, (screenSize.height-400)/2, 600, 400);
        frame.setVisible(true);
        frame.getContentPane().setBackground(Color.WHITE);
        mainPanel.setBackground(Color.BLACK);
        mainPanel.setBorder(new EmptyBorder(1,1,1,1));
        panel.setPreferredSize(new Dimension(600,380));
        clockPanel.setPreferredSize(new Dimension(600,20));
        sudokuPanel.setPreferredSize(new Dimension(600,20));

        clockPanel.setBackground(Color.WHITE);

        clockPanel.add(c.time);
        c.start();



        panel.setLayout(null);

        panel.setBackground(Color.WHITE);
        panel.add(greenButton);
        panel.add(purpleButton);
        panel.add(redButton);
        panel.add(yellowButton);
        panel.add(blueButton);



        panel.setVisible(true);
        clockPanel.setVisible(true);
        mainPanel.add(panel,BorderLayout.CENTER);
        mainPanel.add(clockPanel, BorderLayout.NORTH);

        frame.add(mainPanel);

        newPosition();
        newWord();
        blueButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                numberOfRounds++;
                word.setVisible(false);
                if(setWordColor[0] == 1){
                    score = score + 100;}
                action();
            }
        });
        greenButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                numberOfRounds ++;
                word.setVisible(false);
                if(setWordColor[0] == 2){
                    score = score + 100;}
                action();

            }
        });
        purpleButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                numberOfRounds++;
                word.setVisible(false);
                if(setWordColor[0] == 4){
                    score = score + 100;}
                action();
            }

        });
        redButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                numberOfRounds++;
                word.setVisible(false);
                if(setWordColor[0] == 3){
                    score = score + 100;}
                action();
            }
        });
        yellowButton.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e){
                numberOfRounds++;
                word.setVisible(false);
                if(setWordColor[0] == 5){
                    score = score + 100;}
                action();
            }

        });
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
    public void newWord(){
        Random randomWord = new Random();
        String [] wordColor = {"BLUE", "GREEN", "RED","PURPLE", "YELLOW"};
        for (int i = 0; i < wordColor.length; i++)
        {
            int randomPosition = randomWord.nextInt(wordColor.length);
            String temp = wordColor[i];
            wordColor[i] = wordColor[randomPosition];
            wordColor[randomPosition]=temp;
        }
        color = wordColor[0];
        word = new JLabel (color);
        word.setFont( new Font("Helvetica", Font.PLAIN, 28) );
        word.setBounds(230,5,300,100);
        for (int i = 0; i < setWordColor.length; i++) {
            int randomPosition = randomWord.nextInt(setWordColor.length);
            int temp = setWordColor[i];
            setWordColor[i] = setWordColor[randomPosition];
            setWordColor[randomPosition]=temp;
        }
        color = wordColor[0];
        word = new JLabel (color);
        word.setFont( new Font("Helvetica", Font.PLAIN, 28) );
        word.setBounds(250,5,300,100);
        panel.add(word);
        if(setWordColor[0]==1)
        {
            word.setForeground(Color.blue);
        }
        else if(setWordColor[0]==2)
        {
            word.setForeground(Color.green);
        }
        else if(setWordColor[0]==3)
        {
            word.setForeground(Color.red);
        }
        else if(setWordColor[0]==4)
        {
            word.setForeground(Color.magenta);
        }
        else if(setWordColor[0]==5)
        {
            word.setForeground(Color.yellow);
        }
    }
    public void newPosition(){
        Random rgen = new Random();
        int [] array = {1,2,3,4,5};
        int arraySize = 5;
        for (int i = 0; i < array.length; i++) {
            int randomPosition = rgen.nextInt(array.length);
            int temp = array[i];
            array[i] = array[randomPosition];
            array[randomPosition]=temp;
        }
        for(int i =0; i<array.length;i++){
            if(array[i] == 1){
                if(arraySize == 5)
                    blueButton.setBounds(10,100,100,100);
                else if(arraySize == 4)
                    greenButton.setBounds(10,100,100,100);
                else if(arraySize == 3)
                    redButton.setBounds(10,100,100,100);
                else if(arraySize == 2)
                    purpleButton.setBounds(10,100,100,100);
                else if(arraySize == 1)
                    yellowButton.setBounds(10,100,100,100);
            }
            else if(array[i] == 2){
                if(arraySize == 5)
                    blueButton.setBounds(100,210,100,100);
                else if(arraySize == 4)
                    greenButton.setBounds(100,210,100,100);
                else if(arraySize == 3)
                    redButton.setBounds(100,210,100,100);
                else if(arraySize == 2)
                    purpleButton.setBounds(100,210,100,100);
                else if(arraySize == 1)
                    yellowButton.setBounds(100,210,100,100);
            }
            else if(array[i] == 3){
                if(arraySize == 5)
                    blueButton.setBounds(200,100,100,100);
                else if(arraySize == 4)
                    greenButton.setBounds(200,100,100,100);
                else if(arraySize == 3)
                    redButton.setBounds(200,100,100,100);
                else if(arraySize == 2)
                    purpleButton.setBounds(200,100,100,100);
                else if(arraySize == 1)
                    yellowButton.setBounds(200,100,100,100);
            }
            else if(array[i]==4){
                if(arraySize == 5)
                    blueButton.setBounds(300,210,100,100);
                else if(arraySize == 4)
                    greenButton.setBounds(300,210,100,100);
                else if(arraySize == 3)
                    redButton.setBounds(300,210,100,100);
                else if(arraySize == 2)
                    purpleButton.setBounds(300,210,100,100);
                else if(arraySize == 1)
                    yellowButton.setBounds(300,210,100,100);
            }
            else if(array[i]==5){
                if(arraySize == 5)
                    blueButton.setBounds(400,100,100,100);
                else if(arraySize == 4)
                    greenButton.setBounds(400,100,100,100);
                else if(arraySize == 3)
                    redButton.setBounds(400,100,100,100);
                else if(arraySize == 2)
                    purpleButton.setBounds(400,100,100,100);
                else if(arraySize == 1)
                    yellowButton.setBounds(400,100,100,100);
            }
            arraySize--;
        }



    }
    public void startSudoku(){
        Sudoku s = new Sudoku();
        try {
            s.run();
        } catch (IOException ex) {
            Logger.getLogger(Colors.class.getName()).log(Level.SEVERE, null, ex);
        }
        frame.dispose();
    }
    public void action(){

        if(numberOfRounds < 5){
            newPosition();
            newWord();}
        else{
            frame.dispose();
            startSudoku();
        }
    }
}