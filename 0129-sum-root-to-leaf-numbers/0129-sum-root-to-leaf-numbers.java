class Solution {
    public int sumNumbers(TreeNode root) {
        return dfs(root, 0);
    }

    private int dfs(TreeNode node, int currentSum) {
        if (node == null) {
            return 0;
        }

        // Form the number along the path
        currentSum = currentSum * 10 + node.val;

        // If it's a leaf node, return the completed path number
        if (node.left == null && node.right == null) {
            return currentSum;
        }

        // Aggregate sums from both subtrees
        return dfs(node.left, currentSum) + dfs(node.right, currentSum);
    }
}