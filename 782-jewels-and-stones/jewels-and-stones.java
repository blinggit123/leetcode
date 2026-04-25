import java.util.*;

class Solution {
    public int numJewelsInStones(String jewels, String stones) {
        Set<Character> set = new HashSet<>();
        
        // Store all jewels
        for (char c : jewels.toCharArray()) {
            set.add(c);
        }
        
        int count = 0;
        
        // Count matching stones
        for (char c : stones.toCharArray()) {
            if (set.contains(c)) {
                count++;
            }
        }
        
        return count;
    }
}