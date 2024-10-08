/*5- Crear un programa 'listar' al que se le pase en la línea de comandos una cadena que representa
   un patrón (formado por caracteres alfanuméricos,'.', '?', '*') y otra cadena que represente un nombre de ruta absoluta o nombre simple de un directorio. También sería correcto pasar un solo argumento siempre y cuando fuera la cadena "-h", que indicaría la ayuda.

   La salida mostrará los archivos o directorios del directorio indicado como segundo argumento que encajen con el patron indicado como primer argumento.

   En concreto la salida debe consistir en :

   -"El contenido del directorio" + ruta absoluta del directorio actual + " :"
   -cada línea debe empezar con 4 caracteres:
	-'d' si es un directorio, '-' si es un fichero
	-'r' si puede leerse, '-' en caso contrario
	-'w' si puede escribirse, '-' en caso contrario
	-'-' 
   -los nombres de archivos irán seguidos de su tamaño en bytes
 

   Ejemplos de llamada y salida mostrada:

   -listar e?e*s.odt

     El contenido del directorio /home/pub/fulanito es:

    drw- ejercicios.odt
    -r-- ejercicio.odt                873 bytes
    d-w- ejerciciosespirituales.odt
    -rw- elemento.odt                 2.048 bytes


Sugerencias: 
   -definir una excepción de programador "InvocacionExcepcion" cuya captura muestre la ayuda.
   -definir un método privado booleano 'comparar' que permita comparar un nombre de archivo con el patrón
   -métodos: Clase File: File(...),list(),
                           getName(), getPath(), getAbsolutePath(), canRead(), canWrite(), lenght(), isDirectory() o isFile(). */

                           /*1. Comodín *
Propósito en un patrón de archivos: El símbolo * en un patrón de archivos significa "cero o más caracteres". Por ejemplo, el patrón abc* coincide con cualquier archivo cuyo nombre comience con "abc" y tenga cero o más caracteres después, como abc, abc123, abcxyz, etc.

Expresión regular equivalente: En las expresiones regulares, .* significa exactamente lo mismo: "cero o más caracteres de cualquier tipo". El punto (.) en regex coincide con cualquier carácter, y el asterisco (*) indica "cero o más repeticiones" del carácter anterior. Por eso, convertimos * a .* en el patrón.

2. Comodín ?
Propósito en un patrón de archivos: El símbolo ? en un patrón de archivos representa "un único carácter cualquiera". Por ejemplo, el patrón a?c coincidirá con cualquier archivo que tenga "a" seguido de un carácter cualquiera y luego una "c", como abc, a1c, axc, etc.

Expresión regular equivalente: En regex, el punto (.) por sí solo significa "cualquier carácter único". Así que para el comodín ?, lo convertimos directamente a un . en regex, que tendrá el mismo efecto.

¿Por qué es necesario convertirlos?
Los caracteres * y ? son simplemente símbolos comunes que los usuarios entienden como "comodines" en un contexto de archivos (como cuando buscas en el Explorador de Windows o en la Terminal). Pero Java no entiende estos símbolos de esa manera a menos que se los traduzcamos a una forma que Java sí entienda, que en este caso son las expresiones regulares (regex).

Si no realizamos esta conversión, Java verá el patrón a?b* literalmente, y no entenderá que el ? y * deben tratarse como comodines.
Al convertirlos a regex, le decimos a Java exactamente lo que significan esos comodines:
? → . (cualquier carácter único)
* → .* (cero o más caracteres) */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Listar {
    public static void main(String[] args) {
        try {
            verificarArgumentos(args);

            if (args[0].equals("-h")) {
                mostrarAyuda();
                
            }

            String patron = args[0];
            String rutaDirectorio = args[1];
            File directorio = new File(rutaDirectorio);

            if (!directorio.exists()) {
                throw new FileNotFoundException("El directorio " + rutaDirectorio + " no existe.");
            }
            if (!directorio.isDirectory()) {
                throw new FileNotFoundException("El argumento " + rutaDirectorio + " no es un directorio.");
            }

            listarContenido(directorio, patron);

        } catch (InvocacionExcepcion e) {
            System.out.println(e.getMessage());
            mostrarAyuda();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    // Método para verificar los argumentos
    private static void verificarArgumentos(String[] args) throws InvocacionExcepcion {
        if (args.length == 0 || args.length > 2) {
            throw new InvocacionExcepcion("Número incorrecto de argumentos.");
        }
    }

    // Método para mostrar la ayuda
    private static void mostrarAyuda() {
        System.out.println("Uso: java Listar <patrón> <directorio>");
        System.out.println("Muestra el contenido del directorio cuyo nombre coincida con el patrón.");
        System.out.println("Patrón puede contener comodines como '*', '?', y '.'");
        System.out.println("Si usa '-h', se muestra esta ayuda.");
    }

    // Método para listar el contenido del directorio
    private static void listarContenido(File directorio, String patron) {
        // Convertir el patrón a una expresión regular
        String regex = convertirPatronARegex(patron);

        File[] archivos = directorio.listFiles();

        if (archivos != null && archivos.length > 0) {
            System.out.println("El contenido del directorio " + directorio.getAbsolutePath() + " es:");

            for (File archivo : archivos) {
                String nombreArchivo = archivo.getName();
                if (comparar(nombreArchivo, regex)) {
                    mostrarInfoArchivo(archivo);
                }
            }
        } else {
            System.out.println("El directorio está vacío o no se puede leer.");
        }
    }

    // Método para convertir el patrón con comodines en una expresión regular
    private static String convertirPatronARegex(String patron) {
        // Reemplazar los comodines `*` y `?` por su equivalente en regex
        return patron.replace(".", "\\.")
                     .replace("?", ".")
                     .replace("*", ".*");
    }

    // Método para comparar el nombre del archivo con el patrón (regex)
    private static boolean comparar(String nombreArchivo, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(nombreArchivo);
        return matcher.matches();
    }

    // Método para mostrar la información de un archivo o directorio
    private static void mostrarInfoArchivo(File archivo) {
        String tipo = archivo.isDirectory() ? "d" : "-";
        String permisoLectura = archivo.canRead() ? "r" : "-";
        String permisoEscritura = archivo.canWrite() ? "w" : "-";
        String placeholder = "-";
        long tamanio = archivo.isFile() ? archivo.length() : 0;

        System.out.printf("%s%s%s%s %s %s bytes%n", 
                tipo, permisoLectura, permisoEscritura, placeholder,
                archivo.getName(), tamanio > 0 ? tamanio : "");
    }
}

// Excepción personalizada para invocación incorrecta
class InvocacionExcepcion extends Exception {
    public InvocacionExcepcion(String mensaje) {
        super(mensaje);
    }
}

