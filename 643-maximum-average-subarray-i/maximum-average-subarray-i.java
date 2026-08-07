class Solution {
    public double findMaxAverage(int[] nums, int k) {
        int ans = 0;

        for (int i = 0; i < k; i++) ans += nums[i];

        double maxAvg = (double) ans / k;

        for (int i = 1; i <= nums.length - k; i++) {
            ans = ans - nums[i - 1] + nums[i + k - 1];
            maxAvg = Math.max(maxAvg, (double) ans / k);
        }

        return maxAvg;
    }
}