package structures;

public class Queue<E> {
	
    private Node<E> head;
    private Node<E> tail;
    private int size;

    public Queue() {
        head = null; 
        tail = null;
        this.size = 0;
    }

    public boolean isEmpty() {
    boolean empty = false;
    	if(head == null)
    	{
    		empty = true;
    	}
        return empty;
    }

    public int size() {
        return this.size;
    }

    public void enqueue(E e) 
    {
        Node<E> nodeVVVVVVV = new Node<E>(e);

        if (isEmpty())
        {
            head = nodeVVVVVVV;
            tail = head;
        }
        else
        {
          tail.setNext(nodeVVVVVVV);
        }
            tail = nodeVVVVVVV;
            
            size++;
     }

     public Node<E> dequeue()
     {
    	 if(isEmpty())
    	 {
    		 return null;
    	 }else
    	 {
        	 Node<E> aux = head;
        	 head = head.getNext();
        	 
        	 size--;

        	 return aux;
    	 }
     }


    public Node<E> getHead() {
        return head;
    }
}