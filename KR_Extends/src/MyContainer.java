import java.util.ArrayList;

public class MyContainer<T extends Comparable<T>> extends ArrayList<T> {
    public T findMax() {
        if (this.isEmpty()) throw new IllegalStateException();
        T max = this.get(0);
        for (T item : this) {
            if (item.compareTo(max) > 0) {
                max = item;
            }
        }
        return max;
    }
}
