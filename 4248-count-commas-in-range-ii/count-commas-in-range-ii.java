class Solution {
    public long countCommas(long n) {
        long totalCommas = 0;
        long start = 1000; // First number with 1 comma
        long commas = 1;

        while (start <= n) {
            long end = (start * 1000) - 1; // Upper bound for current comma count
            long currentEnd = Math.min(n, end);
            
            // Count how many numbers in this range
            long count = currentEnd - start + 1;
            totalCommas += count * commas;

            start *= 1000;
            commas++;
        }

        return totalCommas;
    }
}