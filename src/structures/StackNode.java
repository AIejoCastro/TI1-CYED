package structures;

public class StackNode <V> {

    private V value;

    public StackNode(V value){
        this.value = value;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

}
