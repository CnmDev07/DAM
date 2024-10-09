import java.util.Scanner;

public class CuentaLetras {
    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);
        String cadena = sc.nextLine();
        sc.close();

        int contador = 0;
    
        for(int i = 0; i < cadena.length(); i++){

            if(String.valueOf(cadena.charAt(i)).equalsIgnoreCase(args[0])){
                
                contador++;

            }

        }
        System.out.println(args[0] + " -> " + contador);
    }
}

        
