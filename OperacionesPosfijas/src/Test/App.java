package Test;

import java.util.ArrayList;

import Model.AnalizadorPosfijo;
import Model.Archivo;

public class App {
    public static void main(String[] args) throws Exception {
        Archivo archivo = new Archivo();
        ArrayList<String> lineasArchivo = new ArrayList<>();
        AnalizadorPosfijo analizador = new AnalizadorPosfijo();

        archivo.obtenerExpresiones(lineasArchivo);
        analizador.obtenerExpresion(lineasArchivo);


        
    }
}
