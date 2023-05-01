package structures;

public class Node<E> implements Comparable<E>{

    private E item;
    private Node<E> next;
    
    public Node(E element) {
        this.item = element;
        this.next = null;
    }

    
    public Node() {
        this.item = null;
        this.next = null;
    }
    
    public void setItem(E item) {
        this.item = item;
    }


    public E getItem() {
        return item;
    }


    public Node<E> getNext() {
        return next;
    }

    public void setNext(Node<E> next) {
        this.next = next;
    }

    @SuppressWarnings("unchecked")
	@Override
    public int compareTo(E o) {
        return ((Comparable<E>) getItem()).compareTo(o);
    }




    @Override
    public String toString() {
    	
        return getItem().toString();
    }




}