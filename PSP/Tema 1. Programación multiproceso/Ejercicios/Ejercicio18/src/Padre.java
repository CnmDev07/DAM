// Padre.java
import java.io.*;

public class Padre {
    public static void main(String[] args) throws Exception{
        if (args.length != 4) {
            System.out.println("Uso: java Padre Hijo1 Hijo2 entrada.txt salida.txt");
            return;
        }

        String hijo1 = args[0];
        String hijo2 = args[1];
        String archivoEntrada = args[2];
        String archivoSalida = args[3];

        
            
            ProcessBuilder pb1 = new ProcessBuilder("java", hijo1);
            Process hijo1Process = pb1.start();

            
            try (BufferedReader bf = new BufferedReader(new FileReader(archivoEntrada));
                OutputStream os = hijo1Process.getOutputStream()) {
                String linea;
                while ((linea = bf.readLine()) != null) {
                    os.write((linea + System.lineSeparator()).getBytes());
                }
                os.flush();
            }catch(IOException e){
                e.printStackTrace();
            }

            ProcessBuilder pb2 = new ProcessBuilder("java", hijo2);
            pb2.redirectOutput(new File(archivoSalida)); 
            Process hijo2Process = pb2.start();
            
            

            

            try(BufferedReader reader = new BufferedReader(new InputStreamReader(hijo1Process.getInputStream()));
            OutputStream os2 = hijo2Process.getOutputStream()){

            String linea;
            while ((linea = reader.readLine()) != null) {
                os2.write((linea + System.lineSeparator()).getBytes());
            }
            
            }catch(IOException e){
                e.printStackTrace();

            
            

            try{
            hijo1Process.waitFor();
            hijo2Process.waitFor();
            }catch(InterruptedException ex){
                ex.printStackTrace();
            }
    }
}
}