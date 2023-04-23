package structures;

public interface IStack <V> {

    public void push(V value);
    public StackNode<V> pop();
    public StackNode<V> top();
    public boolean isEmpty();
}
