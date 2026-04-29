class Solution {
    public long maximumScore(int[][] grid) {
        int n = grid.length;
        long[][] pref = new long[n][n + 1];
        for (int j = 0; j < n; j++) {
            for (int i = 0; i < n; i++) {
                pref[j][i + 1] = pref[j][i] + grid[i][j];
            }
        }

        // dp[height][state]
        // state 0: Increasing (h_curr >= h_prev)
        // state 1: Decreasing (h_curr < h_prev)
        long[][] dp = new long[n + 1][2];
        for (int i = 0; i <= n; i++) Arrays.fill(dp[i], -1);

        // Initialization for column 0
        for (int h = 0; h <= n; h++) dp[h][0] = 0;

        for (int j = 1; j < n; j++) {
            long[][] nextDp = new long[n + 1][2];
            for (int i = 0; i <= n; i++) Arrays.fill(nextDp[i], -1);

            for (int hCurr = 0; hCurr <= n; hCurr++) {
                for (int hPrev = 0; hPrev <= n; hPrev++) {
                    
                    // Case 1: Increasing to Increasing (hPrev <= hCurr)
                    if (dp[hPrev][0] != -1) {
                        long score = dp[hPrev][0] + (hCurr > hPrev ? pref[j - 1][hCurr] - pref[j - 1][hPrev] : 0);
                        nextDp[hCurr][0] = Math.max(nextDp[hCurr][0], score);
                    }

                    // Case 2: Decreasing to Decreasing (hPrev >= hCurr)
                    if (dp[hPrev][1] != -1) {
                        long score = dp[hPrev][1] + (hPrev > hCurr ? pref[j][hPrev] - pref[j][hCurr] : 0);
                        nextDp[hCurr][1] = Math.max(nextDp[nextH(hPrev, hCurr, 1)][1], score); // Simplified mapping
                    }
                    
                    // Case 3: Transition Increasing -> Decreasing
                    if (dp[hPrev][0] != -1) {
                        long score = dp[hPrev][0] + (hPrev > hCurr ? pref[j][hPrev] - pref[j][hCurr] : 0);
                        nextDp[hCurr][1] = Math.max(nextDp[hCurr][1], score);
                    }

                    // Case 4: Transition Decreasing -> Increasing (The Valley)
                    // This is where a middle column at 0 height gets points from both sides
                    if (dp[hPrev][1] != -1) {
                        // We reset through height 0 to avoid double counting
                        nextDp[hCurr][0] = Math.max(nextDp[hCurr][0], dp[hPrev][1]);
                    }
                }
            }
            // Optimization for the "Big Drop" (h_j-2 >> h_j-1=0 << h_j)
            // If the previous column was height 0, it can effectively start any increase 
            // and gain points from the height it skipped.
            long maxPrev1 = 0;
            for(int h=0; h<=n; h++) maxPrev1 = Math.max(maxPrev1, dp[h][1]);
            nextDp[0][0] = Math.max(nextDp[0][0], maxPrev1);
            
            dp = nextDp;
        }

        long res = 0;
        for (int h = 0; h <= n; h++) {
            res = Math.max(res, Math.max(dp[h][0], dp[h][1]));
        }
        return res;
    }
    
    private int nextH(int hPrev, int hCurr, int state) {
        return hCurr; // Helper for readability
    }
}