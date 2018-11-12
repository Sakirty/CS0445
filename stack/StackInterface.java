public interface StackInterface<E> {
  /** Add elem to the top of the stack. If elem is null do nothing.
   * @param elem Element to add to the stack.
   */
  public void push(E elem);

  /** Removes and returns the top element in the stack or null if empty.
   * @return The top element in the stack, null if empty
   */
  public E pop();

  /**
   * Returns but does not remove the top element from the stack or null if empty.
   * @return The top element in the stack, null if empty.
   */
  public E peek();

  /**
    * Checks if the stack is empty
    * @return true if the stack is empty, false otherwise.
    */
  public boolean isEmpty();

  /**
   * Empty the stack of all items.
   */
  public void clear();
}
