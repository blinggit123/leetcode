public class Solution {
    public ListNode detectCycle(ListNode head) {
        if (head == null) return null;
        
        ListNode slow = head;
        ListNode fast = head;
        
        // Step 1: detect cycle
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            
            if (slow == fast) {
                // Step 2: find cycle start
                ListNode ptr = head;
                
                while (ptr != slow) {
                    ptr = ptr.next;
                    slow = slow.next;
                }
                
                return ptr;
            }
        }
        
        return null; // no cycle
    }
}