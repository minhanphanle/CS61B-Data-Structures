package randomizedtest;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {
    @Test
    public void testThreeAddThreeRemove() {
        AListNoResizing<Integer> a = new AListNoResizing<>();
        BuggyAList<Integer> b = new BuggyAList<>();
        int[] addList = {1, 2, 3};
        for (int i : addList) {
            a.addLast(i);
            b.addLast(i);
        }

        for (int i = 0; i < addList.length; i++) {
            int resultA = a.removeLast();
            int resultB  = b.removeLast();
            assertEquals(resultA, resultB);
        }
    }

    @Test
    public void randomizedTest() {
        AListNoResizing<Integer> L = new AListNoResizing<>();
        BuggyAList<Integer> BL = new BuggyAList<>();

        int N = 5000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 4);
            int sizeL = L.size();
            int sizeBL = BL.size();

            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                L.addLast(randVal);
                BL.addLast(randVal);
                System.out.println("addLast(" + randVal + ")");
            } else if (operationNumber == 1) {
                // size
                assertEquals(sizeL, sizeBL);
                System.out.println("size List: " + sizeL + " " + "size Buggy List: " + sizeBL);
            } else if (operationNumber == 2) {
                // getLast
                if (sizeL == 0 || sizeBL == 0) {
                    return;
                }
                int lastL = L.getLast();
                int lastBL = BL.getLast();

                assertEquals(lastL, lastBL);

            } else if (operationNumber == 3) {
                // removeLast
                if (sizeL == 0 || sizeBL == 0) {
                    return;
                }
                L.removeLast();
                BL.removeLast();
                System.out.println("removeLast()");
            }
        }
    }
}
