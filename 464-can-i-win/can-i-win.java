import java.util.*;

class Solution {
    public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
        if(desiredTotal <= 0) return true;

        int sum = (maxChoosableInteger * (maxChoosableInteger + 1)) / 2;
        if(sum < desiredTotal) return false;

        Map<Integer, Boolean> memo = new HashMap<>();
        return dfs(maxChoosableInteger, desiredTotal, 0, memo);
    }

    private boolean dfs(int max, int total, int used, Map<Integer, Boolean> memo) {
        if(memo.containsKey(used)) return memo.get(used);

        for(int i = 1; i <= max; i++) {
            int mask = 1 << i;

            if((used & mask) == 0) { // not used
                // if current move wins OR opponent loses
                if(i >= total || !dfs(max, total - i, used | mask, memo)) {
                    memo.put(used, true);
                    return true;
                }
            }
        }

        memo.put(used, false);
        return false;
    }
}