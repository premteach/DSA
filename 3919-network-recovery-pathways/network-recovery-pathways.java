import java.util.*;

class Solution {
    public int findMaxPathScore(int[][] edges, boolean[] online, long k) {
        int n = online.length;
        int m = edges.length;

        int[] outDeg = new int[n];
        int[] inDeg = new int[n];
        for (int[] e : edges) { outDeg[e[0]]++; inDeg[e[1]]++; }

        int[] adjStart = new int[n + 1];
        for (int i = 0; i < n; i++) adjStart[i + 1] = adjStart[i] + outDeg[i];

        int[] adjTo = new int[m];
        long[] adjCost = new long[m];
        int[] fillPos = adjStart.clone();
        for (int[] e : edges) {
            int u = e[0], v = e[1], c = e[2];
            int pos = fillPos[u]++;
            adjTo[pos] = v;
            adjCost[pos] = c;
        }

        // Topological order (fixed regardless of thresholds / online status)
        int[] topo = new int[n];
        int idx = 0;
        int[] indegCopy = inDeg.clone();
        int[] queue = new int[n];
        int qh = 0, qt = 0;
        for (int i = 0; i < n; i++) if (indegCopy[i] == 0) queue[qt++] = i;
        while (qh < qt) {
            int u = queue[qh++];
            topo[idx++] = u;
            for (int p = adjStart[u]; p < adjStart[u + 1]; p++) {
                int v = adjTo[p];
                if (--indegCopy[v] == 0) queue[qt++] = v;
            }
        }

        if (m == 0) return -1;

        long[] costsArr = new long[m];
        for (int i = 0; i < m; i++) costsArr[i] = edges[i][2];
        Arrays.sort(costsArr);

        long[] uniqueCosts = new long[m];
        int uniqueCount = 0;
        for (int i = 0; i < m; i++) {
            if (i == 0 || costsArr[i] != costsArr[i - 1]) uniqueCosts[uniqueCount++] = costsArr[i];
        }

        final long INF = Long.MAX_VALUE / 2;
        long[] dp = new long[n];

        int lo = 0, hi = uniqueCount - 1;
        long ans = -1;

        while (lo <= hi) {
            int mid = (lo + hi) / 2;
            long t = uniqueCosts[mid];

            Arrays.fill(dp, INF);
            dp[0] = 0;
            for (int oi = 0; oi < n; oi++) {
                int u = topo[oi];
                if (dp[u] >= INF) continue;
                if (!online[u]) continue;
                for (int p = adjStart[u]; p < adjStart[u + 1]; p++) {
                    long c = adjCost[p];
                    if (c >= t) {
                        int v = adjTo[p];
                        long nd = dp[u] + c;
                        if (nd < dp[v]) dp[v] = nd;
                    }
                }
            }

            boolean feasible = dp[n - 1] <= k;
            if (feasible) {
                ans = t;
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }

        return (int) ans;
    }
}