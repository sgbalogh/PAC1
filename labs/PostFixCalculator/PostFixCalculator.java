/**
 * Created by Stephen Balogh on 12/4/15.
 * PAC 1, Professor Evan Korth
 * New York University
 */
import java.util.Scanner;

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
                    // Note: Double.parseDouble() is used on the string input instead of Integer.parseInt() simply
                    // because the last value on the stack could be a double (if the prior calculations had involved
                    // division). Numeric inputs from the user (handled by the 'default' condition, however, are parsed
                    // as integers.

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
            default: // This handles the input of any number
                try {
                    int inputInt = Integer.parseInt(input);
                    this.calcStack.push("" + inputInt);
                } catch (NumberFormatException ex) {
                    System.out.println("One of the inputs was not an integer, or there is an unmatched parenthesis.");
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
