package structures;

public class NodeHashTable<K,V> {

	private K id;
    private V value;
    private NodeHashTable<K, V> next;
    public NodeHashTable(K id, V value){
        this.id = id;
        this.value = value;
    }
    public K getId(){
        return id;
    }
    public V getValue(){
        return value;
    }
    public NodeHashTable<K, V> getNext(){
        return next;
    }
    public void setNext(NodeHashTable<K, V> nextNode){
        next = nextNode;
    }
    


	
	
}
