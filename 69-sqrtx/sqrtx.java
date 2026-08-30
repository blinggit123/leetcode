class Solution {
    public int mySqrt(int x) {

        if (x == 0 || x == 1) {
            return x;
        }

        int lo = 1;
        int hi = x;
        int ans = 0;

        while (lo <= hi) {

            int mid = lo + (hi - lo) / 2;

            if (mid <= x / mid) {
                ans = mid;
                lo = mid + 1;
            } 
            else {
                hi = mid - 1;
            }
        }

        return ans;
    }
}