class Solution:
    def minElement(self, nums):
        mini = float('inf')

        for num in nums:
            digit_sum = 0

            while num > 0:
                digit_sum += num % 10
                num //= 10

            mini = min(mini, digit_sum)

        return mini