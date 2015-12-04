/**
 * Created by sgb334 on 12/4/15.
 */
public class Stack {

    private String[] arrayStack;
    private int top = -1;
    private int arraySize;


    public Stack() {
        this(10);
    }

    public Stack(int size) {
        this.arrayStack = new String[size];
        this.arraySize = arrayStack.length;
    }

    public String pop() {
        if (top >= 0) {
            this.top--;
            return arrayStack[top + 1];
        } else {
            return "Array is empty.";
        }
    }

    public void push(String pushed) {
        String[] biggerArrayStack;
        if (top < (arraySize - 1)) {
            this.top++;
            this.arrayStack[top] = pushed;
        } else {
            biggerArrayStack = new String[arraySize + 5];
            for (int i = 0; i < arraySize; i++) {
                biggerArrayStack[i] = arrayStack[i];
            }
            this.arrayStack = biggerArrayStack;
            this.arraySize = arrayStack.length;
            this.top++;
            this.arrayStack[top] = pushed;
        }
    }

    public int getTop() {
        return this.top;
    }
}
