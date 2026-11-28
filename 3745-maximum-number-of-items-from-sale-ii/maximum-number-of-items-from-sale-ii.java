
import java.util.*;

class Solution {
    public int maximumSaleItems(int[][] items, int budget) {

        int n = items.length;

        // Step 1: Find maximum factor and minimum price
        int maxFactor = 0;
        int minPrice = Integer.MAX_VALUE;

        int[] freq = new int[n + 1];

        for (int[] item : items) {
            int factor = item[0];
            int price = item[1];

            freq[factor]++;

            maxFactor = Math.max(maxFactor, factor);
            minPrice = Math.min(minPrice, price);
        }

        // Step 2: Calculate how many items each factor unlocks
        int[] gain = new int[maxFactor + 1];

        for (int i = 1; i <= maxFactor; i++) {

            for (int j = i; j <= maxFactor; j += i) {
                gain[i] += freq[j];
            }

            // Exclude the item itself
            gain[i]--;
        }

        // Step 3: Store useful items
        List<int[]> deals = new ArrayList<>();

        for (int[] item : items) {

            int factor = item[0];
            int price = item[1];

            if (gain[factor] > 0 &&
                price <= 2L * minPrice) {

                deals.add(new int[]{price, gain[factor]});
            }
        }

        // Step 4: Sort by price
        deals.sort((a, b) -> Integer.compare(a[0], b[0]));

        long ans = 0;
        long remainingBudget = budget;

        // Step 5: Buy double-value copies first
        for (int[] deal : deals) {

            int price = deal[0];
            int count = deal[1];

            long buy = Math.min(count, remainingBudget / price);

            ans += buy * 2;
            remainingBudget -= buy * price;
        }

        // Step 6: Buy ordinary copies using remaining budget
        ans += remainingBudget / minPrice;

        return (int) ans;
    }
}
