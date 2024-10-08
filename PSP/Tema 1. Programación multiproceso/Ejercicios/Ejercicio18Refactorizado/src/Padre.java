import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Map;

public class Padre {
    public static void main(String[] args) throws Exception {
        String ruta =" ";
        Padre a = new Padre();

        if(a.comprobarArgumentos(args)){
            String hijo1 = args[0];
            String hijo2 = args[1];
            File archivoEntrada = new File(args[2]);
            File archivoSalida = new File(args[3]);

            a.ejecutarProcesos(hijo1, hijo2, archivoEntrada, archivoSalida, ruta);
        }
    }
    private boolean comprobarArgumentos(String[] args){
        boolean bandera = false;
        if (args.length == 4){
            File archivoEntrada = new File(args[2]);
            if (archivoEntrada.exists() || archivoEntrada.isFile()){
                bandera = true;
            }else{
                System.out.println(archivoEntrada.getName() + " no existe");
            }
        }else{
            System.out.println("Uso : java Padre Hijo1 Hijo2 [archivo_entrada] [archivo_salida]");
        }
        return bandera;
    }

    private void ejecutarProcesos(String hijo1, String hijo2, File archivoEntrada, File archivoSalida, String ruta){

        Process h1 = null;
        Process h2 = null;

        try{
            ProcessBuilder pb1 = new ProcessBuilder("java", hijo1);
            Map<String, String> env = pb1.environment();
            env.put("CLASSPATH", ruta);
            pb1.redirectInput(archivoEntrada);
            pb1.redirectError(archivoSalida);

            ProcessBuilder pb2 = new ProcessBuilder("java", hijo2);
            pb2.environment().putAll(env);
            pb2.redirectOutput(archivoSalida);
            pb2.redirectError(archivoSalida);

            h1 = pb1.start();
            h2 = pb2.start(); //lanzo los dos procesos

            try(
                BufferedReader brH1 = new BufferedReader(new InputStreamReader(h1.getInputStream()));
                BufferedWriter bwH2 = new BufferedWriter(new OutputStreamWriter(h2.getOutputStream()))
                ){
                    String linea;
                    while ((linea = brH1.readLine()) != null){
                        bwH2.write(linea);
                        bwH2.newLine();
                        bwH2.flush();
                    }
                }
                h1.waitFor();
                h2.waitFor();
            

        }catch (IOException | InterruptedException e){
            e.printStackTrace();
        }
    }
}
