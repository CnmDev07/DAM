import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Map;
import java.util.Scanner;


public class App {
    public static void main(String[] args) {
        
        ProcessBuilder builder = new ProcessBuilder("java", "Suma");
        Map<String, String> env = builder.environment();
        env.put("CLASSPATH", "/media/alumno/E0A4-F1D5/2DAM/PSP/Tema 1. Programación multiproceso/Ejercicios/Suma/bin");

        Process process = null;
        
        try {
            process = builder.start();
        } catch (IOException e) {
            System.out.println("Error  " + e.getMessage());
            System.exit(1);
        }
            
        try (BufferedWriter bro = new BufferedWriter(new OutputStreamWriter(process.getOutputStream()))) {
            
            bro.write("");
            bro.write("");
            bro.flush(); 

            
            process.getInputStream().transferTo(System.out);
            process.getErrorStream().transferTo(System.err);

            int exitCode = process.waitFor(); 
            System.out.println("Proceso terminado con código: " + exitCode);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            
        }
    }
}
        
        
        

