import java.util.Scanner;

public class Mayusculas2 {
    public static void main(String[] args) throws Exception {
        System.out.println("I'm 'Mayúsculas': Waiting for receiving some string through standard 'in'");
        Scanner sc = new Scanner(System.in);
        String entrada = sc.nextLine().toUpperCase();
        System.out.println("I´m 'Mayúsculas': String received, writing on standard 'out':");
        System.out.println(entrada);
    }
}
