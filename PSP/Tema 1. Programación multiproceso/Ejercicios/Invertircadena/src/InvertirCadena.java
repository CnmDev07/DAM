import java.util.Scanner;

public class InvertirCadena {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String cadena = scanner.nextLine();
        String cadenaInvertida = new StringBuilder(cadena).reverse().toString();
        System.out.println(cadenaInvertida);
    }
}
