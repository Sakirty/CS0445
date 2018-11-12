private static Tree buildTree(Map<Character, Integer> statistics,  
            List<Node> leafs) {  
        Character[] keys = statistics.keySet().toArray(new Character[0]);  
  
        PriorityQueue<Node> priorityQueue = new PriorityQueue<Node>();  
        for (Character character : keys) {  
            Node node = new Node();  
            node.chars = character.toString();  
            node.frequence = statistics.get(character);  
            priorityQueue.add(node);  
            leafs.add(node);  
        }  
  
        int size = priorityQueue.size();  
        for (int i = 1; i <= size - 1; i++) {  
            Node node1 = priorityQueue.poll();  
            Node node2 = priorityQueue.poll();  
  
            Node sumNode = new Node();  
            sumNode.chars = node1.chars + node2.chars;  
            sumNode.frequence = node1.frequence + node2.frequence;  
  
            sumNode.leftNode = node1;  
            sumNode.rightNode = node2;  
  
            node1.parent = sumNode;  
            node2.parent = sumNode;  
  
            priorityQueue.add(sumNode);  
        }  
  
        Tree tree = new Tree();  
        tree.root = priorityQueue.poll();  
        return tree;  
    }  