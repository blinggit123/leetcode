import java.util.*;

class Solution {
    public int[] beautifulArray(int n) {
        return helper(n);
    }

    private int[] helper(int n) {
        if (n == 1) return new int[]{1};

        List<Integer> res = new ArrayList<>();

        // odds
        int[] left = helper((n + 1) / 2);
        for (int x : left) {
            res.add(2 * x - 1);
        }

        // evens
        int[] right = helper(n / 2);
        for (int x : right) {
            res.add(2 * x);
        }

        // convert to array
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            ans[i] = res.get(i);
        }

        return ans;
    }
}