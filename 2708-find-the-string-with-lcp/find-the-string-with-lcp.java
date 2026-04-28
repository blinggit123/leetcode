import java.util.*;

class Solution {
    int[] parent;

    public String findTheString(int[][] lcp) {
        int n = lcp.length;
        parent = new int[n];
        
        for (int i = 0; i < n; i++) parent[i] = i;

        // Step 1: Union
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (lcp[i][j] > 0) {
                    union(i, j);
                }
            }
        }

        // Step 2: Assign characters
        char[] res = new char[n];
        Map<Integer, Character> map = new HashMap<>();
        char ch = 'a';

        for (int i = 0; i < n; i++) {
            int root = find(i);
            if (!map.containsKey(root)) {
                if (ch > 'z') return ""; // more than 26 groups
                map.put(root, ch++);
            }
            res[i] = map.get(root);
        }

        // Step 3: Validate
        for (int i = n - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                int expected;
                if (res[i] == res[j]) {
                    expected = 1;
                    if (i + 1 < n && j + 1 < n) {
                        expected += lcp[i + 1][j + 1];
                    }
                } else {
                    expected = 0;
                }

                if (lcp[i][j] != expected) return "";
            }
        }

        return new String(res);
    }

    int find(int x) {
        if (parent[x] != x) parent[x] = find(parent[x]);
        return parent[x];
    }

    void union(int a, int b) {
        parent[find(a)] = find(b);
    }
}