/**
 * Created by Stephen Balogh on 10/29/15
   PAC 1, NYU Department of Computer Science
   Professor Evan Korth
 */
public class SmallInt {

    public static final int MAXVALUE = 1000;
    private int value;

    SmallInt(int x) {
        this.setDec(x);
    }

    public void setDec(int y) {
        if ((y >= 0) && (y <= MAXVALUE)) {
            this.value = y;
        } else {
            this.value = 0;
            System.out.println("That was not within the acceptable range of 0 to " + MAXVALUE + ". Setting to 0 instead.");
        }
    }

    public String getDec() {
        String myString = "" + value;
        return myString;
    }

    public String getBin() {
        String bitString = "";
        int valueRep = this.value;
        if (valueRep == 0) {
            return "0";
        } else {
            while (valueRep > 0) {
                int bit = (valueRep % 2);
                valueRep = (valueRep / 2);
                if (bit == 1) {
                    bitString = "1" + bitString;
                } else {
                    bitString = "0" + bitString;
                }
            }
            return bitString;
        }
    }

    /*
    EXPLANATION OF getBin()

    Take the base-10 number 499 as an example. That number can be expressed as the binary string 111110011,
    which is returned by getBin() when value = 499. This is computed, in this implementation of getBin(), via
    repeated division of the input within a while-loop, with bits being produced from right to left.

    In the while-loop, the number (499 when the loop begins) is first modulus divided by 2. This will reveal if
    a remainder exists –– or, in other words, what the value at the numper position or power that the loop is currently at
    should be set to. With a base-2 numbering system, there are only two options; therefore, if there is any mod
    remainder at all, we can write that remainder in the current position of the binary string. On the first cycle
    for 499, the product of this is 1 (499 % 2 = 1), so the first position in the string (which corresponds to 2^0,
    or 1 power) is "1". The if/else statement in the while-loop will add the corresponding number to the binary string.

    We also need to see if the number (499) will require an additional (in this case, 2^1) position to be represented;
    this is the purpose of dividing the input using integer division. The product is saved as the value of the
    variable that stored the original number (and which is used in the control of the while-loop). Dividing this number
    by the base (2) is what allows us to jump to the next position –– so, to the next left-most position in the string.
    Since the integer division product of 499 / 2 = 249, then we see that 249 is the value at the beginning of the
    next iteration of the loop.

    We keep doing that –– that is, progressing one more position left after each iteration, checking to see what the
    remainder is (if there is one), writing it, and then integer dividing the further reduced input number by the base
    until nothing is left –– meaning that the value of the input is now 0 (thus making the while-loop condition false),
    and that our original number has been entirely represented in the binary string.

    So far, at the beginning of the second iteration of the loop, we have a binary string of "1", and "valueRep" = 249.
    We first do 249 % 2 = 1, and so know that the corresponding power for this loop iteration (i.e., the power of
    2^1, or 2), will receive a 1. We write 1 to that position, prepending it to the running binary string, now leaving
    us with "11". Then we take 249 / 2 = 124, which is what valueRep gets and the value at the beginning of the third
    iteration. 124 % 2 = 0, so for the third iteration (at the 2^2, or 4 number position) a 0 is prepended.
    Now we have "011".

    Eventually, it takes 9 full iterations until "valueRep" is reduced to 0 and the loop can end, and we are left
    with "111110011".

    EXPLANATION OF getHex()

    The getHex() method works on the input number in a way that is exactly similar to the binary example; the only
    difference is that the string being created is base-16 instead of base-2. But still, the logic is identical:

    An input is given to the method (say, the base-10 number 678). This is held by a variable, and a while-loop is
    begun, the condition of which stipulating that the loop will continue until this variable is no longer > 0.
    Within the loop, the first iteration corresponds to the first number position in a hex string; and since we are using
    hexadecimal numbering, it would be the position corresponding to the power of 16^0 –– the "ones" position. With
    binary, a base-two system, there are only two possibilities (0 or 1); with hex we have 16, (0 - 9, then a - f).
    So we take the input variable and modulus divide it by the base –– just like in getBin(). This gives us the value
    for our first number position.

    678 % 16 = 6, and so this remainder becomes the value for that position. Then we want to check the next-higher
    power –– 16^1, or 16 –– so we integer divide 678 by 16, which equals 42, and write that as the value
    of the variable being checked. Then the loop begins again. Values are always written by prepending them to the
    string, so that they represent the movement leftward from the previous value (i.e., to higher powers, corresponding
    to the amount of times the input variable has been divided by the base).

    With getHex(), the conditional section is a bit more complicated than in getBin(), since there are 16 different
    characters that could be printed. For 0 - 9, it is the same as in base-10, so whenever the mod remainder is less
    than 10 we can directly convert that number to its string counterpart. For the decimal numbers 10 - 15, since they
    are represented by the characters a - f, we find their string counterparts through a simple switch statement.

    At the beginning of the second loop iteration, which will decide the value of the power of 16 position in the
    hex string, "valueRep" has a value of 42. We first take the modulus division product of 42 % 16 = 10; because 10
    is not within the 0 to 9 range we enter the switch statement, 10 is matched with 'a', and 'a' is written to the
    hex string. 42 is then divided with integer division (42 / 16 = 2), and at the beginning of the third iteration
    (for the 16^2 position, or 256 power) we start again with "valueRep" = 2. 2 is the remainder, which is written
    to the third position, leaving us with a hex string of "2a6" –– then 2 / 16 = 0, and so the loop ends.

     */

    public String getHex() {
        String hexString = "";
        int valueRep = this.value;
        if (valueRep == 0) {
            return "0";
        } else {
            while (valueRep > 0) {
                int hexRemain = (valueRep % 16);
                valueRep = (valueRep / 16);
                if (hexRemain > 0) {
                    if (hexRemain < 10) {
                        hexString = hexRemain + hexString;
                    } else {
                        switch (hexRemain) {
                            case 10:
                                hexString = "a" + hexString;
                                break;
                            case 11:
                                hexString = "b" + hexString;
                                break;
                            case 12:
                                hexString = "c" + hexString;
                                break;
                            case 13:
                                hexString = "d" + hexString;
                                break;
                            case 14:
                                hexString = "e" + hexString;
                                break;
                            case 15:
                                hexString = "f" + hexString;
                                break;
                        }
                    }
                } else {
                    hexString = "0" + hexString;
                }
            }
            return hexString;
        }
    }
}
