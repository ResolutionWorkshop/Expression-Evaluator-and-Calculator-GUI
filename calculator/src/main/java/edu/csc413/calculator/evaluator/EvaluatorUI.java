package edu.csc413.calculator.evaluator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class EvaluatorUI extends JFrame implements ActionListener {

    private TextField txField = new TextField();
    private Panel buttonPanel = new Panel();

    // total of 20 buttons on the calculator,
    // numbered from left to right, top to bottom
    // bText[] array contains the text for corresponding buttons
    private static final String[] bText = {
        "7", "8", "9", "+", "4", "5", "6", "- ", "1", "2", "3",
        "*", "0", "^", "=", "/", "(", ")", "C", "CE"
    };

    /**
     * C  is for clear, clears entire expression
     * CE is for clear expression, clears last entry up until the last operator.
     */
    private Button[] buttons = new Button[bText.length];

    public static void main(String argv[]) {
        EvaluatorUI calc = new EvaluatorUI();
    }

    public EvaluatorUI() {
        setLayout(new BorderLayout());
        this.txField.setPreferredSize(new Dimension(600, 50));
        this.txField.setFont(new Font("Courier", Font.BOLD, 28));

        add(txField, BorderLayout.NORTH);
        txField.setEditable(false);

        add(buttonPanel, BorderLayout.CENTER);
        buttonPanel.setLayout(new GridLayout(5, 4));

        //create 20 buttons with corresponding text in bText[] array
        Button bt;
        for (int i = 0; i < EvaluatorUI.bText.length; i++) {
            bt = new Button(bText[i]);
            bt.setFont(new Font("Courier", Font.BOLD, 28));
            buttons[i] = bt;
        }

        //add buttons to button panel
        for (int i = 0; i < EvaluatorUI.bText.length; i++) {
            buttonPanel.add(buttons[i]);
        }

        //set up buttons to listen for mouse input
        for (int i = 0; i < EvaluatorUI.bText.length; i++) {
            buttons[i].addActionListener(this);
        }

        setTitle("Calculator");
        setSize(600, 600);
        setLocationByPlatform(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent arg0) {
        // You need to fill in this function
    	if (arg0.getActionCommand().equals("="))
    	{
            try {
            	Evaluator expression = new Evaluator();
        		int result = expression.eval(txField.getText());	
        		txField.setText(Integer.toString(result));
                }
            catch (Exception ex){
                txField.setText("");
            }
    		
    	}
    	else if (arg0.getActionCommand().equals("C"))
    	{
    		txField.setText("");
    	}
    	else if (arg0.getActionCommand().equals("CE"))
    	{
    		String field = txField.getText();
    		int len = field.length(); //get length of text
    		char c = field.charAt(len-1);  //get last char in text
    		boolean operatorFound = false;
    		String operators = "+-*/^()";
    		while(!operatorFound && len > 0)
    		{
    			
    			//loop through each operator to compare
    			for (int i =0; i<operators.length(); i++)
        		{
        			if (c == operators.charAt(i))
        				operatorFound = true;
        		}	
    			if(!operatorFound)
    			{
    				len--;
    				if (len > 0)
        				c = field.charAt(len-1);
    			}
    		}
    		String newField = "";
    		for (int i = 0; i< len; i++)
    		{
    			newField += field.charAt(i);
    		}
    		txField.setText(newField);
    		
    	}
    	else
    	txField.setText(txField.getText() + arg0.getActionCommand());
    }
}
