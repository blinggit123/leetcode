
   class Solution {
    public int findClosestNumber(int[] nums) {
        int closest = nums[0];

        for (int i = 1; i < nums.length; i++) {

            if (Math.abs(nums[i]) < Math.abs(closest)) {
                closest = nums[i];
            }
            else if (Math.abs(nums[i]) == Math.abs(closest) 
                     && nums[i] > closest) {
                closest = nums[i];
            }
        }

        return closest;
    }
}