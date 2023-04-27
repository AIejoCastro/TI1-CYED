package structures;

import java.util.Arrays;

public abstract class Heap<T> {

    public int DEFAULT_CAPACITY = 10;

    public T[] arr;

    public int size;

    public Heap() {
        arr = (T[]) new Object[DEFAULT_CAPACITY];
        size = -1;
    }

    public Heap(int size) {
        arr = (T[]) new Object[size];
        size = -1;
    }

    public void setArray(T[] ar) {
        arr = ar;
        size = arr.length - 1;
    }

    public int length() {
        return arr.length;
    }

    public int size() {
        return size;
    }

    public int parent(int i) {
        return (i - 1) / 2;
    }

    public int left(int i) {
        return (i*2)+1;
    }

    public int right(int i) {
        return (i*2)+2;
    }

    public void swap(int i1, int i2) {
        T temp = arr[i1];
        arr[i1]=arr[i2];
        arr[i2] = temp;
    }

    public T[] resize(T[] ar) {
        return Arrays.copyOf(ar, ar.length+DEFAULT_CAPACITY);
    }

    @Override
    public String toString() {
        String msg = "";
        for(T t : arr) {
            if(t!=null) {
                msg+=t.toString() + " ";
            }else {
                break;
            }
        }
        return msg;
    }
}