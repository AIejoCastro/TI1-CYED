import org.junit.Test;
import structures.Hashtable;
import structures.HashtableNode;
import static org.junit.Assert.*;


public class HashTableTest {

    private Hashtable<String, Integer> hashtable;

    public void setupStage1(){
        hashtable = new Hashtable<>(100000);
    } // Stage 1


    // INSERT TESTS
    @Test
    public void testInsert(){
        setupStage1();

        HashtableNode<String, Integer> node = new HashtableNode<>("1112388100", 20);

        hashtable.insert(node, node.getKey());

        assertEquals(1, hashtable.getOccupiedSize());
    } // test insert 1

    @Test
    public void testInsert2(){
        setupStage1();

        HashtableNode<String, Integer> node = new HashtableNode<>("1112388100", 20);
        HashtableNode<String, Integer> node2 = new HashtableNode<>("1112388101", 21);
        HashtableNode<String, Integer> node3 = new HashtableNode<>("1112388102", 22);
        HashtableNode<String, Integer> node4 = new HashtableNode<>("1112388103", 23);
        HashtableNode<String, Integer> node5 = new HashtableNode<>("1112388104", 24);

        hashtable.insert(node2, node2.getKey());
        hashtable.insert(node, node.getKey());
        hashtable.insert(node3, node3.getKey());
        hashtable.insert(node4, node4.getKey());
        hashtable.insert(node5, node5.getKey());

        assertEquals(5, hashtable.getOccupiedSize());
    } // test insert 2

    @Test
    public void testInsert3(){
        setupStage1();

        assertEquals(0, hashtable.getOccupiedSize());
    } // test insert 3
    // INSERT TESTS

    // SEARCH TESTS
    @Test
    public void testSearch(){
        setupStage1();

        HashtableNode<String, Integer> node = new HashtableNode<>("1112388100", 20);

        hashtable.insert(node, node.getKey());

        int foundValue = hashtable.search("1112388100");

        assertEquals(1, hashtable.getOccupiedSize());
        assertEquals(20, foundValue);
    } // test search 1

    @Test
    public void testSearch2(){
        setupStage1();

        HashtableNode<String, Integer> node = new HashtableNode<>("1112388100", 20);
        HashtableNode<String, Integer> node2 = new HashtableNode<>("1112388101", 21);
        HashtableNode<String, Integer> node3 = new HashtableNode<>("1112388102", 22);
        HashtableNode<String, Integer> node4 = new HashtableNode<>("1112388103", 23);
        HashtableNode<String, Integer> node5 = new HashtableNode<>("1112388104", 24);

        hashtable.insert(node2, node2.getKey());
        hashtable.insert(node, node.getKey());
        hashtable.insert(node3, node3.getKey());
        hashtable.insert(node4, node4.getKey());
        hashtable.insert(node5, node5.getKey());

        int foundValue = hashtable.search("1112388104");

        assertEquals(5, hashtable.getOccupiedSize());
        assertEquals(24, foundValue);
    } // test search 2

    @Test
    public void testSearch3(){
        setupStage1();

        assertNull(hashtable.search("1112388100"));
    } // test search 3
    // SEARCH TESTS

    // DELETE TESTS
    @Test
    public void testDelete(){
        setupStage1();

        HashtableNode<String, Integer> node = new HashtableNode<>("1112388100", 20);

        hashtable.insert(node, node.getKey());

        assertEquals(1, hashtable.getOccupiedSize());

        hashtable.delete("1112388100");

        assertEquals(0, hashtable.getOccupiedSize());
        assertNull(hashtable.search("1112388100"));
    } // test Delete 1

    @Test
    public void testDelete2(){
        setupStage1();

        HashtableNode<String, Integer> node = new HashtableNode<>("1112388100", 20);
        HashtableNode<String, Integer> node2 = new HashtableNode<>("1112388101", 21);
        HashtableNode<String, Integer> node3 = new HashtableNode<>("1112388102", 22);
        HashtableNode<String, Integer> node4 = new HashtableNode<>("1112388103", 23);
        HashtableNode<String, Integer> node5 = new HashtableNode<>("1112388104", 24);

        hashtable.insert(node3, node3.getKey());
        hashtable.insert(node, node.getKey());
        hashtable.insert(node2, node2.getKey());
        hashtable.insert(node4, node4.getKey());
        hashtable.insert(node5, node5.getKey());

        assertEquals(5, hashtable.getOccupiedSize());

        hashtable.delete("1112388101");
        hashtable.delete("1112388103");

        assertEquals(3, hashtable.getOccupiedSize());

        assertFalse(hashtable.containsKey("1112388101"));
        assertNull(hashtable.search("1112388103"));
    } // test Delete 2

    @Test
    public void testDelete3(){
        setupStage1();

        HashtableNode<String, Integer> node = new HashtableNode<>("1112388100", 20);

        hashtable.insert(node, node.getKey());

        hashtable.delete("00000");

        int foundValue = hashtable.search("1112388100");

        assertEquals(20 ,foundValue);
    } // test Delete 3
    // DELETE TESTS

    // // CONTAINS KEY TESTS
    @Test
    public void testContainsKey(){
        setupStage1();

        HashtableNode<String, Integer> node = new HashtableNode<>("1112388100", 20);

        hashtable.insert(node, node.getKey());

        assertTrue(hashtable.containsKey("1112388100"));
    } // test Delete 1

    @Test
    public void testContainsKey2(){
        setupStage1();

        HashtableNode<String, Integer> node = new HashtableNode<>("1112388100", 20);
        HashtableNode<String, Integer> node2 = new HashtableNode<>("1112388101", 21);
        HashtableNode<String, Integer> node3 = new HashtableNode<>("1112388102", 22);
        HashtableNode<String, Integer> node4 = new HashtableNode<>("1112388103", 23);
        HashtableNode<String, Integer> node5 = new HashtableNode<>("1112388104", 24);

        hashtable.insert(node2, node2.getKey());
        hashtable.insert(node, node.getKey());
        hashtable.insert(node3, node3.getKey());
        hashtable.insert(node4, node4.getKey());
        hashtable.insert(node5, node5.getKey());


        assertTrue(hashtable.containsKey("1112388104"));
    } // test Delete 2

    @Test
    public void testContainsKey3(){
        setupStage1();

        assertFalse(hashtable.containsKey("1112388100"));
    } // test Delete 3
    // CONTAINS KEY TESTS

    // CONTAINS VALUE TESTS
    @Test
    public void testContainsValue(){
        setupStage1();

        HashtableNode<String, Integer> node = new HashtableNode<>("1112388100", 20);

        hashtable.insert(node, node.getKey());

        assertTrue(hashtable.containsValue(20));
    } // test Delete 1

    @Test
    public void testContainsValue2(){
        setupStage1();

        HashtableNode<String, Integer> node = new HashtableNode<>("1112388100", 20);
        HashtableNode<String, Integer> node2 = new HashtableNode<>("1112388101", 21);
        HashtableNode<String, Integer> node3 = new HashtableNode<>("1112388102", 22);
        HashtableNode<String, Integer> node4 = new HashtableNode<>("1112388103", 23);
        HashtableNode<String, Integer> node5 = new HashtableNode<>("1112388104", 24);

        hashtable.insert(node2, node2.getKey());
        hashtable.insert(node, node.getKey());
        hashtable.insert(node3, node3.getKey());
        hashtable.insert(node4, node4.getKey());
        hashtable.insert(node5, node5.getKey());

        assertTrue(hashtable.containsValue(21));
    } // test Delete 2

    @Test
    public void testContainsValue3(){
        setupStage1();

        assertFalse(hashtable.containsValue(20));
    } // test Delete 3
    // CONTAINS VALUE TESTS
}