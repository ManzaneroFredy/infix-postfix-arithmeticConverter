package Test;

import java.util.ArrayList;

import Model.AnalizadorPostfijo;
import Model.Archivo;

public class InfijoAPostfijoApp {
    public static void main(String[] args) throws Exception {
        Archivo archivo = new Archivo();
        ArrayList<String> listaExpresiones = new ArrayList<>();
        AnalizadorPostfijo analizador = new AnalizadorPostfijo();

        archivo.obtenerRutaArchivo();
        archivo.obtenerExpresiones(listaExpresiones);
        analizador.convertirExpresion(listaExpresiones);


        
    }
}
