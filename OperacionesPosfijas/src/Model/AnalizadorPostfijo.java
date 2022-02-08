package Model;

import java.util.ArrayList;

public class AnalizadorPostfijo {
    private GenericStack<Character> salida = new GenericStack<>();
    private GenericStack<Character> operadores = new GenericStack<>();
    private GenericStack<String> evalua = new GenericStack<String>();
    private Archivo archivoSalida = new Archivo();

    private ArrayList<String> listaExpresionesSalida = new ArrayList<>();

    public void convertirExpresion(ArrayList<String> listaExpresiones) throws Exception {
        // For para recorrer cada expresion en la lista
        for (String expresion : listaExpresiones) {
            // for para recorrer cada caracter de la expresion
            for (int caracter = 0; caracter < expresion.length(); caracter++) {
                this.esOperador(expresion.charAt(caracter));
            }

            // Al final se agrega en la pila de salida, lo que sobro en la pila de
            // operadores
            agregarRestoPilaSalida();

            //Se imprime la salida en consola
            salida.printStack();
            System.out.println("");
            //Se envia la axpresion POSTFIJA a la lista de expresiones
            listaExpresionesSalida.add(salida.getStringStack());

            //System.out.println(evaluarPostFija(salida));

            salida.clearStack();
            operadores.clearStack();
        }

        //Despues de convertir todas las expresiones se envia la lista y se genera el archivo de salida
        archivoSalida.generarArchivoSalida(listaExpresionesSalida);

        // DEBUG
        // System.out.println("==========Pila Operadores==========");
        // agregarRestoPilaSalida();
        // operadores.printStackDebug();

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
                // operadores.push(caracter);
                this.eliminarParentesis();
            }
                break;
            default:
                salida.push(caracter);
                break;
        }
    }

    private void agregarRestoPilaSalida() throws Exception {
        for (int i = 0; i <= this.operadores.size(); i++) {
            this.salida.push(this.operadores.peek());
            operadores.pop();
        }
    }

    private void eliminarParentesis() {
        try {
            boolean esParentesisAbierto = false;

            Character tempChar;

            while (!esParentesisAbierto) {

                // Obtenemos el ultimo caracter de la pila de operadores
                tempChar = operadores.peek();
                // Si el operador es un parentesis de apertura entonces lo eliminamos y rompemos
                // la funcion eliminar parentesis
                if (tempChar == '(') {
                    operadores.pop();
                    esParentesisAbierto = true;
                    break;
                }

                // System.out.println(tempChar);
                // Agregamos en la pila de salida todo lo que se encuentre en la pila
                // operadores, hasta buscar un parentesis de apertura
                salida.push(tempChar);
                // Eliminamos elementos de la pila operadores, hasta encontrar un parentesis
                // abierto
                operadores.pop();
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    private String evaluarPostFija(GenericStack<Character> expresion) throws Exception {
        GenericStack<String> E = new GenericStack<String>(); // Pila entrada
        GenericStack<String> P = new GenericStack<String>(); // Pila de operandos

        // Añadir post (array) a la Pila de entrada (E)
        for (int i = 0; i < expresion.size(); i++) {

            E.push(String.valueOf(expresion.peekSpecificElement(i)));
        }

        try {
            String operadores = "+-*/%";
            while (!E.isEmpty()) {
                if (operadores.contains("" + E.peek())) {
                    P.push(evaluar(E.pop(), P.pop(), P.pop()) + "");
                } else {
                    P.push(E.pop());
                }
            }

           

            return "A";
        } catch (Exception e) {
            return "E";
        }
    }

    private static int evaluar(String op, String n2, String n1) {
        int num1 = Integer.parseInt(n1);
        int num2 = Integer.parseInt(n2);
        if (op.equals("+"))
            return (num1 + num2);
        if (op.equals("-"))
            return (num1 - num2);
        if (op.equals("*"))
            return (num1 * num2);
        if (op.equals("/"))
            return (num1 / num2);
        if (op.equals("%"))
            return (num1 % num2);
        return 0;
    }

}
