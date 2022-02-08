package Model;

import java.util.ArrayList;

public class AnalizadorPosfijo {
    private GenericStack<Character> salida = new GenericStack<>();
    private GenericStack<Character> operadores = new GenericStack<>();
    private GenericStack<Character> expresionPostFija = new GenericStack<>();
    private Archivo archivoSalida = new Archivo();

    public void obtenerExpresion(ArrayList<String> lineasArchivo) throws Exception {
        for (String string : lineasArchivo) {
            for (int i = 0; i < string.length(); i++) {
                this.esOperador(string.charAt(i));
            }
            
            for (int j = 0; j < salida.size(); j++) {
                expresionPostFija.push(salida.peekSpecificElement(j));
            }
    
            for (int j = 0; j < operadores.size(); j++) {
                expresionPostFija.push(operadores.peekSpecificElement(j));
            }
    
            archivoSalida.generarArchivoSalida(expresionPostFija);
        }

        
        salida.recorrerElementos();
        operadores.recorrerElementos();
    }

    private void esOperador(Character caracter) {
        switch (caracter) {
            case '+': {
                int i = 0;
                if (verificarMismaJerarquia('-', i)) {
                    try {
                        operadores.push(caracter);
                        salida.push(operadores.remove(i));
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }
                } else {
                    operadores.push(caracter);
                }
            }
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
                operadores.push(caracter);
                this.eliminarParentesis();
            }
                break;
            default:
                salida.push(caracter);
                break;
        }
    }

    private boolean verificarMismaJerarquia(Character caracter, int i) {
        for (i = 0; i < this.operadores.size(); i++) {
            if (caracter == operadores.peekSpecificElement(i)) {
                return true;
            }
        }
        return false;
    }

    private void eliminarParentesis() {
        try {
            int i;
            int contador = 0;
            for (i = operadores.size() - 2; i >= 0; i--) {
                if (operadores.peekSpecificElement(i) != '(') {
                    contador++;
                } else {
                    break;
                }
            }

            if (i != 0) {
                for (int j = 0; j < contador; j++) {
                    try {
                        operadores.pop();
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }
                }
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
