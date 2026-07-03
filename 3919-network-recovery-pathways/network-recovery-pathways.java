class Solution {
    public int findMaxPathScore(int[][] edges, boolean[] online, long k) {
        int n = online.length;

        ArrayList<int[]>[] graph = new ArrayList[n];
        int[] indegree = new int[n];

        for (int i = 0; i < n; i++)
            graph[i] = new ArrayList<>();

        ArrayList<Integer> vals = new ArrayList<>();

        for (int[] e : edges) {
            graph[e[0]].add(new int[]{e[1], e[2]});
            indegree[e[1]]++;
            vals.add(e[2]);
        }

        // Topological order
        int[] topo = new int[n];
        ArrayDeque<Integer> q = new ArrayDeque<>();
        int[] deg = indegree.clone();

        for (int i = 0; i < n; i++)
            if (deg[i] == 0)
                q.offer(i);

        int idx = 0;
        while (!q.isEmpty()) {
            int u = q.poll();
            topo[idx++] = u;
            for (int[] e : graph[u]) {
                if (--deg[e[0]] == 0)
                    q.offer(e[0]);
            }
        }

        Collections.sort(vals);

        ArrayList<Integer> uniq = new ArrayList<>();
        for (int v : vals) {
            if (uniq.isEmpty() || uniq.get(uniq.size() - 1) != v)
                uniq.add(v);
        }

        int ans = -1;
        int lo = 0, hi = uniq.size() - 1;

        while (lo <= hi) {
            int mid = (lo + hi) / 2;
            int limit = uniq.get(mid);

            if (check(limit, graph, topo, online, k)) {
                ans = limit;
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }

        return ans;
    }

    private boolean check(int limit, ArrayList<int[]>[] graph,
                          int[] topo, boolean[] online, long k) {

        int n = graph.length;
        long INF = Long.MAX_VALUE / 4;
        long[] dist = new long[n];
        Arrays.fill(dist, INF);
        dist[0] = 0;

        for (int u : topo) {
            if (dist[u] == INF)
                continue;

            if (u != 0 && u != n - 1 && !online[u])
                continue;

            for (int[] e : graph[u]) {
                int v = e[0];
                int w = e[1];

                if (w < limit)
                    continue;

                if (v != n - 1 && !online[v])
                    continue;

                if (dist[u] + w < dist[v]) {
                    dist[v] = dist[u] + w;
                }
            }
        }

        return dist[n - 1] <= k;
    }
}