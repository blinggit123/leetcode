class Solution {
    public String reverseParentheses(String s) {
        Stack<String> stack = new Stack<>();
        StringBuilder current = new StringBuilder();

        for (char ch : s.toCharArray()) {

            if (ch == '(') {
                // Save everything before this '('
                stack.push(current.toString());
                current.setLength(0);

            } else if (ch == ')') {
                // Reverse the content inside parentheses
                current.reverse();

                // Restore the string before '('
                current.insert(0, stack.pop());

            } else {
                current.append(ch);
            }
        }

        return current.toString();
    }
}
