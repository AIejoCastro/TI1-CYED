package structures;

public class LinkedList<K,V> {

	private NodeHashTable<K,V> head;
	public LinkedList() {
		head = null;
	}

	public void insertNodeHT(NodeHashTable<K,V> node){
		if (head == null)
		{
            head=node;
        }else{
            NodeHashTable<K,V> auxiliar=head;
       
            while (auxiliar.getNext()!=null) {
                auxiliar=auxiliar.getNext();
            }
    
            auxiliar.setNext(node);
        }
		
	}
	
	public NodeHashTable<K, V> searchNode(K key)	{
        if(head != null)
        {
            if(head.getId().equals(key))
            {
                return head;
            } else
            {
                return searchNode(key, head.getNext());
            }
        } else{
        	
            return null;
        }
    }

    private NodeHashTable<K, V> searchNode(K key, NodeHashTable<K, V> current){
        if(current != null)
        {
            if(current.getId().equals(key))
            {
                return current;
            } else{
                return searchNode(key, current.getNext());
            }
        } else{
            return null;
        }
    }
}
