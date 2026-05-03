from functools import cmp_to_key

class Solution:
    def largestNumber(self, nums: List[int]) -> str:
        # Convert to string
        nums = list(map(str, nums))
        
        # Custom comparator
        def compare(a, b):
            if a + b > b + a:
                return -1   # a should come first
            else:
                return 1    # b should come first
        
        nums.sort(key=cmp_to_key(compare))
        
        # Edge case: all zeros
        if nums[0] == "0":
            return "0"
        
        return ''.join(nums)