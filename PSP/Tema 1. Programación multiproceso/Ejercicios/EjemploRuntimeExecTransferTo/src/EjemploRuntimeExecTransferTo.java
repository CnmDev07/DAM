import java.io.IOException;

public class EjemploRuntimeExecTransferTo {
    public static void main(String[] args) throws Exception {
        String[] commandLine = {"cmd","/c", "java", "-cp", "C://Users//cnava//Desktop//2DAM//PSP//Tema 1. Programación multiproceso//Ejercicios//holaMundo//bin", "HolaMundo"};

        try {
            Process p = Runtime.getRuntime().exec(commandLine);
            p.getInputStream().transferTo(System.out);
            p.getErrorStream().transferTo(System.out);
            p.waitFor();
            System.out.println("Done");
        }catch(IOException | InterruptedException e){
            e.printStackTrace();
        }
    }
}
