import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// Windows:
// String[] commandLine = {"cmd", "/c", "dir"};
// Linux (comando 'ls' SIN argumentos):
// String[] commandLine = {"bash", "-c", "ls"};

// Linux (comando 'ls' CON argumentos):
// String[] commandLine = {"bash", "-c", "ls /*"};
// String[] commandLine = {"bash", "-c", "java HolaMundo"};
public class EjemploRuntimeExec {
    public static void main(String[] args) throws Exception {
        
        
        
        String[] commandLine = {"cmd", "/c", "java", "-cp", "C://Users//cnava//Desktop//2DAM//PSP//Tema 1. Programación multiproceso//Ejercicios//EjemploRuntimeExec//bin", "EjemploRuntimeExec"}; 
        Process p = null;
        try {
            p = Runtime.getRuntime().exec(commandLine);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return;
        }

        try (
            BufferedReader bri = new BufferedReader(new InputStreamReader(p.getInputStream()));
            BufferedReader bre = new BufferedReader(new InputStreamReader(p.getErrorStream()))
        ) {
            String line;
            
            while ((line = bri.readLine()) != null) {
                System.out.println(line);
            }
            
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
