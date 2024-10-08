import java.util.Scanner;

public class Suma {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        int n1, n2;

        
        if(!sc.hasNextInt()){
            System.out.println("El primer argumento no es un entero");
            System.exit(1);
        }
        n1 = sc.nextInt();

        
        if (!sc.hasNextInt()){
            System.out.println("El segundo argumento no es un entero");
            System.exit(1);
        }
        n2 = sc.nextInt();

        System.out.println(n1 + n2);
    }
}
