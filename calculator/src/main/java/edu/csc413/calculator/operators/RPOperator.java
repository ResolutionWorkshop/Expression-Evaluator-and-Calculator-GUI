package edu.csc413.calculator.operators;

import edu.csc413.calculator.evaluator.Operand;

public class RPOperator extends Operator {
    public int priority()
    {
    	return 0;
    }
    public Operand execute(Operand op1, Operand op2 )
    {
    	//this method should not be called
    	//Parentheses are not infix operators
        return new Operand(0);
    }
}
