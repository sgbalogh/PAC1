/**
 * Created by Stephen Balogh on 12/4/15.
 * PAC 1, Professor Evan Korth
 * New York University
 */
import java.util.Scanner;
public class PostFixCalculatorInterface {
    public static void main(String[] args) {

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
