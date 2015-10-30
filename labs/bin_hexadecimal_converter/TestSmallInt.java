/**
 * Created by stephen on 10/29/15.
 */
import java.util.Scanner;
public class TestSmallInt {
    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);
        SmallInt siInstance;
        System.out.println("Enter a decimal value between 0 and " + SmallInt.MAXVALUE + ": ");
        siInstance = new SmallInt(userInput.nextInt());
        System.out.println("As binary value: " + siInstance.getBin() + "\nAs hexadecimal value: " + siInstance.getHex());

    }
}
