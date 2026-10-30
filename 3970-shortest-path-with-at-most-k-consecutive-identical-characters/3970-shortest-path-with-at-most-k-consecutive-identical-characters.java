import java.util.*;

class Solution {
    public int shortestPath(int n, int[][] edges, String labels, int k) {
        // Directed adjacency list: u -> list of {v, weight}
        List<List<int[]>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            adj.get(edge[0]).add(new int[]{edge[1], edge[2]});
        }

        // dist[u][streak] tracks the minimum distance to node u with that consecutive streak
        int[][] dist = new int[n][k + 1];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }

        // PriorityQueue stores: {current_dist, node, streak}
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));

        dist[0][1] = 0;
        pq.offer(new int[]{0, 0, 1});

        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int d = curr[0];
            int u = curr[1];
            int count = curr[2];

            if (d > dist[u][count]) {
                continue;
            }

            if (u == n - 1) {
                return d;
            }

            char uChar = labels.charAt(u);

            for (int[] next : adj.get(u)) {
                int v = next[0];
                int weight = next[1];
                char vChar = labels.charAt(v);

                int nextCount = (uChar == vChar) ? count + 1 : 1;

                if (nextCount <= k && d + weight < dist[v][nextCount]) {
                    dist[v][nextCount] = d + weight;
                    pq.offer(new int[]{d + weight, v, nextCount});
                }
            }
        }

        return -1;
    }
}