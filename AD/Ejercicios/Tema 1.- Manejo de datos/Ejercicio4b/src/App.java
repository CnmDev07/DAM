import java.io.File;
import java.io.FileNotFoundException;
import java.util.Set;
import java.util.TreeSet;

public class App {
    public static void main(String[] args) throws Exception {
        String argumento;
        File f;
        File[] contenido;
        Set<String> archivos;
        Set<String> directorios;

        try{
            
            verificarArgumentos(args);
            argumento = args[0];
            f = new File(argumento);

            if (!f.exists()){
                throw new FileNotFoundException("El archivo o directorio no existe");
            }

            archivos = new TreeSet<>();
            directorios = new TreeSet<>();
            
            
                
            contenido = f.listFiles();

            if (contenido != null){
                for(File file: contenido){
                    if(file.isDirectory()){
                        directorios.add(file.getName());
                    }else if(file.isFile()){
                            archivos.add(file.getName());
                    }
                }
            }
                
            

            System.out.println("Directorios: ");
            for (String dir : directorios){
                System.out.println(dir);
            }
            System.out.println("Archivos: ");
            for(String arc : archivos){
                System.out.println(arc);
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