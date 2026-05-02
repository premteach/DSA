class Solution {
    public int strongPasswordChecker(String s) {
        int n = s.length();

        boolean hasLower = false, hasUpper = false, hasDigit = false;

        for(char c : s.toCharArray()) {
            if(Character.isLowerCase(c)) hasLower = true;
            else if(Character.isUpperCase(c)) hasUpper = true;
            else if(Character.isDigit(c)) hasDigit = true;
        }

        int missing = (!hasLower ? 1 : 0) + (!hasUpper ? 1 : 0) + (!hasDigit ? 1 : 0);

        int replace = 0;
        int[] count = new int[3];

        // count repeating sequences
        for(int i = 0; i < n;) {
            int j = i;
            while(j < n && s.charAt(j) == s.charAt(i)) j++;

            int len = j - i;
            if(len >= 3) {
                replace += len / 3;
                count[len % 3]++;
            }

            i = j;
        }

        if(n < 6) {
            return Math.max(missing, 6 - n);
        }

        if(n <= 20) {
            return Math.max(missing, replace);
        }

        int delete = n - 20;

        // Step 1: reduce len % 3 == 0
        int use = Math.min(count[0], delete);
        replace -= use;
        delete -= use;

        // Step 2: reduce len % 3 == 1
        use = Math.min(count[1] * 2, delete);
        replace -= use / 2;
        delete -= use;

        // Step 3: reduce remaining
        replace -= delete / 3;

        return (n - 20) + Math.max(missing, replace);
    }
}