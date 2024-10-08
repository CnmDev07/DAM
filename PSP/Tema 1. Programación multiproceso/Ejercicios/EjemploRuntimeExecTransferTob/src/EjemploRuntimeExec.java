import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class EjemploRuntimeExec {
    public static void main(String[] args) throws Exception {
        String[] commandLine = {"cmd", "/c", "java", "-cp", "C://Users//cnava//Desktop//2DAM//PSP//Tema 1. Programación multiproceso//Ejercicios//holaMundo//bin", "HolaMundo"}; 
        
        Process p = null;
        try {
            p = Runtime.getRuntime().exec(commandLine);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return;
        }

        try (
            InputStream inputStream = p.getInputStream();
            InputStream errorStream = p.getErrorStream();
        ) {
            
            try (BufferedReader bri = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
                String line;
                while ((line = bri.readLine()) != null) {
                    System.out.println(line);  
                }
            }

            
            try (BufferedReader bre = new BufferedReader(new InputStreamReader(errorStream, StandardCharsets.UTF_8))) {
                String line;
                while ((line = bre.readLine()) != null) {
                    System.out.println(line);  
            }

            p.waitFor();  
            System.out.println("Done");
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
}