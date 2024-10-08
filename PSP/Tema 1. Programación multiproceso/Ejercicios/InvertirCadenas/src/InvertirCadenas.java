import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Scanner;

public class InvertirCadenas {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String cadena = "";

        

        while (!cadena.equalsIgnoreCase("FIN")) {
            System.out.println("Introduce una cadena ('FIN' para acabar): ");
            cadena = sc.nextLine();

            try {
                
                Process p = Runtime.getRuntime().exec(new String[]{"java", "-cp", "/home/crixo/Documentos/DAM/PSP/Tema 1. Programación multiproceso/Ejercicios/Invertircadena/bin"
                , "InvertirCadena"});

                
                OutputStream os = p.getOutputStream();
                os.write((cadena + "\n").getBytes()); 
                os.flush(); 
                os.close(); 

                
                BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
                String line;

                
                while ((line = br.readLine()) != null) {
                    System.out.println("Cadena invertida: " + line);
                }

                
                p.waitFor();
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }

        sc.close(); 
    }
}
