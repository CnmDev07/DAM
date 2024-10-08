import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Copia {
    public static void main(String[] args) {
        String archivoOrigen;
        String archivoDestino;
        boolean append = false;

        if (args.length < 2) {
            System.out.println("Uso: java Copia <archivo_origen> <archivo_destino> [-a]");
        } else {
            
            archivoOrigen = args[0];
            archivoDestino= args[1];

            if (args.length == 3 && args[2].equals("-a")) {
                append = true; 
            }

            
            File source = new File(archivoOrigen);
            if (!source.exists()) {
                System.out.println("Error: La ruta de origen no existe: " + archivoOrigen);
                return; 
            }

            
            try (FileInputStream inputStream = new FileInputStream(source);
                FileOutputStream outputStream = new FileOutputStream(archivoDestino, append)) {

                byte[] buffer = new byte[1024];
                int bytesLeidos;

                
                while ((bytesLeidos = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesLeidos);
                }

                System.out.println("Copia completada de " + archivoOrigen + " a " + archivoDestino);

            } catch (IOException e) {
                System.out.println("Error durante la copia del archivo: " + e.getMessage());
            }

            
            File dest = new File(archivoDestino);
            if (!dest.exists()) {
                System.out.println("Error: La ruta de destino no se creó: " + archivoDestino);
            }
        }
    }
}
