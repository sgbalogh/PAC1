/**
 * Created by Stephen Balogh on 9/26/15
 * 'java balogh_calculator' to run
 */
import java.util.Scanner;

public class balogh_lab3 {
    public static void main(String[] args){
        double valueOne;
        double valueTwo;
        double result = 0;
        boolean noExit = true;
        String operation;
        Scanner parseInput = new Scanner(System.in);

        System.out.print("Please enter an initial value: ");
        valueOne = parseInput.nextDouble();
        System.out.print("Enter an operation: ");
        operation = parseInput.next();

        if (operation.equals("x")) {
            noExit = false;
            System.out.print("Exiting!");
        } else if (operation.equals("c")){
            result = 0.0;
            System.out.print("Clearing memory!\nThe current value is: " + result + "\n");
        } else if (!(operation.equals("+") || operation.equals("-") || operation.equals("*") || operation.equals("/"))) {
            System.out.print("ERROR: '" + operation + "' is an unrecognized operator!\n");
            result = valueOne; // Result gets valueOne so that the entered value is not lost upon entering while-loop
        } else { // Only do calculations (and assign to 'result') if operation is one of four possible (+,-,*,/) inputs
            System.out.print("Enter a second value: ");
            valueTwo = parseInput.nextDouble();

            if (operation.equals("+")) {
                result = valueOne + valueTwo;
            } else if (operation.equals("-")) {
                result = valueOne - valueTwo;
            } else if (operation.equals("*")) {
                result = valueOne * valueTwo;
            } else if (operation.equals("/")) {
                if (valueTwo == 0) {
                    System.out.print("ERROR: Cannot divide by zero.\n");
                    result = valueOne;
                } else {
                    result = valueOne / valueTwo;
                }
            }
            System.out.print("The current value is : " + result + "\n");
        }

// Beginning of loop (after initial calculation)
        while (noExit) {
            valueOne = result; // valueOne gets previous 'result' value to use as first input in subsequent calculation

            System.out.print("Enter next operation: ");
            operation = parseInput.next();
            if (operation.equals("x")) {
                noExit = false;
                System.out.print("Exiting!");
            } else if (operation.equals("c")){
                result = 0.0;
                System.out.print("Clearing memory!\nThe current value is: " + result + "\n");
            } else if (!(operation.equals("+") || operation.equals("-") || operation.equals("*") || operation.equals("/"))) {
                System.out.print("ERROR: '" + operation + "' is an unrecognized operator!\n");
            }  else {
                System.out.print("Enter more input: ");
                valueTwo = parseInput.nextDouble();

                if (operation.equals("+")) {
                    result = valueOne + valueTwo;
                } else if (operation.equals("-")) {
                    result = valueOne - valueTwo;
                } else if (operation.equals("*")) {
                    result = valueOne * valueTwo;
                } else if (operation.equals("/")) {
                    if (valueTwo == 0) {
                        System.out.print("ERROR: Cannot divide by zero. Previous value retained: \n");
                    } else {
                        result = valueOne / valueTwo;
                    }
                }
                System.out.print("The current value is : " + result + "\n");
            }
        }
    }
}
