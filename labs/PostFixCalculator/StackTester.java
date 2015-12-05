/**
 * Created by Stephen Balogh on 12/4/15.
 * PAC 1, Professor Evan Korth
 * New York University
 */
public class StackTester {
    public static void main(String[] args) {

        PostFixCalculator myCalc = new PostFixCalculator();

        myCalc.PostFixStringCalc("4 8 +");
        System.out.println(myCalc.getCurrentValue());

        myCalc.InFixStringCalc("(4+8)*(6-5)/((3-2)*(2+2))");
        System.out.println(myCalc.getCurrentValue());


    }
}
