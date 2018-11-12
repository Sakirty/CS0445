/** CS 0445 Fall 2017 (Adapted  from Dr. John Ramirez's assignment code)
 
 Assig1A driver program.  This program must work as is with your
 MultiDS<T> class.  Look carefully at all of the method calls so that
 you create your MultiDS<T> methods correctly.  For example, note the
 constructor calls and the toString() method call.  The output should
 be identical to my sample output, with the exception of the result of
 the shuffle() methods -- since this should be random yours should not
 match mine.
 */
 
public class Assig1A
{
	public static void main(String [] args)
	{
		// Testing constructor and PrimQ<T> interface
		PrimQ<Integer> theQ = new MultiDS<Integer>(5);

		// Testing addItem
		//這個不用管，只是測試
		for (int i = 0; i < 6; i++)
		{
			Integer newItem = new Integer(2 * i);
			if (!(theQ.full()))//沒滿就加
			{
				theQ.addItem(newItem);
				System.out.println(newItem + " added to Q");
			}
			else
			{
				System.out.println("No room for " + newItem);
			}
		}

		// Testing removeItem
		//同上，測試用，不用管
		while (!(theQ.empty()))
		{
			Integer oldItem = theQ.removeItem();
			System.out.println(oldItem + " retrieved from Q");
		}
		Integer noItem = theQ.removeItem();
		if (noItem == null)
			System.out.println("Nothing in the Q");//如果沒有可以移除的就停止

		// Testing array management
		//測試數列管理
		int count = 1;
		PrimQ<String> theQ2 = new MultiDS<String>(5);
		String theItem = new String("Item " + count);//最初值為1
		System.out.println("Adding " + theItem);//打印最初
		theQ2.addItem(theItem);
		for (int i = 0; i < 8; i++)
		{
			count++;//先+1再打印
			theItem = new String("Item " + count);
			System.out.println("Adding " + theItem);//後邊加入，前面拿出，循環進行
			theQ2.addItem(theItem);
			theItem = theQ2.removeItem();
			System.out.println("Removing " + theItem);
		}
		int sz = theQ2.size();
		System.out.println("There are " + sz + " items in the buffer");//最後再看有多少東西剩餘在Q2之中

		// This code will test the toString() method and the Reorder
		// interface.
		//測試轉換為數列和記錄器介面
		System.out.println("\nAbout to test Reorder methods");
		MultiDS<Integer> newDS = new MultiDS<Integer>(15);
		for (int i = 0; i < 8; i++)
		{
			newDS.addItem(new Integer(i));//將1～8加入
		}
		System.out.println(newDS.toString());//01234567
		System.out.println("Reversing");
		newDS.reverse();//76543210
		System.out.println(newDS.toString());
		System.out.println("Removing 3 items then adding 1");
		Integer bogus = newDS.removeItem();//設置一個新的來記錄去掉的items
		bogus = newDS.removeItem();//去掉一個
		bogus = newDS.removeItem();//去掉倆
		newDS.addItem(new Integer(8));//加入8
		System.out.println(newDS.toString());//轉成string打印
	
		System.out.println("Reversing");
		newDS.reverse();//801234
		System.out.println(newDS.toString());
		System.out.println("Shifting right");//向右挪1，480123
		newDS.shiftRight();
		System.out.println(newDS.toString());
		System.out.println("Shifting left twice");//向左挪2，012348
		newDS.shiftLeft();
		newDS.shiftLeft();
		System.out.println(newDS.toString());
		//測試洗牌功能
		System.out.println("\nAbout to test shuffle...");
		newDS.clear();//重新排列好
		for (int i = 0; i < 10; i++)
		{
			newDS.addItem(new Integer(i));//從0到9
		}
		System.out.println(newDS.toString());
		System.out.println("Shuffling...");
		newDS.shuffle();//洗牌
		System.out.println(newDS.toString());
		System.out.println("Removing 2 and adding 1");
		bogus = newDS.removeItem();
		bogus = newDS.removeItem();
		newDS.addItem(new Integer(22));
		System.out.println(newDS.toString());
		System.out.println("Shuffling again");
		newDS.shuffle();
		System.out.println(newDS.toString());
	}
}