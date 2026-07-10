import java.util.*;

class Solution {
    public int[] pathExistenceQueries(int n, int[] nums, int maxDiff, int[][] queries) {

        Integer[] order = new Integer[n];
        for (int i = 0; i < n; i++) order[i] = i;

        Arrays.sort(order, (a, b) -> Integer.compare(nums[a], nums[b]));

        int[] pos = new int[n];
        int[] comp = new int[n];

        int component = 0;

        for (int i = 0; i < n; i++) {
            pos[order[i]] = i;

            if (i > 0 && nums[order[i]] - nums[order[i - 1]] > maxDiff)
                component++;

            comp[order[i]] = component;
        }

        int[] reach = new int[n];

        int r = 0;

        for (int l = 0; l < n; l++) {
            while (r + 1 < n &&
                    nums[order[r + 1]] - nums[order[l]] <= maxDiff)
                r++;

            reach[l] = r;

            if (r == l)
                r++;
        }

        int LOG = 18;

        int[][] jump = new int[LOG][n];

        for (int i = 0; i < n; i++)
            jump[0][i] = reach[i];

        for (int k = 1; k < LOG; k++) {
            for (int i = 0; i < n; i++) {
                jump[k][i] = jump[k - 1][jump[k - 1][i]];
            }
        }

        int[] ans = new int[queries.length];

        for (int qi = 0; qi < queries.length; qi++) {

            int u = queries[qi][0];
            int v = queries[qi][1];

            if (u == v) {
                ans[qi] = 0;
                continue;
            }

            if (comp[u] != comp[v]) {
                ans[qi] = -1;
                continue;
            }

            int l = pos[u];
            int rr = pos[v];

            if (l > rr) {
                int t = l;
                l = rr;
                rr = t;
            }

            int steps = 0;
            int cur = l;

            for (int k = LOG - 1; k >= 0; k--) {
                if (jump[k][cur] < rr) {
                    cur = jump[k][cur];
                    steps += 1 << k;
                }
            }

            ans[qi] = steps + 1;
        }

        return ans;
    }
}