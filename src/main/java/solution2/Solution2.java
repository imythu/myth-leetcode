package solution2;

/**
 * @author LeetCode
 *
 * 2. 两数相加
 * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
 *
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 *
 * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 *
 * 示例：
 *
 * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出：7 -> 0 -> 8
 * 原因：342 + 465 = 807
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/add-two-numbers
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}

/**
 * @author imythu
 */
public class Solution2 {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null && l2 == null) {
            return null;
        }
        if (l1 == null) {
            return haveANull(l2);
        }
        if (l2 == null) {
            return haveANull(l1);
        }
        ListNode listNode = new ListNode(l1.val + l2.val);

        if (listNode.val > 9) {
            listNode.val -= 10;
            if (l1.next == null) {
                listNode.next = addTwoNumbers(new ListNode(1), l2.next);
            } else {
                l1.next.val += 1;
                listNode.next = addTwoNumbers(l1.next, l2.next);
            }
        } else {
            listNode.next = addTwoNumbers(l1.next, l2.next);
        }
        return listNode;
    }

    private ListNode haveANull(ListNode listNode) {
        if (listNode.val > 9) {
            listNode.val -= 10;
            if (listNode.next == null) {
                listNode.next = addTwoNumbers(new ListNode(1), null);
            } else {
                listNode.next.val += 1;
                listNode.next = addTwoNumbers(listNode.next, null);
            }
        }

        return listNode;
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(1);
        int[] arr1 = new int[]{9, 9, 9, 9, 9};
        int[] arr2 = new int[]{1};

        ListNode p1 = l1;
        ListNode p2 = l2;
        for (int i : arr1) {
            p1.next = new ListNode(i);
            p1 = p1.next;
        }
        for (int i : arr2) {
            p2.next = new ListNode(i);
            p2 = p2.next;
        }
        ListNode result = new Solution2().addTwoNumbers(l1.next, l2.next);
        while (result != null) {
            System.out.print(result.val+ ",");
            result = result.next;
        }
    }
}