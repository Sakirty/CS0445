import java.io.*;
import java.util.*;

/**
 * Primes is a program that will compute prime numbers using the sieve of Eratosthenes.
 * 
 * @author Charles Hoot
  * @version 4.0
 */

    
public class Primes
{

    public static void main(String args[])
    {

        int max;
        
        System.out.println("Please enter the maximum value to test for primality");
        max = getInt("   It should be an integer value greater than or equal to 2.");
        
        // COMPLETE THE MAIN
        
        ListInterface Candidate = new AList();
        Candidate.add(2);
        Candidate.add(3);
        Candidate.add(4);
        Candidate.add(5);
        Candidate.add(6);
        Candidate.add(7); 
        
        System.out.println(Candidate);
          
        
        ListInterface Prime = new AList();      
        ListInterface Composites = new AList();


        for (int position = Candidate.getLength(); position>=0; position--){
        Integer index = (Integer)Candidate.getEntry(position);
        if (index > max)
            Candidate.remove(position);
        else break;
        }
     
       System.out.println("   ");      
       System.out.println(Candidate);                        
                                                             
         
       Object o = Candidate.remove(1);
       if (o != null){
       	Prime.add(o);
       }
 
       
       System.out.println("Candidate List: ");
       System.out.println(Candidate);
    
       
       System.out.println("Prime List:");	
       System.out.println(Prime); 
       	
       
       //Composites.getComposites(Candidate, Composites, Prime);
       System.out.println("Composites List: ");
       System.out.println(Composites);
        

        
    }
    
    
    /**
     * getComposites - Remove the composite values from possibles list and
     * put them in the composites list.
     *
     * @param  candidates   A list of integers holding the possible values.
     * @param  composites   A list of integers holding the composite values.
     * @param  prime   An Integer that is prime.
     */
    public static void getComposites(ListInterface<Integer> candidates, ListInterface<Integer> composites, Integer prime)
    {
        // COMPLETE THIS METHOD
        /*
        for (int i=candidates.getLength()-1; i>=0; i--)
                
            int value = candidates.getEntry(i);
            if (prime.intValue() % value.intValue()==0){
                   
                candidates.remove(i);
                composites.add(value);
            }*/
    }
    
    
    
    
    
    
    /**
     * Get an integer value.
     *
     * @return     An integer. 
     */
    private static int getInt(String rangePrompt)
    {
        Scanner input;
        int result = 10;        //Default value is 10
        try
        {
            input = new Scanner(System.in);
            System.out.println(rangePrompt);
            result = input.nextInt();
            
        }
        catch(NumberFormatException e)
        {
            System.out.println("Could not convert input to an integer");
            System.out.println(e.getMessage());
            System.out.println("Will use 10 as the default value");
        }        
        catch(Exception e)
        {
            System.out.println("There was an error with System.in");
            System.out.println(e.getMessage());
            System.out.println("Will use 10 as the default value");
        }
        return result;
                                    
    }    
    
}
