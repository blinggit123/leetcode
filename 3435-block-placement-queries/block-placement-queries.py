from sortedcontainers import SortedList

class SegmentTree:
    def __init__(self, n):
        self.n = n
        self.tree = [0] * (4 * n)

    def update(self, idx, val, node=1, l=0, r=None):
        if r is None:
            r = self.n - 1

        if l == r:
            self.tree[node] = val
            return

        mid = (l + r) // 2

        if idx <= mid:
            self.update(idx, val, node * 2, l, mid)
        else:
            self.update(idx, val, node * 2 + 1, mid + 1, r)

        self.tree[node] = max(
            self.tree[node * 2],
            self.tree[node * 2 + 1]
        )

    def query(self, L, R, node=1, l=0, r=None):
        if r is None:
            r = self.n - 1

        if L > r or R < l:
            return 0

        if L <= l and r <= R:
            return self.tree[node]

        mid = (l + r) // 2

        return max(
            self.query(L, R, node * 2, l, mid),
            self.query(L, R, node * 2 + 1, mid + 1, r)
        )


class Solution:
    def getResults(self, queries):
        MAX = 50005

        obstacles = SortedList([0, MAX])

        st = SegmentTree(MAX + 1)
        st.update(MAX, MAX)

        ans = []

        for q in queries:

            if q[0] == 1:
                x = q[1]

                idx = obstacles.bisect_left(x)

                prev = obstacles[idx - 1]
                nxt = obstacles[idx]

                st.update(nxt, nxt - x)
                st.update(x, x - prev)

                obstacles.add(x)

            else:
                x, sz = q[1], q[2]

                idx = obstacles.bisect_right(x) - 1
                last = obstacles[idx]

                best = st.query(0, last)
                best = max(best, x - last)

                ans.append(best >= sz)

        return ans