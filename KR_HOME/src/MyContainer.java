import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyContainer<T extends Comparable<T>> implements Iterable<T> {

    Node<T> head;

    private class Node<T> {
        public T data;
        public Node<T> front;

        public Node(T data, Node<T> next) {
            this.data = data;
            this.front = next;
        }
    }

    public MyContainer() {
        head = null;
    }

    public void pushFront(T item) {
        head = new Node<T>(item, head);
    }

    public void pushBack(T item) {
        if (head == null) pushFront(item);

        Node<T> temp = head;
        while (temp.front != null) temp = temp.front;
        temp.front = new Node<T>(item, null);
    }

    public void insertBefore(T item, T nextItem) {
        if (head == null) return;
        if (head.data.equals(nextItem)) {
            pushFront(item);
            return;
        }

        Node<T> current = head;
        Node<T> previous = head;

        while (current != null) {
            if (current.data.equals(nextItem)) {
                previous.front = new Node<>(item, current);
                return;
            }
            previous = current;
            current = current.front;
        }
    }

    public void remove(T item) throws Exception {
        if (head == null) return;
        if (head.data.equals(item)) {
            head = head.front;
            return;
        }

        Node<T> current = head;
        Node<T> previous = head;

        while (current != null && !current.data.equals(item)) {
            previous = current;
            current = current.front;
        }

        if (current == null) {
            throw new Exception("unable to delete");
        }

        previous.front = current.front;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node<T> temp = head;
        while (temp.front != null) {
            sb.append(temp.data);
            sb.append(" ");
            temp = temp.front;
        }
        sb.append(temp.data);
        return sb.toString();
    }

    public class MyContainerIterator implements Iterator<T> {

        private Node<T> currentNode = head;

        @Override
        public boolean hasNext() {
            return currentNode != null;
        }

        @Override
        public T next() {
            if (!hasNext()) throw new NoSuchElementException();
            T nextData = currentNode.data;
            currentNode = currentNode.front;
            return nextData;
        }
    }

    public Iterator<T> iterator() {
        return new MyContainerIterator();
    }

    public T findMax() {
        if (head == null) throw new IllegalStateException();
        T max = head.data;
        for (T item: this) {
            if (item.compareTo(max) > 0) {
                max = item;
            }
        }
        return max;
    }

    public T findMin() {
        if (head == null) throw new IllegalStateException();
        T min = head.data;
        for (T item: this) {
            if (item.compareTo(min) < 0) {
                min = item;
            }
        }
        return  min;
    }
}
