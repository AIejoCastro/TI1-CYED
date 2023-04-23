package structures;

import java.util.*;

public class Stack <V> implements IStack <V>{

    private ArrayList<StackNode<V>> stack;
    private StackNode<V> top;

    public Stack(){
        stack = new ArrayList<>();
        this.top = null;
    }

    @Override
    public void push(V value) {
        top = new StackNode<>(value);
        stack.add(top);
    }

    @Override
    public StackNode<V> pop() {

        if(stack.size()==0){
            return null;
        } else {
            StackNode<V> topAux = stack.get(stack.size()-1);
            stack.remove(stack.size()-1);

            if(stack.size()>0){
                top = stack.get(stack.size()-1);
            } else {
                top = null;
            }

            return topAux;
        }
    }

    @Override
    public StackNode<V> top() {
        return top;
    }

    @Override
    public boolean isEmpty() {
        return (stack.size() == 0);
    }

    public ArrayList<StackNode<V>> getStack() {
        return stack;
    }

    public void setStack(ArrayList<StackNode<V>> stack) {
        this.stack = stack;
    }

    public StackNode<V> getTop() {
        return top;
    }

    public void setTop(StackNode<V> top) {
        this.top = top;
    }
}
