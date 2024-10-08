import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;

public class MaximoMinimoMediaRef {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Integer> numeros = new ArrayList<>();
        int numero;
        String ruta ="/media/alumno/Crixo/DAM/PSP/Tema 1. Programación multiproceso/Ejercicios/MaximoMinimoMediaRefactorizado/bin";
        MaximoMinimoMediaRef m = new MaximoMinimoMediaRef();

        Process minimo = null;
        Process maximo = null;
        Process media = null;

        while (sc.hasNextInt()){
            numero = sc.nextInt();
            numeros.add(numero); 
        }
        
        /*Creo los tres ProcessBuilders */
        ProcessBuilder pbMin = m.crearProcessBuilder("Minimo", "minimo.txt", ruta);
        ProcessBuilder pbMax = m.crearProcessBuilder("Maximo", "maximo.txt", ruta);
        ProcessBuilder pbMed = m.crearProcessBuilder("Media", "media.txt", ruta);

        try{
        /*Inicio los tres procesos */
        minimo = pbMin.start();
        maximo = pbMax.start();
        media = pbMed.start();
        }catch(IOException e){
            e.printStackTrace();
        }

        /*Envio los numeros */
        m.envNumeros(minimo, numeros);
        m.envNumeros(maximo, numeros);
        m.envNumeros(media, numeros);

        try{
        /*Espero a que los procesos terminen */
        minimo.waitFor();
        maximo.waitFor();
        media.waitFor();
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }
    private ProcessBuilder crearProcessBuilder(String operacion, String archivoSalida, String ruta) {
        ProcessBuilder pb = new ProcessBuilder("java", operacion);
        Map<String, String> env = pb.environment();
        env.put("CLASSPATH", ruta); 
        pb.redirectOutput(new File(archivoSalida)); 
        pb.redirectErrorStream(true);
        return pb; 
    }
    
    private void envNumeros(Process proceso, List<Integer> numeros) {
        try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(proceso.getOutputStream()))) {
            for (int num : numeros) {
                bw.write(Integer.toString(num));
                bw.newLine();
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
