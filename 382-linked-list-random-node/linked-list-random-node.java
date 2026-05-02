/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
import java.util.*;

class Solution {
    ListNode head;
    Random rand;

    public Solution(ListNode head) {
        this.head = head;
        rand = new Random();
    }

    public int getRandom() {
        int result = head.val;
        ListNode curr = head.next;
        int i = 2;

        while(curr != null) {
            if(rand.nextInt(i) == 0) {
                result = curr.val;
            }
            curr = curr.next;
            i++;
        }

        return result;
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(head);
 * int param_1 = obj.getRandom();
 */