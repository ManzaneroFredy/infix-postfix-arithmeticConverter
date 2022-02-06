package Model;

import java.util.ArrayList;

public class GenericQueue<E>{
    private ArrayList<E> queArray;
    private int front;
    private int rear;

    public GenericQueue() {
        this.queArray = new ArrayList<E>();
        front = 0;
        rear = -1;
    }

    public void insert(E j) {
        queArray.add(j);
        rear++;
    }

    public E remove() throws Exception{
        if(isEmpty()){
            throw new Exception("La cola esta vacia");
        }else{
            E temp = queArray.remove(front);
            rear--;
            return temp;
        }
       
    }

    public E peekFront() {
        return queArray.get(front);
    }

    public boolean isEmpty() {
        return (rear+1==front);
    }

    public int size() {
        return queArray.size();
    }
}