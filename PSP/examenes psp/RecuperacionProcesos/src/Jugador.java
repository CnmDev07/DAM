import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Jugador {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        int objetivo = Integer.parseInt(args[0]);
        String line;
        ArrayList<String> lines = new ArrayList<>();
        int operadores = 0;

        /*while (lines.size()<4) {
            line = sc.nextLine();
            lines.add(line);
        }*/

        lines.add("1");
        lines.add("2");
        lines.add("3");
        lines.add("4");
        int resultado = Integer.parseInt(lines.get(0));
        int cont = 0;
        while (cont < 4) {

            operadores = new Random().nextInt(4);
            int auxResult = 0;
            switch (operadores) {
                case 0:
                    auxResult = resultado + Integer.parseInt(lines.get(cont));
                    if (auxResult < objetivo) {
                        resultado = auxResult;
                        cont++;
                    }
                    break;

                case 1:
                    auxResult = resultado - Integer.parseInt(lines.get(cont));
                    if (auxResult < objetivo) {
                        resultado = auxResult;
                        cont++;
                    }
                    break;

                case 2:
                    auxResult = resultado * Integer.parseInt(lines.get(cont));
                    if (auxResult < objetivo) {
                        resultado = auxResult;
                        cont++;
                    }
                    break;

                default:
                    auxResult = resultado / Integer.parseInt(lines.get(cont));
                    if (auxResult < objetivo) {
                        resultado = auxResult;
                        cont++;
                    }
                    break;
            }
        }

        System.out.println(new Random().nextInt(4));

    }
}