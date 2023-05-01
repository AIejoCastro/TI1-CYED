import org.junit.Test;
import structures.Node;
import structures.Queue;
import static org.junit.Assert.*;

public class QueueTest {

    //Estandar
    @Test
    public void isEmptyStandard() {
        Queue<String> queue = new Queue<>();
        assertTrue(queue.isEmpty());
    }

    //Limites
    @Test
    public void isEmptyLimit() {
        Queue<String> queue = new Queue<>();
        queue.enqueue("A");
        queue.dequeue();
        assertTrue(queue.isEmpty());
    }

    //Interesante
    @Test
    public void isEmptyInteresting() {
        Queue<Integer> queue = new Queue<>();
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        assertTrue(queue.isEmpty());
    }

    //Estandar
    @Test
    public void sizeStandard() {
        Queue<String> queue = new Queue<>();
        assertEquals(0, queue.size());
    }

    //Limites
    @Test
    public void sizeLimit() {
        Queue<String> queue = new Queue<>();
        queue.enqueue("A");
        assertEquals(1, queue.size());
    }

    //Interesante
    @Test
    public void sizeInteresting() {
        Queue<Integer> queue = new Queue<>();
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        assertEquals(0, queue.size());
    }

    //Estandar
    @Test
    public void enqueueStandard() {
        Queue<String> queue = new Queue<>();
        queue.enqueue("A");
        assertEquals(1, queue.size());
    }

    //Limites
    @Test
    public void enqueueLimit() {
        Queue<String> queue = new Queue<>();
        queue.enqueue("A");
        queue.enqueue("B");
        queue.dequeue();
        queue.dequeue();
        assertEquals(0, queue.size());
    }

    //Interesante
    @Test
    public void enqueueInteresting() {
        Queue<Integer> queue = new Queue<>();
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        assertEquals(3, queue.size());
    }

    //Estandar
    @Test
    public void dequeueStandard() {
        Queue<Integer> queue = new Queue<>();
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);

        Node<Integer> node1 = queue.dequeue();
        assertEquals(1, node1.getItem().intValue());

        Node<Integer> node2 = queue.dequeue();
        assertEquals(2, node2.getItem().intValue());

        Node<Integer> node3 = queue.dequeue();
        assertEquals(3, node3.getItem().intValue());

        Node<Integer> node4 = queue.dequeue();
        assertEquals(4, node4.getItem().intValue());
    }

    //Limites
    @Test
    public void dequeueLimit() {
        Queue<Integer> queue = new Queue<>();

        Node<Integer> node = queue.dequeue();
        assertNull(node);
    }

    //Interesante
    @Test
    public void dequeueInteresting() {
        Queue<String> queue = new Queue<>();
        queue.enqueue("A");
        queue.enqueue("B");
        queue.enqueue("C");
        queue.enqueue("D");
        queue.enqueue("E");

        Node<String> node1 = queue.dequeue();
        Node<String> node2 = queue.dequeue();

        assertEquals(3, queue.size());
        assertEquals("C", queue.getHead().getItem());
        assertEquals("D", queue.getHead().getNext().getItem());
    }

    //Estandar
    @Test
    public void getHeadStandard() {
        Queue<String> queue = new Queue<>();
        queue.enqueue("A");
        queue.enqueue("B");
        queue.enqueue("C");
        assertEquals("A", queue.getHead().getItem());
    }

    //Limite
    @Test
    public void getHeadLimit() {
        Queue<Integer> queue = new Queue<>();
        Node<Integer> node = queue.getHead();
        assertNull(node);
    }

    //Interesante
    @Test
    public void getHeadInteresting() {
        Queue<String> queue = new Queue<>();
        queue.enqueue("A");
        queue.enqueue("B");
        queue.enqueue("C");

        queue.dequeue();
        
        assertEquals("B", queue.getHead().getItem());
    }
}