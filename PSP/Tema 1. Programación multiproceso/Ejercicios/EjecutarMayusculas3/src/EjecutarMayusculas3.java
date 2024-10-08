import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Scanner;

public class EjecutarMayusculas3 {
    public static void main(String[] args) throws Exception {
        
        String[] commandLine = {"java", "-cp", "C://Users//cnava//Desktop//2DAM//PSP//Tema 1. Programación multiproceso//Ejercicios//Mayusculas2//bin", "Mayusculas2"};

        try {
            
            Process p = Runtime.getRuntime().exec(commandLine);

            
            InputStream in = System.in;
            OutputStream out = p.getOutputStream();
            Scanner scanner = new Scanner(in);

            
            while (true) {
                
                String input = scanner.nextLine(); 
                out.write((input + "\n").getBytes()); 
                out.flush(); 

                
                InputStream processInputStream = p.getInputStream();
                InputStream processErrorStream = p.getErrorStream();

                
                processInputStream.transferTo(System.out);
                
                processErrorStream.transferTo(System.out);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
