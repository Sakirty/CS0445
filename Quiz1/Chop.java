import java.io.*;
import java.util.*;

public class chop{
    public static void main(String args[]){
        Scanner heads = new Scanner(System.in);
        System.out.println("Enter Heads Size:");
        String initialHead = heads.nextLine();

        ResizableArrayBag numHeads = new ResizableArrayBag();
        numHeads.add(initialHead);//add head to bag
        
        int chop = 0;
        ResizableArrayBag chopTake = new ResizableArrayBag();
        

        while (!numHeads.isEmpty()){
            String headLeft = numHeads.remove();
            if (headLeft()!= 1){
                numHeads.add(headLeft.substring(1));
                numHeads.add(headLeft.substring(1));//add 2 new heads
                chop++;
            }
        }



        System.out.println("ITS KILLED, TOOK " + chop + "ROUNDS.");


       



    }
}