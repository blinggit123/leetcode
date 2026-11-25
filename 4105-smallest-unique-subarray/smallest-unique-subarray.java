
import java.util.*;

class Solution {
    public int smallestUniqueSubarray(int[] nums) {
        int n = nums.length;

        int[] sa = new int[n];
        int[] rank = new int[n];
        int[] temp = new int[n];

        for (int i = 0; i < n; i++) {
            sa[i] = i;
            rank[i] = nums[i];
        }

        // Build Suffix Array using doubling
        for (int k = 1; k < n; k *= 2) {

            countingSort(sa, rank, k, n);
            countingSort(sa, rank, 0, n);

            temp[sa[0]] = 0;
            int classes = 1;

            for (int i = 1; i < n; i++) {
                int a = sa[i - 1];
                int b = sa[i];

                int a1 = rank[a];
                int b1 = rank[b];

                int a2 = a + k < n ? rank[a + k] : -1;
                int b2 = b + k < n ? rank[b + k] : -1;

                if (a1 != b1 || a2 != b2) {
                    classes++;
                }

                temp[b] = classes - 1;
            }

            int[] swap = rank;
            rank = temp;
            temp = swap;

            if (classes == n) break;
        }

        // Calculate LCP using Kasai's algorithm
        int[] pos = new int[n];
        int[] lcp = new int[n];

        for (int i = 0; i < n; i++) {
            pos[sa[i]] = i;
        }

        int h = 0;

        for (int i = 0; i < n; i++) {
            int r = pos[i];

            if (r == 0) continue;

            int j = sa[r - 1];

            while (i + h < n && j + h < n &&
                   nums[i + h] == nums[j + h]) {
                h++;
            }

            lcp[r] = h;

            if (h > 0) h--;
        }

        // Find minimum unique subarray length
        int ans = n;

        for (int i = 0; i < n; i++) {
            int r = pos[i];

            int maxLcp = 0;

            if (r > 0) {
                maxLcp = Math.max(maxLcp, lcp[r]);
            }

            if (r < n - 1) {
                maxLcp = Math.max(maxLcp, lcp[r + 1]);
            }

            int len = maxLcp + 1;

            if (len <= n - i) {
                ans = Math.min(ans, len);
            }
        }

        return ans;
    }

    private void countingSort(int[] sa, int[] rank,
                              int offset, int n) {

        int max = Math.max(n + 1, 100005);
        int[] count = new int[max];

        for (int i : sa) {
            int key = i + offset < n
                    ? rank[i + offset] + 1 : 0;
            count[key]++;
        }

        int sum = 0;

        for (int i = 0; i < max; i++) {
            int c = count[i];
            count[i] = sum;
            sum += c;
        }

        int[] sorted = new int[n];

        for (int i : sa) {
            int key = i + offset < n
                    ? rank[i + offset] + 1 : 0;
            sorted[count[key]++] = i;
        }

        System.arraycopy(sorted, 0, sa, 0, n);
    }
}
