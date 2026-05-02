class Solution {
    public int nthSuperUglyNumber(int n, int[] primes) {
        int k = primes.length;
        int[] ugly = new int[n];
        int[] index = new int[k];

        ugly[0] = 1;

        for(int i = 1; i < n; i++) {
            long next = Long.MAX_VALUE;

            for(int j = 0; j < k; j++) {
                long val = (long)primes[j] * ugly[index[j]];
                next = Math.min(next, val);
            }

            ugly[i] = (int)next;

            for(int j = 0; j < k; j++) {
                if((long)primes[j] * ugly[index[j]] == next) {
                    index[j]++;
                }
            }
        }

        return ugly[n - 1];
    }
}