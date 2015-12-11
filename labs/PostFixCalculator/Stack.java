/**
 * Created by Stephen Balogh on 12/4/15.
 * PAC 1, Professor Evan Korth
 * New York University
 */

public class Stack implements UnboundedStackInterface<String> {

    private String[] arrayStack;
    private int topIndex;
    private int arraySize;

    public Stack() {
        this(10);
    }

    public Stack(int size) {
        this.arrayStack = new String[size];
        this.arraySize = arrayStack.length;
        topIndex = -1;
    }

    public void pop() {
        if (!isEmpty()) {
            this.topIndex--;
        } else {
            throw new StackUnderflowException("Pop attempted on an empty stack.");
        }
    }

    public void push(String pushed) {
        String[] biggerArrayStack;
        if (topIndex < (arraySize - 1)) {
            this.topIndex++;
            this.arrayStack[topIndex] = pushed;
        } else {
            biggerArrayStack = new String[arraySize + 5];
            for (int i = 0; i < arraySize; i++) {
                biggerArrayStack[i] = arrayStack[i];
            }
            this.arrayStack = biggerArrayStack;
            this.arraySize = arrayStack.length;
            this.topIndex++;
            this.arrayStack[topIndex] = pushed;
        }
    }

    public void clear() {
        this.topIndex = -1;
    }

    public boolean isEmpty() {
        if (this.topIndex == -1) {
            return true;
        } else {
            return false;
        }
    }

    public int getTopIndex() {
        return this.topIndex;
    }

    public String top() {
        if (this.topIndex > -1) {
            return this.arrayStack[topIndex];
        } else {
            return "EMPTY";
        }
    }
}
