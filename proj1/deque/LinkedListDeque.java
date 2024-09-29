package deque;
import java.util.Iterator;

/** Circular Doubly Linked List */
public class LinkedListDeque<T> implements Iterable<T>, Deque<T> {

    private class RandNode {
        public T item;
        public RandNode next;
        public RandNode prev;

        public RandNode(T i) {
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
    private RandNode sentinel;
    private int size;


    /** Construct an empty LinkedListDeque */
    public LinkedListDeque() {
        sentinel = new RandNode();
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }

    /** Construct a 1-item LinkedListDeque */
    public LinkedListDeque(T i) {
        sentinel = new RandNode();
        RandNode newNode = new RandNode(i);
        sentinel.next = newNode;
        newNode.next = sentinel;
        newNode.prev = sentinel;
        sentinel.prev = newNode;

        size = 1;
    }

    public Iterator<T> iterator() {
        return new LinkedListDequeIterator();
    }



    private class LinkedListDequeIterator implements Iterator<T> {
        private RandNode ptr;

        // constructor
        public LinkedListDequeIterator() {
            ptr = sentinel.next;
        }

        public boolean hasNext() {
            return ptr.item != null;
        }

        public T next() {
            T returnItem = ptr.item;
            ptr = ptr.next;
            return returnItem;
        }

    }



    /** Equal */
    @Override
    public boolean equals(Object o) {
        // refer to itself
        if (this == o) {
            return true;
        }

        if (o == null) {
            return false;
        }

        // w/o pattern matching (auto type casting)
        if (! (o instanceof LinkedListDeque)) {
            return false;
        }

        LinkedListDeque<T> castedO = (LinkedListDeque<T>) o;


        if (this.size() != castedO.size()) {
            return false;
        }

        Iterator<T> thisIter = this.iterator();
        Iterator<T> castedOIter = castedO.iterator();

        // order matters in array deque
        while (thisIter.hasNext()) {
            if (!(thisIter.next().equals(castedOIter.next()))) {
                return false;
            }
        }

        return true;
    }


    @Override
    public void addFirst(T item) {
        RandNode newNode = new RandNode(item);
        // set prev and next for newNode;
        newNode.prev = sentinel;
        newNode.next = sentinel.next;

        // set the next ptr of sentinel and prev ptr of the old first elem
        sentinel.next.prev = newNode;
        sentinel.next = newNode;


        size += 1;
    }
    @Override
    public void addLast(T item) {
        RandNode newNode = new RandNode(item);

        // set ptrs for newNode;
        newNode.next = sentinel;
        newNode.prev = sentinel.prev;

        // set last node next ptr;
        sentinel.prev.next = newNode;

        // set sentinel ptr;
        sentinel.prev = newNode;

        size += 1;
    }


    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        RandNode ptr = sentinel.next;
        while (ptr != sentinel.prev) {
            System.out.println(ptr.item + " ");
            ptr = ptr.next;
        }
        System.out.println(ptr.item + " ");
        System.out.println("\n");
    }

    @Override
    public T removeFirst() {
        if (size == 0) return null;
        RandNode first = sentinel.next;
        // change the prev ptr of the second node
        first.next.prev = sentinel;

        // change the next ptr of the sentinel;
        sentinel.next = first.next;

        size -= 1;
        return first.item;
    }

    @Override
    public T removeLast() {
        if (size == 0) return null;
        RandNode last = sentinel.prev;

        // change the next ptr of second to last node
        last.prev.next = sentinel;

        sentinel.prev = last.prev;

        size -= 1;

        return last.item;
    }



    // recursive
    // have a private helper method to deal with the node
    private RandNode getNode(int index) {
        if (index == 0) {
            return sentinel.next;
        }

        return getNode(index - 1).next;
    }

    // public function to get the item property from the private helper
    @Override
    public T get(int index) {
        return getNode(index).item;
    }



}
