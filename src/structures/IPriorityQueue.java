package structures;

public interface IPriorityQueue<E , K extends Comparable<K>> {

    public boolean insert(PriorityQueueNode<E, K> node);
    public void delete(int index);
    public PriorityQueueNode<E, K> maximum();
    public E extractMax();
}