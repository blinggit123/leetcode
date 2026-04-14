import java.util.*;

class Solution {
    public long minimumTotalDistance(List<Integer> robot, int[][] factory) {
        Collections.sort(robot);
        Arrays.sort(factory, (a, b) -> a[0] - b[0]);

        // Expand factory slots
        List<Integer> slots = new ArrayList<>();
        for (int[] f : factory) {
            for (int i = 0; i < f[1]; i++) {
                slots.add(f[0]);
            }
        }

        int n = robot.size();
        int m = slots.size();

        long[][] dp = new long[n + 1][m + 1];

        // Initialize
        for (int i = 1; i <= n; i++) {
            Arrays.fill(dp[i], Long.MAX_VALUE / 2);
        }

        for (int j = 0; j <= m; j++) {
            dp[0][j] = 0;
        }

        // DP
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                // Skip
                dp[i][j] = dp[i][j - 1];

                // Take
                long cost = Math.abs(robot.get(i - 1) - slots.get(j - 1));
                dp[i][j] = Math.min(dp[i][j],
                        dp[i - 1][j - 1] + cost);
            }
        }

        return dp[n][m];
    }
}