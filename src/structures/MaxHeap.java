package structures;

public class MaxHeap<T extends Comparable<T>> extends Heap<T> {

    public MaxHeap() {
        arr = (T[]) new Comparable[DEFAULT_CAPACITY];
    }

    public MaxHeap(int i) {
        arr = (T[]) new Comparable[i];
    }

    public MaxHeap(T[] a) {
        arr = (T[]) new Comparable[a.length];
        for (T object : a) {
            add(object);
        }
    }

    public void add(T object) {

        if (size >= arr.length - 1) {
            arr = resize(arr);
        }
        size++;
        arr[size] = object;
        bubbleUp();
    }

    public T poll() {
        T ret = arr[0];
        arr[0] = arr[size];
        arr[size] = null;
        size--;
        bubbleDown(arr, 0);
        return ret;
    }

    public void bubbleUp() {
        int index = size;
        while (parent(index) > -1 && arr[parent(index)].compareTo(arr[index]) < 0) {
            swap(index, parent(index));
            index = parent(index);
        }
    }

    public void heapify() {
        bubbleDown(arr, 0);
    }

    public void bubbleDown(T[] a, int index) {
        int smallest = size;
        int l = left(index);
        int r = right(index);
        if (l <= index && a[l].compareTo(a[smallest]) > 0) {
            smallest = l;
        }
        if (r <= index && a[r].compareTo(a[smallest]) > 0) {
            smallest = r;
        }
        if (smallest != index) {
            swap(smallest, index);
            bubbleDown(a, smallest);
        }
    }
}
