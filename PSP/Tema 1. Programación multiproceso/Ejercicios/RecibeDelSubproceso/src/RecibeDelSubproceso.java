import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class RecibeDelSubproceso {
    public static void main(String[] args) throws Exception {
        if(args.length != 1){
            System.err.println("Se necesita un programa para ejecutar");
            System.exit(-1);
        }

        Process p = null;
        try{
            p = new ProcessBuilder("java", args[0]).start();

        }catch(IOException e){
            e.printStackTrace();
            System.exit(-1);
        }
        try (InputStream is = p.getInputStream(); BufferedReader br = new BufferedReader(new InputStreamReader(is))){
            String line;
            System.out.println("Salida del proceso " + args[0] + ": ");

            while (( line = br.readLine()) != null){
                System.out.println(line);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
