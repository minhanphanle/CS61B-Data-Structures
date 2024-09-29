package deque;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Comparator;
import java.util.Arrays;

public class MaxArrayDequeTest {
    // Integer Comparator
    static class IntegerComparator implements Comparator<Integer> {
        @Override
        public int compare(Integer a, Integer b) {
            return a.compareTo(b);
        }
    }

    // String Comparator
    static class StringComparator implements Comparator<String> {
        @Override
        public int compare(String a, String b) {
            return a.compareTo(b);
        }
    }

    // 2D Array Comparator
    static class TwoDArrayComparator implements Comparator<int[][]> {
        @Override
        public int compare(int[][] arr1, int[][] arr2) {
            if (arr1 == null || arr1.length == 0 || arr1[0].length == 0) {
                return (arr2 == null || arr2.length == 0 || arr2[0].length == 0) ? 0 : -1;
            }
            if (arr2 == null || arr2.length == 0 || arr2[0].length == 0) {
                return 1;
            }
            return Integer.compare(arr1[0][0], arr2[0][0]);
        }
    }

    @Test
    public void testIntegerMaxArrayDeque() {
        Comparator<Integer> intComparator = new IntegerComparator();
        MaxArrayDeque<Integer> intDeque = new MaxArrayDeque<>(intComparator);

        intDeque.addLast(3);
        intDeque.addLast(1);
        intDeque.addLast(4);
        intDeque.addLast(1);
        intDeque.addLast(5);

        assertEquals("Default max should be 5", Integer.valueOf(5), intDeque.max());
        assertEquals("Min (using reverse comparator) should be 1",
                Integer.valueOf(1), intDeque.max((a, b) -> b.compareTo(a)));
    }

    @Test
    public void testStringMaxArrayDeque() {
        Comparator<String> strComparator = new StringComparator();
        MaxArrayDeque<String> strDeque = new MaxArrayDeque<>(strComparator);

        strDeque.addLast("banana");
        strDeque.addLast("apple");
        strDeque.addLast("cherry");
        strDeque.addLast("date");

        assertEquals("Default max should be 'date'", "date", strDeque.max());
        assertEquals("Longest string should be 'banana'", "banana",
                strDeque.max((a, b) -> Integer.compare(a.length(), b.length())));
    }

    @Test
    public void testTwoDArrayMaxArrayDeque() {
        Comparator<int[][]> arrComparator = new TwoDArrayComparator();
        MaxArrayDeque<int[][]> arrDeque = new MaxArrayDeque<>(arrComparator);

        int[][] arr1 = {{3, 2}, {1, 4}};
        int[][] arr2 = {{1, 5}, {6, 7}};
        int[][] arr3 = {{4, 8}, {9, 0}};

        arrDeque.addLast(arr1);
        arrDeque.addLast(arr2);
        arrDeque.addLast(arr3);

        assertTrue("Default max should be {{4, 8}, {9, 0}}", Arrays.deepEquals(arr3, arrDeque.max()));

        Comparator<int[][]> sumComparator = (a, b) -> {
            int sumA = Arrays.stream(a).flatMapToInt(Arrays::stream).sum();
            int sumB = Arrays.stream(b).flatMapToInt(Arrays::stream).sum();
            return Integer.compare(sumA, sumB);
        };

        assertTrue("Max sum array should be {{1, 5}, {6, 7}}",
                Arrays.deepEquals(arr3, arrDeque.max(sumComparator)));
    }

    @Test
    public void testEdgeCases() {
        MaxArrayDeque<Integer> emptyDeque = new MaxArrayDeque<>(new IntegerComparator());
        assertNull("Max of empty deque should be null", emptyDeque.max());

        MaxArrayDeque<String> singleElementDeque = new MaxArrayDeque<>(new StringComparator());
        singleElementDeque.addLast("only");
        assertEquals("Max of single element deque should be 'only'", "only", singleElementDeque.max());

        MaxArrayDeque<int[][]> nullElementDeque = new MaxArrayDeque<>(new TwoDArrayComparator());
        nullElementDeque.addLast(null);
        nullElementDeque.addLast(new int[][]{{1, 2}});
        assertTrue("Max should ignore null elements",
                Arrays.deepEquals(new int[][]{{1, 2}}, nullElementDeque.max()));
    }
}