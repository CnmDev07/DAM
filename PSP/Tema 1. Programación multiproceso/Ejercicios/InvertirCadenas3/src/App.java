import java.io.File;
import java.io.OutputStream;
import java.util.Map;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        String cadena = "";

        while(!cadena.equalsIgnoreCase("FIN")){
            System.out.println("Introduce cadena, acaba con 'FIN'");
            cadena = sc.nextLine();

            try{
                File f = new File("salida.txt");
                ProcessBuilder pb = new ProcessBuilder("java", "InvertirCadena");
                Map<String, String> env = pb.environment();
                env.put("CLASSPATH", "/media/alumno/E0A4-F1D5/2DAM/PSP/Tema 1. Programación multiproceso/Ejercicios/InvertirCadenas/bin");
                    
                pb.redirectOutput(ProcessBuilder.Redirect.appendTo(f));
                
                Process p = pb.start();

                while(cadena.toUpperCase() != "FIN"){
                    cadena = sc.nextLine();
                }

                
                
                

                    OutputStream os = p.getOutputStream();
                    
                    os.write((cadena + "\n").getBytes());
                    os.flush();
                    os.close();
                
                
            }
        }
    }
}
