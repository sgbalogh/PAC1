public class ListNode<T> {
	public T value;
	public ListNode<T> next;

	public ListNode(T value, ListNode<T> next) {
		this.value = value;
		this.next = next;
	}
	
	public ListNode<T> setValue(T value) {
		return new ListNode<T>(value, this);
	}

}
