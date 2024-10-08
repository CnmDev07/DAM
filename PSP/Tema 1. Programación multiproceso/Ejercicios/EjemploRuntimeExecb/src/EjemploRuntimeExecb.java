import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class EjemploRuntimeExecb {
    public static void main(String[] args) throws Exception {
        String[] commandLine = {"cmd", "/c", "java", "-cp", "C://Users//cnava//Desktop//2DAM//PSP//Tema 1. Programación multiproceso//Ejercicios//EjemploRuntimeExecb//bin", "EjemploRuntimeExecb"}; 
        
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
            OutputStream outputStream = System.out;
        ) {
            
            inputStream.transferTo(outputStream);

            
            errorStream.transferTo(outputStream);

            p.waitFor();  
            System.out.println("Done");
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
