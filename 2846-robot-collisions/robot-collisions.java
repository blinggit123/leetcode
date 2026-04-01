import java.util.*;

class Solution {
    public List<Integer> survivedRobotsHealths(int[] positions, int[] healths, String directions) {
        int n = positions.length;
        
        // Step 1: Create robot list
        int[][] robots = new int[n][4]; // pos, health, dir, index
        
        for (int i = 0; i < n; i++) {
            robots[i][0] = positions[i];
            robots[i][1] = healths[i];
            robots[i][2] = directions.charAt(i); // 'R' or 'L'
            robots[i][3] = i;
        }
        
        // Step 2: Sort by position
        Arrays.sort(robots, (a, b) -> a[0] - b[0]);
        
        Stack<int[]> stack = new Stack<>();
        
        for (int[] robot : robots) {
            if (robot[2] == 'R') {
                stack.push(robot);
            } else {
                // direction L
                while (!stack.isEmpty() && stack.peek()[2] == 'R') {
                    int[] top = stack.peek();
                    
                    if (top[1] < robot[1]) {
                        stack.pop();
                        robot[1]--; // lose 1 health
                    } 
                    else if (top[1] > robot[1]) {
                        top[1]--; // stack robot loses 1
                        robot[1] = 0;
                        break;
                    } 
                    else {
                        // equal
                        stack.pop();
                        robot[1] = 0;
                        break;
                    }
                }
                
                if (robot[1] > 0) {
                    stack.push(robot);
                }
            }
        }
        
        // Step 6: Collect survivors
        int[] result = new int[n];
        Arrays.fill(result, -1);
        
        for (int[] r : stack) {
            result[r[3]] = r[1];
        }
        
        List<Integer> ans = new ArrayList<>();
        for (int val : result) {
            if (val != -1) ans.add(val);
        }
        
        return ans;
    }
}