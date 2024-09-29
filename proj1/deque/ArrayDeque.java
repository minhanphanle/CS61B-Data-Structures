package deque;
import java.util.Iterator;

/** Circular Buffer ArrayDeque*/
public class ArrayDeque<T> implements Iterable<T>, Deque<T> {
    private T[] items;
    private int size;
    private int capacity;
    private int nextFirst;
    private int nextLast;
    private static final int INITIAL_CAPACITY = 8;
    private static final double SHRINK_THRESHOLD = 0.25;

    public ArrayDeque() {
        items = (T[]) new Object[INITIAL_CAPACITY];
        size = 0;
        capacity = INITIAL_CAPACITY;

        // first and last needs to be next to each other
        nextFirst = 1;
        nextLast = 2;
    }

    /** Iterator */

    public Iterator<T> iterator() {
        return new ArrayDequeIterator();

    }

    private class ArrayDequeIterator implements Iterator<T> {
        private int pos;
        private int remaining;

        // constructor
        public ArrayDequeIterator() {
            pos = plusOne(nextFirst);
            remaining = size;
        }

        public boolean hasNext() {
            return remaining > 0;
        }

        public T next() {
            T returnItem = items[pos];
            pos = plusOne(pos);
            remaining--;
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
        if (! (o instanceof ArrayDeque)) {
            return false;
        }

        ArrayDeque<T> castedO = (ArrayDeque<T>) o;


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


    // add and move nextFirst to the left
    @Override
    public void addFirst(T item) {
        if (items[nextFirst] != null) {
            resize(capacity * 2);
        }

        items[nextFirst] = item;
        nextFirst = minusOne(nextFirst);
        size++;
    }

    // add and move nextLast to the right
    @Override
    public void addLast(T item) {
        if (items[nextLast] != null) {
            resize(capacity * 2);
        }

        items[nextLast] = item;
        nextLast = plusOne(nextLast);
        size++;
    }

    @Override
    public int size() {
        return size;
    }


    @Override
    public void printDeque() {
        if (isEmpty()) {
            System.out.println();
            return;
        }

        int i = plusOne(nextFirst);
        for (int k = 0; k < size; k++ ) {
            System.out.println(items[i] + " ");
            i = plusOne(i);
        }

        System.out.println("\n");
    }
    @Override
    public T removeFirst() {
        int indexFirst = plusOne(nextFirst);
        T val = items[indexFirst];
        items[indexFirst] = null;
        nextFirst = plusOne(nextFirst);

        checkAndShrink();

        size--;

        return val;

    }
    @Override
    public T removeLast() {
        int indexLast = minusOne(nextLast);
        T val = items[indexLast];
        items[indexLast] = null;
        nextLast = minusOne(nextLast);

        checkAndShrink();

        size--;

        return val;
    }

    private void checkAndShrink() {
        if (size < capacity * SHRINK_THRESHOLD && capacity > INITIAL_CAPACITY) {
            shrink();
        }
    }

    private void shrink() {
        int newCapacity = Math.max(capacity / 2, INITIAL_CAPACITY);
        resize(newCapacity);

    }


    @Override
    public T get(int index) {
        if (size == 0 || index > size - 1) return null;

        int newIndex = (nextFirst + 1 + index) % capacity;
        return items[newIndex];
    }

    private void resize(int newCapacity) {

        T[] newItems = (T[]) new Object[newCapacity];

        // Copy elements from the old array to the new array

        int firstIndex = plusOne(nextFirst);
        for (int i = 0; i < size; i++) {
            newItems[i] = items[firstIndex];
            firstIndex = plusOne(firstIndex);
        }

        // move nextFirst to the last pos in the array
        nextFirst = newCapacity - 1;

        // move nextLast after the last-copied element
        nextLast = size;

        items = newItems;
        capacity = newCapacity;
    }

    private int minusOne(int index) {
        return (index - 1 + items.length) % items.length;
    }

    private int plusOne(int index) {
        return (index + 1) % items.length;
    }
}