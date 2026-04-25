import java.util.*;

class Solution {
    public int maxDistance(int side, int[][] points, int k) {
        
        int n = points.length;
        long[] pos = new long[n];
        
        // Map to perimeter
        for (int i = 0; i < n; i++) {
            int x = points[i][0], y = points[i][1];
            
            if (y == 0) pos[i] = x;
            else if (x == side) pos[i] = side + y;
            else if (y == side) pos[i] = 3L * side - x;
            else pos[i] = 4L * side - y;
        }
        
        Arrays.sort(pos);
        
        // Extend array
        long[] ext = new long[2 * n];
        for (int i = 0; i < n; i++) {
            ext[i] = pos[i];
            ext[i + n] = pos[i] + 4L * side;
        }
        
        long low = 0, high = 2L * side, ans = 0;
        
        while (low <= high) {
            long mid = (low + high) / 2;
            if (can(ext, n, k, mid, 4L * side)) {
                ans = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        
        return (int) ans;
    }
    
    private boolean can(long[] ext, int n, int k, long d, long perimeter) {
        
        for (int i = 0; i < n; i++) {
            int count = 1;
            long first = ext[i];
            long last = first;
            int idx = i;
            
            while (count < k) {
                // Binary search for next valid point
                int next = lowerBound(ext, idx + 1, i + n, last + d);
                if (next == -1) break;
                
                last = ext[next];
                idx = next;
                count++;
            }
            
            if (count == k) {
                // Check circular condition
                if ((first + perimeter) - last >= d) return true;
            }
        }
        
        return false;
    }
    
    private int lowerBound(long[] arr, int l, int r, long target) {
        int res = -1;
        
        while (l <= r) {
            int mid = (l + r) >>> 1;
            if (arr[mid] >= target) {
                res = mid;
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        
        return res;
    }
}