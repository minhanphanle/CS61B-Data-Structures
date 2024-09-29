package deque;


/** 2 Sentinel Doubly Linked List */
public class LinkedListDeque2Sentinel<RandType> {

    private class RandNode {
        public RandType item;
        public RandNode next;
        public RandNode prev;

        public RandNode(RandType i) {
            prev = null;
            item = i;
            next = null;
        }

        // constructor chaining, calling another constructor in the same class
        public RandNode() {
            this(null);
        }
    }

    /* Sentinel with next always point to a node whose value does not count */
    private RandNode sentinelF;
    private RandNode sentinelB;


    private int size;


    /** Construct an empty LinkedListDeque */
    public LinkedListDeque2Sentinel() {
        sentinelF = new RandNode();
        sentinelB = new RandNode();
        sentinelF.next = sentinelB;
        sentinelB.prev = sentinelF;
        size = 0;
    }

    /** Construct a 1-item LinkedListDeque */
    public void LinkedListDeque2Sentinel(RandType i) {
        sentinelF = new RandNode();
        sentinelB = new RandNode();

        RandNode newNode = new RandNode(i);
        sentinelF.next = newNode;
        newNode.prev = sentinelF;
        newNode.next = sentinelB;
        sentinelB.prev = newNode;

        size = 1;
    }

    public void addFirst(RandType item) {
        RandNode newNode = new RandNode(item);

        // set ptrs for new node
        newNode.prev = sentinelF;
        newNode.next = sentinelF.next;

        sentinelF.next.prev = newNode;
        sentinelF.next = newNode;

        size += 1;
    }

    public void addLast(RandType item) {
        RandNode newNode = new RandNode(item);

        // set ptrs for new node
        newNode.prev = sentinelB.prev;
        newNode.next = sentinelB;

        sentinelB.prev.next = newNode;
        sentinelB.prev = newNode;
        size += 1;
    }


    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        RandNode ptr = sentinelF.next;
        int curr = 0;
        while (curr < size - 1) {
            System.out.println(ptr.item + " ");
            ptr = ptr.next;
            curr++;
        }
        System.out.println("\n");
    }


    public RandType removeFirst() {
        if (sentinelF.next == sentinelB) {
            return null;
        }
        RandNode first = sentinelF.next;

        first.next.prev = sentinelF;
        sentinelF.next = first.next;

        size -= 1;
        return first.item;
    }

    public RandType removeLast() {
        if (sentinelF.next == sentinelB) {
            return null;
        }

        RandNode last = sentinelB.prev;
        last.prev.next = sentinelB;
        sentinelB.prev = last.prev;

        size -= 1;

        return last.item;
    }

    // iterative
    public RandType get(int index) {
        if (index > size - 1 || index < 0) {
            return null;
        }

        RandNode ptr = sentinelF.next;
        int currIndex = 0;
        while (currIndex < index) {
            ptr = ptr.next;
            currIndex++;
        }

        return ptr.item;
    }



}
