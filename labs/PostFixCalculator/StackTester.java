/**
 * Created by sgb334 on 12/4/15.
 */
public class StackTester {
    public static void main(String[] args) {

        PostFixCalculator myCalc = new PostFixCalculator();

        myCalc.insert("4");
        myCalc.insert("3");
        myCalc.insert("*");
        myCalc.insert("10");
        myCalc.insert("*");

        System.out.println(myCalc.read());







    }
}
