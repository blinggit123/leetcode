class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        
        Set<String> wordSet = new HashSet<>(wordList);
        
        // If endWord is not in dictionary, no transformation possible
        if (!wordSet.contains(endWord)) return 0;
        
        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);
        
        int level = 1;  // beginWord counts as level 1
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            
            for (int i = 0; i < size; i++) {
                String current = queue.poll();
                
                if (current.equals(endWord)) {
                    return level;
                }
                
                char[] chars = current.toCharArray();
                
                // Try all one-letter transformations
                for (int j = 0; j < chars.length; j++) {
                    char original = chars[j];
                    
                    for (char c = 'a'; c <= 'z'; c++) {
                        chars[j] = c;
                        String next = new String(chars);
                        
                        if (wordSet.contains(next)) {
                            queue.offer(next);
                            wordSet.remove(next); // mark visited
                        }
                    }
                    
                    chars[j] = original; // restore
                }
            }
            
            level++;
        }
        
        return 0;
    }
}