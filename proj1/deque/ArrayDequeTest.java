package deque;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class ArrayDequeTest {

    private ArrayDeque<Integer> deque;

    @Before
    public void setUp() {
        deque = new ArrayDeque<>();
    }


    @Test
    public void testIterator() {
        for (int i = 0; i < 50; i++) {
            deque.addLast(i);
        }

        for (Integer elem: deque) {
            System.out.println(elem.toString());
        }

    }


        @Test
        public void testEquals() {
            // Test basic equality
            ArrayDeque<Integer> deque1 = new ArrayDeque<>();
            ArrayDeque<Integer> deque2 = new ArrayDeque<>();
            assertTrue("Empty deques should be equal", deque1.equals(deque2));

            deque1.addLast(1);
            deque1.addLast(2);
            deque1.addLast(3);
            deque2.addLast(1);
            deque2.addLast(2);
            deque2.addLast(3);
            assertTrue("Deques with same elements should be equal", deque1.equals(deque2));

            // Test inequality
            ArrayDeque<Integer> deque3 = new ArrayDeque<>();
            deque3.addLast(1);
            deque3.addLast(2);
            assertFalse("Deques with different number of elements should not be equal", deque1.equals(deque3));

            // Test with null
            assertFalse("Deque should not be equal to null", deque1.equals(null));

            // Test with different type
            assertFalse("Deque should not be equal to a non-ArrayDeque object", deque1.equals("Not an ArrayDeque"));

            // Test with same elements but different order
            ArrayDeque<Integer> deque4 = new ArrayDeque<>();
            deque4.addLast(3);
            deque4.addLast(2);
            deque4.addLast(1);
            assertFalse("Deques with same elements in different order should not be equal", deque1.equals(deque4));

            // Test 2D ArrayDeque case
            ArrayDeque<ArrayDeque<Integer>> deque2D1 = new ArrayDeque<>();
            ArrayDeque<ArrayDeque<Integer>> deque2D2 = new ArrayDeque<>();
            deque2D1.addLast(deque1);
            deque2D1.addLast(deque2);
            deque2D2.addLast(deque1);
            deque2D2.addLast(deque2);
            assertTrue("2D ArrayDeques with same elements should be equal", deque2D1.equals(deque2D2));

            // Test 3D ArrayDeque case
            ArrayDeque<ArrayDeque<ArrayDeque<Integer>>> deque3D1 = new ArrayDeque<>();
            ArrayDeque<ArrayDeque<ArrayDeque<Integer>>> deque3D2 = new ArrayDeque<>();
            deque3D1.addLast(deque2D1);
            deque3D2.addLast(deque2D2);
            assertTrue("3D ArrayDeques with same elements should be equal", deque3D1.equals(deque3D2));

            // Test unequal 2D ArrayDeque case
            ArrayDeque<ArrayDeque<Integer>> deque2D3 = new ArrayDeque<>();
            deque2D3.addLast(deque1);
            deque2D3.addLast(deque3);
            assertFalse("2D ArrayDeques with different elements should not be equal", deque2D1.equals(deque2D3));

            // Test with different generic types
            ArrayDeque<String> dequeString = new ArrayDeque<>();
            dequeString.addLast("1");
            dequeString.addLast("2");
            dequeString.addLast("3");
            assertFalse("ArrayDeques with different generic types should not be equal", deque1.equals(dequeString));

            // Test reflexivity
            assertTrue("An ArrayDeque should be equal to itself", deque1.equals(deque1));

            // Test symmetry
            assertTrue("Equality should be symmetric", deque1.equals(deque2) && deque2.equals(deque1));

            // Test transitivity
            ArrayDeque<Integer> deque7 = new ArrayDeque<>();
            deque7.addLast(1);
            deque7.addLast(2);
            deque7.addLast(3);
            assertTrue("Equality should be transitive",
                    deque1.equals(deque2) && deque2.equals(deque7) && deque1.equals(deque7));
        }



    @Test
    public void testEmptyDeque() {
        assertTrue(deque.isEmpty());
        assertEquals(0, deque.size());
        assertNull(deque.get(0));
        assertNull(deque.removeFirst());
        assertNull(deque.removeLast());
    }

    @Test
    public void testAddRemoveSingleElement() {
        deque.addFirst(1);
        assertFalse(deque.isEmpty());
        assertEquals(1, deque.size());
        assertEquals(Integer.valueOf(1), deque.removeFirst());
        assertTrue(deque.isEmpty());

        deque.addLast(2);
        assertFalse(deque.isEmpty());
        assertEquals(1, deque.size());
        assertEquals(Integer.valueOf(2), deque.removeLast());
        assertTrue(deque.isEmpty());
    }

    @Test
    public void testAddFirstRemoveLast() {
        for (int i = 1; i <= 5; i++) {
            deque.addFirst(i);
        }
        for (int i = 1; i <= 5; i++) {
            assertEquals(Integer.valueOf(i), deque.removeLast());
        }
        assertTrue(deque.isEmpty());
    }

    @Test
    public void testAddLastRemoveFirst() {
        for (int i = 1; i <= 5; i++) {
            deque.addLast(i);
        }
        for (int i = 1; i <= 5; i++) {
            assertEquals(Integer.valueOf(i), deque.removeFirst());
        }
        assertTrue(deque.isEmpty());
    }

    @Test
    public void testGet() {
        for (int i = 0; i < 10; i++) {
            deque.addLast(i);
        }
        for (int i = 0; i < 10; i++) {
            assertEquals(Integer.valueOf(i), deque.get(i));
        }
        assertNull(deque.get(10));
        assertNull(deque.get(-1));
    }

    @Test
    public void testAlternatingAddRemove() {
        deque.addFirst(1);
        deque.addLast(2);

        assertEquals(Integer.valueOf(1), deque.removeFirst());


        deque.addFirst(3);

        assertEquals(Integer.valueOf(2), deque.removeLast());

        deque.addLast(4);

        assertEquals(2, deque.size());
        assertEquals(Integer.valueOf(3), deque.get(0));
        assertEquals(Integer.valueOf(4), deque.get(1));
    }

    @Test
    public void testClear() {
        for (int i = 0; i < 5; i++) {
            deque.addLast(i);
        }
        assertFalse(deque.isEmpty());
        for (int i = 0; i < 5; i++) {
            deque.removeFirst();
        }
        assertTrue(deque.isEmpty());
        assertEquals(0, deque.size());
    }

    @Test
    public void testLargeDeque() {
        int size = 1000000;
        for (int i = 0; i < size; i++) {
            deque.addLast(i);
        }
        assertEquals(size, deque.size());
        for (int i = 0; i < size; i++) {
            assertEquals(Integer.valueOf(i), deque.get(i));
        }
        for (int i = 0; i < size; i++) {
            assertEquals(Integer.valueOf(i), deque.removeFirst());
        }
        assertTrue(deque.isEmpty());
    }

    @Test
    public void testNullElements() {
        deque.addFirst(null);
        deque.addLast(null);
        assertEquals(2, deque.size());
        assertNull(deque.get(0));
        assertNull(deque.get(1));
        assertNull(deque.removeFirst());
        assertNull(deque.removeLast());
        assertTrue(deque.isEmpty());
    }
}