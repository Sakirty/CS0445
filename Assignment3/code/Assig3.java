import java.util.*;
import java.io.*;

public class Assig3{
	
	private Integer[] startX = new Integer[50];
    private Integer[] startY = new Integer[50];
    private Integer[] endX = new Integer[50];
    private Integer[] endY = new Integer[50];

    private Integer ex;
    private Integer ey;
    
    public static void main(String []args){
        new Assig3();
    }

    public Assig3(){

        Scanner inScan = new Scanner(System.in);
		Scanner fReader;
		File fName;
        String fString = "", word = "";
		String splitWord[];
       	
        while (true)
        {
           try
           {
               System.out.println("Please enter grid filename:");
               fString = inScan.nextLine();
               fName = new File(fString);
               fReader = new Scanner(fName);
              
               break;
           }
           catch (IOException e)
           {
               System.out.println("Problem " + e);
           }
        }
        String [] dims = (fReader.nextLine()).split(" ");
		int rows = Integer.parseInt(dims[0]);
		int cols = Integer.parseInt(dims[1]);
		
		char [][] theBoard = new char[rows][cols];

		for (int i = 0; i < rows; i++)
		{
			String rowString = fReader.nextLine();
			for (int j = 0; j < rowString.length(); j++)
			{
				theBoard[i][j] = Character.toLowerCase(rowString.charAt(j));
			}
		}

		// Show user the grid
		for (int i = 0; i < rows; i++)
		{
			for (int j = 0; j < cols; j++)
			{
				System.out.print(theBoard[i][j] + " ");
			}
			System.out.println();
		}
		
		System.out.println("Please enter words to search for:");
        word = (inScan.nextLine()).toLowerCase();

		while(!(word.equals(""))){
			boolean found = false;
			splitWord = word.split("\\s+");
			for(int i = 0; i<splitWord.length; i++){
				splitWord[i]=splitWord[i].replaceAll("\\W","");
			}
			System.out.println("Looking for: " + word);
            System.out.println("Contains " + splitWord.length + " words.");
			for(int r=0;(r<rows && !found);r++){
				for (int c = 0; (c < cols && !found); c++)
                {
                    startX[0] = r;
                    startY[0] = c;
                    found = findPhrase(r, c, splitWord, splitWord[0], 0, theBoard);
                    if (!found)
                    {
                        for (int i = 0; i < rows; i++) {
                            for (int j = 0; j < cols; j++) {
                                theBoard[i][j] = Character.toLowerCase(theBoard[i][j]);
                            }
                        }
                    }
                }
			}

			if (found)
            {
                System.out.println("The phrase: " + word);
                System.out.println("was found:");
                for (int i = 0; i < splitWord.length; i++)
                {
                    System.out.println(splitWord[i] + ":  (" + startX[i] + "," + startY[i] + ") to (" + endX[i] + "," + endY[i] + ")");
                }

                for (int i = 0; i < rows; i++) {
                    for (int j = 0; j < cols; j++) {
                        System.out.print(theBoard[i][j] + " ");
                        theBoard[i][j] = Character.toLowerCase(theBoard[i][j]);
                    }
                    System.out.println();
                }
            }
            else
            {
                System.out.println("The phrase: " + word);
                System.out.println("was not found");
                for (int i = 0; i < rows; i++)
                {
                    for (int j = 0; j < cols; j++)
                    {
                        theBoard[i][j] = Character.toLowerCase(theBoard[i][j]);
                        System.out.print(theBoard[i][j] + " ");
                    }
                    System.out.println();
                }
            }

            System.out.print("Please enter words to search for: ");
            word = inScan.nextLine().toLowerCase();
		}
		
	}			

	public boolean findPhrase(int r, int c, String words[], String word, int num, char [][] bo){
        
        boolean found = false;

        if (r >= bo.length || r < 0 || c >= bo[0].length || c < 0)
            return false;
        else if (!findWord(r, c, words[num], 0, bo, 0))
            return false;
        else
        {
            startX[num] = r;
            startY[num] = c;
            endX[num] = ex;
            endY[num] = ey;
            if (num > words.length - 2)
            {
                found = true;
            }
            else
            {
                found = findPhrase(ex, ey + 1, words, words[num ], num + 1, bo);  //->
                if (!found)
                    found = findPhrase(ex + 1, ey, words, words[num], num + 1, bo);  //|V
                if (!found)
                    found = findPhrase(ex, ey - 1, words, words[num], num + 1, bo);  // <-
                if (!found)
                    found = findPhrase(ex - 1, ey, words, words[num], num + 1, bo);  // A|
            }
        }
        return found;
    }

    public boolean findWord(int r, int c, String word, int loc, char [][] bo, int way){
       

        // Check boundary conditions
        if (r >= bo.length || r < 0 || c >= bo[0].length || c < 0)
            return false;
        else if (bo[r][c] != word.charAt(loc))  // char does not match
            return false;
        else  	// current character matches
        {
            bo[r][c] = Character.toUpperCase(bo[r][c]);  // Change it to
            // upper case.  This serves two purposes:
            // 1) It will no longer match a lower case char, so it will
            //    prevent the same letter from being used twice
            // 2) It will show the word on the board when displayed

            boolean answer;
            if (loc == word.length()-1)		// base case - word found and we are done!
            {
                ex = r;
                ey = c;
                answer = true;
            }

            else // Still have more letters to match, so recurse.
            {		// Try all four directions if necessary.
                answer = false;
                if (way == 0)// || loc < 2)
                    answer = findWord(r, c + 1, word, loc + 1, bo, 0);  // Right
                if (!answer && (way == 1 || loc < 2))
                    answer = findWord(r+1, c, word, loc+1, bo, 1);  // Down
                if (!answer && (way == 2 || loc < 2))
                    answer = findWord(r, c-1, word, loc+1, bo, 2);  // Left
                if (!answer && (way == 3 || loc < 2))
                    answer = findWord(r-1, c, word, loc+1, bo, 3);  // Up

                // If answer was not found, backtrack.  Note that in order to
                // backtrack for this algorithm, we need to move back in the
                // board (r and c) and in the word index (loc) -- these are both
                // handled via the activation records, since after the current AR
                // is popped, we revert to the previous values of these variables.
                // However, we also need to explicitly change the character back
                // to lower case before backtracking.
                if (!answer)
                    bo[r][c] = Character.toLowerCase(bo[r][c]);
            }
            return answer;
        }
    }
}