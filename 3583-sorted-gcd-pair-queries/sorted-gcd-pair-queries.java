class Solution {
    public int[] gcdValues(int[] nums, long[] queries) {
        int max = 0;
        for (int x : nums) {
            max = Math.max(max, x);
        }

        int[] freq = new int[max + 1];
        for (int x : nums) {
            freq[x]++;
        }

        long[] divisiblePairs = new long[max + 1];

        // Pairs whose gcd is divisible by i
        for (int i = 1; i <= max; i++) {
            long cnt = 0;
            for (int j = i; j <= max; j += i) {
                cnt += freq[j];
            }
            divisiblePairs[i] = cnt * (cnt - 1) / 2;
        }

        long[] exact = new long[max + 1];

        // Inclusion-Exclusion
        for (int i = max; i >= 1; i--) {
            exact[i] = divisiblePairs[i];
            for (int j = i * 2; j <= max; j += i) {
                exact[i] -= exact[j];
            }
        }

        // Prefix counts in sorted gcdPairs
        long[] prefix = new long[max + 1];
        for (int i = 1; i <= max; i++) {
            prefix[i] = prefix[i - 1] + exact[i];
        }

        int[] ans = new int[queries.length];

        for (int k = 0; k < queries.length; k++) {
            long target = queries[k] + 1; // prefix is 1-based count

            int l = 1, r = max;
            while (l < r) {
                int mid = l + (r - l) / 2;
                if (prefix[mid] >= target) {
                    r = mid;
                } else {
                    l = mid + 1;
                }
            }
            ans[k] = l;
        }

        return ans;
    }
}