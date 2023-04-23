package structures;

import structures.HashtableNode;

public interface IHashtable <K, V> {

    public int insert(HashtableNode<K,V> hashtableNode, K key);
    public V search (K key);
    public void delete(K key);
    public int hashFunction(K key, int i);
    public void chaining(HashtableNode<K,V> node, HashtableNode<K, V> current);
    public boolean containsKey(K key);
    public boolean containsValue(V value);
    public int occupedSize();
    public int size();

}
