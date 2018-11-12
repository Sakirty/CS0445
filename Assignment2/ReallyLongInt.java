/** CS 0445 Fall 2017 (Adapted  from Dr. John Ramirez's assignment code)
 This is a partial implementation of the ReallyLongInt class.  You need to
 complete the implementations of the remaining methods.  Also, for this class
 to work, you must complete the implementation of the LinkedDS class.
 See additional comments below.
*/
import java.util.*;
public class ReallyLongInt 	extends LinkedDS<Integer> 
							implements Comparable<ReallyLongInt>
{
	// Instance variables are inherited.  You may not add any new instance variables
	
	// Default constructor
	private ReallyLongInt()
	{
		super();
	}

	// Note that we are adding the digits here in the FRONT. This is more efficient
	// (no traversal is necessary) and results in the LEAST significant digit first
	// in the list.  It is assumed that String s is a valid representation of an
	// unsigned integer with no leading zeros.
	public ReallyLongInt(String s)
	{
		super();
		char c;
		int digit;
		// Iterate through the String, getting each character and converting it into
		// an int.  Then make an Integer and add at the front of the list.  Note that
		// the addItem() method (from LinkedDS) does not need to traverse the list since
		// it is adding in position 0.  
		for (int i = 0; i < s.length(); i++)
		{
			c = s.charAt(i);
			if (('0' <= c) && (c <= '9'))
			{
				digit = c - '0';
				this.add(1,new Integer(digit));
			}
			else throw new NumberFormatException("Illegal digit " + c);
		}
	}

	// Simple call to super to copy the nodes from the argument ReallyLongInt
	// into a new one.
	public ReallyLongInt(ReallyLongInt rightOp)
	{
		super(rightOp);
	}
	
	// Method to put digits of number into a String.  Since the numbers are
	// stored "backward" (least significant digit first) we first reverse the
	// number, then traverse it to add the digits to a StringBuilder, then
	// reverse it again.  This seems like a lot of work, but given the
	// limitations of the super classes it is what we must do.
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		if (numberOfEntries > 0)
		{
			this.reverse();
			for (Node curr = firstNode; curr != null; curr = curr.next)
			{
				sb.append(curr.data);
			}
			this.reverse();
		}
		return sb.toString();
	}

	// You must implement the methods below.  See the descriptions in the
	// assignment sheet
	public int size(){
    	return numberOfEntries;
    }
	public ReallyLongInt add(ReallyLongInt rightOp)
	{	
		ReallyLongInt answer = new ReallyLongInt();
		ReallyLongInt temp1, temp2;
		Integer sum;
		boolean tf = false;
		

		if(this.size()>=rightOp.size()){
			temp1 = this;
			temp2 = rightOp;
		}	else {
			temp2 = this;
			temp1 = rightOp;
		}
		
		Node index1 = temp1.firstNode;
		Node index2 = temp2.firstNode;

		for(int i = 1; i< temp2.size(); i++){
			sum = index1.getData()+index2.getData();
			if(tf){
				sum++;
				//tf = false;
			}
			if(sum>=10){
				sum = sum%10;
				tf=true;
			}
			answer.addItem(sum);
			index1=index1.next;
			index2=index2.next;
		}

		sum = index1.getData()+index2.getData();

		if(tf){
			sum++;
		}
		if(sum>=10){
			sum=sum%10;
			tf=true;
		}
		for( int i=0; i<temp1.size()-temp2.size(); i++){
			index1=index1.next;
			sum = index1.getData();
			if(tf){
				sum++;
			}
			if(sum>=10){
				sum = sum%10;
				tf=true;
			}
			answer.addItem(sum);
		}
		if(tf)
			answer.addItem(1);
		
		return answer;
	}
	public ReallyLongInt subtract(ReallyLongInt rightOp)
	{	
		int result = this.compareTo(rightOp);
		ReallyLongInt answer = new ReallyLongInt();
		
		if (result<0){
				throw new ArithmeticException("Invalid Difference -- Negative Number");
		}
		if(result==0){
			answer.addItem(0);
		}	else	{
			Integer different, minuend;
			boolean borUp = false;
			Node index3, index4;

			index3 = this.firstNode;
			index4 = rightOp.firstNode;
			
			for(int i=1; i<rightOp.size(); i++){
				minuend = index3.getData()-0;
				if(borUp)
					minuend--;
				if(minuend>=index4.getData()){
					different = minuend - index4.getData();
				}	else	{
					different = minuend - index4.getData()+10;
					borUp = true;
				}
				answer.addItem(different);
				index3=index3.next;
				index4=index4.next;
			}
			
			minuend = index3.getData() - 0;

			if(borUp)
				minuend--;
			if(minuend>=index4.getData()){
				different = minuend - index4.getData();
			}	else	{
				different = minuend - index4.getData()+10;
				borUp = true;
			}
			answer.addItem(different);

			for(int i=0; i<this.size()-rightOp.size();i++){
				index3=index3.next;
				minuend = index3.getData()-0;
				if(borUp)
					minuend--;
				if(minuend==0){
					different = minuend;
				}	else	{
					different = minuend + 10;
					borUp = true;
				}
				answer.addItem(different);
			}
			

			answer.reverse();
			Node temporary = answer.firstNode;
			answer.reverse();
		}
		
		return answer;
	}

	public int compareTo(ReallyLongInt rOp)
	{
		if(this.size() > rOp.size())
			return 1;
		else if(this.size()> rOp.size())
			return(-1);
		else{
			this.reverse();
			rOp.reverse();
			Node index1 = firstNode;
			Node index2 = rOp.firstNode;
			boolean equal = true;
			int answer = 0;
			int i = 0;
			while(i<this.size()&&equal){
				if(index1.getData()<index2.getData()){
					equal = false;
					answer = -1;
					i++;
				}	else if(index1.getData()>index2.getData()){
					equal = false;
					answer = 1;
					i++;
				}	else	{
					index1=index1.next;
					index2=index2.next;
					equal=true;
					i++;
				}
			}
			this.reverse();
			rOp.reverse();
			return answer;
		}
	}


	public boolean equals(Object rightOp)
	{
		int result = this.compareTo((ReallyLongInt) rightOp);
		if(result == 0)
			return true;
		else return false;
	}

	public void multTenToThe(int num)
	{	
		this.reverse();
		for(int i =0; i< num; i++)
			this.addItem(new Integer(0));
		this.reverse();
	}

	public void divTenToThe(int num)
	{
		this.leftShift(num);
	}
	
	
}
