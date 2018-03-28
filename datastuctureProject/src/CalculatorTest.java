//Ismail belmouh
//Brian Morales
//Jessica Lourenco
//Marian balcacer
//Ariel Bonificiao
//Mustafa Alfaouri

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Stack;
import java.util.StringTokenizer;
import javax.swing.JFrame;


public class CalculatorTest extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	TextField t = new TextField();
	Float f1;
	Panel p = new Panel();
	Button[] button = new Button[20]; // there are 20 buttons
	Evaluator cal = new Evaluator(); //initiate the class.
	
	//The string array is arranged in a way that when I use forloop it will fall into the right place of the gui
	String[] buttons = {"(",")","C","CE","7","8","9","+","4","5","6","-","1","2","3","*","0",".","=","/"};
	public void init(){
		setSize(400,400);
		setLayout(new BorderLayout());
		Font font = new Font("Times New Roman", Font.BOLD, 50);
		t.setFont(font);
		t.setEditable(false);
		add(t, BorderLayout.NORTH);
		add(p, BorderLayout.CENTER);
		p.setLayout(new GridLayout(5,4));
		for(int i = 0; i<20;i++){		//initiating the buttons with their string
			button[i] = new Button(buttons[i]);
			p.add(button[i]); // add the button into the panel
			button[i].addActionListener(this);	//adding eventlistener to each buttons, so it will respond when it was clicked.
		}
	}


	@Override
	public void actionPerformed(ActionEvent e) {//not the C, CE or = button
		if(e.getSource()!=button[18]&&e.getSource()!=button[2]&&e.getSource()!=button[3]){
			for(int i= 0;i<button.length;i++){
				if(e.getSource() == button[i]){
				t.setText(t.getText()+buttons[i]);
				System.out.println(t.getText().toString());//added this line to keep track of the code
				}
			}
		}else if(e.getSource() == button[2]){
			String temp = t.getText();
			if(!temp.equals("")) //so no error if t is empty.
				t.setText(temp.substring(0,temp.length()-1));
		}else if(e.getSource() == button[18]){
			String result = cal.eval(t.getText().toString());
			//String result = calculatorAlgorithmn(t.getText().toString());
			t.setText(result);
		}else if(e.getSource() == button[3]){
			t.setText("");
		}
	}
	public static void main(String[] args) {
        CalculatorTest  app = new CalculatorTest();
        app.init();
        app.setVisible(true);
    }
}


/*
  I added "(" and ")" 
 * and if it sees "(", it will push it into the operator stack, and if it sees a ")", it will 
 * execute the expression until it sees a "(" and pop the "(" from the stack. it will not push
 * ")" into the stack at all.
 *
 * 			Priority for each operator:
 * 				"#" -1
 * 				"(" 0
 * 				")" 1
 * 				"!" 1
 * 				"+" 2
 * 				"-" 2
 * 				"*" 3
 * 				"/" 3
 */
class Evaluator {
private Stack<Operand> opdStack; private Stack<Operator> oprStack;
	public Evaluator() {
		opdStack = new Stack<Operand>(); 
		oprStack = new Stack<Operator>();
		//put all mapping all the operator to their corresponding methods
		Operator.operators.put("*", new MultiplyOperator());
		Operator.operators.put("/", new DivideOperator());
		Operator.operators.put("+", new AdditionOperator());
		Operator.operators.put("-", new MinusOperator());
		Operator.operators.put("#", new HashOperator());
		Operator.operators.put("!", new ExclamationOperator());
		Operator.operators.put("(", new leftParent());
		Operator.operators.put(")", new rightParent());


		}
	public String eval(String expr) { 
		String tok;
		// init stack - necessary with operator priority schema;
		// the priority of any operator in the operator stack other then 
		// the usual operators - "+-*/" - should be less than the priority of the usual operators
		oprStack.push(Operator.operators.get("#")); 
		//added string
		String delimiters = "+-*/#!() ";
		expr= expr+"!"; // so the stack Stack will have +/! and ! priority is < /
		StringTokenizer st = new StringTokenizer(expr,delimiters,true);
		// the 3rd arg is true to indicate to use the delimiters as tokens, too but we'll filter out spaces
		
		while (st.hasMoreTokens()) {
			if ( !(tok = st.nextToken()).equals(" ")) {
				if (Operand.check(tok)) { 
					opdStack.push(new Operand(tok));
				} else {
					if (!Operator.check(tok)) {
						// filter out spaces ; check if tok is an operand
						System.out.println("*****invalid token******");
						System.exit(1); 
						}
					Operator newOpr = (Operator) Operator.operators.get(tok); // POINT 1
					//System.err.println("\n\n"+newOpr.priority+"\n");
					//when opdstack == 1, thats mean it solved to the final answer.
					while ( ((Operator)oprStack.peek()).priority() >= newOpr.priority()&&!newOpr.equals(Operator.operators.get(("(")))) { 
						// note that when we eval the expression 1 - 2 we will
						// push the 1 then the 2 and then do the subtraction operation
						// This means that the first number to be popped is the
						// second operand, not the first operand - see the following code
						if(tok.equals(")")){
							while(!oprStack.peek().equals(Operator.operators.get("("))&&!oprStack.peek().equals(Operator.operators.get("#"))){
								Operator oldOpr = ((Operator)oprStack.pop());
								Operand op2 = (Operand)opdStack.pop();
								Operand op1 = (Operand)opdStack.pop();
								opdStack.push(oldOpr.execute(op1,op2));
							}
							if(!oprStack.peek().equals(Operator.operators.get("#"))){
								oprStack.pop();
							}
							 newOpr = (Operator) Operator.operators.get(tok); // POINT 1

						}else{
						Operator oldOpr = ((Operator)oprStack.pop());
						Operand op2 = (Operand)opdStack.pop();
						Operand op1 = (Operand)opdStack.pop();
						opdStack.push(oldOpr.execute(op1,op2));
						}
					}
					if(!newOpr.equals(Operator.operators.get(")")))
						oprStack.push(newOpr);
				}
			}	
			/* Control gets here when we've picked up all of the tokens; you must add
        // code to complete the evaluation - consider how the code given here
			// will evaluate the expression 1+2*3
			// When we have no more tokens to scan, the operand stack will contain 1 2
         // and the operator stack will have + * with 2 and * on the top;
			// In order to complete the evaluation we must empty the stacks (except
			// the init operator on the operator stack); that is, we should keep
			// evaluating the operator stack until it only contains the init operator;
			// Suggestion: create a method that takes an operator as argument and
			// then executes the while loop; also, move the stacks out of the main method
			*/ 		
		} 
		oprStack.clear();		//remove the #! each time it solve an equation
		return Double.toString(opdStack.pop().getValue());//return the final value. or pop it and return..
	}

}

abstract class Operator {
	int priority;
	//creating an instance of HashMap in the Operator class.
	
	static HashMap<String, Operator> operators = new HashMap<String, Operator>();
	abstract int priority();
	static boolean check(String tok){
		return operators.containsKey(tok);
	} //use contain key
	abstract Operand execute(Operand opd1, Operand opd2);
}

class Operand{
	double value;
	Operand(String tok){
		this.value = Double.parseDouble(tok);
	}
	Operand(double value){
		this.value = value;
	}
	static boolean check(String tok){	//made static.
		try{
			Double.parseDouble(tok);
		}catch(Exception exp){
			return false;
		}
		return true;		
	}
	double getValue(){
		return value;
	}
}
class leftParent extends Operator{

	@Override
	int priority() {

		return 0;
	}

	@Override
	Operand execute(Operand opd1, Operand opd2) {
		return opd1;
	}
}
class rightParent extends Operator{

	@Override
	int priority() {
		return 1;
	}

	@Override
	Operand execute(Operand opd1, Operand opd2) {
		return opd1;
	}
}
class AdditionOperator extends Operator{
	@Override
	int priority() {
		return 2;
	}
	@Override
	Operand execute(Operand opd1, Operand opd2) {
		Operand temp = new Operand(opd1.getValue() + opd2.getValue());
		return temp;
	}
}

class MinusOperator extends Operator{
	@Override
	int priority() {
		return 2;
	}
	@Override
	Operand execute(Operand opd1, Operand opd2) {
		Operand temp = new Operand(opd1.getValue() - opd2.getValue());
		return temp;
	}
}

class MultiplyOperator extends Operator{
	@Override
	int priority() {
		return 3;
	}
	@Override
	Operand execute(Operand opd1, Operand opd2) {
		Operand temp = new Operand(opd1.getValue() * opd2.getValue());
		return temp;
	}	
}

class DivideOperator extends Operator{
	@Override
	int priority() {
		return 3;
	}
	@Override
	Operand execute(Operand opd1, Operand opd2) {
		Operand temp = new Operand(opd1.getValue() / opd2.getValue());
		return temp;
	}	
}

class HashOperator extends Operator{
	@Override
	int priority() {
		return -1;
	}
	@Override
	Operand execute(Operand opd1, Operand opd2) {
		return opd1;//return #
	}
}

class ExclamationOperator extends Operator{
	@Override
	int priority() {
		return 1;
	}
	@Override
	Operand execute(Operand opd1, Operand opd2) {
		return opd1;//return !
	}
}