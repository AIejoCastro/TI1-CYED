import org.junit.Test;
import structures.PriorityQueue;
import structures.PriorityQueueNode;
import static org.junit.Assert.*;

public class PriorityQueueTest {

    //Estándar
    @Test
    public void deleteStandardTest() {
        PriorityQueue<String, Integer> queue = new PriorityQueue<>(4);
        queue.insert(new PriorityQueueNode<>("A", 1));
        queue.insert(new PriorityQueueNode<>("B", 2));
        queue.insert(new PriorityQueueNode<>("C", 3));
        queue.insert(new PriorityQueueNode<>("D", 4));
        queue.delete(2);
        int compare = queue.maximum().getKey();
        assertEquals(4, compare);
    }

    //Limites
    @Test
    public void deleteLimitsTest() {
        PriorityQueue<String, Integer> queue = new PriorityQueue<>(5);
        queue.delete(0);
        assertNull(queue.maximum());
    }

    //Interesante
    @Test
    public void deleteInterestingTest() {
        PriorityQueue<String, Integer> queue = new PriorityQueue<>(5);
        queue.insert(new PriorityQueueNode<>("A", 3));
        queue.insert(new PriorityQueueNode<>("B", 1));
        queue.insert(new PriorityQueueNode<>("C", 2));
        queue.delete(0);
        int compare = queue.maximum().getKey();
        assertEquals(2, compare);
    }

    //Estándar
    @Test
    public void insertStandardTest() {
        PriorityQueue<String, Integer> queue = new PriorityQueue<>(5);
        queue.insert(new PriorityQueueNode<>("A", 2));
        queue.insert(new PriorityQueueNode<>("B", 3));
        queue.insert(new PriorityQueueNode<>("C", 1));
        queue.insert(new PriorityQueueNode<>("D", 4));
        int compareMax = queue.maximum().getKey();
        assertEquals(4, compareMax);
        int compareMin = queue.minimum().getKey();
        assertEquals(1, compareMin);
    }

    //Límites
    @Test
    public void insertLimitsTest() {
        PriorityQueue<String, Integer> queue = new PriorityQueue<>(1);
        queue.insert(new PriorityQueueNode<>("A", 1));
        assertFalse(queue.insert(new PriorityQueueNode<>("B", 2)));
    }

    //Interesante
    @Test
    public void insertInterestingTest() {
        PriorityQueue<String, Integer> queue = new PriorityQueue<>(5);
        queue.insert(new PriorityQueueNode<>("A", 1));
        queue.insert(new PriorityQueueNode<>("B", 2));
        queue.insert(new PriorityQueueNode<>("C", 2));
        queue.insert(new PriorityQueueNode<>("D", 4));
        int compare = queue.maximum().getKey();
        assertEquals(4, compare);

    }

    //Estándar
    @Test
    public void maximumStandardTest() {
        PriorityQueue<String, Integer> queue = new PriorityQueue<>(4);
        queue.insert(new PriorityQueueNode<>("A", 10));
        queue.insert(new PriorityQueueNode<>("B", 7));
        queue.insert(new PriorityQueueNode<>("C", 12));
        queue.insert(new PriorityQueueNode<>("D", 5));
        assertEquals("C", queue.maximum().getElement());
    }

    //Limite
    @Test
    public void maximumLimitTest() {
        PriorityQueue<String, Integer> queue = new PriorityQueue<>(0);
        assertNull(queue.maximum());
    }

    //Interesante
    @Test
    public void maximumInterestingTest() {
        PriorityQueue<String, Integer> queue = new PriorityQueue<>(4);
        queue.insert(new PriorityQueueNode<>("A", 10));
        queue.insert(new PriorityQueueNode<>("B", 7));
        queue.insert(new PriorityQueueNode<>("C", 12));
        queue.insert(new PriorityQueueNode<>("D", 5));
        queue.extractMax();
        assertEquals("A", queue.maximum().getElement());
    }

    //Estándar
    @Test
    public void minimumStandardTest() {
        PriorityQueue<String, Integer> queue = new PriorityQueue<>(4);
        queue.insert(new PriorityQueueNode<>("A", 10));
        queue.insert(new PriorityQueueNode<>("B", 7));
        queue.insert(new PriorityQueueNode<>("C", 12));
        queue.insert(new PriorityQueueNode<>("D", 5));
        assertEquals("D", queue.minimum().getElement());
    }

    //Limite
    @Test
    public void minimumLimitTest() {
        PriorityQueue<String, Integer> queue = new PriorityQueue<>(0);
        assertNull(queue.minimum());
    }

    //Interesante
    @Test
    public void minimumInterestingTest() {
        PriorityQueue<String, Integer> queue = new PriorityQueue<>(4);
        queue.insert(new PriorityQueueNode<>("A", 10));
        queue.insert(new PriorityQueueNode<>("B", 7));
        queue.insert(new PriorityQueueNode<>("C", 12));
        queue.insert(new PriorityQueueNode<>("D", 5));
        queue.extractMin();
        assertEquals("B", queue.minimum().getElement());
    }

    //Estandar
    @Test
    public void extractMaxStandardTest() {
        PriorityQueue<String, Integer> queue = new PriorityQueue<>(4);
        queue.insert(new PriorityQueueNode<>("A", 10));
        queue.insert(new PriorityQueueNode<>("B", 7));
        queue.insert(new PriorityQueueNode<>("C", 12));
        queue.insert(new PriorityQueueNode<>("D", 5));
        String compare = queue.extractMax();
        assertEquals("C", compare);
    }

    //Limites
    @Test
    public void extractMaxLimitTest() {
        PriorityQueue<String, Integer> queue = new PriorityQueue<>(0);
        assertNull(queue.extractMax());
    }

    //Interesante
    @Test
    public void extractMaxInterestingTest() {
        PriorityQueue<String, Integer> queue = new PriorityQueue<>(4);
        queue.insert(new PriorityQueueNode<>("A", 10));
        queue.insert(new PriorityQueueNode<>("B", 7));
        queue.insert(new PriorityQueueNode<>("C", 12));
        queue.insert(new PriorityQueueNode<>("D", 5));
        queue.extractMax();
        queue.extractMax();
        assertEquals("B", queue.maximum().getElement());
    }

    //Estandar
    @Test
    public void extractMinStandardTest() {
        PriorityQueue<String, Integer> queue = new PriorityQueue<>(4);
        queue.insert(new PriorityQueueNode<>("A", 10));
        queue.insert(new PriorityQueueNode<>("B", 7));
        queue.insert(new PriorityQueueNode<>("C", 12));
        queue.insert(new PriorityQueueNode<>("D", 5));
        String compare = queue.extractMin();
        assertEquals("D", compare);
    }

    //Limites
    @Test
    public void extractMinLimitTest() {
        PriorityQueue<String, Integer> queue = new PriorityQueue<>(0);
        assertNull(queue.extractMin());
    }

    //Interesante
    @Test
    public void extractMinInterestingTest() {
        PriorityQueue<String, Integer> queue = new PriorityQueue<>(4);
        queue.insert(new PriorityQueueNode<>("A", 10));
        queue.insert(new PriorityQueueNode<>("B", 7));
        queue.insert(new PriorityQueueNode<>("C", 12));
        queue.insert(new PriorityQueueNode<>("D", 5));
        queue.extractMin();
        queue.extractMin();
        assertEquals("A", queue.minimum().getElement());
    }

    //Estandar
    @Test
    public void quickSortMajorMinorStandardTest() {
        PriorityQueue<String, Integer> queue = new PriorityQueue<>(5);
        queue.insert(new PriorityQueueNode<>("A", 3));
        queue.insert(new PriorityQueueNode<>("B", 5));
        queue.insert(new PriorityQueueNode<>("C", 2));
        queue.insert(new PriorityQueueNode<>("D", 1));
        queue.insert(new PriorityQueueNode<>("E", 4));
        queue.quickSortMajorMinor(0, 4);
        assertEquals("B", queue.extractMax());
        assertEquals("E", queue.extractMax());
        assertEquals("A", queue.extractMax());
        assertEquals("C", queue.extractMax());
        assertEquals("D", queue.extractMax());
    }

    //Limites
    public void quickSortMajorMinorLimitTest() {
        PriorityQueue<Integer, Integer> queue = new PriorityQueue<>(1);
        queue.insert(new PriorityQueueNode<>(1, 10));
        queue.quickSortMajorMinor(0);
        assertEquals((Integer) 1, queue.extractMin());

    }
}