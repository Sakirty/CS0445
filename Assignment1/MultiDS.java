import java.util.*;							

public class MultiDS<T> implements PrimQ<T>, Reorder {

	private T[] listArray;							
	private int itemCounts;

	@SuppressWarnings("unchecked")

	public MultiDS(int arraySize) {
		
      if (arraySize < 1){																
			throw new IllegalArgumentException("Minium for array lenght is one.");		
	  }
		listArray = (T[])new Object[arraySize];//設置一個新的數列					
		itemCounts = 0;//設置初始值為1												
	}
	
	
	public boolean addItem(T item) {//添加
		
		if (listArray == null)//如果數列為null												
			throw new IllegalStateException("Not initialized");//未初始化	
		
		if (itemCounts == listArray.length)//如果數列已滿
			return false;//返回錯誤
		
		listArray[itemCounts] = item;//將item存入數列						
		itemCounts++;//所含物計數+1											
		return true;//返回真，添加成功										
	}
	
	
	public T removeItem() {//移除
		
		if (listArray == null)//同上												
			throw new IllegalStateException("Array not initialized");		
		
		if (itemCounts == 0)//如果沒了，返回null值									
			return null;
		
		T itemRemoved = listArray[0];//移除第一個元素						
		for (int i = 0; i < itemCounts - 1; i++)//並補上洞				
         listArray[i] = listArray[i + 1];				
		
		itemCounts--;//計數-1											
		
		listArray[itemCounts] = null;						
		
		return itemRemoved;									
	}
	
	public T top() {
		
		if (listArray == null)												
			throw new IllegalStateException("Array not initialized");

		if (itemCounts == 0)
			return null;

		return listArray[itemCounts-1];

	}
	
	public boolean full() {//已滿
		
		if (listArray == null)												
			throw new IllegalStateException("Array not initialized");		
		
		if (itemCounts == listArray.length)//如果滿了，就返回true						
			return true;										
		
		else return false;//否則返回false										
	}
	
	
	public boolean empty() {//看是否為空
		
		if (listArray == null)												
			throw new IllegalStateException("Array not initialized");		
		
		if (itemCounts == 0)//裡面如果剩餘0個元素返回true										
			return true;
		
		else return false;//否則false，裏邊仍存留有元素										
	}
	
	
	public int size() {//現在裡面有多少個數
		
		if (listArray == null)												
			throw new IllegalStateException("Array not initialized");		
		
		return itemCounts;//直接返回數字值										
	}
	
	
	@SuppressWarnings("unchecked")
	public void clear() {
		
		if (listArray == null)												
			throw new IllegalStateException("Array not initialized");		
		
		listArray = (T[])new Object[listArray.length];				
		
		itemCounts = 0;													
	}
	
	
   
   
	@SuppressWarnings("unchecked")
	public void reverse() {
		
		if (listArray == null)												
			throw new IllegalStateException("Array not initialized");		
         		
		T[] tempArray = (T[])new Object[listArray.length];				
		int reverseCounter = listArray.length - 1;						
		
		for (int i = 0; i < listArray.length; i++) {					
			tempArray[reverseCounter] = listArray[i];					
			reverseCounter--;											
		}
		for (int i = 0; i < listArray.length; i++)						
			listArray[i] = tempArray[i];								
		
		if (itemCounts != listArray.length) {							
			for (int i = 0; i < itemCounts; i++)										
				listArray[i] = listArray[i + (listArray.length - itemCounts)];		
			for (int i = itemCounts; i < listArray.length; i++)			
				listArray[i] = null;
		}
	}
	
	
	public void shiftRight() {
		
		if (listArray == null)												
			throw new IllegalStateException("Array not initialized");		
		
		T storedItem = listArray[itemCounts - 1];					
		for (int i = itemCounts - 1; i > 0; i--)						
			listArray[i] = listArray[i - 1];
		listArray[0] = storedItem;									
	}
	
	
	public void shiftLeft() {
		
		if (listArray == null)												
			throw new IllegalStateException("Array not initialized");		
		
		T storedItem = listArray[0];								
		for (int i = 0; i < itemCounts - 1; i++)						
			listArray[i] = listArray[i + 1];
		listArray[itemCounts - 1] = storedItem;						
	}
	
	
	public void shuffle() {
		
		if (listArray == null)												
			throw new IllegalStateException("Array not initialized");		
		
		T storedItem;											
		int randomNumber;										
		Random randomNumGen = new Random();						

		for (int i = itemCounts - 1; i > 0; i--) {				
			randomNumber = randomNumGen.nextInt(i);				
			storedItem = listArray[i];							
			listArray[i] = listArray[randomNumber];			
			listArray[randomNumber] = storedItem;				
			}
	}
	
	

	public String toString() {
		
		if (listArray == null)												
			throw new IllegalStateException("Array not initialized");		
		
		String contents = "Contents: \n";							
		
		for (int i = 0; i < listArray.length; i++)					
			if (listArray[i] != null)								
				contents = contents.concat(listArray[i] + " ");	
		
		return contents;											
	}
}