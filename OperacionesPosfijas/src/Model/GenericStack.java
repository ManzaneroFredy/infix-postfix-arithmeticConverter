package Model;

import java.util.ArrayList;
import java.util.List;

import javax.sound.sampled.SourceDataLine;

public class GenericStack<T> {
    private int maxSize;
    private int top;
    private ArrayList<T> stackArray;
    
     
    public GenericStack() {
        stackArray = new ArrayList<>();
        top = 0;
    }

    public void push(T j){
       stackArray.add(j);
       this.top++;
    }
    
    public T pop() throws Exception{
        if(isEmpty()){
            throw new Exception("No hay ningun elemento en la pila");
        }else{
            top--;
            return stackArray.remove(top); 
        }
    }
     
    public T peek() throws Exception{
        if(isEmpty()){
            throw new Exception("No hay elementos en el stack");
        }
        return stackArray.get(top -1); 
    }

    public void recorrerElementos(){
        for (T t : stackArray) {
            System.out.println(t);
        }
    }

    public T peekSpecificElement(int elementIndex){
        return stackArray.get(elementIndex);
    }

    public T remove(int index) throws Exception{
        if(isEmpty()){
            throw new Exception("No hay elementos en el stack");
        }else if(index ){
            
        }
        return stackArray.remove(index);
       
    }

    private boolean isEmpty() {
        return (top == 0); 
    }

    public int size(){
        return stackArray.size();
    }

}

