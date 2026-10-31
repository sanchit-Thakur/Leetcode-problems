class Solution {
    private static final int MOD = 1_000_000_007;

    public int uniquePaths(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        
        // dp[r][c] stores the number of valid paths from (r, c) to (m - 1, n - 1)
        Integer[][] dp = new Integer[m][n];
        
        return dfs(0, 0, grid, m, n, dp);
    }

    private int dfs(int r, int c, int[][] grid, int m, int n, Integer[][] dp) {
        // Reached the destination
        if (r == m - 1 && c == n - 1) {
            return 1;
        }
        
        if (dp[r][c] != null) {
            return dp[r][c];
        }

        long totalPaths = 0;

        // Option 1: Move Right
        int[] nextRight = simulateMove(r, c + 1, 0, grid, m, n); // dir: 0 = Right
        if (nextRight != null) {
            totalPaths = (totalPaths + dfs(nextRight[0], nextRight[1], grid, m, n, dp)) % MOD;
        }

        // Option 2: Move Down
        int[] nextDown = simulateMove(r + 1, c, 1, grid, m, n);  // dir: 1 = Down
        if (nextDown != null) {
            totalPaths = (totalPaths + dfs(nextDown[0], nextDown[1], grid, m, n, dp)) % MOD;
        }

        return dp[r][c] = (int) totalPaths;
    }

    /**
     * Follows reflections until landing on an empty cell (0) or moving out of bounds.
     * @param dir 0 for entering horizontally (Right), 1 for entering vertically (Down)
     * @return int[]{finalRow, finalCol} or null if out of bounds
     */
    private int[] simulateMove(int r, int c, int dir, int[][] grid, int m, int n) {
        while (r < m && c < n) {
            // Reached destination or an empty cell
            if (grid[r][c] == 0) {
                return new int[]{r, c};
            }
            
            // Hit a mirror cell (grid[r][c] == 1)
            if (dir == 0) {
                // Was moving Right -> turned Down to the cell directly below the mirror
                r++;
                dir = 1;
            } else {
                // Was moving Down -> turned Right to the cell directly to the right of the mirror
                c++;
                dir = 0;
            }
        }
        return null; // Out of bounds
    }
}