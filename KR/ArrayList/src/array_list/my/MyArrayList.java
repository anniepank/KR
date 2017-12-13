package array_list.my;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyArrayList<T extends Comparable<T>> implements Iterable<T>{
    private T[] elementData;
    private int size;

    public MyArrayList() {
        elementData = (T[]) new Comparable[10];
        size = 0;
    }

    public MyArrayList(int capacity) {
        elementData = (T[]) new Comparable[capacity];
        size = 0;
    }

    public void add(T value) {
        ensureCapacity();
        elementData[size++] = value;
    }

    private void ensureCapacity() {
        if (elementData.length < size + 1) {
            T[] oldData = elementData;
            int newCapacity = (elementData.length * 3) / 2 + 1;
            elementData = (T[]) new Comparable[newCapacity];
            System.arraycopy(oldData, 0, elementData, 0, size);
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            sb.append(elementData[i]).append(" ");
        }
        return sb.toString();
    }

    public void add(int index, T item) {
        if (index > size) throw new IndexOutOfBoundsException();
        ensureCapacity();
        System.arraycopy(elementData, index, elementData, index + 1, size - index);
        elementData[index] = item;
        size++;

    }

    public void remove(T value) {
        int indexToRemove = -1;
        for (int i = 0; i < size; i++) {
            if (elementData[i].equals(value)) indexToRemove = i;
        }

        if (indexToRemove != -1) {
            remove(indexToRemove);
        }
    }

    public void remove(int index) {
        int numMoved = size - index - 1;
        System.arraycopy(elementData, index + 1, elementData, index, numMoved);
        elementData[--size] = null;
    }

    public T findMax() {
        if (size == 0) throw new IllegalStateException();
        T max = elementData[0];
        for (T item : this) {
            if (item.compareTo(max) > 0) {
                max = item;
            }
        }

        return max;
    }

    public class MyArrayListIterator implements Iterator<T> {
        int currentIndex;

        @Override
        public boolean hasNext() {
            return currentIndex != size;
        }

        @Override
        public T next() {
            if (!hasNext()) throw new NoSuchElementException();
            return elementData[currentIndex++];
        }
    }

    public Iterator<T> iterator() {
        return new MyArrayListIterator();
    }
}
