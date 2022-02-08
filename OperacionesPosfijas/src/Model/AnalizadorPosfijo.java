package Model;

import java.util.ArrayList;

public class AnalizadorPosfijo {
    private GenericStack<Character> salida = new GenericStack<>();
    private GenericStack<Character> operadores = new GenericStack<>();
    private Archivo archivoSalida = new Archivo();
    private boolean operadoresFirstKey;
    private boolean operadoresSecondKey;
    
    public void obtenerExpresion(ArrayList<String> lineasArchivo) throws Exception{
       for (String string : lineasArchivo) {
           for(int i = 0; i < string.length(); i++){
               this.esOperador(string.charAt(i));
           }
       }
       archivoSalida.generarArchivoSalida(salida);
       salida.recorrerElementos();
       operadores.recorrerElementos();
    }


    private void esOperador(Character caracter){
        switch (caracter) {
            case '+':{
                operadores.push(caracter);
                /*
                if(estaEnlaMismaJerarquia(caracter, operadoresSecondKey)){

                }
                */
            }
            break;
            case '-': operadores.push(caracter);
            break;
            case '/': operadores.push(caracter);
            break;
            case '*': operadores.push(caracter);
            break;
            case '(': operadores.push(caracter);
            break;
            case ')':{
                operadores.push(caracter);
                this.eliminarParentesis();
            } 
            break;
            default: salida.push(caracter);
            break;
        }
    }
    /*
    private boolean estaEnlaMismaJerarquia(Character caracter, boolean esLaMismaJerarquia){
        if((caracter == '+') || (caracter == '-') && (!operadoresSecondKey)){
            
        }
    }
    */

    private void eliminarParentesis(){
        try{
            int i;
            int contador = 0;
            for(i = operadores.size() - 2; i >= 0; i--){
                if(operadores.peekSpecificElement(i) != '('){
                    contador++;
                }else{
                    break;
                }
            }

            if(i != 0){
                for(int j = 0; j < contador; j++){
                    try{
                        operadores.pop();
                    }catch(Exception ex){
                        System.out.println(ex.getMessage());
                    }
                }
            }
            

        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }
}
