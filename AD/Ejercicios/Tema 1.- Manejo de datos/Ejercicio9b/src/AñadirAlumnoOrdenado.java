import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class AñadirAlumnoOrdenado {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String nombreAlumno;
        String nombreArchivo = "ListaDeClase.txt";
        String linea;
        List<String> alumnos = new ArrayList<>();
        
        System.out.print("Introduce el nombre del nuevo alumno: ");
        nombreAlumno = scanner.nextLine();

        try (BufferedReader reader = new BufferedReader(new FileReader(nombreArchivo))) {
            while ((linea = reader.readLine()) != null) {
                alumnos.add(linea); 
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }

        
        alumnos.add(nombreAlumno);

        Collections.sort(alumnos);

        
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo))) {
            for (String alumno : alumnos) {
                writer.write(alumno);
                writer.newLine(); 
            }
            System.out.println("Alumno añadido y lista actualizada: " + nombreAlumno);
        } catch (IOException e) {
            System.out.println("Error al escribir en el archivo: " + e.getMessage());
        } finally {
            scanner.close(); 
        }
    }
}
        
        

        
