class Solution {
    public long sumAndMultiply(int n) {
        if (n == 0) return 0;

        int temp = n;
        long rev = 0;
        int sum = 0;

        // Build reversed non-zero digits
        while (temp > 0) {
            int d = temp % 10;
            if (d != 0) {
                rev = rev * 10 + d;
                sum += d;
            }
            temp /= 10;
        }

        // Reverse again to restore original order
        long x = 0;
        while (rev > 0) {
            x = x * 10 + rev % 10;
            rev /= 10;
        }

        return x * sum;
    }
}