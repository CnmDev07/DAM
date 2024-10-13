import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class Ordena {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        String diccionario = sc.nextLine();

        Set<String> salida = new TreeSet<>();
        String[] aux = diccionario.split("\n");

        for (int i = 0; i < aux.length; i++) {
            if (String.valueOf(aux[i].charAt(0)).equalsIgnoreCase(args[0])) {//falta comprobar que no se repitan
                
                salida.add(aux[i]);
            }
        }

        for (int i = 0; i < salida.size(); i++) {
            System.out.println(salida.toArray()[i]);
        }

    }
}
