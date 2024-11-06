import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {
        
        if (args.length < 6){
            System.out.println("Error en argumentos");
        }else{

            int numHilos = Integer.parseInt(args[1]);
            int tiempoMaximo = Integer.parseInt(args[3]);
            String archivo = args[5];

        }

    }

    private static List<Integer> leerNumerosDeArchivo(String archivo) {
        List<Integer> numeros = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                try {
                    numeros.add(Integer.parseInt(linea.trim()));
                } catch (NumberFormatException e) {
                    System.out.println("Número inválido en el archivo: " + linea);
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
        return numeros;
    }

    private static List<List<Integer>> dividirLista(List<Integer> numeros, int numParticiones) {
        List<List<Integer>> subListas = new ArrayList<>();
        int cantidadPorParticion = numeros.size() / numParticiones;
        int restante = numeros.size() % numParticiones; 
    
        int inicio = 0;
        for (int i = 0; i < numParticiones; i++) {
            int fin = inicio + cantidadPorParticion + (i < restante ? 1 : 0); 
            subListas.add(numeros.subList(inicio, fin));
            inicio = fin;
        }
        return subListas;
    }
}
