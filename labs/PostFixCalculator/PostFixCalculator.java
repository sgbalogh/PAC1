/**
 * Created by sgb334 on 12/4/15.
 */
import java.util.Scanner;
public class PostFixCalculator {

    private Stack calcStack;

    public PostFixCalculator() {
        this.calcStack = new Stack();
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
            default:
                this.calcStack.push(input);
                break;
        }
    }


    public double read() {
        return Double.parseDouble(this.calcStack.pop());
    }
}
