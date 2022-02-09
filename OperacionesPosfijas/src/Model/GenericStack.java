package Model;

import java.util.ArrayList;

public class GenericStack<T> {
    private int top;
    private ArrayList<T> stackArray;

    public GenericStack() {
        stackArray = new ArrayList<>();
        top = 0;
    }

    public void push(T j) {
        stackArray.add(j);
        this.top++;
    }

    public T pop() throws Exception {
        if (isEmpty()) {
            throw new Exception("No hay ningun elemento en la pila");
        } else {
            top--;
            return stackArray.remove(top);
        }
    }

    public T peek() throws Exception {
        if (isEmpty()) {
            throw new Exception("No hay elementos en el stack");
        }
        return stackArray.get(top - 1);
    }

    public void printStackDebug() {
        for (T t : stackArray) {
            System.out.print(t + "     ");
        }
    }

    public String getStringStack() {

        String stringStack = "";
        for (T t : stackArray) {
            stringStack += t;
        }

        return stringStack;
    }

    public void printStack() {
        for (T t : stackArray) {
            System.out.print(t);
        }
    }

    public T peekSpecificElement(int elementIndex) {
        return stackArray.get(elementIndex);
    }

    public T remove(int index) throws Exception {
        if (isEmpty()) {
            throw new Exception("No hay elementos en el stack (remove)");
        } else if (index > stackArray.size()) {
            throw new Exception("No existe algún elemento en ese indice");
        }
        return stackArray.remove(index);
    }

    public void pushSpecificIndex(int index, T element){
        stackArray.add(index, element);
    }

    public void cloneStack(GenericStack<T> stack){
        for(int i = 0; i < stack.size(); i++){
            this.stackArray.add((T)stack.peekSpecificElement(i));
            top++;
        }
    }

    public void convertStackToString(GenericStack<String> stackOfStrings){
        for(int i = 0; i < stackArray.size(); i++){
            stackOfStrings.push(this.stackArray.get(i).toString());
        }
        
    }

    

    public boolean isEmpty() {
        return (top == 0);
    }

    public int size() {
        return stackArray.size();
    }

    public void clearStack() {
        stackArray.clear();
    }

    

}
