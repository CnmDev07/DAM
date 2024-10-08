// Media.java
import java.util.Scanner;

public class Media {
    public static void main(String[] args) {
        int suma = 0;
        int contador = 0;

        
        try (Scanner scanner = new Scanner(System.in)) {
            while (scanner.hasNextLine()) {
                String linea = scanner.nextLine().trim();
                if (!linea.isEmpty()) {
                    int numero = Integer.parseInt(linea);
                    suma += numero;
                    contador++;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        
        if (contador > 0) {
            double media = (double) suma / contador;
            System.out.println(media);
        } else {
            System.out.println("No se ingresaron números.");
        }
    }
}
