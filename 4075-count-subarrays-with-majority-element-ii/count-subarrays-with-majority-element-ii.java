class Solution {
    public long countMajoritySubarrays(int[] nums, int target) {
        int n = nums.length;
        int offset = n + 2;
        int size = 2 * n + 5;

        Fenwick bit = new Fenwick(size);

        int prefix = 0;
        long ans = 0;

        bit.add(prefix + offset, 1);

        for (int x : nums) {
            if (x == target) {
                prefix++;
            } else {
                prefix--;
            }

            ans += bit.query(prefix - 1 + offset);
            bit.add(prefix + offset, 1);
        }

        return ans;
    }

    static class Fenwick {
        int[] tree;

        Fenwick(int n) {
            tree = new int[n + 2];
        }

        void add(int idx, int val) {
            while (idx < tree.length) {
                tree[idx] += val;
                idx += idx & -idx;
            }
        }

        int query(int idx) {
            int sum = 0;
            while (idx > 0) {
                sum += tree[idx];
                idx -= idx & -idx;
            }
            return sum;
        }
    }
}