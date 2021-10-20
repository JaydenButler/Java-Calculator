package com.company;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class Main extends JFrame implements ActionListener {
    JLabel numberDisplay;
    JButton clearButton;
    JButton plusMinusButton;
    JButton percentButton;
    JButton divideButton;
    JButton multiplyButton;
    JButton subtractButton;
    JButton additionButton;
    JButton equalsButton;
    JButton periodButton;
    JButton zeroButton;

    JPanel mainPanel;

    enum MathFunctions {
        DIVIDE,
        MULTIPLY,
        SUBTRACT,
        ADDITION,
        PERCENT,
        NONE,
    }

    MathFunctions lastMathFuncUsed = MathFunctions.NONE;
    double lastNumUsed = 0;
    boolean enteringText = false;

    public Main(){
        mainPanel = new JPanel();
        GridBagLayout grid = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        mainPanel.setLayout(grid);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        numberDisplay = new JLabel("0", SwingConstants.RIGHT);
        numberDisplay.setBorder(new EmptyBorder(0, 0, 0, 15));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 4;
        mainPanel.add(numberDisplay, gbc);
        gbc.gridwidth = 1;

        clearButton = new JButton("C");
        clearButton.addActionListener(this);
        gbc.gridx = 0;
        gbc.gridy = 1;
        mainPanel.add(clearButton, gbc);

        plusMinusButton = new JButton("+/-");
        plusMinusButton.addActionListener(this);
        gbc.gridx = 1;
        gbc.gridy = 1;
        mainPanel.add(plusMinusButton, gbc);

        percentButton = new JButton("%");
        percentButton.addActionListener(this);
        gbc.gridx = 2;
        gbc.gridy = 1;
        mainPanel.add(percentButton, gbc);

        divideButton = new JButton("/");
        divideButton.addActionListener(this);
        gbc.gridx = 3;
        gbc.gridy = 1;
        mainPanel.add(divideButton, gbc);

        multiplyButton = new JButton("x");
        multiplyButton.addActionListener(this);
        gbc.gridx = 3;
        gbc.gridy = 2;
        mainPanel.add(multiplyButton, gbc);

        subtractButton = new JButton("-");
        subtractButton.addActionListener(this);
        gbc.gridx = 3;
        gbc.gridy = 3;
        mainPanel.add(subtractButton, gbc);

        additionButton = new JButton("+");
        additionButton.addActionListener(this);
        gbc.gridx = 3;
        gbc.gridy = 4;
        mainPanel.add(additionButton, gbc);

        equalsButton = new JButton("=");
        equalsButton.addActionListener(this);
        gbc.gridx = 3;
        gbc.gridy = 5;
        mainPanel.add(equalsButton, gbc);

        periodButton = new JButton(".");
        periodButton.addActionListener(this);
        gbc.gridx = 2;
        gbc.gridy = 5;
        mainPanel.add(periodButton, gbc);

        zeroButton = new JButton("0");
        zeroButton.addActionListener(this);
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        mainPanel.add(zeroButton, gbc);
        gbc.gridwidth = 1;

        int i = 1;
        for(int y = 4; y > 1; y--){
             for(int x = 0; x < 3; x++){
                gbc.gridx = x;
                gbc.gridy = y;
                JButton newButton = new JButton(Integer.toString(i));
                newButton.addActionListener(this);
                mainPanel.add(newButton, gbc);
                i++;
            }
        }
    }

    public JPanel getUI(){
        return mainPanel;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.getContentPane().add(new Main().getUI());
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String numPressedString = e.getActionCommand();
        if(e.getActionCommand() == "C") {
            // Do clear
            numberDisplay.setText("0");
            lastMathFuncUsed = MathFunctions.NONE;
            lastNumUsed = 0;
            enteringText = false;
        }
        else if(e.getActionCommand() == "+/-") {
            // Do plus minus
            if(numberDisplay.getText().contains("-")){
                numberDisplay.setText(numberDisplay.getText().substring(1));
            }
            else {
                numberDisplay.setText("-" + numberDisplay.getText());
            }
        }
        else if(e.getActionCommand() == "%") {
            // Do percentage
            lastMathFuncUsed = MathFunctions.PERCENT;
            lastNumUsed = Double.parseDouble(numberDisplay.getText());
            enteringText = false;
        }
        else if(e.getActionCommand() == "/"){
            // Do divide
            lastMathFuncUsed = MathFunctions.DIVIDE;
            lastNumUsed = Double.parseDouble(numberDisplay.getText());
            enteringText = false;
        }
        else if(e.getActionCommand() == "x") {
            // Do multiply
            lastMathFuncUsed = MathFunctions.MULTIPLY;
            lastNumUsed = Double.parseDouble(numberDisplay.getText());
            enteringText = false;
        }
        else if(e.getActionCommand() == "-") {
            // Do subtract
            lastMathFuncUsed = MathFunctions.SUBTRACT;
            lastNumUsed = Double.parseDouble(numberDisplay.getText());
            enteringText = false;
        }
        else if(e.getActionCommand() == "+") {
            // Do addition
            lastMathFuncUsed = MathFunctions.ADDITION;
            lastNumUsed = Double.parseDouble(numberDisplay.getText());
            enteringText = false;
        }
        else if(e.getActionCommand() == "=") {
            DecimalFormat df = new DecimalFormat("##.##");
            df.setRoundingMode(RoundingMode.DOWN);
            // Do equals
            if(lastMathFuncUsed == MathFunctions.ADDITION){
                numberDisplay.setText(df.format(lastNumUsed + Double.parseDouble(numberDisplay.getText())));
            }
            else if(lastMathFuncUsed == MathFunctions.MULTIPLY){
                numberDisplay.setText(df.format(lastNumUsed * Double.parseDouble(numberDisplay.getText())));
            }
            else if(lastMathFuncUsed == MathFunctions.SUBTRACT){
                numberDisplay.setText(df.format(lastNumUsed - Double.parseDouble(numberDisplay.getText())));
            }
            else if(lastMathFuncUsed == MathFunctions.DIVIDE){
                numberDisplay.setText(df.format(lastNumUsed / Double.parseDouble(numberDisplay.getText())));
            }
            else if(lastMathFuncUsed == MathFunctions.PERCENT){
                numberDisplay.setText(df.format(lastNumUsed * (Double.parseDouble(numberDisplay.getText()) / 100)));
            }
            lastMathFuncUsed = MathFunctions.NONE;
            enteringText = false;
        }
        else if(e.getActionCommand() == ".") {
            if(enteringText){
                numberDisplay.setText(numberDisplay.getText() + numPressedString);
            }
        }
        else {
            try {
                if(enteringText){
                    if(numberDisplay.getText().contains("-")) {
                        numberDisplay.setText("-" + numberDisplay.getText() + numPressedString);
                    }
                    else {
                        numberDisplay.setText(numberDisplay.getText() + numPressedString);
                    }
                }
                else {
                    numberDisplay.setText(numPressedString);
                    enteringText = true;
                }
            }
            catch(Exception exception) {
                System.out.println(exception);
            }
        }
    }
}
