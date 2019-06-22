package edu.csc413.calculator.evaluator;


import edu.csc413.calculator.operators.Operator;

import java.util.Stack;
import java.util.StringTokenizer;

public class Evaluator {
  private Stack<Operand> operandStack;
  private Stack<Operator> operatorStack;
  private StringTokenizer tokenizer;
  private static final String DELIMITERS = "+-*^/ ()";

  public Evaluator() {
    operandStack = new Stack<>();
    operatorStack = new Stack<>();
  }

  public int eval( String expression ) {
    String token;
    this.tokenizer = new StringTokenizer( expression, DELIMITERS, true );

    while ( this.tokenizer.hasMoreTokens() ) {
      // filter out spaces
      if ( !( token = this.tokenizer.nextToken() ).equals( " " )) {
        // check if token is an operand
        if ( Operand.check( token )) {
          operandStack.push( new Operand( token ));
        }
        else
        {
          if ( ! Operator.check( token )) {
            System.out.println( "*****invalid token******" + token );
            throw new RuntimeException("*****invalid token******");
          }
          //this operator is valid
          
          Operator newOperator = Operator.getOperator(token);
          
          if(token.equals(")"))
          {
        	  //process expressions until the matching "(" is found
        	  processExpressions(newOperator);
        	  continue;
          }
        	  	
          if (!operatorStack.empty() && !token.equals("(") && 
        		  operatorStack.peek().priority() >= newOperator.priority()) {
        	  if(operatorStack.peek().priority() == newOperator.priority())
        	  {
        	  	  Operator operate = operatorStack.pop();
        		  Operand op2 = operandStack.pop();
        	  	  Operand op1 = operandStack.pop();
        	  	  operandStack.push(operate.execute(op1, op2));
        	  }
        	  else
        	  processExpressions(newOperator);	
          }
	
          operatorStack.push( newOperator );
        }
      }
    } //end of tokenizer loop

   processExpressions(Operator.getOperator(")"));   
   return operandStack.pop().getValue();
   
  }
  
  private void processExpressions(Operator tokenOp)
  {
  	while(!operatorStack.empty())
  	{
  	  	Operator operate = operatorStack.pop();
  	  	if(operate.getClass() == Operator.getOperator("(").getClass())
  	  	{
  	  		if(tokenOp == Operator.getOperator(")"))
  	  	  		break;
  	  		//if closed parenthesis is still needed, push it back onto stack
  	  		operatorStack.push(operate);
  	  		break;
  	  	}
  	  	//process and expression
  	  	Operand op2 = operandStack.pop();
  	  	Operand op1 = operandStack.pop();
  	  	operandStack.push(operate.execute(op1, op2));
  	}
  }
}
