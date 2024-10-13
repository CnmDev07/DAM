import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.ProcessBuilder.Redirect;
import java.util.ArrayList;
import java.util.Map;

public class RecuentoApariciones {
    public static void main(String[] args) throws Exception {
        try {
        
            int suma = 0;
            ArrayList<ProcessBuilder> pbArr = new ArrayList<ProcessBuilder>();
            RecuentoApariciones ra = new RecuentoApariciones();

            pbArr = ra.procesarComando(args); //ejecuto el metodo

            if (pbArr != null) {

                for (int i = 0; i < pbArr.size(); i++) {

                    pbArr.get(i).start(); //coger process p

                    try (BufferedReader br = new BufferedReader(new FileReader(pbArr.get(i).directory()))){//p.get...
                        String line;

                        while ((line = br.readLine()) != null) {
                            suma =  suma +  Integer.parseInt(line);
                        }

                    } catch (Exception e) {
                        System.out.println("Errora!");
                    }

                    try (BufferedWriter bw = new BufferedWriter(new FileWriter(args[args.length-1]))) {
                        bw.write(suma);
                    } catch (Exception e) {
                        System.out.println("Error!");
                    }
  
                }

            }
            
        } catch (IOException e) {
            System.out.println("Error! No se ha encontrado el fichero de entrada estandar.");
        }
    }



    public ArrayList<ProcessBuilder> procesarComando(String[] args) {
        ArrayList<ProcessBuilder> pbArr = new ArrayList<ProcessBuilder>();

        try {

            String classpath = ""; // Aqui se guardará el classpath de los subprocesos.
            String entrada = ""; // Aqui se guardará el nombre del fichero de entrada de datos.
            String salida = ""; // Aquí se guardará el nombre del fichero de salida de datos predeterminado.

            if (args.length >= 10) {

                if (args[0].equals("-cp")) { //compruebo que se especifica el classpath
                    classpath = args[1]; 

                    if (args[2].equals("-fe")) { //compruebo que se especifica un fichero de entrada
                        entrada = args[3];

                        if (args[args.length - 2].equals("-r")) { //compruebo que se especifica un fichero de salida

                            for (int i = 3; i < args.length - 2; i++) {

                                if (args[i].equals("-t")) {
                                    ProcessBuilder pb = new ProcessBuilder(args[i + 1], args[i + 2], args[i + 3]);

                                    pb.redirectInput(new File(entrada)); // redireccion de la entrada

                                    Map<String, String> env = pb.environment(); // Se guarda el entorno del proceso.
                                    env.put("CLASSPATH", classpath);

                                    if (args[i + 4].equals("-fs")) { // Si el cuarto argumento detrás del "-t" es un "-fs".
                                        pb.redirectOutput(Redirect.to(new File(args[i + 5]))); // Se redirecciona la salida a otro fichero.

                                    } else { // si no se especifia el "-fs".
                                        salida = args[args.length-1];
                                        pb.redirectOutput(Redirect.to(new File(salida))); // redireccion de la salida al fichero de salida estandar.

                                    }

                                    pbArr.add(pb);
                                }

                            }

                        } else {
                            System.out.println("No se ha especificado el fichero de salida estandar");
                        }

                    } else {
                        System.out.println("No se ha especificado el fichero de entrada estandar");

                    }

                } else {
                    System.out.println("No se ha especificado el classpath de los subprocesos");

                }
            } else {
                System.out.println("Argumentos insuficientes");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return pbArr;

    }

}
