public static String encode(String originalStr,  
            Map<Character, Integer> statistics) {  
        if (originalStr == null || originalStr.equals("")) {  
            return "";  
        }  
  
        char[] charArray = originalStr.toCharArray();  
        List<Node> leafNodes = new ArrayList<Node>();  
        buildTree(statistics, leafNodes);  
        Map<Character, String> encodInfo = buildEncodingInfo(leafNodes);  
  
        StringBuffer buffer = new StringBuffer();  
        for (char c : charArray) {  
            Character character = new Character(c);  
            buffer.append(encodInfo.get(character));  
        }  
  
        return buffer.toString();  
    }  

private static Map<Character, String> buildEncodingInfo(List<Node> leafNodes) {  
        Map<Character, String> codewords = new HashMap<Character, String>();  
        for (Node leafNode : leafNodes) {  
            Character character = new Character(leafNode.getChars().charAt(0));  
            String codeword = "";  
            Node currentNode = leafNode;  
  
            do {  
                if (currentNode.isLeftChild()) {  
                    codeword = "0" + codeword;  
                } else {  
                    codeword = "1" + codeword;  
                }  
  
                currentNode = currentNode.parent;  
            } while (currentNode.parent != null);  
  
            codewords.put(character, codeword);  
        }  
  
        return codewords;  
    }  