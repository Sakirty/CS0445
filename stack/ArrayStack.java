import java.util.Arrays;

public class ArrayStack<E> implements StackInterface<E> {
  private static final int DEFAULT_SIZE = 20;

  private E[] contents;
  private int top;

  public ArrayStack() {
    this(DEFAULT_SIZE);
  }

  public ArrayStack(int cap) {
    contents = (E[]) new Object[cap];
    top = 0;
  }

  public void push(E elem) {
    // Notice this implementation does not allow nulls to be added to the stack.
    if (elem != null) {
      contents[++top] = elem;
    }

    // Usually better to resize after an addition rather than before for
    // multi-threading reasons. Although, that is not important for this class.
    if (top == contents.length-1) resize();
  }

  private void resize() {
    // Arrays.copyOf is easily the best method for these reziable array implementations.
    // Look it up in the Java Class Library if you're interested.
    contents = Arrays.copyOf(contents, top*2);
  }

  public E pop() {
    if (!isEmpty()) {
      E temp = contents[top];
      contents[top--] = null;
      return temp;
    } else {
      return null;
    }
  }

  public E peek() {
    if (!isEmpty()) {
      return contents[top];
    } else {
      return null;
    }
  }

  public boolean isEmpty() {
    return top == 0;
  }

  public void clear() {
    while(!isEmpty())
      pop();
  }

  // Tests
  public static void main(String[] args) {
    StackInterface<Integer> myStack = new ArrayStack<>();

    myStack.push(12);
    myStack.push(15);
    myStack.push(22);

    while (!myStack.isEmpty())
      System.out.println(myStack.pop());

    // Testing resize
    System.out.println("\nTesting resize");
    for (Integer i = 0; i < 100; i++) {
      myStack.push(i);
    }

    while (!myStack.isEmpty())
      System.out.print(myStack.pop() + " ");
  }
}
