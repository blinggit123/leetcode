import java.util.*;

class Solution {
    public long[] distance(int[] nums) {
        int n = nums.length;
        long[] res = new long[n];
        
        // Step 1: Map value -> list of indices
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.computeIfAbsent(nums[i], k -> new ArrayList<>()).add(i);
        }
        
        // Step 2: Process each group
        for (List<Integer> indices : map.values()) {
            int size = indices.size();
            
            // Prefix sum array
            long[] prefix = new long[size + 1];
            for (int i = 0; i < size; i++) {
                prefix[i + 1] = prefix[i] + indices.get(i);
            }
            
            // Compute distances
            for (int i = 0; i < size; i++) {
                int idx = indices.get(i);
                
                long left = (long) idx * i - prefix[i];
                long right = (prefix[size] - prefix[i + 1]) - (long) idx * (size - i - 1);
                
                res[idx] = left + right;
            }
        }
        
        return res;
    }
}