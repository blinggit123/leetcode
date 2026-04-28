import java.util.*;

class Solution {
    public int minOperations(int[][] grid, int x) {
        List<Integer> list = new ArrayList<>();
        
        // Step 1: Flatten grid
        for (int[] row : grid) {
            for (int num : row) {
                list.add(num);
            }
        }
        
        // Step 2: Check feasibility
        int mod = list.get(0) % x;
        for (int num : list) {
            if (num % x != mod) return -1;
        }
        
        // Step 3: Sort and find median
        Collections.sort(list);
        int median = list.get(list.size() / 2);
        
        // Step 4: Count operations
        int ops = 0;
        for (int num : list) {
            ops += Math.abs(num - median) / x;
        }
        
        return ops;
    }
}