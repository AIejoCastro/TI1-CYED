package structures;

public class Queue<V> implements IQueue<V> {

    private QueueNode<V> queue[];
    private QueueNode<V> front;
    private QueueNode<V> back;

    public Queue(int size){
        queue = new QueueNode[size];
        front = null;
        back = null;
    } // Constructor

    public void delete(int index){

        if(isEmpty()){ // 1
            return; // 1
        } else {
            queue[index] = null; // 1

            for (int i = 0; i < queue.length-1; i++) { // n - 1

                queue[i] = queue[i+1]; // n - 2
            }
        }
    } // delete -> TOTAL TEMPORAL = 1 + 1 +

    @Override
    public boolean isEmpty() {
        if(queue[0]==null){
            return true;
        } else {
            return false;
        }
    } // Is empty

    @Override
    public V peek() {

        if(isEmpty()){
            return null;
        } else {
            front = queue[0];
            return queue[0].getValue();
        }
    } // Peek

    @Override
    public V poll() {

        V value;

        if(isEmpty()){
            return null;
        } else {
            int occupied = occupedSize();
            value = queue[0].getValue();
            queue[0] = null;

            for (int i = 0; i <= occupied-1; i++) {
                if(i==occupied-1){
                    queue[i]=null;
                } else {
                    queue[i] = queue[i+1];
                }
            }
        }

        return value;
    } // Poll

    @Override
    public boolean offer(QueueNode<V> item) {

        for (int i = 0; i < queue.length; i++) { // n

            if(queue[i]==null){ // n - 1
                queue[i] = item; // n - 1
                back = queue[i]; // n - 1
                return true; // n - 1
            }
        }

        return false; // 1
    } // Offer -> TOTAL TEMPORAL = n + 4(n-1) + 1 = n + 4n - 4 + 1 = | 5n - 3 |

    public int occupedSize(){

        int size = 0;

        for (int i = 0; i < queue.length; i++) {

            if(queue[i]!=null){
                size++;
            }

            if(queue[i]==null){
                break;
            }
        }

        return size;
    } // occuped size

    @Override
    public int size() {
        return queue.length;
    }

    public QueueNode<V>[] getQueue() {
        return queue;
    }

    public void setQueue(QueueNode<V>[] queue) {
        this.queue = queue;
    }

    public QueueNode<V> getFront() {
        return front;
    }

    public void setFront(QueueNode<V> front) {
        this.front = front;
    }

    public QueueNode<V> getBack() {
        return back;
    }

    public void setBack(QueueNode<V> back) {
        this.back = back;
    }

} // Queue