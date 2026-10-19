class Solution {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        // Both nodes are null -> structurally identical at this branch
        if (p == null && q == null) {
            return true;
        }
        
        // One is null while the other is not -> structures differ
        if (p == null || q == null) {
            return false;
        }
        
        // Values do not match
        if (p.val != q.val) {
            return false;
        }
        
        // Recursively check left and right subtrees
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
}