package structures;

public class PriorityQueue<E, K extends Comparable<K>> implements IPriorityQueue<E, K> {

    private PriorityQueueNode<E, K>[] priorityQueue;

    public PriorityQueue(int size){
        this.priorityQueue = new PriorityQueueNode[size];
    }



    @Override
    public boolean insert(PriorityQueueNode<E, K> node) {

        for (int i = 0; i < priorityQueue.length; i++) {

            if(priorityQueue[i]==null){
                priorityQueue[i] = node;
                return true;
            }
        }
        return false;
    }

    @Override
    public PriorityQueueNode<E, K> maximum() {
        if(priorityQueue.length>0){
            sortGrMin(0 );
            return priorityQueue[0];
        } else {
            return null;
        }

    }

    @Override
    public E extractMax() {
        sortGrMin(0);

        E element;

        if(isEmpty()){
            return null;
        } else {
            int occupied = occupiedSize();
            element = priorityQueue[0].getElement();
            priorityQueue[0] = null;

            for (int i = 0; i <= occupied-1; i++) {
                if(i==occupied-1){
                    priorityQueue[i] = null;
                } else {
                    priorityQueue[i] = priorityQueue[i+1];
                }
            }
        }
        return element;
    }

    public void sortGrMin(int start){
        int end = 0;

        for (int i = 0; i < priorityQueue.length; i++) {
            if(priorityQueue[i]==null){
                end = i-1;
                break;
            } else if(i==priorityQueue.length-1){
                end = priorityQueue.length-1;
            }
        }

        sortGrMin(start, end);
    }

    public void sortGrMin(int beggining, int end) {

        if (beggining >= end) return;
        PriorityQueueNode<E,K> piv = priorityQueue[beggining];
        int leftElement = beggining + 1;
        int rightElement = end;

        while (leftElement <= rightElement) {
            while (leftElement <= end && (priorityQueue[leftElement].compareTo(piv.getKey())) >= 0)  {
                leftElement++;
            }
            while (rightElement > beggining && (priorityQueue[rightElement].compareTo(piv.getKey())) < 0) {
                rightElement--;
            }
            if (leftElement < rightElement) {
                PriorityQueueNode<E, K> temp = priorityQueue[leftElement];
                priorityQueue[leftElement] = priorityQueue[rightElement];
                priorityQueue[rightElement] = temp;
            }
        }

        if (rightElement > beggining) {
            PriorityQueueNode<E, K> temp = priorityQueue[beggining];
            priorityQueue[beggining] = priorityQueue[rightElement];
            priorityQueue[rightElement] = temp;
        }
        sortGrMin(beggining, rightElement - 1);
        sortGrMin(rightElement + 1, end);
    }


    public void sortMinGr(int inicio){
        int fin = 0;

        for (int i = 0; i < priorityQueue.length; i++) {
            if(priorityQueue[i]==null){
                fin = i-1;
                break;
            } else if(i==priorityQueue.length-1){
                fin = priorityQueue.length-1;
            }
        }

        sortMinGr(inicio, fin);
    }

    public void sortMinGr(int begin, int end) {

        if (begin >= end) return;

        PriorityQueueNode<E,K> pivote = priorityQueue[begin];
        int leftElement = begin + 1;
        int rightElement = end;


        while (leftElement <= rightElement) {
            while (leftElement <= end && (priorityQueue[leftElement].compareTo(pivote.getKey())<0) ) {
                leftElement++;
            }
            while (rightElement > begin && (priorityQueue[rightElement].compareTo(pivote.getKey()) >= 0) ) {
                rightElement--;
            }
            if (leftElement < rightElement) {
                PriorityQueueNode<E, K> temp = priorityQueue[leftElement];
                priorityQueue[leftElement] = priorityQueue[rightElement];
                priorityQueue[rightElement] = temp;
            }
        }

        if (rightElement > begin) {
            PriorityQueueNode<E, K> temp = priorityQueue[begin];
            priorityQueue[begin] = priorityQueue[rightElement];
            priorityQueue[rightElement] = temp;
        }
        sortMinGr(begin, rightElement - 1);
        sortMinGr(rightElement + 1, end);
    }

    public boolean isEmpty() {

        if(priorityQueue.length==0){
            return true;
        }

        if(priorityQueue[0]==null){
            return true;
        } else {
            return false;
        }
    }

    public int occupiedSize(){

        int size = 0;

        for (int i = 0; i < priorityQueue.length; i++) {

            if(priorityQueue[i]!=null){
                size++;
            }

            if(priorityQueue[i]==null){
                break;
            }
        }

        return size;
    }
    @Override
    public void delete(int index){

        if(isEmpty()){
            return;
        } else {
            priorityQueue[index] = null; // 1

            for (int i = index; i < priorityQueue.length-1; i++) {

                priorityQueue[i] = priorityQueue[i+1];
            }
        }
    }
}