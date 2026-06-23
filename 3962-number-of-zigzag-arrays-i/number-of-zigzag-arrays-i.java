class Solution {
    static final long MOD = 1_000_000_007L;

    public int zigZagArrays(int n, int l, int r) {

        int m = r - l + 1;

        long[] up = new long[m];
        long[] down = new long[m];

        for (int b = 0; b < m; b++) {
            up[b] = b;                 // < b
            down[b] = m - 1 - b;      // > b
        }

        if (n == 2) {
            long ans = 0;
            for (int i = 0; i < m; i++) {
                ans = (ans + up[i] + down[i]) % MOD;
            }
            return (int) ans;
        }

        for (int len = 3; len <= n; len++) {

            long[] prefixDown = new long[m + 1];
            long[] prefixUp = new long[m + 1];

            for (int i = 0; i < m; i++) {
                prefixDown[i + 1] =
                    (prefixDown[i] + down[i]) % MOD;

                prefixUp[i + 1] =
                    (prefixUp[i] + up[i]) % MOD;
            }

            long totalUp = prefixUp[m];

            long[] newUp = new long[m];
            long[] newDown = new long[m];

            for (int v = 0; v < m; v++) {

                // sum down[x] for x < v
                newUp[v] = prefixDown[v];

                // sum up[x] for x > v
                newDown[v] =(totalUp - prefixUp[v + 1] + MOD) % MOD;
            }

            up = newUp;
            down = newDown;
        }

        long ans = 0;

        for (int i = 0; i < m; i++) {
            ans = (ans + up[i] + down[i]) % MOD;
        }

        return (int) ans;
    }
}