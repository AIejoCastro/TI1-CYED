package structures;


public class Hashtable<K, V> implements IHashtable<K, V> {

    private HashtableNode<K,V>[] hashtable;
    private int occupiedSize;

    public Hashtable(int size) {
        hashtable = new HashtableNode[size];
        occupiedSize = 1;
    } // Constructor

    @Override
    public String toString(){
        String msg = "";

        for(HashtableNode<K,V> node : hashtable) {

            if(node!=null){
                if(node.getNext()!=null){
                    msg += searchLastMsg(node);
                } else {
                    msg += node.getValue().toString()+"\n";
                }
            }
        }

        return msg;
    }

    public String searchLastMsg(HashtableNode<K, V> node) {

        String msg = "";

        if(node!=null){
            msg += node.getValue().toString()+"\n";
            searchLastMsg(node.getNext());
        } else {
            return msg;
        }

        return msg;
    } // search last

    @Override
    public int insert(HashtableNode<K, V> hashtableNode, K key) {

        int slot = hashFunction(key, hashtable.length);

        if (hashtable[slot] == null) {
            hashtable[slot] = hashtableNode;
            occupiedSize++;
            return slot;

        } else {
            chaining(hashtableNode, slot);
            occupiedSize++;
            return slot;
        }
    } // Insert

    @Override
    public V search(K key) {

        V value = null;

        for (int i = 0; i < hashtable.length; i++) {

            if (hashtable[i] != null) {
                if (hashtable[i].getNext() != null) {

                    if (searchLast(hashtable[i], key) != null) {
                        value = searchLast(hashtable[i], key);
                        break;
                    }

                } else {

                    if (hashtable[i].getKey().equals(key)) {
                        return hashtable[i].getValue();

                    } // if

                } // if else
            }
        } // for

        return value;
    } // search

    public V searchLast(HashtableNode<K, V> node, K key) {

        if(node!=null){
            if (node.getKey().equals(key)) {
                return node.getValue();
            } else if (node.getNext() == null) {
                return null;
            } else {
                return searchLast(node.getNext(), key);
            }
        } else {
            return null;
        }
    } // search last


    @Override
    public void delete(K key) {

        for (int i = 0; i < hashtable.length; i++) {

            if(hashtable[i]!=null){
                if (hashtable[i].getNext() != null) {

                    if (searchLastDelete(hashtable[i], key)) {
                        occupiedSize--;
                        break;
                    }

                } else {

                    if (hashtable[i].getKey().equals(key)) {
                        hashtable[i] = null;
                        occupiedSize--;
                        break;
                    } // if

                } // if else
            }
        } // for
    } // delete

    public boolean searchLastDelete(HashtableNode<K, V> node, K key) {

        if(node!=null){
            if (node.getKey().equals(key)) {

                if(node.getNext()!=null){
                    node.getPrevious().setNext(node.getNext());
                    node.getNext().setPrevious(node.getPrevious());
                } else {
                    node.getPrevious().setNext(null);
                }

                node.setNext(null);
                node.setPrevious(null);
                return true;

            } else if (node.getNext() == null) {
                return false;

            } else {
                return searchLastDelete(node.getNext(), key);
            }
        } else {
            return false;
        }
    } // search last delete

    @Override
    public int hashFunction(K key, int i) {

        double kHash = Math.abs(key.hashCode());
        double a = Math.sqrt(5 / 2);

        return (int) Math.floor(i * ((kHash * a) % 1));
    } // Hash function

    public void chaining(HashtableNode<K, V> node, int slot){
        chaining(node, hashtable[slot]);
    }

    @Override
    public void chaining(HashtableNode<K, V> node, HashtableNode<K, V> current) {
        if(node!=null && current!=null){
            if (current.getNext() == null) {
                current.setNext(node);
                node.setPrevious(current);

            } else {
                chaining(node, current.getNext());
            }
        }

    } // Chaining

    @Override
    public boolean containsKey(K key) {

        for (int i = 0; i < hashtable.length; i++) {

            if (hashtable[i] != null) {
                if (hashtable[i].getNext() != null) {

                    if (searchLast(hashtable[i], key) != null) {
                        return true;
                    }

                } else {

                    if (hashtable[i].getKey().equals(key)) {
                        return true;
                    } // if

                } // if else
            }
        } // for

        return false;
    } // contains key

    @Override
    public boolean containsValue(V value) {
        for (int i = 0; i < hashtable.length; i++) {

            if (hashtable[i] != null) {
                if (hashtable[i].getNext() != null) {

                    if (searchLastValue(hashtable[i], value) != null) {
                        return true;
                    }

                } else {

                    if (hashtable[i].getValue().equals(value)) {
                        return true;
                    } // if

                } // if else
            }
        } // for

        return false;
    }

    public V searchLastValue(HashtableNode<K, V> node, V value) {

        if (node.getValue().equals(value)) {
            return node.getValue();
        } else if (node.getNext() == null) {
            return null;
        } else {
            searchLastValue(node.getNext(), value);
        }

        return null;
    } // search last

    @Override
    public int occupedSize() {
        return (occupiedSize -1);
    }

    @Override
    public int size() {
        return hashtable.length;
    }

    public HashtableNode<K, V>[] getHashtable() {
        return hashtable;
    }

    public void setHashtable(HashtableNode<K, V>[] hashtable) {
        this.hashtable = hashtable;
    }

    public int getOccupiedSize() {
        return occupiedSize - 1;
    }

    public void setOccupiedSize(int occupiedSize) {
        this.occupiedSize = occupiedSize;
    }
}