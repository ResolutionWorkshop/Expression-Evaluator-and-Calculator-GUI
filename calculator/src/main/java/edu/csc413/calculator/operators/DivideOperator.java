package edu.csc413.calculator.operators;

import edu.csc413.calculator.evaluator.Operand;

public class DivideOperator extends Operator{
    public int priority()
    {
    	return 2;
    }
    public Operand execute(Operand op1, Operand op2 )
    {
    	int res = op1.getValue() / op2.getValue();
    	Operand result = new Operand(res);
    	return result;
    }
}
