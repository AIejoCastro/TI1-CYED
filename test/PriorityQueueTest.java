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
    @Test
    public void quickSortMajorMinorLimitTest() {
        PriorityQueue<String, Integer> queue = new PriorityQueue<>(1);
        queue.insert(new PriorityQueueNode<>("A", 10));
        queue.quickSortMajorMinor(0);
        assertEquals("A", queue.extractMax());
        assertNull(queue.extractMax());
    }

    //Interesante
    @Test
    public void quickSortMajorMinorInterestingTest() {
        PriorityQueue<String, Integer> queue = new PriorityQueue<>(5);
        queue.insert(new PriorityQueueNode<>("A", 2));
        queue.insert(new PriorityQueueNode<>("B", 5));
        queue.insert(new PriorityQueueNode<>("C", 1));
        queue.insert(new PriorityQueueNode<>("D", 5));
        queue.insert(new PriorityQueueNode<>("E", 3));
        queue.quickSortMajorMinor(0);
        assertEquals("D", queue.extractMax());
        assertEquals("B", queue.extractMax());
    }

    //Estandar
    @Test
    public void quickSortMinorMajorStandardTest() {
        PriorityQueue<String, Integer> queue = new PriorityQueue<>(5);
        queue.insert(new PriorityQueueNode<>("A", 3));
        queue.insert(new PriorityQueueNode<>("B", 5));
        queue.insert(new PriorityQueueNode<>("C", 2));
        queue.insert(new PriorityQueueNode<>("D", 1));
        queue.insert(new PriorityQueueNode<>("E", 4));
        queue.quickSortMinorMajor(0, 4);
        assertEquals("B", queue.extractMax());
        assertEquals("E", queue.extractMax());
        assertEquals("A", queue.extractMax());
        assertEquals("C", queue.extractMax());
        assertEquals("D", queue.extractMax());
    }

    //Limites
    @Test
    public void quickSortMinorMajorLimitTest() {
        PriorityQueue<String, Integer> queue = new PriorityQueue<>(1);
        queue.insert(new PriorityQueueNode<>("A", 10));
        queue.quickSortMinorMajor(0);
        assertEquals("A", queue.extractMax());
        assertNull(queue.extractMax());
    }

    //Interesante
    @Test
    public void quickSortMinorMajorInterestingTest() {
        PriorityQueue<String, Integer> queue = new PriorityQueue<>(5);
        queue.insert(new PriorityQueueNode<>("A", 2));
        queue.insert(new PriorityQueueNode<>("B", 1));
        queue.insert(new PriorityQueueNode<>("C", 5));
        queue.insert(new PriorityQueueNode<>("D", 5));
        queue.insert(new PriorityQueueNode<>("E", 3));
        queue.quickSortMinorMajor(0);
        assertEquals("C", queue.extractMax());
        assertEquals("D", queue.extractMax());
    }

    //Estandar
    @Test
    public void isEmptyStandard() {
        PriorityQueue<String, Integer> queue = new PriorityQueue<>(5);
        assertTrue(queue.isEmpty());
    }

    //Limites
    @Test
    public void isEmptyLimit() {
        PriorityQueue<String, Integer> queue = new PriorityQueue<>(0);
        assertTrue(queue.isEmpty());
    }

    //Interesante
    @Test
    public void isEmptyInteresting() {
        PriorityQueue<String, Integer> queue = new PriorityQueue<>(5);
        queue.insert(new PriorityQueueNode<>("A", 20));
        assertFalse(queue.isEmpty());
    }

    //Estandar
    @Test
    public void occupiedSizeStandard() {
        PriorityQueue<String, Integer> queue = new PriorityQueue<>(5);
        assertEquals(0, queue.occupiedSize());
    }

    //Limites
    @Test
    public void occupiedSizeLimits() {
        PriorityQueue<String, Integer> queue = new PriorityQueue<>(3);
        queue.insert(new PriorityQueueNode<>("A", 20));
        queue.insert(new PriorityQueueNode<>("B", 25));
        queue.insert(new PriorityQueueNode<>("C", 10));
        assertEquals(3, queue.occupiedSize());
    }

    //Interesante
    @Test
    public void occuiedSizeInteresting() {
        PriorityQueue<String, Integer> queue = new PriorityQueue<>(5);
        queue.insert(new PriorityQueueNode<>("A", 20));
        queue.insert(new PriorityQueueNode<>("B", 25));
        queue.insert(new PriorityQueueNode<>("C", 10));
        queue.extractMax();
        queue.extractMax();
        assertEquals(1, queue.occupiedSize());

    }
}