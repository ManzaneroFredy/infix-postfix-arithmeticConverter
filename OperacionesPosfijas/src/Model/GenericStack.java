package Model;

import java.util.ArrayList;

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

    public void printStackDebug(){
        for (T t : stackArray) {
            System.out.print(t + "     ");
        }
    }

    public void printStack(){
        for (T t : stackArray) {
            System.out.print(t);
        }
    }

    public T peekSpecificElement(int elementIndex){
        return stackArray.get(elementIndex);
    }

    public T remove(int index) throws Exception{
        if(isEmpty()){
            throw new Exception("No hay elementos en el stack");
        }else if(index > stackArray.size()){
            throw new Exception("No existe algún elemento en ese indice");
        }
        return stackArray.remove(index);
    }

    private boolean isEmpty() {
        return (top == 0); 
    }

    public int size(){
        return stackArray.size();
    }

    public void clearStack(){
        stackArray.clear();
    }



}

