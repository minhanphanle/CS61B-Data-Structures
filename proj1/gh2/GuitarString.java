package gh2;
 import deque.ArrayDeque;
 import deque.Deque;
 import java.util.Iterator;

//Note: This file will not compile until you complete the Deque implementations
public class GuitarString {
    private static final int SR = 44100;      // Sampling Rate
    private static final double DECAY = .996; // energy decay factor

    /* Buffer for storing sound data. */
     private Deque<Double> buffer;

    /* Create a guitar string of the given frequency.  */
    public GuitarString(double frequency) {
        // TODO: Create a buffer with capacity = SR / frequency. You'll need to
        //       cast the result of this division operation into an int. For
        //       better accuracy, use the Math.round() function before casting.
        //       Your should initially fill your buffer array with zeros.
        int capacity = (int) Math.round(SR / frequency);

        buffer = new ArrayDeque<>();
        for (int i = 0; i < capacity; i++) {
            buffer.addLast((double) 0);
        }
    }

    /* random number between -0.5 and 0.5 */
    private Double getRandom() {
        return Math.random() - 0.5;
    }


    /* Pluck the guitar string by replacing the buffer with white noise. */
    public void pluck() {
        for (int i = 0; i < buffer.size(); i++) {
            buffer.removeFirst();
            buffer.addFirst(getRandom());
        }
    }

    /* Advance the simulation one time step by performing one iteration of
     * the Karplus-Strong algorithm.
     */
    public void tic() {
        // Dequeue the front sample and enqueue a new sample that is
        // the average of the two multiplied by the DECAY factor.

        Double first = buffer.removeFirst();
        Double newDouble = (first + buffer.get(0)) / 2 * DECAY;
        buffer.addLast(newDouble);
    }

    /* Return the double at the front of the buffer. */
    public double sample() {
        return buffer.get(0);
    }
}
