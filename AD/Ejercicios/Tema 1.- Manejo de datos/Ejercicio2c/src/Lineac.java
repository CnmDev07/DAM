/*c) Si se invoca incorrectamente el programa (no se indican argumentos o se indica mas de uno), debe mostrarse la ayuda a modo de mensaje de error. 
   Sugerencias:
   -definir una excepción "checked" (de obligada captura) de programador "LineaComandosException", en cuya captura se muestre la ayuda.
   -métodos: clase LineaComandosException extends Exception; no requiere ningún método. */

import java.io.File;
import java.io.FileNotFoundException;


public class Lineac {
    public static void main(String[] args) {
        String argumento;
        File f;
        String[] archivos;

        try{
            verificarArgumentos(args);
            argumento = args[0];
            f = new File (argumento);

            if (!f.exists()){
                throw new FileNotFoundException("El archivo o directorio " + argumento + " no existe");
            }
            if (f.isFile()){
                System.out.println("Archivo: " +  f.getName());
            }else if( f.isDirectory()){
                System.out.println("Directorio: " + f.getAbsolutePath());
                archivos = f.list();
                if (archivos != null){
                    for (String archivo: archivos){
                        System.out.println(archivo);
                    }
                }
            }
        }catch (LineaComandosException e){
            System.out.println("Error: " + e.getMessage());
            mostrarAyuda();
        }catch(FileNotFoundException e){
            System.out.println(e.getMessage());
        }
    }

    private static void mostrarAyuda() {
        System.out.println("Uso: java MostrarDirectorio [ruta o archivo]");
        System.out.println("Si proporciona una ruta, mostrará el contenido del directorio.");
        System.out.println("Si proporciona un archivo, mostrará el nombre del archivo.");
        System.out.println("Use el argumento '-h' para mostrar esta ayuda.");
    }

    private static void verificarArgumentos(String[] args) throws LineaComandosException {
        
        String mensaje = "Debe proporcionar exactamente un argumento (ruta o -h). ";
        if (args.length != 1) {
            throw new LineaComandosException(mensaje);
        }
    }
}
