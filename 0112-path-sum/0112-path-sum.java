class Solution {
    public boolean hasPathSum(TreeNode root, int targetSum) {
        // Base case: an empty tree has no paths
        if (root == null) {
            return false;
        }

        // Leaf node check: verify if the remaining sum matches the leaf's value
        if (root.left == null && root.right == null) {
            return targetSum == root.val;
        }

        // Recursively check left and right subtrees with the reduced targetSum
        int remainingSum = targetSum - root.val;
        return hasPathSum(root.left, remainingSum) || hasPathSum(root.right, remainingSum);
    }
}