package edu.csc413.calculator.operators;

import edu.csc413.calculator.evaluator.Operand;

public class PowerOperator extends Operator{
    public int priority()
    {
    	return 3;
    }
    public Operand execute(Operand op1, Operand op2 )
    {
    	int exponent = op2.getValue();

    	if (exponent > 0)
    	{
        	int res = op1.getValue();
        	for  (int i =1; i<exponent; i++)
        	{
        		res = res * op1.getValue();
        	}
        	Operand result = new Operand(res);
        	return result;
    	}
    	if (exponent == 0)
    	{
    		return new Operand(1);
    	}
    	if (exponent < 0)
    	{
    		if (op1.getValue() == 1)
    			return new Operand(1);
    	}
    	return new Operand(0);

    }
}
