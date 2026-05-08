from collections import defaultdict, deque
from math import isqrt
from typing import List

class Solution:
    def minJumps(self, nums: List[int]) -> int:
        n = len(nums)

        max_num = max(nums)

        is_prime = [True] * (max_num + 1)
        if max_num >= 0:
            is_prime[0] = False
        if max_num >= 1:
            is_prime[1] = False

        for i in range(2, isqrt(max_num) + 1):
            if is_prime[i]:
                for j in range(i * i, max_num + 1, i):
                    is_prime[j] = False

        divisible = defaultdict(list)

        for i, val in enumerate(nums):
            x = val
            factors = set()

            d = 2
            while d * d <= x:
                if x % d == 0:
                    factors.add(d)
                    while x % d == 0:
                        x //= d
                d += 1

            if x > 1:
                factors.add(x)

            for f in factors:
                divisible[f].append(i)

        q = deque([0])
        dist = [-1] * n
        dist[0] = 0

        used_prime = set()

        while q:
            i = q.popleft()

            if i == n - 1:
                return dist[i]

            if i - 1 >= 0 and dist[i - 1] == -1:
                dist[i - 1] = dist[i] + 1
                q.append(i - 1)

            if i + 1 < n and dist[i + 1] == -1:
                dist[i + 1] = dist[i] + 1
                q.append(i + 1)

            val = nums[i]

            if is_prime[val] and val not in used_prime:
                for nxt in divisible[val]:
                    if dist[nxt] == -1:
                        dist[nxt] = dist[i] + 1
                        q.append(nxt)

                used_prime.add(val)

        return -1