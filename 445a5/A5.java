import java.util.*;
import java.io.*;
import TreePackage.*;

public class A5{

    //tatic String huff;
    static BinaryTree treeOne;
    static String huffCrs = new String();
    int nodeNum;
    
    public static void main(String [] args) throws Exception{
        Scanner infile = new Scanner(new File(args[0]));//load huff
        String line;
        BinaryNode root  = new BinaryNode();
        
        while(infile.hasNextLine()){
            line = infile.nextLine();
            if(line.startsWith("I")){             
            System.out.println("starts with I");
            BinaryNode tempNode;
            
            } else if(line.startsWith("L")){
            System.out.println("starts with L");
            String[] parts = line.split(" ");
            String partData = parts[1];
            System.out.println(partData);
            huffCrs += partData;
            //node.setData(partData);
            }
        }
        int n = huffCrs.length();
        treeOne = new BinaryTree(n);
        BinaryNode[] node1 = new BinaryNode[2*n-1];
        treeOne.setTree(node1);
        //treeOne.setTree(root);
        System.out.println(treeOne);
        //huff = infileName.clone();
        //huff.replace("I","");
        //huff.replace("L","");
        //System.out.println(infile);
        new A5();
    }

    //public static BinaryNode node1(String line){}
    
    /*
    public void haffmanCode(BinaryNode[] nodes,Code[] haffCode)
    {
        int n = this.nodeNum;
        Code code = new Code(n);//4
        int child,parent;
        
        for(int i=0;i<n; i++)//给前面n个输入的节点进行编码
        {
           code.start = n-1;//3
           code.weight = nodes[i].weight;//1
           child = i;//0
           parent = nodes[child].parent;
           //从叶子节点向上走来生成编码，画图即可。
           while(parent!=0){
              if(nodes[parent].leftChild == child)
              {
                  code.bit[code.start] = 0;
              }
              else
              {
                  code.bit[code.start] = 1;
              }
              
              code.start--; 
              child = parent;
              parent = nodes[child].parent;
           }
           
           Code temp = new Code(n);
           for(int j=code.start+1;j < n;j++){
               temp.bit[j] = code.bit[j];
            }
           temp.weight = code.weight;
           temp.start = code.start;
           haffCode[i] = temp;
            
        }
    }
    */
    public void setNode(BinaryNode node, String line,int n){
        int[] bit; 
        for(int i=0; i<2*n-1;i++){
            BinaryNode temp = new BinaryNode();
            if(line.startsWith("I")){
                if(temp.canAdvanceToLeft){
                temp.advanceToLeft();
                temp.setData(0);
                temp.canAdvanceToLeft = false;
                } else if(!temp.canAdvanceToLeft){
                    if(temp.canAdvanceToRight){
                        temp.advanceToRight();
                        temp.setData(0);
                        temp.canAdvanceToRight = false;
                    }
                } else
                    temp.resetAccess();

            } else if ( line.startsWith("L")){
                String[] parts = line.split(" ");
                String partData = parts[1];
                temp.setData(partData);
                temp.resetAccess();
            }
        }
    } 

    public A5(){
        //Provide opitions
        
        System.out.println("Please choose from the following:");
        System.out.println("1) Encode a text string");
        System.out.println("2) Decode a Huffman string");
        System.out.println("3) Quit");
        //scan the input
        Scanner inScan = new Scanner(System.in);
        int inPut;
        inPut = inScan.nextInt();
        //options
        options(inPut);
    }

    public static void options(int inPut){
        Scanner inScan = new Scanner(System.in);
        if(inPut == 1){
            System.out.println("1 OK");
        } else if(inPut == 2){
            System.out.println("2 OK");
            System.out.println("Enter a String from the following characters: ");
            System.out.println(huffCrs);
        } else if(inPut == 3){
            System.out.println("Good-Bye.");
            System.exit(0);
        } else {
            System.out.println("Please enter 1,2 or 3");
            inPut = inScan.nextInt();
            options(inPut);
        }     
    }
}