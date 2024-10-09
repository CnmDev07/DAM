/*) (10 puntos) Crea un programa RecuentoApariciones que permita lanzar una serie de tareas, a partir de
la información recibida por la línea de comandos. Este programa debe c cuantas letras hay en una
frase escrita un fichero y almacenar la cuenta total en otro fichero, para lo cual:
a) Estas tareas deben ser realizadas por procesos adicionales que se ejecutarían concurrentemente.
Concretamente, el programa debe lanzar subprocesos, donde cada uno de ellos se ocupará de c
una letra concreta (que puede ser minúscula o mayúscula). Cada subproceso que cuenta una letra
deberá dejar el resultado en un fichero o en la salida estándar según corresponda.
El programa padre se ocupará de recuperar los resultados, sumar todos los subtotales y almacenar
el resultado final en un fichero.
La línea de comandos de RecuentoApariciones sería:
• -cp ruta representa la variable de entorno CLASSPATH y su valor correspondiente que se deberá
definir para el entorno de los subprocesos
• -fe ruta del archivo de texto de entrada de los subprocesos
• Secuencia de:
o -t ruta de la aplicación que representa el subproceso seguida de su argumento
o -fs ruta del archivo de texto de salida de cada subproceso (opcional)
• -r ruta del archivo de texto donde el programa padre almacenará el resultado final.
b) Asimismo, RecuentoApariciones deberá comprobar el resultado de ejecución1 de cada subproceso,
y si el resultado es distinto de cero, volverá a lanzarlo, hasta que dé como resultado cero. Cuando
todas subprocesos devuelvan 0, entonces podrá finalizar la aplicación. */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.ProcessBuilder.Redirect;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class RecuentoApariciones {

    public static void main(String[] args) {

        RecuentoApariciones c = new RecuentoApariciones();

        try {
            
            
            Map<String, String> argumentos = c.comprobarArgumentos(args);
            ArrayList<ProcessBuilder> procesos = c.PbProcesos(args, argumentos); 
            c.lanzarProcesos(procesos, new File(argumentos.get("SALIDA"))); 
            
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
            
    }

        


    
    public Map<String, String> comprobarArgumentos(String[] args) throws IOException, InterruptedException {
        
        Map<String, String> argumentos = new TreeMap<>();
        if (args.length >= 10) { 

            for (int i = 0; i < args.length; i++) {

                switch (args[i]) {
                    case "-cp":
                        argumentos.put("CLASSPATH", args[i + 1]);
                        break;
                    case "-fe":
                        argumentos.put("ENTRADA", args[i + 1]);
                        break;
                    case "-r":
                        argumentos.put("SALIDA", args[i + 1]);
                        break;
                    default:
                        break;
                }

            }
            
        }
        return argumentos;

    }

    
    public ArrayList<ProcessBuilder> PbProcesos(String[] args, Map<String, String> argumentos) {

        
        ArrayList<ProcessBuilder> procesos = new ArrayList<>();

        for (int i = 0; i < args.length; i++) {

            if (args[i].equals("-t")) {
                
                ProcessBuilder pb = new ProcessBuilder(args[i + 1], args[i + 2], args[i + 3]);

                pb.redirectInput(new File(argumentos.get("ENTRADA")));
                pb.environment().put("CLASSPATH", argumentos.get("CLASSPATH"));
                

                

                if (args[i + 4].equals("-fs")) { //ruta salida 'opcional'
                    pb.redirectOutput(new File(args[i + 5]));
                } else {
                    pb.redirectOutput(Redirect.appendTo(new File(argumentos.get("SALIDA"))));
                }
                procesos.add(pb);

            }

        }

        return procesos;

    }
    public void lanzarProcesos(ArrayList<ProcessBuilder> procesos, File salida) throws IOException, InterruptedException {

        for (ProcessBuilder pb : procesos) { 
            pb.start().waitFor();

            
            File salidaHijo = pb.redirectOutput().file();

            if (!salidaHijo.getAbsolutePath().equals(salida.getAbsolutePath())) { 

                try (
                        BufferedReader bf = new BufferedReader(new FileReader(salidaHijo));
                        FileWriter fw = new FileWriter(salida, true)) {

                    
                    fw.write(bf.readLine() + "\n");

                }

            }

        }

    }

}

                    

                    
                    


                

    

            