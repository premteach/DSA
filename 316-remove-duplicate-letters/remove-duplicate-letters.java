class Solution {
    public String removeDuplicateLetters(String s) {
        int[] freq = new int[26];
        boolean[] used = new boolean[26];

        // count frequency
        for(char c : s.toCharArray()) {
            freq[c - 'a']++;
        }

        StringBuilder stack = new StringBuilder();

        for(char c : s.toCharArray()) {
            freq[c - 'a']--;

            if(used[c - 'a'])
                continue;

            while(stack.length() > 0 &&
                  c < stack.charAt(stack.length() - 1) &&
                  freq[stack.charAt(stack.length() - 1) - 'a'] > 0) {
                
                used[stack.charAt(stack.length() - 1) - 'a'] = false;
                stack.deleteCharAt(stack.length() - 1);
            }

            stack.append(c);
            used[c - 'a'] = true;
        }

        return stack.toString();
    }
}