package linkedlist;

import java.util.Hashtable;

/**
 * Created by Admin on 2018/4/12.
 */
public class MyLinkedList {
    Node head = null;  // 链表头的引用

    /**
     * 添加节点
     * @param d
     */
    public void addNode(int d) {
        Node newNode = new Node(d);
        if (head == null) {
            head = newNode;
            return;
        }
        Node tmp = head;

        while (tmp.next != null) {
            tmp = tmp.next;
        }

        tmp.next = newNode;
    }


    /**
     * 删除第index和节点
     * @param index
     * @return
     */
    public Boolean deleteNode(int index) {
        if (index <1 || index > length()) {
            return false;
        }
        // 删除第一个元素
        if (index == 1) {
            head = head.next;
            return true;
        }

        int i = 1;

        Node preNode = head;
        Node curNode = preNode.next;
        while (curNode != null) {
            if (i == index) {
                preNode.next = curNode.next;
                return true;
            }
            preNode = curNode;
            curNode = curNode.next;
            i++;
        }
        return true;
    }

    /**
     * 返回链表长度
     * @return
     */
    private int length() {
        int length = 0;
        Node tmp = head;
        while (tmp != null) {
            length++;
            tmp = tmp.next;
        }
        return length;
    }


    /**
     * 对链表进行排序，返回排序后的头结点
     * @return
     */
    public Node orderList() {
        Node nextNode = null;
        int tmp = 0;
        Node curNode = head;

        while (curNode.next != null) {
            nextNode = curNode.next;
            while (nextNode != null) {
                if (curNode.data > nextNode.data) {
                    tmp  = curNode.data;
                    curNode.data = nextNode.data;
                    nextNode.data = tmp;
                }
                nextNode = nextNode.next;
            }
            curNode = curNode.next;
        }
        return head;
    }

    public void printList() {
        Node tmp = head;
        while (tmp != null) {
            System.out.println(tmp.data);
            tmp = tmp.next;
        }
    }

    /**
     * 删除链表重复数据
     * 时间复杂度低，但是需要额外空间
     * @param head
     */
    public void deleteDuplecate(Node head) {
        Hashtable<Integer, Integer> table = new Hashtable<Integer, Integer>();
        Node tmp = head;
        Node pre = null;

        while (head != null) {
            if (table.containsKey(tmp.data)) {
                pre.next = tmp.next;
            } else {
                table.put(tmp.data,1);
                pre = tmp;
            }
            tmp = tmp.next;
        }
    }

    /**
     * 对链表双重遍历，外循环正常遍历，假设外循环当前遍历节点为cur，内循环从cur开始遍历
     * 若碰到与cur所指向节点值相同，则删除  时间复杂度高
     * @param head
     */
    public void deteleDuplecate(Node head) {
        Node p = head;
        while (p!= null) {
            Node q = p;
            while (q.next != null){
                if (p.data == q.next.data){
                    q.next = q.next.next;
                }else {
                    q = q.next;
                }
                p = p.next;
            }
        }
    }


    /**
     * 找到倒数第k个元素
     * @param head
     * @param k
     * @return
     */
    public Node findElem(Node head, int k) {
        if (k<1 || k > this.length()) {
            return null;
        }
        Node p1 = head;
        Node p2 = head;
        for (int i=0; i<k-1;i++){
            p1 = p1.next;
        }

        while (p1 != null){
            p1 = p1.next;
            p2 = p2.next;
        }
        return p2;

    }


    /**
     * 链表反转
     * 非递归
     * @param head
     */
    private static Node reverseHead(Node head) {
        if (head == null) {
            return head;
        }

        Node pre = head;
        Node cur = head.next;
        Node next = null;
        while(cur != null){
            next = cur.next;
            cur.next = pre;

            pre = cur;
            cur = next;
        }
        head.next = null;
        head = pre;
        return head;
    }


    /**
     * 链表反转，递归
     *
     * @param current
     * @return
     */
    private static Node reverseByRecur(Node current) {
        if (current == null || current.next == null) return current;
        Node nextNode = current.next;
        current.next = null;
        Node reverseRest = reverseByRecur(nextNode);
        nextNode.next = current;
        return reverseRest;
    }

    private static void out(Node head) {
        Node tempNode = head;
        while(tempNode != null){
            System.err.println(tempNode.data);
            tempNode = tempNode.next;
        }
    }

    public static void main(String[] args) {
        MyLinkedList list = new MyLinkedList();
        list.addNode(5);
        list.addNode(3);
        list.addNode(1);
        list.addNode(3);

//        out(list.head);
//        System.out.println(list.head.data);
//        System.out.println("listLen = " + list.length());
//        System.out.println("before order:");
//        list.printList();
//        list.orderList();
//        System.out.println("after order");
//        list.printList();

        System.out.println("after reverse");
        list.reverseHead(list.head);
//        list.printList();
        out(list.head);


    }



}
