/**
 * Created by Stephen Balogh on 12/4/15.
 * PAC 1, Professor Evan Korth
 * New York University
 */
public class Stack {

    private String[] arrayStack;
    private int top;
    private int arraySize;

    public Stack() {
        this(10);
    }

    public Stack(int size) {
        this.arrayStack = new String[size];
        this.arraySize = arrayStack.length;
        top = -1;
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

    public void clear() {
        this.top = -1;
    }

    public int getTop() {
        return this.top;
    }

    public String getValueAtTop() {
        if (this.top > -1) {
            return this.arrayStack[top];
        } else {
            return "EMPTY";
        }
    }
}
