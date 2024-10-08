import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class CopiaMayusculas {
    public static void main(String[] args) {
        String archivoOrige = args[0];
        String archivoDestino = args[1];

        if (args.length < 2) {
            System.out.println("Uso: java JavaMayusculas <archivo_origen> <archivo_destino>");
            return;
        }

        
        archivoOrige = args[0];
        archivoDestino = args[1];

        
        try (BufferedReader reader = new BufferedReader(new FileReader(archivoOrige));
            BufferedWriter writer = new BufferedWriter(new FileWriter(archivoDestino))) {

            String linea;
            
            while ((linea = reader.readLine()) != null) {
                
                writer.write(linea.toUpperCase());
                writer.newLine(); 
            }

            System.out.println("Copia completada de " + archivoOrige + " a " + archivoDestino + " en mayúsculas.");

        } catch (IOException e) {
            System.out.println("Error al procesar los archivos: " + e.getMessage());
        }
    }
}
