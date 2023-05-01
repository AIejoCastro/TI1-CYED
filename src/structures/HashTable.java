package structures;

public class HashTable<K,V> {
	private LinkedList<K,V>[] hashtable;
	private final int PASSENGER_QUANTITY = 54;
	public HashTable(){
		hashtable = (LinkedList<K, V>[]) new LinkedList<?,?>[PASSENGER_QUANTITY];
	}
	public int hash(K k){
		int keyToHash = k.hashCode();
		return keyToHash % PASSENGER_QUANTITY;
	}
	public void insertOnHashTable(K key, V value){
		int hashPosition = hash(key);
		NodeHashTable<K,V> nodeToInsert = new NodeHashTable<>(key, value);
		
		if(hashtable[hashPosition] != null) {
			hashtable[hashPosition].insertNodeHT(nodeToInsert);
		} else{

			LinkedList<K, V> chaining = new LinkedList<>();

			hashtable[hashPosition] = chaining;

			hashtable[hashPosition].insertNodeHT(nodeToInsert);
		}
	}
	public V searchOnHashTable(K keyToSearch){

		int hashPosition = hash(keyToSearch);
		NodeHashTable<K, V> nodeFounded = hashtable[hashPosition].searchNode(keyToSearch);

		if(nodeFounded != null){
			return nodeFounded.getValue();
		} else{
			return null;
		}
	}
}
