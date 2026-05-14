class Solution:
    def isGood(self, nums: List[int]) -> bool:
        nums.sort()
        n = nums[-1]

        if len(nums) != n + 1:
            return False

        for i in range(n):
            if nums[i] != i + 1:
                return False

        return nums[-1] == nums[-2]