

import java.util.ArrayList;
import java.util.Scanner;

public class Busqueda {
    public static void main (String[] args){
        Scanner sc = new Scanner(System.in);
        String line;
        ArrayList<String> lines = new ArrayList<>();

        while(true){
            line = sc.nextLine();
            if (line.equals("FIN")) {
                break;
            }
            lines.add(line);
        }

        for (int i = 0; i < lines.size(); i++) {
            if(lines.get(i).equals(args[0])){
                System.out.println("El numero " + args[0] + " se encontro en el indice " + i);
                return;
            }
        }
    } 
}
