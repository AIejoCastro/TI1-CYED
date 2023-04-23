package structures;

public class HashtableNode<K, V> {

    private K key;
    private V value;
    private HashtableNode<K,V> next;
    private HashtableNode<K,V> previous;

    public HashtableNode(K key, V value){
        this.key = key;
        this.value = value;
        next = null;
        previous = null;
    }

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public HashtableNode<K, V> getNext() {
        return next;
    }

    public void setNext(HashtableNode<K, V> next) {
        this.next = next;
    }

    public HashtableNode<K, V> getPrevious() {
        return previous;
    }

    public void setPrevious(HashtableNode<K, V> previous) {
        this.previous = previous;
    }
}
