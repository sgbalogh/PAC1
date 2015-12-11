/**
 * Created by Stephen Balogh on 12/4/15.
 * PAC 1, Professor Evan Korth
 * New York University
 */


import java.util.Scanner;
import java.util.StringTokenizer;

public class PostFixCalculator {

    private Stack calcStack;
    private Converter inStringToPostString;

    public PostFixCalculator() {
    }

    public void InFixStringCalc(String input) {
        this.inStringToPostString = new Converter();
        PostFixStringCalc(this.inStringToPostString.toPostFix(input));
    }

    public void PostFixStringCalc(String input) {
        String regExDelim = "[ ]+";
        String[] discreteInputs = input.split(regExDelim); // Uses above reg-ex to remove spaces from input string
        this.calcStack = new Stack();
        for (int i = 0; i < discreteInputs.length; i++) {
            this.insert(discreteInputs[i]);
        }
    }

    // Used so that the client program can see infix to postfix conversion without instantiating the Converter class
    public String InFixToPostFix(String input) {
        return inStringToPostString.toPostFix(input).replaceAll("\\s+", " ");

    }

    public void insert(String input) {
        double Op1;
        double Op2;
        double product;
        String warning = "Unable to properly perform calculation.";
        String pushBack;

        switch (input) {
            case "+":
                try {
                    Op2 = Double.parseDouble(this.calcStack.top());
                    this.calcStack.pop();
                    Op1 = Double.parseDouble(this.calcStack.top());
                    this.calcStack.pop();
                    product = Op1 + Op2;
                    pushBack = "" + product;
                    this.calcStack.push(pushBack);
                } catch (NumberFormatException ex) {
                    System.out.println(warning);
                }
                break;
            case "-":
                try {
                    Op2 = Double.parseDouble(this.calcStack.top());
                    this.calcStack.pop();
                    Op1 = Double.parseDouble(this.calcStack.top());
                    this.calcStack.pop();
                    product = Op1 - Op2;
                    pushBack = "" + product;
                    this.calcStack.push(pushBack);
                } catch (NumberFormatException ex) {
                    System.out.println(warning);
                }
                break;
            case "*":
                try {
                    Op2 = Double.parseDouble(this.calcStack.top());
                    this.calcStack.pop();
                    Op1 = Double.parseDouble(this.calcStack.top());
                    this.calcStack.pop();
                    product = Op1 * Op2;
                    pushBack = "" + product;
                    this.calcStack.push(pushBack);
                } catch (NumberFormatException ex) {
                    System.out.println(warning);
                }
                break;
            case "/":
                try {
                    Op2 = Double.parseDouble(this.calcStack.top());
                    this.calcStack.pop();
                    Op1 = Double.parseDouble(this.calcStack.top());
                    this.calcStack.pop();
                    product = Op1 / Op2;
                    pushBack = "" + product;
                    this.calcStack.push(pushBack);
                } catch (NumberFormatException ex) {
                    System.out.println(warning);
                }
                break;
            case "^":
                try {
                    Op2 = Double.parseDouble(this.calcStack.top());
                    this.calcStack.pop();
                    Op1 = Double.parseDouble(this.calcStack.top());
                    this.calcStack.pop();
                    product = Math.pow(Op1, Op2);
                    pushBack = "" + product;
                    this.calcStack.push(pushBack);
                } catch (NumberFormatException ex) {
                    System.out.println(warning);
                }
                break;
            default:
                try {
                    int inputInt = Integer.parseInt(input);
                    this.calcStack.push("" + inputInt);
                } catch (NumberFormatException ex) {
                    System.out.println("One of the inputs was not an integer.");
                }
                break;
        }
    }

    public double getCurrentValue() {
        try {
            return Double.parseDouble(calcStack.top());
        } catch (NumberFormatException ex) {
            System.out.println("Error: Only insert integers and operators. Answer set to 0.0");
            return 0.0;
        }
    }

    public static void main(String[] args) { // Calculator interface
        PostFixCalculator myCalc = new PostFixCalculator();
        boolean dontExit = true;
        Scanner userInput = new Scanner(System.in);
        String menuSelection;
        String inputExpression;

        System.out.println("Calculator");
        while (dontExit) {
            System.out.println("\nSelect your input type (or any other key to exit):\na) Postfix expression\nb) infix expression");
            menuSelection = userInput.nextLine();

            if (menuSelection.equals("a") || menuSelection.equals("A")) {
                System.out.println("Please enter your postfix expression:");
                myCalc.PostFixStringCalc(userInput.nextLine());
                System.out.println("Ans: " + myCalc.getCurrentValue());
            } else if (menuSelection.equals("b") || menuSelection.equals("B")) {
                System.out.println("Please enter your infix expression:");
                inputExpression = userInput.nextLine();
                myCalc.InFixStringCalc(inputExpression);
                System.out.println("As postfix: " + myCalc.InFixToPostFix(inputExpression));
                System.out.println("Ans: " + myCalc.getCurrentValue());
            } else {
                dontExit = false;
                System.out.println("Bye!");
            }
        }
    }
}

class Converter {
    private StringTokenizer tokenizer;
    private String[] tokenList;
    private int tokenCount;
    private String PostFixResult;
    Stack operatorStack;

    public void tokenize(String input) {

        this.tokenizer = new StringTokenizer(input, "+-/()*^", true);
        this.tokenCount = tokenizer.countTokens();
        this.tokenList = new String[tokenCount];
        for (int i = 0; i < tokenCount; i++) {
            this.tokenList[i] = tokenizer.nextToken();
        }
    }

    public String toPostFix(String input) {
        this.PostFixResult = "";
        this.operatorStack = new Stack();
        this.tokenize(input);

        for (int i = 0; i < this.tokenList.length; i++) {
            switch (this.tokenList[i]) {
                case "(":
                    operatorStack.push(this.tokenList[i]);
                    break;
                case ")":
                    while (!(operatorStack.top().equals("("))) {
                        this.PostFixResult += this.operatorStack.top() + " ";
                        this.operatorStack.pop();
                    }
                    this.operatorStack.pop(); // One last time to remove "("
                    break;
                case "*":
                    while (!(this.isLastOnStackLower("*"))) {
                        this.PostFixResult += this.operatorStack.top() + " ";
                        this.operatorStack.pop();
                    }
                    this.operatorStack.push("*");
                    break;
                case "^":
                    while (!(this.isLastOnStackLower("^"))) {
                        this.PostFixResult += this.operatorStack.top() + " ";
                        this.operatorStack.pop();
                    }
                    this.operatorStack.push("^");
                    break;
                case "/":
                    while (!(this.isLastOnStackLower("/"))) {
                        this.PostFixResult += this.operatorStack.top() + " ";
                        this.operatorStack.pop();
                    }
                    this.operatorStack.push("/");
                    break;
                case "+":
                    while (!(this.isLastOnStackLower("+"))) {
                        this.PostFixResult += this.operatorStack.top() + " ";
                        this.operatorStack.pop();
                    }
                    this.operatorStack.push("+");
                    break;
                case "-":
                    while (!(this.isLastOnStackLower("-"))) {
                        this.PostFixResult += this.operatorStack.top() + " ";
                        this.operatorStack.pop();
                    }
                    this.operatorStack.push("-");
                    break;
                default:
                    this.PostFixResult += this.tokenList[i] + " ";
                    break;
            }
        }
        while (!(this.operatorStack.top().equals("EMPTY"))) {
            this.PostFixResult += this.operatorStack.top() + " ";
            this.operatorStack.pop();
        }
        return this.PostFixResult;
    }

    private boolean isLastOnStackLower(String currentOperand) {
        String lastOperand = operatorStack.top();
        boolean condition;
        switch (currentOperand) {
            case "*":
                if (lastOperand.equals("+") || lastOperand.equals("-") || lastOperand.equals("(") || lastOperand.equals("EMPTY")) {
                    condition = true;
                } else {
                    condition = false;
                }
                break;
            case "/":
                if (lastOperand.equals("+") || lastOperand.equals("-") || lastOperand.equals("(") || lastOperand.equals("EMPTY")) {
                    condition = true;
                } else {
                    condition = false;
                }
                break;
            case "^":
                if (lastOperand.equals("+") || lastOperand.equals("-") || lastOperand.equals("(") || lastOperand.equals("EMPTY") || lastOperand.equals("/") || lastOperand.equals("*")) {
                    condition = true;
                } else {
                    condition = false;
                }
                break;
            case "+":
                if (lastOperand.equals("(") || lastOperand.equals("EMPTY")) {
                    condition = true;
                } else {
                    condition = false;
                }
                break;
            case "-":
                if (lastOperand.equals("(") || lastOperand.equals("EMPTY")) {
                    condition = true;
                } else {
                    condition = false;
                }
                break;
            default:
                condition = false;
        }
        return condition;
    }
}
