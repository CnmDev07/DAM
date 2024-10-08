/*b) ¿Que ocurre si el nombre de directorio o archivo indicado como argumento no existe?. Tratar el problema adecuadamente.
   Sugerencias:
   -lanzar sin capturar una excepción java.io.FileNotFoundException
   -métodos: java.io.FileNotFoundException("mensaje"), File.exists() */

import java.io.File;
import java.io.FileNotFoundException;

public class MostrarDirectorio {
    public static void main(String[] args) throws FileNotFoundException {
        String argumento;
        File f;
        String[] archivos;
        
        if(args.length != 1){
            System.out.println("Error: proporcione un argumento");
            mostrarAyuda();
        }else{
            argumento = args[0];

            if(argumento.equals("-h")){
                mostrarAyuda();
            }else{
                f = new File(argumento);

                if(!f.exists()){
                    throw new FileNotFoundException("El archivo o directorio " + argumento + " no existe");
                }
                if(f.isFile()){
                    System.out.println("Archivo: " + f.getName());
                    System.out.println("Ruta absoluta: " + f.getAbsolutePath());
                }else if(f.isDirectory()){
                    System.out.println("Contenido del directorio :" + f.getAbsolutePath());
                    archivos = f.list();
                    if(archivos != null && archivos.length > 0){
                        for(String archivo : archivos){
                            System.out.println(archivo);
                        }
                    }else{
                        System.out.println("Directorio vacio");
                    }
                }else{
                    System.out.println("Argumento no valido");
                }
            }
        }
    }

    private static void mostrarAyuda() {
        System.out.println("Uso: java MostrarDirectorio [ruta o archivo]");
        System.out.println("Si proporciona una ruta, mostrará el contenido del directorio.");
        System.out.println("Si proporciona un archivo, mostrará el nombre del archivo.");
        System.out.println("Use el argumento '-h' para mostrar esta ayuda.");
    }
}
