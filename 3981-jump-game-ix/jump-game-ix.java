class Solution {
    public int[] maxValue(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];
        
        // 1. Precompute prefix maximums
        int[] preMax = new int[n];
        preMax[0] = nums[0];
        for (int i = 1; i < n; i++) {
            preMax[i] = Math.max(preMax[i - 1], nums[i]);
        }
        
        // 2. Iterate backward and maintain the minimum value seen so far
        int sufMin = Integer.MAX_VALUE;
        for (int i = n - 1; i >= 0; i--) {
            // If the max value available in the prefix [0...i] is greater than 
            // any value to the right (sufMin), it means we can jump forward 
            // to that smaller value, then use that index's reachability.
            if (i < n - 1 && preMax[i] > sufMin) {
                ans[i] = ans[i + 1];
            } else {
                // Otherwise, we are stuck within the current prefix's limits.
                ans[i] = preMax[i];
            }
            
            // Update the smallest value seen to the right for the next index
            sufMin = Math.min(sufMin, nums[i]);
        }
        
        return ans;
    }
}