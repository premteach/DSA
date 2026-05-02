class Solution {
    public String getHint(String secret, String guess) {
        int bulls = 0, cows = 0;
        int[] count = new int[10];

        for(int i = 0; i < secret.length(); i++) {
            char s = secret.charAt(i);
            char g = guess.charAt(i);

            if(s == g) {
                bulls++;
            } else {
                // If previously seen in guess
                if(count[s - '0'] < 0) cows++;
                // If previously seen in secret
                if(count[g - '0'] > 0) cows++;

                count[s - '0']++;
                count[g - '0']--;
            }
        }

        return bulls + "A" + cows + "B";
    }
}