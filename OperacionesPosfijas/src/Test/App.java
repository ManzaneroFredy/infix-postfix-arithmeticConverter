package Test;

import java.util.ArrayList;
import java.util.Stack;

import Model.AnalizadorPosfijo;
import Model.Archivo;
import Model.GenericStack;

public class App {
    public static void main(String[] args) throws Exception {
        Archivo archivo = new Archivo();
        ArrayList<String> lineasArchivo = new ArrayList<>();
        AnalizadorPosfijo analizador = new AnalizadorPosfijo();
        Stack<Integer> pila = new Stack<>();
        
        GenericStack<Integer> pilaPrueba = new GenericStack<>();

        pilaPrueba.push(10);
        pilaPrueba.push(10);
        pilaPrueba.push(10);
        pilaPrueba.push(10);
        pilaPrueba.remove(0);


        archivo.obtenerExpresiones(lineasArchivo);
        analizador.obtenerExpresion(lineasArchivo);


        
    }
}
