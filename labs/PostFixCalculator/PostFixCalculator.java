/**
 * Created by Stephen Balogh on 12/4/15.
 * PAC 1, Professor Evan Korth
 * New York University
 */
import java.util.StringTokenizer;

public class PostFixCalculator {

    private Stack calcStack;
    private Converter inStringtoPostString;

    public PostFixCalculator() {
    }

    public void InFixStringCalc(String input) {
        this.inStringtoPostString = new Converter();
        String converted = this.inStringtoPostString.toPostFix(input);
        PostFixStringCalc(converted);
    }

    public void PostFixStringCalc(String input) {
        String regExDelim = "[ ]+";
        String[] discreteInputs = input.split(regExDelim); // Uses above reg-ex to remove spaces from input string
        this.calcStack = new Stack();
        for (int i = 0; i < discreteInputs.length; i++) {
            this.insert(discreteInputs[i]);
        }
    }

    public void insert(String input) {
        double Op1;
        double Op2;
        double product;
        String pushBack;

        switch (input) {
            case "+":
                Op2 = Double.parseDouble(this.calcStack.pop());
                Op1 = Double.parseDouble(this.calcStack.pop());
                product = Op1 + Op2;
                pushBack = "" + product;
                this.calcStack.push(pushBack);
                break;
            case "-":
                Op2 = Double.parseDouble(this.calcStack.pop());
                Op1 = Double.parseDouble(this.calcStack.pop());
                product = Op1 - Op2;
                pushBack = "" + product;
                this.calcStack.push(pushBack);
                break;
            case "*":
                Op2 = Double.parseDouble(this.calcStack.pop());
                Op1 = Double.parseDouble(this.calcStack.pop());
                product = Op1 * Op2;
                pushBack = "" + product;
                this.calcStack.push(pushBack);
                break;
            case "/":
                Op2 = Double.parseDouble(this.calcStack.pop());
                Op1 = Double.parseDouble(this.calcStack.pop());
                product = Op1 / Op2;
                pushBack = "" + product;
                this.calcStack.push(pushBack);
                break;
            case "^":
                Op2 = Double.parseDouble(this.calcStack.pop());
                Op1 = Double.parseDouble(this.calcStack.pop());
                product = Math.pow(Op1, Op2);
                pushBack = "" + product;
                this.calcStack.push(pushBack);
                break;
            default:
                this.calcStack.push(input);
                break;
        }
    }

    public double getCurrentValue() {
        return Double.parseDouble(calcStack.getValueAtTop());
    }
}

class Converter {
    private StringTokenizer tokenizer;
    private String[] tokenList;
    private int tokenCount;
    private String PostFixResult;
    Stack operatorStack;

    public void tokenize(String input) {

        // This should also clean spaces out of the String, leaving only operators and operands

        this.tokenizer = new StringTokenizer(input, "+-/()*^ ", true);
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
                    while (!(operatorStack.getValueAtTop().equals("("))) {
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
                    this.operatorStack.push("+");
                    break;
                case "-":
                    this.operatorStack.push("-");
                    break;
                default:
                    this.PostFixResult += this.tokenList[i] + " ";
                    break;
            }
        }
        while (!(this.operatorStack.getValueAtTop().equals("EMPTY"))) {
            this.PostFixResult += this.operatorStack.pop();
        }
        return this.PostFixResult;
    }

    private boolean isLastOnStackLower(String currentOperand) {
        String lastOperand = operatorStack.getValueAtTop();
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
            default:
                condition = false;
        }
        return condition;
    }
}
