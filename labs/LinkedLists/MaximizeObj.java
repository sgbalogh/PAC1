/**
 * Created by Stephen Balogh on 12/14/15.
 * This is used to test FastMaxStack
 */
public class MaximizeObj implements Maximizer<String> {

    public String getGlobalMin() {
        return "";
    }

    public String getMax(String t1, String t2) {
        if (t1.length() >= t2.length()) {
            return t1;
        } else {
            return t2;
        }
    }
}
