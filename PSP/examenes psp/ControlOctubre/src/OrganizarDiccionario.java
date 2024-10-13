import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class OrganizarDiccionario {
    public static void main(String[] args) throws Exception {

        try {
            
            ArrayList<ProcessBuilder> pbList = new ArrayList<>();
            OrganizarDiccionario od = new OrganizarDiccionario();

            pbList = od.comandos(args);//creo la lista de procesos

            if (pbList != null) {

                for (int i = 0; i < pbList.size(); i++) {//recorro la lista de procesos

                    pbList.get(i).start();//inicio cada proceso
                    
                }
            }


        } catch (IOException e) {
            System.out.println("Error!");
        }

    }

    public ArrayList<ProcessBuilder> comandos(String[] args) {
        ArrayList<ProcessBuilder> pbList = new ArrayList<>();
        String[] letra = { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "ñ", "o", "p", "q",
                "r", "s", "t", "u", "v", "w", "x", "y", "z" };

        try {
            if (args[0].equals("-cp")) {//compruebo que se introduzca el classpath

                if (args[2].equals("-if")) {//compruebo que se introduzca el fichero de entrada

                    for (int i = 0; i < letra.length; i++) {//bucle para ordenar por todas las letras

                        ProcessBuilder pb = new ProcessBuilder("java", "Ordena", letra[i]);//creo cada proceso

                        pb.redirectInput(new File(args[3]));//redirecciono la entrada 

                        Map<String, String> env = pb.environment();//asigno el classpath
                        env.put("CLASSPATH", args[1]);

                        if (args.length>4 && args[4].equals("-wd")) { //si existe un working directory lo asigno

                            pb.directory(new File(args[5]));

                        }

                        pb.redirectOutput(new File(letra[i] + ".txt"));//redirecciono la salida (quiza no deberia haber hecho esto)

                        pbList.add(pb);// añado el proceso a una lista

                    }

                }

            }
        } catch (Exception e) {
            e.printStackTrace();;
        }

        return pbList;
    }

}
