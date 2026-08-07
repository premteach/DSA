class Solution {
    public boolean isSubsequence(String s, String t) {
        int ptr1 = 0, ptr2 = 0;
        int n = s.length(), m = t.length();
        
        if (n > m) return false;
        
        while (ptr1 < n && ptr2 < m) {
            if (s.charAt(ptr1) == t.charAt(ptr2)) {
                ptr1++;
            }
            ptr2++;
        }
        
        return ptr1 == n;
    }
}