package Model;

import java.util.ArrayList;

public class AnalizadorPostfijo {
    private GenericStack<Character> salida = new GenericStack<>();
    private GenericStack<Character> operadores = new GenericStack<>();
    private Archivo archivoSalida = new Archivo();

    public void convertirExpresion(ArrayList<String> listaExpresiones) throws Exception {
        // For para recorrer cada expresion en la lista
        for (String expresion : listaExpresiones) {
            // for para recorrer cada caracter de la expresion
            for (int caracter = 0; caracter < expresion.length(); caracter++) {
                this.esOperador(expresion.charAt(caracter));
            }

            //Al final se agrega en la pila de salida, lo que sobro en la pila de operadores
             agregarRestoPilaSalida();

             salida.printStack();
            System.out.println("");

             salida.clearStack();
             operadores.clearStack();

        }

        //DEBUG
        //  System.out.println("==========Pila Operadores==========");
        //  agregarRestoPilaSalida();
        //  operadores.printStackDebug();
         
        // System.out.println("\n==========Pila Salida==========");
        // salida.printStackDebug();


    }

    private void esOperador(Character caracter) {
        switch (caracter) {
            case '+':
                operadores.push(caracter);
                break;
            case '-':
                operadores.push(caracter);
                break;
            case '/':
                operadores.push(caracter);
                break;
            case '*':
                operadores.push(caracter);
                break;
            case '(':
                operadores.push(caracter);
                break;
            case ')': {
                //operadores.push(caracter);
                this.eliminarParentesis();
            }
                break;
            default:
                salida.push(caracter);
                break;
        }
    }


    private void agregarRestoPilaSalida() throws Exception{
        for(int i = 0 ; i <= this.operadores.size() ; i++){
            this.salida.push(this.operadores.peek());
            operadores.pop();
        }
    }

    private void eliminarParentesis() {
        try {
            boolean esParentesisAbierto = false;  

            Character tempChar;

            while(!esParentesisAbierto){
                
                //Obtenemos el ultimo caracter de la pila de operadores
                tempChar = operadores.peek();
                // Si el operador es un parentesis de apertura entonces lo eliminamos y rompemos la funcion eliminar parentesis
                if(tempChar == '('){
                    operadores.pop();
                    esParentesisAbierto = true;
                    break;
                }


               //System.out.println(tempChar);
               //Agregamos en la pila de salida todo lo que se encuentre en la pila operadores, hasta buscar un parentesis de apertura
                salida.push(tempChar);
                //Eliminamos elementos de la pila operadores, hasta encontrar un parentesis abierto
                operadores.pop();
            }


        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

}
