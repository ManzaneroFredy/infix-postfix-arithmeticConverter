package Model;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Archivo {
    private  String rutaArchivo;
    private BufferedReader lectorDocumento = null;

    public void obtenerRutaArchivo(){
        System.out.println("Porfavor, Ingrese la dirección absoluta del archivo");
        System.out.println("Ejemplo: E:/Archivos/Semestre 4/Estructura de datos/Unidad/ADA3/OperacionesPosfijas/src/Docs/exp_postfijas.txt");
        Scanner entrada = new Scanner(System.in);
        this.rutaArchivo = entrada.nextLine();
        entrada.close();
    }

    public void obtenerExpresiones(ArrayList<String> listaExpresiones) throws IOException {
        try {

            lectorDocumento = new BufferedReader(new FileReader(this.rutaArchivo));
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

    public void generarArchivoSalida(ArrayList<String> listaExpresiones, ArrayList<Integer> listaResultados) throws IOException {
        String direccionSalida = "src/Docs/exp_postfijas.txt";
        FileOutputStream archivoTxt = new FileOutputStream(direccionSalida);
        OutputStreamWriter salida = new OutputStreamWriter(archivoTxt, "UTF-8");


        for(int i = 0; i < listaExpresiones.size(); i++){
            salida.write("Expr: " + listaExpresiones.get(i) + "; Eval: " + listaResultados.get(i) + "\n");
        }

        salida.close();
    }

}
