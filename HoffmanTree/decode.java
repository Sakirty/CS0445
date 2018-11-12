public static String decode(String binaryStr,  
            Map<Character, Integer> statistics) {  
        if (binaryStr == null || binaryStr.equals("")) {  
            return "";  
        }  
  
        char[] binaryCharArray = binaryStr.toCharArray();  
        LinkedList<Character> binaryList = new LinkedList<Character>();  
        int size = binaryCharArray.length;  
        for (int i = 0; i < size; i++) {  
            binaryList.addLast(new Character(binaryCharArray[i]));  
        }  
  
        List<Node> leafNodes = new ArrayList<Node>();  
        Tree tree = buildTree(statistics, leafNodes);  
  
        StringBuffer buffer = new StringBuffer();  
  
        while (binaryList.size() > 0) {  
            Node node = tree.root;  
  
            do {  
                Character c = binaryList.removeFirst();  
                if (c.charValue() == '0') {  
                    node = node.leftNode;  
                } else {  
                    node = node.rightNode;  
                }  
            } while (!node.isLeaf());  
  
            buffer.append(node.chars);  
        }  
  
        return buffer.toString();  
    }