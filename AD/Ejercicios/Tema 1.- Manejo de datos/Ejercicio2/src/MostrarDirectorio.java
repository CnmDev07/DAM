/*2- a) Crear un programa 'MostrarDirectorio' que al ser invocado desde la línea de comandos deba pasarse también un argumento que represente un nombre o ruta de archivo o directorio; el programa debe mostrar el nombre de los ficheros contenidos en el directorio, o el nombre del fichero. Si ese (único) argumento es "-h" se mostrará una pequeña ayuda sobre como utilizar correctamente el programa.
   Sugerencias:
   -definir un método privado "mostrarAyuda()"
   -métodos: File.File(...), File.list().

  b) ¿Que ocurre si el nombre de directorio o archivo indicado como argumento no existe?. Tratar el problema adecuadamente.
   Sugerencias:
   -lanzar sin capturar una excepción java.io.FileNotFoundException
   -métodos: java.io.FileNotFoundException("mensaje"), File.exists()

  c) Si se invoca incorrectamente el programa (no se indican argumentos o se indica mas de uno), debe mostrarse la ayuda a modo de mensaje de error. 
   Sugerencias:
   -definir una excepción "checked" (de obligada captura) de programador "LineaComandosException", en cuya captura se muestre la ayuda.
   -métodos: clase LineaComandosException extends Exception; no requiere ningún método. */

   /*recibir datos, tratar datos, datos a salida */
import java.io.File;

public class MostrarDirectorio {
    public static void main(String[] args) {
        String argumento = args[0];
        String[] archivos;
        File f;

        if (args.length != 1){
            System.out.println("Debes proporcionar un argumento");
            mostrarAyuda();
        }

        if (argumento.equals("-h")){
            mostrarAyuda();
        }else{
            f = new File(argumento);

            if(f.exists()){
                
                if(f.isDirectory()){
                    System.out.println("Contenido del directorio: " + argumento);
                    archivos = f.list();

                    if(archivos != null && archivos.length > 0){
                        for(String archivo : archivos){
                            System.out.println(archivo);
                        }
                    }else{
                        System.out.println("Directorio vacio");
                    }

                }else if(f.isFile()){
                    System.out.println("Es un archivo: " + f.getName());
                }else{
                    System.out.println("Archivo o directorio no valido");
                }
            }else{
                System.out.println("El archivo o directorio no existe");
            }
        }
    }

    private static void mostrarAyuda() {
        
        System.out.println("Uso: java Mostrardirectorio ruta_o_archivo");
        System.out.println("Si proporciona una ruta muestra el contenido del directorio");
        System.out.println("Si proporciona un archivo, mostrara el nombre del archivo");
        System.out.println("'-h' muestra ayuda");
    }
}
