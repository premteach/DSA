class Solution {
    public int findNthDigit(int n) {
        long len = 1;
        long count = 9;
        long start = 1;

        // Step 1: find the digit length
        while(n > len * count) {
            n -= len * count;
            len++;
            count *= 10;
            start *= 10;
        }

        // Step 2: find the actual number
        long num = start + (n - 1) / len;

        // Step 3: find the digit inside number
        String s = Long.toString(num);
        return s.charAt((n - 1) % (int)len) - '0';
    }
}