public class LinkedStack<E> implements StackInterface<E> {
  private Node head;

  public LinkedStack() {
    head = null;
  }

  public void push(E elem) {
    // Note this implementation does not allow nulls to be added to the stack.
    if (elem != null) {
      Node newNode = new Node(elem); // Add newNode to front of chain
      newNode.next = head;
      head = newNode;
    }
  }

  public E pop() {
    E result = null;

    if (!isEmpty()) {
      result = head.data;
      head = head.next;
    }

    return result;
  }

  public E peek() {
    if (!isEmpty())
      return head.data;
    return null;
  }

  public boolean isEmpty() {
    return head == null;
  }

  public void clear() {
    head = null; // If head is null, there is no reference to chain.
    // Java garbage collector will deallocate all memory the nodes used.
  }

  private class Node {
    private E data;
    private Node next;

    private Node(E dataP) {
      data = dataP;
      next = null;
    }
  }

  // Tests
  public static void main(String[] args) {
    StackInterface<Integer> test = new LinkedStack<>();

    test.push(1);
    test.push(2);
    test.push(3);
    System.out.println(test.pop()); // 3
    System.out.println(test.peek()); // 2
    System.out.println(test.pop()); // 2
    System.out.println(test.pop()); // 1
    System.out.println(test.peek()); // null.
  }
}
