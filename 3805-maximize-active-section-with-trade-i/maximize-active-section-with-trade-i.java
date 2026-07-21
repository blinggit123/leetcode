class Solution {
    public int maxActiveSectionsAfterTrade(String s) {
        int n = s.length();
        int ones = 0;
        int maxMerge = 0;
        int prevZeroGroup = Integer.MIN_VALUE;

        int i = 0;
        while (i < n) {
            int j = i;
            while (j < n && s.charAt(j) == s.charAt(i)) {
                j++;
            }

            int len = j - i;

            if (s.charAt(i) == '1') {
                ones += len;
            } else {
                maxMerge = Math.max(maxMerge, prevZeroGroup + len);
                prevZeroGroup = len;
            }

            i = j;
        }

        return ones + maxMerge;
    }
}