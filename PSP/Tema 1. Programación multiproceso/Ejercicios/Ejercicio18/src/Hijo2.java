// Hijo2.java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Hijo2 {
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                
                System.out.println(linea); 
            }
        } catch (IOException e) {
            System.out.println("Error en Hijo2: " + e.getMessage());
        }
    }
}
