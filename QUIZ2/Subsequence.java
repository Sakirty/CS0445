import java.util.*;

public class Subsequence{
    public static void main(String args[]){
        VectorStack<Integer> LTM = new VectorStack<Integer>();
        VectorStack<Integer> WM = new VectorStack<Integer>();
        fianl VectorStack<Integer> FS = new VectorStack<Integer>();
        Scanner inScan = new Scanner(System.in);
		Scanner fReader;
        String ones="";
        String splitones[];
        ones = inScan.nextLine();

        System.out.println("You entered:" + ones);

        int long = ones.length();
        splitones= ones.split("+ =");
        int spli = splitones.length;
        int index=0;
        while(index<spli){
            LTM.push(1);
            index++;
        }
        for(int i = 0; i<index; i++){
            LTM.pop(1);
            WM.push(1);
            FS.push(1);
        }
        while(FS!=empty()){
            FS.pop(1);
            LTM.push(1);
        }
        String sub = splitones.subString(0,spli);
        boolean valid = false;
        for(int i = 0; i<sub.length();i++){
            if(WM.empty()&&i<sub.length){
                System.out.println("NOT VALID");
                return valid = false;
            }else if(WM!=empty()&&i=sub.length()){
                return valid = false;
                System.out.println("NOT VALID");
            }   else{
                System.out.println("VALID");
                return valid = ture;
            } 
        }
    }
}