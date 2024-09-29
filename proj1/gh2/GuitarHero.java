package gh2;
import edu.princeton.cs.algs4.StdAudio;
import edu.princeton.cs.algs4.StdDraw;
import deque.ArrayDeque;

public class GuitarHero {
    public static final String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";

    private static double getFrequency(int index) {
        return 440 * Math.pow(2, (index - 24) / 12);
    }

    public static void main(String[] args) {
        // array of 37 guitar strings
        ArrayDeque<GuitarString> arr = new ArrayDeque<>();

        for (int i = 0; i < 37; i++) {
            arr.addLast(new GuitarString(getFrequency(i)));
        }

        while (true) {
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();

                // not valid key, indexOf returns -1
                if (keyboard.indexOf(key) >= 0) {
                    arr.get(keyboard.indexOf(key)).pluck();
                }

            }

            /* compute the superposition of samples */
            double sample = 0;
            for (GuitarString gs : arr) {
                sample += gs.sample();
            }

            /* play the sample on standard audio */
            StdAudio.play(sample);

            /* advance the simulation of each guitar string by one step */

            for (GuitarString gs : arr) {
                gs.tic();
            }
        }

    }



}
