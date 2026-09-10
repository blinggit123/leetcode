class Solution {
    private int count = 0;

    public int averageOfSubtree(TreeNode root) {
        postOrder(root);
        return count;
    }

    // Returns an int array where index 0 is the sum of the subtree and index 1 is the node count.
    private int[] postOrder(TreeNode node) {
        if (node == null) {
            return new int[] { 0, 0 };
        }

        int[] left = postOrder(node.left);
        int[] right = postOrder(node.right);

        int currentSum = node.val + left[0] + right[0];
        int currentCount = 1 + left[1] + right[1];

        if (node.val == currentSum / currentCount) {
            count++;
        }

        return new int[] { currentSum, currentCount };
    }
}