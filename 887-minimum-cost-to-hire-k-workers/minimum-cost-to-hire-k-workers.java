import java.util.*;

class Solution {
    public double mincostToHireWorkers(int[] quality, int[] wage, int k) {
        int n = quality.length;
        
        // [ratio, quality]
        double[][] workers = new double[n][2];
        
        for (int i = 0; i < n; i++) {
            workers[i][0] = (double) wage[i] / quality[i];
            workers[i][1] = quality[i];
        }
        
        // Sort by ratio
        Arrays.sort(workers, (a, b) -> Double.compare(a[0], b[0]));
        
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        
        int sumQuality = 0;
        double result = Double.MAX_VALUE;
        
        for (double[] worker : workers) {
            int q = (int) worker[1];
            
            maxHeap.add(q);
            sumQuality += q;
            
            // Keep only k workers
            if (maxHeap.size() > k) {
                sumQuality -= maxHeap.poll();
            }
            
            // When we have k workers
            if (maxHeap.size() == k) {
                double cost = worker[0] * sumQuality;
                result = Math.min(result, cost);
            }
        }
        
        return result;
    }
}