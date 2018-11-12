/** CS 0445 Fall 2017 (Adapted  from Dr. John Ramirez's assignment code)
 Assignment 1 Reorder interface
 Carefully read the specifications for the methods below and
 implement them in your MultiDS<T> class.  As with the PrimQ<T>
 interface, don't worry as much about efficiency here as you do
 about correctness.
*/

public interface Reorder
{
	// Logically reverse the data in the Reorder object so that the item
	// that was logically first will now be logically last and vice
	// versa.  The physical implementation of this can be done in 
	// many different ways, depending upon how you actually implemented
	// your physical MultiDS<T> class
	public void reverse();
	//設立一個新的array，從後往前拷貝，再將其拷貝回去

	// Remove the logical last item of the DS and put it at the 
	// front.  As with reverse(), this can be done physically in
	// different ways depending on the underlying implementation.  
	public void shiftRight();
	//設立一個新的array，另i=i+1來拷貝，超出的一個assign到第一

	// Remove the logical first item of the DS and put it at the
	// end.  As above, this can be done in different ways.
	public void shiftLeft();
	//反向執行向右
	
	public void shuffle();
	// Reorganize the items in the object in a pseudo-random way.  The exact
	// way is up to you but it should utilize a Random object (see Random in 
	// the Java API).  Thus, after this operation the "oldest" item in the
	// DS could be arbitrary.
	//用隨機API來解決
}