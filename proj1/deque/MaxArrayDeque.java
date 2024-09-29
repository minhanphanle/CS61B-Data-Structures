package deque;
import java.util.Comparator;
import java.util.Iterator;

public class MaxArrayDeque<T> extends ArrayDeque<T> {
    private Comparator<T> c;

    public MaxArrayDeque(Comparator<T> comparator) {
        super();
        c = comparator;
    }

    // return the max element based on the default comparator
    public T max() {
        if (this.isEmpty()) {
            return null;
        }

        return max(this.c);
    }

    // return max element based on comparator
    public T max(Comparator<T> customC) {
        if (this.isEmpty()) {
            return null;
        }

        Iterator<T> iter = this.iterator();
        T maxEl  = iter.next();
        while (iter.hasNext()) {
            T nextEl = iter.next();
            if (customC.compare(nextEl, maxEl) > 0) {
                maxEl = nextEl;
            }
        }

        return maxEl;
    }
}
