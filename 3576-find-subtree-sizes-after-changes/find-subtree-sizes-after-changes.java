
class Solution {
    List<Integer>[] tree;
    List<Integer>[] newTree;
    int[] last;
    int[] ans;

    public int[] findSubtreeSizes(int[] parent, String s) {
        int n = parent.length;

        tree = new ArrayList[n];
        newTree = new ArrayList[n];
        last = new int[26];
        ans = new int[n];

        Arrays.fill(last, -1);

        for (int i = 0; i < n; i++) {
            tree[i] = new ArrayList<>();
            newTree[i] = new ArrayList<>();
        }

        for (int i = 1; i < n; i++) {
            tree[parent[i]].add(i);
        }

        findNewTree(0, s, parent);

        calculateSize(0);

        return ans;
    }

    void findNewTree(int node, String s, int[] parent) {
        int ch = s.charAt(node) - 'a';

        int old = last[ch];
        last[ch] = node;

        for (int child : tree[node]) {
            int c = s.charAt(child) - 'a';

            int p = last[c];

            if (p == -1) {
                p = parent[child];
            }

            newTree[p].add(child);

            findNewTree(child, s, parent);
        }

        last[ch] = old;
    }

    int calculateSize(int node) {
        int size = 1;

        for (int child : newTree[node]) {
            size += calculateSize(child);
        }

        ans[node] = size;

        return size;
    }
}
