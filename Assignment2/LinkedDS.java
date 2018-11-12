import java.util.*;

public class LinkedDS<T> implements PrimQ<T>,Reorder {
        protected Node firstNode;
        protected int numberOfEntries;
        

        public LinkedDS(){
            clear();
        }

        public LinkedDS(LinkedDS<T> oldList){

		    if (oldList.size() > 0){

			    Node temp = oldList.firstNode;		
			    Node nodeOL = new Node(temp.data);	
			    firstNode = nodeOL;				
			
			
			    Node currentNode = firstNode;
			    temp = temp.next;

			    while (temp != null){
				    currentNode.next = new Node(temp.data);
				    temp = temp.next;
				    currentNode = currentNode.next;
			    }

                numberOfEntries = oldList.numberOfEntries;

		    }			
        }


        public void clear(){
            firstNode = null;
            numberOfEntries = 0;
        }

        public boolean addItem(T newEntry){

            Node nodeAdd = new Node(newEntry);

            if(empty()){
                firstNode = nodeAdd;
            }   else    { 
                Node lastNode = getNode(numberOfEntries);
                lastNode.setNextNode(nodeAdd);
            }
            numberOfEntries++;
            return true;
        }

        public T removeItem(){
           
           T result;
           
           if(!empty()){
               result = firstNode.getData();
               firstNode = firstNode.getNextNode();
               numberOfEntries--;
            }   else    {
                result= null;
            }
            
            return result;
        }

        public boolean empty(){

            if (numberOfEntries == 0)
                return true;
            else return false;

        }

        public int size(){
            return numberOfEntries;
        }
        
        public boolean add(int position, T newEntry){
		    boolean success = true;
		        if((position >=1) && (position <= numberOfEntries +1)){
			        Node node = new Node(newEntry);
			        if(position ==1){
				        node.setNextNode(firstNode);
				        firstNode = node;
			        }   else    {
                        Node node1 = getNode(position - 1);
                        Node node2 = node1.getNextNode();
				        node.setNextNode(node2);
				        node1.setNextNode(node);
			        }
			numberOfEntries++;
		}   else    success=false;
		return success;
	}

        //⬆PrimQ    ⬇reorder

        public void reverse(){
            Node before, current, after;//a,b,c

            before = null;
            current = firstNode;

            while(current!=null){
                after = current.next;//assign to after
                current.next = before;//assign after to before
                before = current;//
                current = after;
            }

            firstNode = before;

        }

        public void shiftRight(){
            rightRotate(1);
        }

        public void shiftLeft(){
            leftRotate(1);		
        }


        public void leftShift(int num){
            
            Node temp = firstNode;

            for(int i = 0; i < num-1; i++){
                temp = temp.next;
            }
            firstNode=temp.next;  
            temp.next = null;

            numberOfEntries = numberOfEntries - num;
        }

        public void rightShift(int num){

            Node temp = firstNode;	
			
            for (int i = 0; i < numberOfEntries-num-1; i++)	 
			{								
				temp = temp.next;
			}
			temp.next = null;		   
	          									
			numberOfEntries = numberOfEntries - num;
        }

        public void leftRotate(int num){
            
            Node temp = firstNode;

            while(temp.next!=null){
                temp = temp.next;
            }
            temp.next = firstNode;
            for(int i = 0; i < num; i++){
                temp = temp.next;
            }
            firstNode = temp.next;
            temp.next = null;
        }

        public void rightRotate(int num){
            Node temp = firstNode;

            while(temp.next != null){
                temp = temp.next;
            }
            temp.next = firstNode;
            for(int i=0; i<numberOfEntries-num; i++){
                temp = temp.next;
            }        
            firstNode = temp.next;
            temp.next = null;
        }

        public String toString(){
		    StringBuilder stringB = new StringBuilder();
		    for (Node cur = firstNode; cur != null; cur = cur.next){
			    stringB.append(cur.data.toString());
			    stringB.append(" ");
		    }
		    return stringB.toString();
	    }
        
        private Node getNode(int aPosition)	{
		    assert !empty() && (1 <= aPosition) && (aPosition <= numberOfEntries);
		    Node currentNode = firstNode;
		
      
		    for (int counter = 1; counter < aPosition; counter++)
			currentNode = currentNode.getNextNode();
		
		    assert currentNode != null;
      
		    return currentNode;
	    }

        private Node getReferenceTo(T anEntry){
            boolean found = false;
            Node currentNode = firstNode;

            while(!found&&(currentNode!=null)){
                if(anEntry.equals(currentNode.data))
                    found = true;
                else
                    currentNode = currentNode.next;
            }

            return currentNode;
        }

    public class Node{
        public T data;  
        public Node next;  
      
        public Node(T dataPortion){
            this(dataPortion, null);
        }  
      
        
        public Node(T dataPortion, Node nextNode){
                data = dataPortion;
                next = nextNode;
        }  
      
        public T getData(){
                return data;
        }  
      
        public void setData(T newData){
                data = newData;
        }  
  
        public Node getNextNode(){
                return next;
        }
      
        public void setNextNode(Node nextNode){
                next = nextNode;
        }
    }
}