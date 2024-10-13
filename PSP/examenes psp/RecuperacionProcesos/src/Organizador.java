import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.lang.ProcessBuilder.Redirect;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Organizador {
    public static void main(String[] args) throws Exception {


        //Solo contempla la entrada desde fichero .txt


        Organizador org = new Organizador();
        ArrayList<ArrayList<ProcessBuilder>> parts = new ArrayList<>();
        parts = org.crearProcesos(org.verificarLineaDeComandos(args));

        System.out.println("Partidas jugadas: " + parts.size());

        for (int i = 0; i < parts.size(); i++) {
            ArrayList<Integer> respuestas = new ArrayList<>();
            for (int j = 0; j < parts.get(i).size(); j++) {
                Process p = parts.get(i).get(j).start();
                String line;
                BufferedReader bri = new BufferedReader(new InputStreamReader(p.getInputStream()));
                while ((line = bri.readLine()) != null)
                    respuestas.add(Integer.parseInt(line));

            }

            String ganador = "";
            int respuestaGanadora = 0;
            int idGanador = 0;
            ArrayList<Integer> ganadores = new ArrayList<>();
            for (int j = 0; j < respuestas.size(); j++) {
                if (j == 0) {
                    idGanador = j + 1;
                    ganador = ", ha ganado el jugador " + idGanador;
                    respuestaGanadora = respuestas.get(j);
                    ganadores.add(idGanador);
                } else {
                    if (respuestas.get(j) > respuestaGanadora) {
                        ganadores.clear();
                        idGanador = j+1;
                        respuestaGanadora = respuestas.get(j);
                        ganador = ", ha ganado el jugador " + idGanador;
                        ganadores.add(idGanador);
                    } else if (respuestas.get(j) == respuestas.get(j - 1)) {
                        idGanador = j+1;
                        ganadores.add(idGanador);
                        ganador = " Hay un empate entre los jugadores: ";
                        for (int k = 0; k < ganadores.size(); k++) {
                            if (k == 0) {
                                ganador += ganadores.get(k);
                            } else if ((k+1) == ganadores.size()) {
                                ganador += " y " + ganadores.get(k);
                            } else {
                                ganador += ", " + ganadores.get(k);
                            }
                        }
                    }
                }
            }
            System.out.println("-   Juego " + (i + 1) + ganador);
        }
    }

    public Map<String, ArrayList<String>> verificarLineaDeComandos(String[] args) {
        Map<String, ArrayList<String>> info = new HashMap<>();

        if (!(args.length < 6)) {
            info.put("-n", new ArrayList<>());
            info.put("-o", new ArrayList<>());
            info.put("-j", new ArrayList<>());
            for (int i = 0; i < args.length; i++) {
                if (args[i].equals("-n")) {
                    if (args[i + 1].contains(".txt")) {
                        info.get(args[i]).add(args[i + 1]);
                    } else {
                        String numeros = "";
                        for (int j = 1; j < 5; j++) {
                            numeros += args[i + j];
                        }
                        info.get(args[i]).add(numeros);
                    }

                } else if (args[i].equals("-o")) {
                    info.get(args[i]).add(args[i + 1]);
                } else if (args[i].equals("-j")) {
                    info.get(args[i]).add(args[i + 1]);
                }
            }
        }
        return info;
    }

    public ArrayList<ArrayList<ProcessBuilder>> crearProcesos(Map<String, ArrayList<String>> info) {

        ArrayList<ArrayList<ProcessBuilder>> partidas = new ArrayList<>();

        int numP = info.get("-n").size();

        for (int i = 0; i < numP; i++) {
            int jugPart = Integer.parseInt(info.get("-j").get(i));
            ArrayList<ProcessBuilder> procesos = new ArrayList<>();
            for (int j = 0; j < jugPart; j++) {
                ProcessBuilder pb = new ProcessBuilder("java", "Jugador", info.get("-o").get(i));

                pb.redirectInput(Redirect.from(new File(info.get("-n").get(i))));
                procesos.add(pb);
            }
            partidas.add(procesos);
        }

        return partidas;
    }
}
