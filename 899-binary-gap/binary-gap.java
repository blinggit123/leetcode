class Solution {
    public int binaryGap(int n) {
        int lastPos = -1;   // stores position of previous 1
        int maxGap = 0;
        int position = 0;   // current bit position
        
        while (n > 0) {
            if ((n & 1) == 1) {  // if current bit is 1
                if (lastPos != -1) {
                    maxGap = Math.max(maxGap, position - lastPos);
                }
                lastPos = position;
            }
            
            n = n >> 1;  // shift right
            position++;
        }
        
        return maxGap;
    }
}