

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.lang.ProcessBuilder.Redirect;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class BusquedaConcurrente {

    public static void main(String[] args) {

        BusquedaConcurrente bc = new BusquedaConcurrente();
        ArrayList<ProcessBuilder> pList = new ArrayList<>();
        pList = bc.verificarLineaComandos(args);

        for (int i = 0; i < pList.size(); i++) {
            try {

                Process p = pList.get(i).start();
                String line;

                BufferedReader bri = new BufferedReader(new InputStreamReader(p.getInputStream()));
                BufferedReader bre = new BufferedReader(new InputStreamReader(p.getErrorStream()));

                while ((line = bri.readLine()) != null)
                    System.out.println(line);

                while ((line = bre.readLine()) != null)
                    System.out.println(line);

            } catch (IOException e) {
                // TODO: handle exception
            }
        }

    }

    public ArrayList<ProcessBuilder> verificarLineaComandos(String[] args) {
        String num = "";
        String archivo;
        ProcessBuilder pb;
        ArrayList<ProcessBuilder> pbMap = new ArrayList<>();
        try {
            if (args[0].equals("-n")) {
                for (int i = 0; i < args.length; i++) {
                    if (args[i].equals("-n")) {
                        num = args[i + 1];
                    } else if (args[i].contains(".txt") && !num.equals("")) {
                        archivo = args[i];
                        pb = new ProcessBuilder("java", "Busqueda", num);
                        pb.redirectInput(Redirect.from(new File(archivo)));
                        pbMap.add(pb);
                    }
                }

            } else {
                throw new BusquedaConcurrenteException("Falta el parametro -n. ");
            }

        } catch (Exception e) {
        }
        return pbMap;
    }
}
