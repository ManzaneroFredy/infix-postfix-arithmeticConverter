package Model;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class Archivo {
    private static final String RUTA_ARCHIVO = "OperacionesPosfijas/src/Docs/exp_infijas.txt";
    private BufferedReader lectorDocumento = null;

    public void obtenerExpresiones(ArrayList<String> listaExpresiones) throws IOException {
        try {

            lectorDocumento = new BufferedReader(new FileReader(RUTA_ARCHIVO));
            String linea = lectorDocumento.readLine();
            while (linea != null) {
                String[] values = linea.split(";");
                for (int i = 0; i < values.length; i++) {
                    listaExpresiones.add(values[i]);
                }
                linea = lectorDocumento.readLine();
            }

        } catch (Exception ex) {
            System.out.println("Problema detectado " + ex);

        } finally {
            if (null != lectorDocumento) {
                lectorDocumento.close();
            }

        }
    }

    public void generarArchivoSalida(GenericStack<Character> pila) throws IOException {
        String direccionSalida = "OperacionesPosfijas/src/Docs/salida.txt";
        FileOutputStream archivoTxt = new FileOutputStream(direccionSalida);
        OutputStreamWriter salida = new OutputStreamWriter(archivoTxt, "UTF-8");

        for (int i = 0; i < pila.size(); i++) {
            if (i != pila.size() - 1) {
                salida.write(pila.peekSpecificElement(i) + "\n");
            } else {
                salida.write(pila.peekSpecificElement(i));
            }
        }
        salida.close();
    }

}
