package Model;

import java.util.ArrayList;

public class AnalizadorPostfijo {
    private GenericStack<Character> salida = new GenericStack<>();
    private GenericStack<Character> operadores = new GenericStack<>();
    private Archivo archivoSalida = new Archivo();

    private ArrayList<String> listaExpresionesSalida = new ArrayList<>();
    private ArrayList<Integer> listaResultadosSalida = new ArrayList<>();

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
            //salida.printStack();
            try{
                listaResultadosSalida.add(evaluarExpresiones(salida));
            }catch(Exception ex){
                System.err.println(ex.getMessage());
                
            }
            
            
            
            //Se envia la axpresion POSTFIJA a la lista de expresiones
            listaExpresionesSalida.add(salida.getStringStack());

            //System.out.println(evaluarPostFija(salida));

            salida.clearStack();
            operadores.clearStack();
        }

        //Despues de convertir todas las expresiones se envia la lista y se genera el archivo de salida
        archivoSalida.generarArchivoSalida(listaExpresionesSalida, listaResultadosSalida);

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
            case '^':
                operadores.push(caracter);
                break;
            default:
                salida.push(caracter);
                break;
        }
    }

    private boolean elSimboloEsOperador(String caracter) {
        switch (caracter) {
            case "+":
                return true;
            case "-":
                return true;
            case "/":
                return true;
            case "*":
                return true;
            case "^":
                return true;
            default:{
                return false;
            }
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
        if (op.equals("^"))
            return (num1 ^ num2);
        return 0;
    }


    private int evaluarExpresiones(GenericStack<Character> salida) throws Exception{
        //Se crea una pila de string temporales 
        GenericStack<String> temp = new GenericStack<>();

        //Se convierte la pila de caracteres de salida a una pila de strings.
        salida.convertStackToString(temp);

        //DEBUG: temp.printStackDebug();
        //DEBUG: System.out.println(temp.size());

        int operacion = 0, tamaño = temp.size(), i = 0;

        //Se hace un loop hasta obtener una pila de string con tamaño 1
        while(tamaño > 1){
            //Se evalua si cuando hay un operador en la string de entrada en forma postfija
            if(elSimboloEsOperador(temp.peekSpecificElement(i))){

                //Para realizar una operación se hace de esta forma: operacion = <num> <operador> <num> 
                //Por lo tanto, para realizar la operación cuando se llega a un operador, se retrocede dos lugares de la pila
                operacion = evaluar(temp.remove(i),temp.remove(i-1), temp.remove(i-2));

                //DEBUG System.out.println("Valor operacion: "  + operacion);

                //Una vez que se hace la operación se inserta en la posición del primer <num>
                temp.pushSpecificIndex((i - 2),String.valueOf(operacion));

                //DEBUG temp.printStackDebug();

                //Se actualiza el tamaño de la pila, debido a que como se eliminaron elementos, el tamaño cambia
                tamaño = temp.size();

                //se reinicia el indice: i
                i = 0;
            }
            i++;
        }
        
        return operacion;
    }

}
