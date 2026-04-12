class Solution {
    public int minimumDistance(String word) {
        int n = word.length();
        
        // dp[j] = max saving when second finger at j
        int[] dp = new int[26];
        
        int total = 0;
        
        for (int i = 0; i < n - 1; i++) {
            int a = word.charAt(i) - 'A';
            int b = word.charAt(i + 1) - 'A';
            
            int d = dist(a, b);
            total += d;
            
            int[] newDp = dp.clone();
            
            for (int j = 0; j < 26; j++) {
                // move second finger instead of first
                newDp[a] = Math.max(newDp[a], dp[j] + d - dist(j, b));
            }
            
            dp = newDp;
        }
        
        int maxSave = 0;
        for (int x : dp) maxSave = Math.max(maxSave, x);
        
        return total - maxSave;
    }
    
    private int dist(int a, int b) {
        if (a == -1) return 0;
        return Math.abs(a / 6 - b / 6) + Math.abs(a % 6 - b % 6);
    }
}