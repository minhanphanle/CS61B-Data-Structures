package deque;

import org.junit.Test;
import static org.junit.Assert.*;


/** Performs some basic linked list tests. */
public class LinkedListDequeTest {

    @Test
    /** Adds a few things to the list, checking isEmpty() and size() are correct,
     * finally printing the results.
     *
     * && is the "and" operation. */
    public void addIsEmptySizeTest() {

        LinkedListDeque<String> lld1 = new LinkedListDeque<String>();

		assertTrue("A newly initialized LLDeque should be empty", lld1.isEmpty());
		lld1.addFirst("front");

		// The && operator is the same as "and" in Python.
		// It's a binary operator that returns true if both arguments true, and false otherwise.
        assertEquals(1, lld1.size());
        assertFalse("lld1 should now contain 1 item", lld1.isEmpty());

		lld1.addLast("middle");
		assertEquals(2, lld1.size());

		lld1.addLast("back");
		assertEquals(3, lld1.size());

		System.out.println("Printing out deque: ");
		lld1.printDeque();
        assertEquals("middle", lld1.get(1));
        assertEquals("back", lld1.get(2));
    }

    @Test
    public void testIterator() {
        LinkedListDeque<Integer> deque = new LinkedListDeque();
        for (int i = 0; i < 10; i++) {
            deque.addLast(i);
        }

        for (Integer elem: deque) {
            System.out.println(elem.toString());
        }

    }


    @Test
    public void testEquals() {
        // Test basic equality
        LinkedListDeque<Integer> deque1 = new LinkedListDeque<>();
        LinkedListDeque<Integer> deque2 = new LinkedListDeque<>();
        assertTrue("Empty deques should be equal", deque1.equals(deque2));

        deque1.addLast(1);
        deque1.addLast(2);
        deque1.addLast(3);
        deque2.addLast(1);
        deque2.addLast(2);
        deque2.addLast(3);
        assertTrue("Deques with same elements should be equal", deque1.equals(deque2));

        // Test inequality
        LinkedListDeque<Integer> deque3 = new LinkedListDeque<>();
        deque3.addLast(1);
        deque3.addLast(2);
        assertFalse("Deques with different number of elements should not be equal", deque1.equals(deque3));

        // Test with null
        assertFalse("Deque should not be equal to null", deque1.equals(null));

        // Test with different type
        assertFalse("Deque should not be equal to a non-LinkedListDeque object", deque1.equals("Not an LinkedListDeque"));

        // Test with same elements but different order
        LinkedListDeque<Integer> deque4 = new LinkedListDeque<>();
        deque4.addLast(3);
        deque4.addLast(2);
        deque4.addLast(1);
        assertFalse("Deques with same elements in different order should not be equal", deque1.equals(deque4));

        // Test 2D LinkedListDeque case
        LinkedListDeque<LinkedListDeque<Integer>> deque2D1 = new LinkedListDeque<>();
        LinkedListDeque<LinkedListDeque<Integer>> deque2D2 = new LinkedListDeque<>();
        deque2D1.addLast(deque1);
        deque2D1.addLast(deque2);
        deque2D2.addLast(deque1);
        deque2D2.addLast(deque2);
        assertTrue("2D LinkedListDeques with same elements should be equal", deque2D1.equals(deque2D2));

        // Test 3D LinkedListDeque case
        LinkedListDeque<LinkedListDeque<LinkedListDeque<Integer>>> deque3D1 = new LinkedListDeque<>();
        LinkedListDeque<LinkedListDeque<LinkedListDeque<Integer>>> deque3D2 = new LinkedListDeque<>();
        deque3D1.addLast(deque2D1);
        deque3D2.addLast(deque2D2);
        assertTrue("3D LinkedListDeques with same elements should be equal", deque3D1.equals(deque3D2));

        // Test unequal 2D LinkedListDeque case
        LinkedListDeque<LinkedListDeque<Integer>> deque2D3 = new LinkedListDeque<>();
        deque2D3.addLast(deque1);
        deque2D3.addLast(deque3);
        assertFalse("2D LinkedListDeques with different elements should not be equal", deque2D1.equals(deque2D3));

        // Test with different generic types
        LinkedListDeque<String> dequeString = new LinkedListDeque<>();
        dequeString.addLast("1");
        dequeString.addLast("2");
        dequeString.addLast("3");
        assertFalse("LinkedListDeques with different generic types should not be equal", deque1.equals(dequeString));

        // Test reflexivity
        assertTrue("An LinkedListDeque should be equal to itself", deque1.equals(deque1));

        // Test symmetry
        assertTrue("Equality should be symmetric", deque1.equals(deque2) && deque2.equals(deque1));

        // Test transitivity
        LinkedListDeque<Integer> deque7 = new LinkedListDeque<>();
        deque7.addLast(1);
        deque7.addLast(2);
        deque7.addLast(3);
        assertTrue("Equality should be transitive",
                deque1.equals(deque2) && deque2.equals(deque7) && deque1.equals(deque7));
    }


    @Test
    /** Adds an item, then removes an item, and ensures that dll is empty afterwards. */
    public void addRemoveTest() {

        System.out.println("Make sure to uncomment the lines below (and delete this print statement).");
        LinkedListDeque<Integer> lld1 = new LinkedListDeque<Integer>();
		// should be empty
		assertTrue("lld1 should be empty upon initialization", lld1.isEmpty());

		lld1.addFirst(10);
		// should not be empty
		assertFalse("lld1 should contain 1 item", lld1.isEmpty());

		lld1.removeFirst();
		// should be empty
		assertTrue("lld1 should be empty after removal", lld1.isEmpty());
    }

    @Test
    /* Tests removing from an empty deque */
    public void removeEmptyTest() {

        LinkedListDeque<Integer> lld1 = new LinkedListDeque<>();
        lld1.addFirst(3);

        lld1.removeLast();
        lld1.removeFirst();
        lld1.removeLast();
        lld1.removeFirst();

        int size = lld1.size();
        String errorMsg = "  Bad size returned when removing from empty deque.\n";
        errorMsg += "  student size() returned " + size + "\n";
        errorMsg += "  actual size() returned 0\n";

        assertEquals(errorMsg, 0, size);

    }

    @Test
    /* Check if you can create LinkedListDeques with different parameterized types*/
    public void multipleParamTest() {

        LinkedListDeque<String>  lld1 = new LinkedListDeque<String>();
        LinkedListDeque<Double>  lld2 = new LinkedListDeque<Double>();
        LinkedListDeque<Boolean> lld3 = new LinkedListDeque<Boolean>();

        lld1.addFirst("string");
        lld2.addFirst(3.14159);
        lld3.addFirst(true);

        String s = lld1.removeFirst();
        double d = lld2.removeFirst();
        boolean b = lld3.removeFirst();
    }

    @Test
    /* check if null is return when removing from an empty LinkedListDeque. */
    public void emptyNullReturnTest() {

        LinkedListDeque<Integer> lld1 = new LinkedListDeque<Integer>();

        boolean passed1 = false;
        boolean passed2 = false;
        assertEquals("Should return null when removeFirst is called on an empty Deque,", null, lld1.removeFirst());
        assertEquals("Should return null when removeLast is called on an empty Deque,", null, lld1.removeLast());


    }

    @Test
    /* Add large number of elements to deque; check if order is correct. */
    public void bigLLDequeTest() {

        LinkedListDeque<Integer> lld1 = new LinkedListDeque<Integer>();
        for (int i = 0; i < 1000000; i++) {
            lld1.addLast(i);
        }

        for (double i = 0; i < 500000; i++) {
            assertEquals("Should have the same value", i, (double) lld1.removeFirst(), 0.0);
        }

        for (double i = 999999; i > 500000; i--) {
            assertEquals("Should have the same value", i, (double) lld1.removeLast(), 0.0);
        }

    }
}
