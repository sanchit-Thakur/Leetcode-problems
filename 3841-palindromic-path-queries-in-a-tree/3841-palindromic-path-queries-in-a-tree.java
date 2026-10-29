import java.util.*;

class Solution {
    private int timer = 0;
    private int[] tin, tout, depth;
    private int[][] up;
    private int[] bit;
    private int n;
    private int LOG;

    public List<Boolean> palindromePath(int n, int[][] edges, String s, String[] queries) {
        this.n = n;
        this.LOG = 1;
        while ((1 << LOG) <= n) LOG++;

        List<Integer>[] adj = new ArrayList[n];
        for (int i = 0; i < n; i++) adj[i] = new ArrayList<>();
        for (int[] e : edges) {
            adj[e[0]].add(e[1]);
            adj[e[1]].add(e[0]);
        }

        tin = new int[n];
        tout = new int[n];
        depth = new int[n];
        up = new int[n][LOG];
        bit = new int[n + 2];

        // Iterative DFS to avoid StackOverflowError on deep trees
        dfsIterative(0, adj);

        int[] masks = new int[n];
        for (int i = 0; i < n; i++) {
            masks[i] = 1 << (s.charAt(i) - 'a');
            rangeXor(tin[i], tout[i], masks[i]);
        }

        List<Boolean> ans = new ArrayList<>();

        for (String q : queries) {
            String[] parts = q.split(" ");
            String type = parts[0];

            // Handle update query: e.g., "update u c" or "1 u c" or "u x c"
            if (type.equals("update") || type.equals("1") || (type.equals("u") && parts[2].length() == 1 && Character.isLetter(parts[2].charAt(0)))) {
                int u = Integer.parseInt(parts[1]);
                char c = parts[2].charAt(0);
                int newMask = 1 << (c - 'a');
                int diff = masks[u] ^ newMask;
                if (diff != 0) {
                    rangeXor(tin[u], tout[u], diff);
                    masks[u] = newMask;
                }
            } else {
                // Handle path query: e.g., "query u v" or "2 u v" or "q u v"
                int u = Integer.parseInt(parts[1]);
                int v = Integer.parseInt(parts[2]);
                int lca = getLCA(u, v);
                int pathMask = pointQuery(tin[u]) ^ pointQuery(tin[v]) ^ masks[lca];
                ans.add((pathMask & (pathMask - 1)) == 0);
            }
        }

        return ans;
    }

    private void dfsIterative(int root, List<Integer>[] adj) {
        int[] parent = new int[n];
        Arrays.fill(parent, -1);
        int[] edgeIdx = new int[n];
        int[] stack = new int[n];
        int top = 0;

        stack[top] = root;
        tin[root] = ++timer;
        up[root][0] = root;
        for (int j = 1; j < LOG; j++) up[root][j] = root;

        while (top >= 0) {
            int u = stack[top];
            if (edgeIdx[u] < adj[u].size()) {
                int v = adj[u].get(edgeIdx[u]++);
                if (v == parent[u]) continue;
                parent[v] = u;
                depth[v] = depth[u] + 1;
                tin[v] = ++timer;
                up[v][0] = u;
                for (int j = 1; j < LOG; j++) {
                    up[v][j] = up[up[v][j - 1]][j - 1];
                }
                stack[++top] = v;
            } else {
                tout[u] = timer;
                top--;
            }
        }
    }

    private int getLCA(int u, int v) {
        if (depth[u] < depth[v]) {
            int temp = u;
            u = v;
            v = temp;
        }
        int diff = depth[u] - depth[v];
        for (int j = 0; j < LOG; j++) {
            if (((diff >> j) & 1) != 0) {
                u = up[u][j];
            }
        }
        if (u == v) return u;
        for (int j = LOG - 1; j >= 0; j--) {
            if (up[u][j] != up[v][j]) {
                u = up[u][j];
                v = up[v][j];
            }
        }
        return up[u][0];
    }

    private void update(int idx, int val) {
        for (; idx <= n + 1; idx += idx & -idx) {
            bit[idx] ^= val;
        }
    }

    private void rangeXor(int l, int r, int val) {
        update(l, val);
        update(r + 1, val);
    }

    private int pointQuery(int idx) {
        int res = 0;
        for (; idx > 0; idx -= idx & -idx) {
            res ^= bit[idx];
        }
        return res;
    }
}