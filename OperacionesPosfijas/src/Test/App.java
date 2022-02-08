package Test;

import java.util.ArrayList;

import Model.AnalizadorPostfijo;
import Model.Archivo;

public class App {
    public static void main(String[] args) throws Exception {
        Archivo archivo = new Archivo();
        ArrayList<String> listaExpresiones = new ArrayList<>();
        AnalizadorPostfijo analizador = new AnalizadorPostfijo();


        archivo.obtenerExpresiones(listaExpresiones);

        // System.out.println("=======EXPRESIONES CARGADAS=======");
        // for(String linea : listaExpresiones){
        //     System.out.println(linea);
        // }
        // System.out.println("");
        analizador.convertirExpresion(listaExpresiones);


        
    }
}
