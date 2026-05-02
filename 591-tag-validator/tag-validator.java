import java.util.*;

class Solution {
    public boolean isValid(String code) {
        Stack<String> stack = new Stack<>();
        int i = 0;
        int n = code.length();

        while (i < n) {
            if (i > 0 && stack.isEmpty()) return false;

            // CDATA
            if (i + 9 < n && code.substring(i, i + 9).equals("<![CDATA[")) {
                int j = code.indexOf("]]>", i);
                if (j < 0) return false;
                i = j + 3;
            }
            // End tag
            else if (i + 2 < n && code.substring(i, i + 2).equals("</")) {
                int j = code.indexOf(">", i);
                if (j < 0) return false;

                String tag = code.substring(i + 2, j);
                if (!isValidTag(tag) || stack.isEmpty() || !stack.pop().equals(tag)) {
                    return false;
                }

                i = j + 1;
            }
            // Start tag
            else if (code.charAt(i) == '<') {
                int j = code.indexOf(">", i);
                if (j < 0) return false;

                String tag = code.substring(i + 1, j);
                if (!isValidTag(tag)) return false;

                stack.push(tag);
                i = j + 1;
            }
            else {
                i++;
            }
        }

        return stack.isEmpty();
    }

    private boolean isValidTag(String tag) {
        if (tag.length() < 1 || tag.length() > 9) return false;

        for (char c : tag.toCharArray()) {
            if (c < 'A' || c > 'Z') return false;
        }

        return true;
    }
}