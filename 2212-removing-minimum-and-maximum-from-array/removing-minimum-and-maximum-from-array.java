class Solution {
    public int minimumDeletions(int[] nums) {

        int n = nums.length;

        int minIndex = 0;
        int maxIndex = 0;

        // Find minimum and maximum indices
        for (int i = 0; i < n; i++) {

            if (nums[i] < nums[minIndex]) {
                minIndex = i;
            }

            if (nums[i] > nums[maxIndex]) {
                maxIndex = i;
            }
        }

        int first = Math.min(minIndex, maxIndex);
        int last = Math.max(minIndex, maxIndex);

        // 3 possibilities
        int bothFront = last + 1;
        int bothBack = n - first;
        int oneEach = first + 1 + n - last;

        return Math.min(bothFront,
                Math.min(bothBack, oneEach));
    }
}