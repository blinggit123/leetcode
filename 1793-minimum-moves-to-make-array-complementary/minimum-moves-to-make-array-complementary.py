class Solution:
    def minMoves(self, nums: List[int], limit: int) -> int:
        n = len(nums)
        
        # Difference array
        diff = [0] * (2 * limit + 2)

        for i in range(n // 2):
            a = nums[i]
            b = nums[n - 1 - i]

            low = min(a, b)
            high = max(a, b)
            s = a + b

            # Initially assume 2 moves
            diff[2] += 2

            # 1 move range starts
            diff[low + 1] -= 1

            # 0 move at exact sum
            diff[s] -= 1

            # back to 1 move
            diff[s + 1] += 1

            # back to 2 moves
            diff[high + limit + 1] += 1

        ans = float('inf')
        curr = 0

        for target in range(2, 2 * limit + 1):
            curr += diff[target]
            ans = min(ans, curr)

        return ans