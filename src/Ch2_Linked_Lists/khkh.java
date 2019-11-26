/* -----------------------------------
 *  WARNING:
 * -----------------------------------
 *  Your code may fail to compile
 *  because it contains public class
 *  declarations.
 *  To fix this, please remove the
 *  "public" keyword from your class
 *  declarations.
 */

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */

class ListNode {
      int val;
      ListNode next;
      ListNode(int x) {
          val = x;
          next = null;
      }
 }

public class Solution {

    class Result {
        int size;
        ListNode tail;

        public Result (ListNode tail, int size){
            this.size = size;
            this.tail = tail;
        }

        public static Result getTailnSize(ListNode head){

            if (head == null){
                return null;
            }

            ListNode current = head;
            int size = 1;

            while (current.next!=null){
                size++;
                current = current.next;
            }

            return new Result(current,size);
        }
    }

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {

        Result nodeA = Result.getTailnSize(headA);
        Result nodeB = getTailnSize(headB);

        if(nodeA.tail != nodeB.tail){
            return null;
        }

        ListNode shortPtr = nodeA.size < nodeB.size? headA:headB;
        ListNode longPtr = nodeA.size < nodeB.size? headB:headA;

        longPtr = movePointer(longPtr, Math.abs(nodeA.size-nodeB.size));

        while (shortPtr!=longPtr){
            shortPtr = shortPtr.next;
            longPtr = longPtr.next;
        }
        return shortPtr;
    }

    public ListNode movePointer(ListNode head, int diff) {

        ListNode current = head;

        for(int i = 0; i< diff && current!=null; i++){
            current = current.next;
        }

        return current;
    }

}

public class MainClass {
    public static int[] stringToIntegerArray(String input) {
        input = input.trim();
        input = input.substring(1, input.length() - 1);
        if (input.length() == 0) {
            return new int[0];
        }

        String[] parts = input.split(",");
        int[] output = new int[parts.length];
        for(int index = 0; index < parts.length; index++) {
            String part = parts[index].trim();
            output[index] = Integer.parseInt(part);
        }
        return output;
    }

    public static ListNode stringToListNode(String input) {
        // Generate array from the input
        int[] nodeValues = stringToIntegerArray(input);

        // Now convert that list into linked list
        ListNode dummyRoot = new ListNode(0);
        ListNode ptr = dummyRoot;
        for(int item : nodeValues) {
            ptr.next = new ListNode(item);
            ptr = ptr.next;
        }
        return dummyRoot.next;
    }

    public static String listNodeToString(ListNode node) {
        if (node == null) {
            return "[]";
        }

        String result = "";
        while (node != null) {
            result += Integer.toString(node.val) + ", ";
            node = node.next;
        }
        return "[" + result.substring(0, result.length() - 2) + "]";
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            int intersectVal = Integer.parseInt(line);
            line = in.readLine();
            ListNode listA = stringToListNode(line);
            line = in.readLine();
            ListNode listB = stringToListNode(line);
            line = in.readLine();
            int skipA = Integer.parseInt(line);
            line = in.readLine();
            int skipB = Integer.parseInt(line);

            ListNode ret = new Solution().getIntersectionNode(intersectVal, listA, listB, skipA, skipB);

            String out = listNodeToString(ret);

            System.out.print(out);
        }
    }
}