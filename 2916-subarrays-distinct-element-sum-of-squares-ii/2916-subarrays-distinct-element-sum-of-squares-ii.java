import java.util.HashMap;
import java.util.Map;

class Solution {
    private static final int MOD = 1_000_000_007;

    // Segment tree node
    static class SegmentTree {
        int n;
        long[] sum;   // sum of d[j]
        long[] sumSq; // sum of d[j]^2
        long[] lazy;  // lazy tag for range addition

        public SegmentTree(int n) {
            this.n = n;
            this.sum = new long[4 * n];
            this.sumSq = new long[4 * n];
            this.lazy = new long[4 * n];
        }

        private void apply(int node, int l, int r, long val) {
            val %= MOD;
            long len = r - l + 1;
            // (x + val)^2 = x^2 + 2*val*x + val^2
            sumSq[node] = (sumSq[node] + 2 * val % MOD * sum[node] % MOD + val * val % MOD * len % MOD) % MOD;
            sum[node] = (sum[node] + val * len) % MOD;
            lazy[node] = (lazy[node] + val) % MOD;
        }

        private void pushDown(int node, int l, int r) {
            if (lazy[node] != 0) {
                int mid = l + (r - l) / 2;
                apply(2 * node, l, mid, lazy[node]);
                apply(2 * node + 1, mid + 1, r, lazy[node]);
                lazy[node] = 0;
            }
        }

        private void pushUp(int node) {
            sum[node] = (sum[2 * node] + sum[2 * node + 1]) % MOD;
            sumSq[node] = (sumSq[2 * node] + sumSq[2 * node + 1]) % MOD;
        }

        public void update(int node, int l, int r, int ql, int qr, int val) {
            if (ql <= l && r <= qr) {
                apply(node, l, r, val);
                return;
            }
            pushDown(node, l, r);
            int mid = l + (r - l) / 2;
            if (ql <= mid) {
                update(2 * node, l, mid, ql, qr, val);
            }
            if (qr > mid) {
                update(2 * node + 1, mid + 1, r, ql, qr, val);
            }
            pushUp(node);
        }
    }

    public int sumCounts(int[] nums) {
        int n = nums.length;
        SegmentTree tree = new SegmentTree(n);
        Map<Integer, Integer> lastSeen = new HashMap<>();

        long totalSum = 0;

        for (int i = 0; i < n; i++) {
            int prevIndex = lastSeen.getOrDefault(nums[i], -1);
            // Range [prevIndex + 1, i] gains 1 to their distinct count
            tree.update(1, 0, n - 1, prevIndex + 1, i, 1);
            lastSeen.put(nums[i], i);

            // Add sum of squares of all subarrays ending at index i
            totalSum = (totalSum + tree.sumSq[1]) % MOD;
        }

        return (int) totalSum;
    }
}