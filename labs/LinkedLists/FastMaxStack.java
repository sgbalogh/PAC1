public class FastMaxStack<T> implements MaxStack<T> {

    private final Maximizer<T> maximizer;
    private ListNode<T> top;
    private ListNode<T> maxTop;

    public FastMaxStack(Maximizer<T> maximizer) {
        this.maximizer = maximizer;
    }

    @Override
    public boolean isEmpty() {
        return top == null;
    }

    @Override
    public void push(T value) {
        if (top == null) {
            top = new ListNode<T>(value, null);
        } else {
            top = top.setValue(value);
        }

        if (maxTop == null) {
            maxTop = new ListNode<T>(value, null);
        } else {
            T currentMax = maximizer.getMax(value, maxTop.value);
            maxTop = new ListNode<T>(currentMax, maxTop);
        }

    }

    @Override
    public T pop() {
        T value = top.value;
        top = top.next;

        maxTop = maxTop.next;

        return value;
    }

    @Override
    public T getMaxSoFar() {

        return maxTop.value;
    }

}
