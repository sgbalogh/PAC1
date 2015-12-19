public interface MaxStack<T> {
	boolean isEmpty();
	
	void push(T value);
	
	T pop();
	
	T getMaxSoFar();
}
