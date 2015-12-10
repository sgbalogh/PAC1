/**
 * Created by Stephen Balogh on 12/4/15.
 * PAC 1, Professor Evan Korth
 * New York University
 */

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
        String pushBack;

        switch (input) {
            case "+":
                try {
                    Op2 = Double.parseDouble(this.calcStack.pop());
                    Op1 = Double.parseDouble(this.calcStack.pop());
                    product = Op1 + Op2;
                    pushBack = "" + product;
                    this.calcStack.push(pushBack);
                } catch (NumberFormatException ex) {
                    System.out.println("Unable to properly parse number. Only enter numbers, operators, or parentheses.");
                }
                break;
            case "-":
                try {
                    Op2 = Double.parseDouble(this.calcStack.pop());
                    Op1 = Double.parseDouble(this.calcStack.pop());
                    product = Op1 - Op2;
                    pushBack = "" + product;
                    this.calcStack.push(pushBack);
                } catch (NumberFormatException ex) {
                    System.out.println("Unable to properly parse number. Only enter numbers, operators, or parentheses.");
                }
                break;
            case "*":
                try {
                    Op2 = Double.parseDouble(this.calcStack.pop());
                    Op1 = Double.parseDouble(this.calcStack.pop());
                    product = Op1 * Op2;
                    pushBack = "" + product;
                    this.calcStack.push(pushBack);
                } catch (NumberFormatException ex) {
                    System.out.println("Unable to properly parse number. Only enter numbers, operators, or parentheses.");
                }
                break;
            case "/":
                try {
                    Op2 = Double.parseDouble(this.calcStack.pop());
                    Op1 = Double.parseDouble(this.calcStack.pop());
                    product = Op1 / Op2;
                    pushBack = "" + product;
                    this.calcStack.push(pushBack);
                } catch (NumberFormatException ex) {
                    System.out.println("Unable to properly parse number. Only enter numbers, operators, or parentheses.");
                }
                break;
            case "^":
                try {
                    Op2 = Double.parseDouble(this.calcStack.pop());
                    Op1 = Double.parseDouble(this.calcStack.pop());
                    product = Math.pow(Op1, Op2);
                    pushBack = "" + product;
                    this.calcStack.push(pushBack);
                } catch (NumberFormatException ex) {
                    System.out.println("Unable to properly parse number. Only enter numbers, operators, or parentheses.");
                }
                break;
            default:
                this.calcStack.push(input);
                break;
        }
    }

    public double getCurrentValue() {
        try {
            return Double.parseDouble(calcStack.top());
        } catch (NumberFormatException ex) {
            System.out.println("Something went wrong; most likely something other than an operator or operand has been inserted. Answer set to 0.0.");
            return 0.0;
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
                        this.PostFixResult += this.operatorStack.pop() + " ";
                    }
                    this.operatorStack.pop(); // One last time to remove "("
                    break;
                case "*":
                    while (!(this.isLastOnStackLower("*"))) {
                        this.PostFixResult += this.operatorStack.pop() + " ";
                    }
                    this.operatorStack.push("*");
                    break;
                case "^":
                    while (!(this.isLastOnStackLower("^"))) {
                        this.PostFixResult += this.operatorStack.pop() + " ";
                    }
                    this.operatorStack.push("^");
                    break;
                case "/":
                    while (!(this.isLastOnStackLower("/"))) {
                        this.PostFixResult += this.operatorStack.pop() + " ";
                    }
                    this.operatorStack.push("/");
                    break;
                case "+":
                    while (!(this.isLastOnStackLower("+"))) {
                        this.PostFixResult += this.operatorStack.pop() + " ";
                    }
                    this.operatorStack.push("+");
                    break;
                case "-":
                    while (!(this.isLastOnStackLower("-"))) {
                        this.PostFixResult += this.operatorStack.pop() + " ";
                    }
                    this.operatorStack.push("-");
                    break;
                default:
                    this.PostFixResult += this.tokenList[i] + " ";
                    break;
            }
        }
        while (!(this.operatorStack.top().equals("EMPTY"))) {
            this.PostFixResult += " " + this.operatorStack.pop();
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
                if (lastOperand.equals("+") || lastOperand.equals("-") || lastOperand.equals("(") || lastOperand.equals("EMPTY")) {
                    condition = true;
                } else {
                    condition = false;
                }
                break;
            case "+":
                if (lastOperand.equals("EMPTY")) {
                    condition = true;
                } else {
                    condition = false;
                }
                break;
            case "-":
                if (lastOperand.equals("EMPTY")) {
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
