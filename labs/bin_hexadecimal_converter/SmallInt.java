/**
 * Created by stephen on 10/29/15.
 */
public class SmallInt {

    public static final int MAXVALUE = 1000000;
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
