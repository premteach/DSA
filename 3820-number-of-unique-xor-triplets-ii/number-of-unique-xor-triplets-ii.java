class Solution {
    public int uniqueXorTriplets(int[] nums) {
        final int MAX = 2048;

        boolean[][] dp = new boolean[4][MAX];
        dp[0][0] = true;

        for (int val : nums) {
            boolean[][] ndp = new boolean[4][MAX];

            // Option: don't use this index
            for (int k = 0; k <= 3; k++) {
                System.arraycopy(dp[k], 0, ndp[k], 0, MAX);
            }

            for (int used = 0; used <= 3; used++) {
                for (int x = 0; x < MAX; x++) {
                    if (!dp[used][x]) continue;

                    // Take current index 1, 2, or 3 times
                    for (int cnt = 1; cnt + used <= 3; cnt++) {
                        int nx = x;
                        if ((cnt & 1) == 1) {
                            nx ^= val;
                        }
                        ndp[used + cnt][nx] = true;
                    }
                }
            }

            dp = ndp;
        }

        int ans = 0;
        for (int x = 0; x < MAX; x++) {
            if (dp[3][x]) ans++;
        }

        return ans;
    }
}