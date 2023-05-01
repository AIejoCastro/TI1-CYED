import org.junit.Test;
import structures.HashTable;
import static org.junit.Assert.*;

public class HashTableTest {

    //Estandar
    @Test
    public void hashStandard() {
        HashTable<String, Integer> ht = new HashTable<>();
        int hashResult = ht.hash("key");
        assertEquals(23, hashResult);
    }

    //Limite
    @Test
    public void hashLimit() {
        HashTable<String, Integer> ht = new HashTable<>();
        int hashResult = ht.hash("");
        assertEquals(0, hashResult);
    }

    //Interesante
    @Test
    public void hashInteresting() {
        HashTable<String, Integer> ht = new HashTable<>();
        int hashResult = ht.hash("key");
        int hashResult1 = ht.hash("hello");
        int hashResult2 = ht.hash("apple");
        assertEquals(23, hashResult);
        assertEquals(16, hashResult1);
        assertEquals(8, hashResult2);
    }

    //Estandar
    @Test
    public void insertOnHashTableStandard() {
        HashTable<String, Integer> ht = new HashTable<>();
        ht.insertOnHashTable("test1", 1);
        ht.insertOnHashTable("test2", 2);
        assertEquals(1, (int) ht.searchOnHashTable("test1"));
        assertEquals(2, (int) ht.searchOnHashTable("test2"));
    }

    //Limite
    @Test
    public void insertOnHashTableLimit() {
        HashTable<String, Integer> ht = new HashTable<>();
        ht.insertOnHashTable("hello", 1);
        ht.insertOnHashTable("", 2);
        assertEquals(1, (int) ht.searchOnHashTable("hello"));
        assertEquals(2, (int) ht.searchOnHashTable(""));
    }

    //Interesante
    @Test
    public void insertOnHashTableInteresting() {
        HashTable<Integer, String> ht = new HashTable<>();
        ht.insertOnHashTable(1, "one");
        ht.insertOnHashTable(55, "fifty-five");
        ht.insertOnHashTable(109, "one hundred and nine");
        assertEquals("one", ht.searchOnHashTable(1));
        assertEquals("fifty-five", ht.searchOnHashTable(55));
        assertEquals("one hundred and nine", ht.searchOnHashTable(109));
    }

    //Estandar
    @Test
    public void searchOnHashTableStandard() {
        HashTable<String, Integer> ht = new HashTable<>();
        ht.insertOnHashTable("test", 1);
        assertEquals(1, (int) ht.searchOnHashTable("test"));
    }

    //Limite
    @Test
    public void searchOnHashTableLimit() {
        HashTable<String, Integer> ht = new HashTable<>();
        ht.insertOnHashTable("null", 1);
        assertEquals(1, (int) ht.searchOnHashTable("null"));
    }

    //Interesante
    @Test
    public void searchOnHashTableInteresting() {
        HashTable<Integer, String> ht = new HashTable<>();
        ht.insertOnHashTable(1, "one");
        ht.insertOnHashTable(55, "fifty-five");
        ht.insertOnHashTable(109, "one hundred and nine");
        assertEquals("fifty-five", ht.searchOnHashTable(55));
    }
}