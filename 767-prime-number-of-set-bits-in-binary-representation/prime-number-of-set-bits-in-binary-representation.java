class Solution {
    public int countPrimeSetBits(int left, int right) {
        
        boolean[] isPrime = new boolean[21];
        int[] primes = {2, 3, 5, 7, 11, 13, 17, 19};
        
        for (int p : primes) {
            isPrime[p] = true;
        }
        
        int count = 0;
        
        for (int i = left; i <= right; i++) {
            int setBits = Integer.bitCount(i);
            if (isPrime[setBits]) {
                count++;
            }
        }
        
        return count;
    }
}