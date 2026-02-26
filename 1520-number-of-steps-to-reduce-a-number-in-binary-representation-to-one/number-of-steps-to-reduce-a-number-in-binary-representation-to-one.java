class Solution {
    public int numSteps(String s) {
        int steps = 0;
        int carry = 0;

        // Traverse from right to left (ignore first bit for now)
        for (int i = s.length() - 1; i > 0; i--) {
            int bit = (s.charAt(i) - '0') + carry;

            if (bit == 1) {
                // Odd: add 1 (step 1), then divide by 2 (step 2)
                steps += 2;
                carry = 1;
            } else {
                // Even: just divide by 2
                steps += 1;
            }
        }

        // If carry remains, we need one extra step
        return steps + carry;
    }
}