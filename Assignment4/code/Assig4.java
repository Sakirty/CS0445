import java.util.*;
import java.io.*;
import java.lang.*;

public class Assig4 {
	
	int arraySize;
	Integer [] randomArray;
	Integer[] reverseArray;	
	Integer [] sortedArray;
	String fileName;
	
	//設定變量：數組大小，隨機，反向，已排列，和輸入名稱
	public static void main(String[] args) {
		new Assig4();
	}
	public Assig4() {
		
		int trials;
		
		Scanner inScan = new Scanner(System.in);
		//掃描用戶需求

		System.out.println("Please enter the size of the array: ");
		arraySize = inScan.nextInt();
		
		System.out.println("Please enter Trails: ");
		trials = inScan.nextInt();

		if(arraySize>20){
			System.out.println("Please enter the name of the file(with.txt): ");
			fileName = inScan.next();
			File file = new File(fileName);
			try {
				file.createNewFile();
			} catch (IOException e) {}
		}
		//以上掃瞄完畢 建立數組
		setupArray(arraySize);
		
		simpleQuick(randomArray, "Random", arraySize, trials);
		medianOfThree(randomArray, "Random", arraySize, 5, trials);
		medianOfThree(randomArray, "Random", arraySize, 20, trials);
		medianOfThree(randomArray, "Random", arraySize, 100, trials);
		randomPivot(randomArray, "Random", arraySize, 5, trials);
		mergeSort(randomArray, "Random", arraySize, trials);


		simpleQuick(sortedArray, "Sorted", arraySize, trials);
		medianOfThree(sortedArray, "Sorted", arraySize, 5, trials);
		medianOfThree(sortedArray, "Sorted", arraySize, 20, trials);
		medianOfThree(sortedArray, "Sorted", arraySize, 100, trials);
		randomPivot(sortedArray, "Sorted", arraySize, 5, trials);
		mergeSort(sortedArray, "Sorted", arraySize, trials);


		simpleQuick(reverseArray, "Reverse", arraySize, trials);
		medianOfThree(reverseArray, "Reverse", arraySize, 5, trials);
		medianOfThree(reverseArray, "Reverse", arraySize, 20, trials);
		medianOfThree(reverseArray, "Reverse", arraySize, 100, trials);
		randomPivot(reverseArray, "Reverse", arraySize, 5, trials);
		mergeSort(reverseArray, "Reverse", arraySize, trials);
		
		if(arraySize>20)
			System.out.println("File generated.");
	}
	
	
	public void setupArray(int size) {
        //初始化數組，設定大小
		randomArray = new Integer [size];
		reverseArray = new Integer [size];	
		sortedArray = new Integer [size];
		
		
		Integer [] tempArray = new Integer [size];
        //設定一個臨時數組來儲存產生的隨機數
		Random tempRand = new Random();
		for (int i = 0; i < size; i++) {
			Integer randomInteger = tempRand.nextInt((size - 0) + 1) + 0;
			tempArray[i] = randomInteger;
		}
        //生成該隨機數列
		
		System.arraycopy(tempArray, 0, randomArray, 0, tempArray.length);
		Arrays.fill(tempArray, null);	
		//將全部temp拷入random，再清空temp
		
		for (int i = 1; i <= size; i++) 
			tempArray[i-1] = i;
		System.arraycopy(tempArray, 0, sortedArray, 0, size);
		Arrays.fill(tempArray, null);
		
		
		for (int i = size; i > 0; i--)
			tempArray[size-i] = i;
		System.arraycopy(tempArray, 0, reverseArray, 0, size);
		Arrays.fill(tempArray, null);
		
	}

	
	
	public void simpleQuick(Integer [] theArray, String type, int size, int trials) {
		
		long sum = 0;
		Integer [] tempArray = new Integer[size];
		for (int i = 0; i < trials; i++) {
			Arrays.fill(tempArray, null);
			
			System.arraycopy(theArray, 0, tempArray, 0, theArray.length);
			//開始計時
			long start = System.nanoTime();
			
			Quick.quickSort(tempArray, size);
			
			long finish = System.nanoTime();	
			
			long timeUsed = finish - start;
			sum = sum + timeUsed;
			
			if (size <= 20)
				underTwenty("SimpleQuick",theArray, tempArray, type, size, sum);
	
			
		}
		
		double average = sum / trials;
		average = average / 1000000000;		
		
		try{
			PrintWriter generateFile = new PrintWriter(new BufferedWriter(new FileWriter(fileName, true)));
			generateFile.println("Algorithm: Simple QuickSort");
			generateFile.println("Array Size: " + size);
			generateFile.println("Order: " + type);
			generateFile.println("Number of Trials: " + trials);
			generateFile.println("Average Time per trail: " + average +" sec.");
			generateFile.println();
			generateFile.close();
		} catch(IOException e){}
		
	}
	
	public void medianOfThree(Integer [] theArray, String type, int size, int min, int trials) {
		
		double sum = 0;
		Integer [] tempArray = new Integer[size];
		for (int i = 0; i < trials; i++) {
			Arrays.fill(tempArray, null);
			System.arraycopy(theArray, 0, tempArray, 0, randomArray.length);
			
			long start = System.nanoTime();
			
			TextMergeQuick.quickSort(tempArray, tempArray.length, min);
			
			long finish = System.nanoTime();	
			
			long timeUsed = finish - start;		
			sum = sum + timeUsed;
			
			if (size <= 20) 
				underTwenty("MedianOfThree",theArray, tempArray, type, size, sum);
			
		}
		
		double average = sum / trials;
		average = average / 1000000000;		
		
		try{
			PrintWriter generateFile = new PrintWriter(new BufferedWriter(new FileWriter(fileName, true)));
			generateFile.println("Algorithm: Median Of Three (" + min + ")");
			generateFile.println("Array Size: " + size);
			generateFile.println("Order: " + type);
			generateFile.println("Number of Trials: " + trials);
			generateFile.println("Average Time per trail: " + average +" sec.");
			generateFile.println();
			generateFile.close();
		} catch(IOException e){}
			

	}
	


	public void randomPivot(Integer [] theArray, String type, int size, int min, int trials) {
		long sum = 0;
		Integer [] tempArray = new Integer[size];
		for (int i = 0; i < trials; i++) {
			
			Arrays.fill(tempArray, null);
			System.arraycopy(theArray, 0, tempArray, 0, theArray.length);
			
			long start = System.nanoTime();		
			
			RandomPivot.quickSort(tempArray, size);
			
			long finish = System.nanoTime();	
			
			long timeUsed = finish - start;		
			sum = sum + timeUsed;
			
			if (size <= 20) 
				underTwenty( "randomPivot",theArray, tempArray, type, size, sum);
		}
			
		
		
		double average = sum / trials;
		average = average / 1000000000;		
		
		try{
			PrintWriter generateFile = new PrintWriter(new BufferedWriter(new FileWriter(fileName, true)));
			generateFile.println("Algorithm: RandomPivot (" + min + ")");
			generateFile.println("Array Size: " + size);
			generateFile.println("Order: " + type);
			generateFile.println("Number of Trials: " + trials);
			generateFile.println("Average Time per trail: " + average +" sec.");
			generateFile.println();
			generateFile.close();
		} catch(IOException e){}
		
			
	}		

	

	public void mergeSort(Integer [] theArray, String type, int size, int trials){
		double sum = 0;
		Integer [] tempArray = new Integer[size];
		for (int i = 0; i < trials; i++) {
			Arrays.fill(tempArray, null);
			System.arraycopy(theArray, 0, tempArray, 0, randomArray.length);
			
			long start = System.nanoTime();
			
			TextMergeQuick.quickSort(tempArray, size);
			
			long finish = System.nanoTime();	
			
			long timeUsed = finish - start;		
			sum = sum + timeUsed;
			
			if (size <= 20) 
				underTwenty("MergeSort",theArray, tempArray, type, size, sum);
			
		}
		
		double average = sum / trials;
		average = average / 1000000000;		
		try{
			PrintWriter generateFile = new PrintWriter(new BufferedWriter(new FileWriter(fileName, true)));
			generateFile.println("Algorithm: MergeSort");
			generateFile.println("Array Size: " + size);
			generateFile.println("Order: " + type);
			generateFile.println("Number of Trials: " + trials);
			generateFile.println("Average Time per trail: " + average +" sec.");
			generateFile.println();
			generateFile.close();
		} catch(IOException e){}
		
			
	}
	
	public void underTwenty(String name, Integer[] theArray, Integer[] tempArray, String type, int size, double sum){
		System.out.println("Algorithm: " + name );
		System.out.println("Array Size: " + size);
		System.out.println("Order: " + type);
		System.out.println("Initial Data in Array: " + Arrays.toString(theArray));
		System.out.println("Data in Array after Sorting: " + Arrays.toString(tempArray));
		System.out.println("Time: " + sum +" nanoSecond.");
		System.out.println();
	}
}