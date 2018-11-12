
/**
 * Use a double recursion to compute the maximum of an array of values.
 * 
 * @author Charles Hoot
 * @version 4.0
 */
public class RecursiveMaxOfArray
{

    
    /**
     * Compute the maximum of a range of values in an array recursively.
     *
     * @param data   An array of integers.
     * @param from  The low index for searching for the maximum.
     * @param to    The high index for searching for the maximum.
     * 
     * preconditions: from <= to, from >=0, to<length, length>0
     *                
     * @return     The maximum value in the array.
     */
    
    public  int max(int data[], int from, int to)
    {
        int result = 0;
        
        if(data ==null || from >to || to > data.length-1||from<0){
           throw new BadArgumentsForMaxException("Invalid arguments");
       }
        
        if(from == to){
            result = data[from];
            
            return result;
        }
        else{
            int result1 =0;
            int result2 =0;
            int mid = (from + to)/2;
            result1 =max(data, from, mid);
            result2 = max(data,mid+1,to);
            if(result1>=result2){
                result = result1;
            }
            else{
                result = result2;
            }
        }
        return result;
    }
    
    
}

