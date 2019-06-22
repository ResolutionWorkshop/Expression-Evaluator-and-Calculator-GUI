package edu.csc413.calculator.operators;



import edu.csc413.calculator.evaluator.Operand;
import edu.csc413.calculator.operators.AddOperator;
import java.util.HashMap;


public abstract class Operator {
    private static final HashMap<String,Operator> operators = createOperators();
    private static HashMap<String, Operator> createOperators()
    {
    	HashMap<String, Operator> operators = new HashMap<String, Operator>();
    	operators.put("+", new AddOperator() );
    	operators.put("-", new SubtractOperator() );
    	operators.put("*", new MultiplyOperator() );
    	operators.put("^", new PowerOperator() );
    	operators.put("/", new DivideOperator() );
    	operators.put("(", new LPOperator() );
    	operators.put(")", new RPOperator() );
    	return operators;
    }
    
    public static Operator getOperator(String key)
    {
    	return operators.get(key);
    }
    
    public abstract int priority();
    public abstract Operand execute(Operand op1, Operand op2 );


    public static boolean check( String token ) {
    	if(operators.containsKey(token))
    		return true;
    	return false;
    }

}
