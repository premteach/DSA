/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 *     // Constructor initializes an empty nested list.
 *     public NestedInteger();
 *
 *     // Constructor initializes a single integer.
 *     public NestedInteger(int value);
 *
 *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
 *     public boolean isInteger();
 *
 *     // @return the single integer that this NestedInteger holds, if it holds a single integer
 *     // Return null if this NestedInteger holds a nested list
 *     public Integer getInteger();
 *
 *     // Set this NestedInteger to hold a single integer.
 *     public void setInteger(int value);
 *
 *     // Set this NestedInteger to hold a nested list and adds a nested integer to it.
 *     public void add(NestedInteger ni);
 *
 *     // @return the nested list that this NestedInteger holds, if it holds a nested list
 *     // Return empty list if this NestedInteger holds a single integer
 *     public List<NestedInteger> getList();
 * }
 */
import java.util.*;

class Solution {
    public NestedInteger deserialize(String s) {
        if(s.charAt(0) != '[') {
            return new NestedInteger(Integer.parseInt(s));
        }

        Stack<NestedInteger> stack = new Stack<>();
        NestedInteger curr = null;

        int num = 0;
        boolean negative = false;
        boolean hasNum = false;

        for(int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if(c == '[') {
                if(curr != null) {
                    stack.push(curr);
                }
                curr = new NestedInteger();
            }
            else if(c == ']') {
                if(hasNum) {
                    curr.add(new NestedInteger(negative ? -num : num));
                }

                if(!stack.isEmpty()) {
                    NestedInteger parent = stack.pop();
                    parent.add(curr);
                    curr = parent;
                }

                num = 0;
                negative = false;
                hasNum = false;
            }
            else if(c == ',') {
                if(hasNum) {
                    curr.add(new NestedInteger(negative ? -num : num));
                }

                num = 0;
                negative = false;
                hasNum = false;
            }
            else if(c == '-') {
                negative = true;
            }
            else { // digit
                num = num * 10 + (c - '0');
                hasNum = true;
            }
        }

        return curr;
    }
}