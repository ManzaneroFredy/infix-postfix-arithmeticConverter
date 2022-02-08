package Model;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class Archivo {
    private static final String RUTA_ARCHIVO = "src/Docs/exp_infijas.txt";
    private BufferedReader lectorDocumento = null;

    public void obtenerExpresiones(ArrayList<String> lineasArchivo) throws IOException {
        try {
            lectorDocumento = new BufferedReader(new FileReader(RUTA_ARCHIVO));
            String linea = lectorDocumento.readLine();
            while (linea != null) {
                lineasArchivo.add(linea);
                linea = lectorDocumento.readLine();
            }

        } catch (Exception ex) {
            System.out.println("problema detectado " + ex);

        } finally {
            if (null != lectorDocumento) {
                lectorDocumento.close();
            }

        }
    }

    public void generarArchivoSalida(GenericStack<Character> pila) throws IOException{
        String direccionSalida = "src/Docs/salida.txt";
        FileOutputStream archivoTxt = new FileOutputStream(direccionSalida);
        OutputStreamWriter salida = new OutputStreamWriter(archivoTxt, "UTF-8");

        for(int i = 0 ; i <  pila.size(); i++){
            if(i !=(pila.size() - 1)){
                salida.write(pila.peekSpecificElement(i));
            }else{
                salida.write(pila.peekSpecificElement(i) + "\n");
            }
        }
        salida.close();
    }

}
