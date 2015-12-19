/* Stephen Balogh, December 19, 2015
*  PAC 1, Professor Evan Korth
*  New York University
*/

public class LLHomeworkFunctions {

	public static void main(String[] args) {

		/* Testing of FastMaxStack class */
		MaximizeObj myMax = new MaximizeObj();
		FastMaxStack<String> myStack = new FastMaxStack<>(myMax);
		myStack.push("Test");
		myStack.push("TestTestTest");
		myStack.push("Test");
		System.out.println(myStack.getMaxSoFar());

		/* Testing of terminates() method */
		ListNode<String> myNode1 = new ListNode<>("World", null);
		ListNode<String> myNode2 = new ListNode<>("Hello", myNode1);
		ListNode<String> myNode3 = new ListNode<>("World", myNode2);
		ListNode<String> myNode4 = new ListNode<>("Hello", myNode3);
		ListNode<String> myNode5 = new ListNode<>("World", myNode4);
		ListNode<String> myNode6 = new ListNode<>("Hello", myNode5);

		myNode1.next = myNode6;

		System.out.println(terminates(myNode6));

	}

	/**
	 * @param <T>
	 * @param list1
	 * @param list2
	 * @return true if the lists are equal.  Assume both lists terminate.
	 */
	public static <T> boolean equalLists(ListNode<T> list1, ListNode<T> list2) {
		// TODO: Part of this assignment is to implement this correctly.
		ListNode<T> current1 = list1;
		ListNode<T> current2 = list2;

		while (!(current1 == null)) {
			if (!(current1.value == current2.value)) {
				return false;
			}
			current1 = current1.next;
			current2 = current2.next;
		}
		return true;
	}

	/**
	 * @param <T>
	 * @param list
	 * @return true if the list eventually terminates, and false if the list points back at one of it's
	 * previous nodes.
	 */
	public static <T> boolean terminates(ListNode<T> list) {

		ListNode<ListNode<T>> prevSeenListHead = null;
		ListNode<T> currentCheckedList = list;

		while (!(currentCheckedList == null)) {

			prevSeenListHead = new ListNode<>(currentCheckedList, prevSeenListHead);

			ListNode<ListNode<T>> prevSeenListChecker = prevSeenListHead.next;
			while (!(prevSeenListChecker == null)) {
				if (prevSeenListChecker.value == currentCheckedList) {
					return false;
				}
				prevSeenListChecker = prevSeenListChecker.next;
			}
			currentCheckedList = currentCheckedList.next;
		}
		return true;
	}
}

