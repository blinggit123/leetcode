import java.util.*;

class Solution {
    public int minimumDistance(int[] nums) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        
        // Store indices for each value
        for (int i = 0; i < nums.length; i++) {
            map.computeIfAbsent(nums[i], k -> new ArrayList<>()).add(i);
        }
        
        int ans = Integer.MAX_VALUE;
        
        for (List<Integer> list : map.values()) {
            if (list.size() < 3) continue;
            
            // Check consecutive triplets
            for (int i = 0; i + 2 < list.size(); i++) {
                int first = list.get(i);
                int third = list.get(i + 2);
                ans = Math.min(ans, 2 * (third - first));
            }
        }
        
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}