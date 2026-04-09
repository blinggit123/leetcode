class Solution {
    public int xorAfterQueries(int[] nums, int[][] queries) {
        int n = nums.length;
        int MOD = 1_000_000_007;
        int B = (int)Math.sqrt(n) + 1;

        long[] mult = new long[n];
        for (int i = 0; i < n; i++) mult[i] = 1;

        // Group queries by k
        List<int[]>[] smallK = new ArrayList[B];
        for (int i = 0; i < B; i++) smallK[i] = new ArrayList<>();

        for (int[] q : queries) {
            int k = q[2];
            if (k < B) smallK[k].add(q);
            else {
                int l = q[0], r = q[1], v = q[3];
                for (int i = l; i <= r; i += k) {
                    mult[i] = (mult[i] * v) % MOD;
                }
            }
        }

        // Handle small k
        for (int k = 1; k < B; k++) {
            if (smallK[k].isEmpty()) continue;

            // process each remainder separately
            for (int rem = 0; rem < k; rem++) {

                // collect positions with this remainder
                List<Integer> pos = new ArrayList<>();
                for (int i = rem; i < n; i += k) pos.add(i);

                int m = pos.size();
                long[] prefix = new long[m + 1];
                Arrays.fill(prefix, 1);

                // apply queries
                for (int[] q : smallK[k]) {
                    int l = q[0], r = q[1], v = q[3];

                    if (l % k != rem) continue;

                    // binary search range in pos[]
                    int left = (l - rem) / k;
                    int right = (r - rem) / k;

                    prefix[left] = (prefix[left] * v) % MOD;
                    if (right + 1 < prefix.length) {
                        prefix[right + 1] = (prefix[right + 1] * modInv(v, MOD)) % MOD;
                    }
                }

                // apply prefix multiplication
                long cur = 1;
                for (int i = 0; i < m; i++) {
                    cur = (cur * prefix[i]) % MOD;
                    mult[pos.get(i)] = (mult[pos.get(i)] * cur) % MOD;
                }
            }
        }

        // final XOR
        int xor = 0;
        for (int i = 0; i < n; i++) {
            long val = (nums[i] * mult[i]) % MOD;
            xor ^= (int)val;
        }

        return xor;
    }

    // modular inverse (Fermat)
    private long modInv(long a, int mod) {
        return pow(a, mod - 2, mod);
    }

    private long pow(long a, long b, int mod) {
        long res = 1;
        while (b > 0) {
            if ((b & 1) == 1) res = (res * a) % mod;
            a = (a * a) % mod;
            b >>= 1;
        }
        return res;
    }
}