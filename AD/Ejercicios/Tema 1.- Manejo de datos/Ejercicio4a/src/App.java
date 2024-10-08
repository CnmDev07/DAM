/*4-Mostrar el contenido del directorio cuyo nombre está indicado como argumento en forma de ruta absoluta o nombre simple, distinguiendo por un lado los directorios y por otro los archivos, ordenados alfabéticamente. Gestione adecuadamente los errores utilizando excepciones.

a)   Examinar la API de "ArrayList" y de "List". ¿Es eficiente el uso de "ArrayList" en este problema?
Realizar una primera versión del programa utilizando "ArrayList" para guardar los nombres de los archivos y los directorios respectivamente. */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {
        String argumento;
        File f;
        File[] contenido;
        List<String> archivos;
        List<String> directorios;

        try{
            
            verificarArgumentos(args);
            argumento = args[0];
            f = new File(argumento);

            if (!f.exists()){
                throw new FileNotFoundException("El archivo o directorio no existe");
            }

            archivos = new ArrayList<>();
            directorios = new ArrayList<>();
            
            if(f.isDirectory()){
                System.out.println("Es un directorio");
                contenido = f.listFiles();

                if (contenido != null){
                    for(File file: contenido){
                        if(f.isDirectory()){
                            directorios.add(f.getName());
                        }else if(f.isFile()){
                            archivos.add(f.getName());
                        }
                    }
                }
                Collections.sort(directorios);
                System.out.println("Directorios:");
                for(String dir : directorios){
                    System.out.println(dir);
                }
                Collections.sort(archivos);
                System.out.println("Archivos:");
                for(String arc : archivos){
                    System.out.println(arc);
                }
            }else if (f.isFile()){
                System.out.println("Es un archivo: " + f.getName());
            }

        }catch(LineaComandosException e){
            System.out.println("error: " + e.getMessage());
            mostrarAyuda();
        }catch(FileNotFoundException e){
            System.out.println(e.getMessage());
        }
    }
    private static void verificarArgumentos(String[] args) throws LineaComandosException {
        String mensaje = "Debe proporcionar exactamente un argumento (ruta o -h). ";
        if (args.length != 1) {
            throw new LineaComandosException(mensaje);
        }
    }

    private static void mostrarAyuda() {
        System.out.println("Uso: java MostrarDirectorio [ruta o archivo]");
        System.out.println("Si proporciona una ruta, mostrará el contenido del directorio.");
        System.out.println("Si proporciona un archivo, mostrará el nombre del archivo.");
        System.out.println("Use el argumento '-h' para mostrar esta ayuda.");
    }
}
