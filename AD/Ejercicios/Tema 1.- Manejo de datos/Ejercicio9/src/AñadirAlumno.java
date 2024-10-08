import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class AñadirAlumno {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String nombreAlumno;
        String nombreArchivo;
        
        System.out.print("Introduce el nombre del nuevo alumno: ");
        nombreAlumno = scanner.nextLine();

        
        nombreArchivo = "ListaDeClase.txt";

        
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo, true))) {
            writer.write(nombreAlumno);
            writer.newLine(); 
            System.out.println("Alumno añadido: " + nombreAlumno);
        } catch (IOException e) {
            System.out.println("Error al escribir en el archivo: " + e.getMessage());
        } finally {
            scanner.close();         }
    }
}
