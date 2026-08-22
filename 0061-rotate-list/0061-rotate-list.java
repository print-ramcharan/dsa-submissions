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
class Solution {
    public ListNode rotateRight(ListNode head, int k) {
        if(head == null || head.next == null || k == 0) return head;

        int n = 0;

        ListNode temp = head;

        while(temp != null){
            n ++;
            temp = temp.next;
        }

        k = k % n;
        if(k == 0) return head;

        int count = 1;
        int m = n - k;
        ListNode prev = null;

        temp = head;
        while(count <= m && temp != null){
            prev = temp;
            temp = temp.next;
            count ++;
        }

        prev.next = null;

        ListNode newHead = temp;

        while(temp.next != null){
            temp = temp.next;
        }

        temp.next = head;


        return newHead;
    }

}