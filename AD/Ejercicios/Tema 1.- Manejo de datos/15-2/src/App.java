import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;
import java.util.TreeMap;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.FileInputStream;
import java.io.File;

public class App {

    private static final String archivo_empleados = "empleados.dat";
    private static final String archivo_indice_codigo = "indice_codigo.dat";
    private static final String archivo_indice_nombre = "indice_nombre.dat"; 
    private static TreeMap<String, Long> indiceCodigoEmpleado = new TreeMap<>();
    private static TreeMap<String, Long> indiceNombreApellidos = new TreeMap<>();
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        cargarIndices();
        int opcion;
        do {
            mostrarMenu();
            opcion = sc.nextInt();
            sc.nextLine();
            switch (opcion) {
                case 1:
                    crearIndices();
                    break;
                case 2:
                    altaEmpleado(sc);
                    break;
                case 3:
                    bajaEmpleado(sc);
                    break;
                case 4:
                    modificarEmpleado(sc);
                    break;
                case 5:
                    consulta(sc);
                    break;
                case 6:
                    compactarArchivo();
                    break;
                case 0:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción no válida");
                    break;
            }
        } while (opcion != 0);
    }

    private static void mostrarMenu() {
        System.out.println("Menú:");
        System.out.println("1. Crear índice");
        System.out.println("2. Alta Empleado");
        System.out.println("3. Bajas");
        System.out.println("4. Modificaciones");
        System.out.println("5. Consultas");
        System.out.println("6. Reconstruir fichero de Datos");
        System.out.println("0. Salir");
        System.out.print("Elija una opción: ");
    }

    
    private static void crearIndices() {
        try (RandomAccessFile raf = new RandomAccessFile(archivo_empleados, "r");
            RandomAccessFile rafCodigo = new RandomAccessFile(archivo_indice_codigo, "rw");
            RandomAccessFile rafNombre = new RandomAccessFile(archivo_indice_nombre, "rw")
            ) {

            long posicion;
            indiceCodigoEmpleado.clear();
            indiceNombreApellidos.clear();

            
            

            while (raf.getFilePointer() < raf.length()) {
                posicion = raf.getFilePointer();
                String codigo = raf.readUTF();
                String nombre = raf.readUTF();
                int departamento = raf.readInt();
                String ciudad = raf.readUTF();
                double salario = raf.readDouble();
                int estado = raf.readInt();

                if (estado == 0) { // Solo indexar empleados activos
                    indiceCodigoEmpleado.put(codigo, posicion);
                    indiceNombreApellidos.put(nombre, posicion);
                }
            }

            escribirIndices(rafCodigo, rafNombre);
            

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    
    private static void escribirIndices(RandomAccessFile rafCodigo, RandomAccessFile rafNombre) throws IOException {
        
        rafCodigo.writeInt(indiceCodigoEmpleado.size());
        for (String codigo : indiceCodigoEmpleado.keySet()) {
            rafCodigo.writeUTF(codigo);
            rafCodigo.writeLong(indiceCodigoEmpleado.get(codigo));
        }

        
        rafNombre.writeInt(indiceNombreApellidos.size());
        for (String nombre : indiceNombreApellidos.keySet()) {
            rafNombre.writeUTF(nombre);
            rafNombre.writeLong(indiceNombreApellidos.get(nombre));
        }
    }
    private static void cargarIndices() {
        try (RandomAccessFile rafCodigo = new RandomAccessFile(archivo_indice_codigo, "r");
            RandomAccessFile rafNombre = new RandomAccessFile(archivo_indice_nombre, "r")
        ) {
            indiceCodigoEmpleado.clear();
            indiceNombreApellidos.clear();
    
            
            int tamanoCodigo = rafCodigo.readInt();  
            for (int i = 0; i < tamanoCodigo; i++) {
                String codigo = rafCodigo.readUTF();  
                long posicion = rafCodigo.readLong(); 
                indiceCodigoEmpleado.put(codigo, posicion); 
            }
    
            
            int tamanoNombre = rafNombre.readInt();  
            for (int i = 0; i < tamanoNombre; i++) {
                String nombre = rafNombre.readUTF();  
                long posicion = rafNombre.readLong(); 
                indiceNombreApellidos.put(nombre, posicion); 
            }
    
        } catch (IOException e) {
            System.out.println("No se encontraron índices, será necesario crearlos.");
        }
    }
    private static void escribirIndicesSerializados() {
        try (ObjectOutputStream oosCodigo = new ObjectOutputStream(new FileOutputStream(archivo_indice_codigo));
            ObjectOutputStream oosNombre = new ObjectOutputStream(new FileOutputStream(archivo_indice_nombre))
        ) {
            oosCodigo.writeObject(indiceCodigoEmpleado);  
            oosNombre.writeObject(indiceNombreApellidos);  
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static void cargarIndicesSerializados() {
        try (ObjectInputStream oisCodigo = new ObjectInputStream(new FileInputStream(archivo_indice_codigo));
            ObjectInputStream oisNombre = new ObjectInputStream(new FileInputStream(archivo_indice_nombre))
        ) {
            indiceCodigoEmpleado = (TreeMap<String, Long>) oisCodigo.readObject();  
            indiceNombreApellidos = (TreeMap<String, Long>) oisNombre.readObject();  
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("No se encontraron índices serializados, será necesario crearlos.");
        }
    }

    
    

    private static void altaEmpleado(Scanner sc) {
        System.out.println("Introduce código empleado:");
        String codigo = sc.nextLine();

        System.out.println("Introduce nombre:");
        String nombre = sc.nextLine();

        System.out.println("Introduce departamento:");
        int departamento = sc.nextInt();
        sc.nextLine();

        System.out.println("Introduce ciudad:");
        String ciudad = sc.nextLine();

        System.out.println("Introduce salario:");
        double salario = sc.nextDouble();
        sc.nextLine();

        try (RandomAccessFile raf = new RandomAccessFile(archivo_empleados, "rw");
            RandomAccessFile rafCodigo = new RandomAccessFile(archivo_indice_codigo, "rw");
            RandomAccessFile rafNombre = new RandomAccessFile(archivo_indice_nombre, "rw")
        ) {
            long pos = raf.length();
            raf.seek(pos);
            raf.writeUTF(codigo);
            raf.writeUTF(nombre);
            raf.writeInt(departamento);
            raf.writeUTF(ciudad);
            raf.writeDouble(salario);
            raf.writeInt(0); // Empleado activo (estado 0)

            
            indiceCodigoEmpleado.put(codigo, pos);
            indiceNombreApellidos.put(nombre, pos);

            escribirIndices(rafCodigo, rafNombre);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void bajaEmpleado(Scanner sc) {
        System.out.println("Introduce código del empleado a eliminar:");
        String codigo = sc.nextLine();

        if (indiceCodigoEmpleado.containsKey(codigo)) {
            long posicion = indiceCodigoEmpleado.get(codigo);
            marcarComoEliminado(posicion);
            System.out.println("Empleado eliminado correctamente.");
        } else {
            System.out.println("Código no encontrado.");
        }
    }

    private static void marcarComoEliminado(long posicion) {
        try (RandomAccessFile raf = new RandomAccessFile(archivo_empleados, "rw")) {
            raf.seek(posicion);
            
            raf.readUTF(); 
            raf.readUTF(); 
            raf.readInt(); 
            raf.readUTF(); 
            raf.readDouble(); 
            raf.writeInt(1);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void modificarEmpleado(Scanner sc) {
        System.out.println("Introduce código del empleado a modificar:");
        String codigo = sc.nextLine();

        if (indiceCodigoEmpleado.containsKey(codigo)) {
            long posicion = indiceCodigoEmpleado.get(codigo);
            modificarDatosEmpleado(posicion, sc);
        } else {
            System.out.println("Código no encontrado.");
        }
    }

    private static void modificarDatosEmpleado(long posicion, Scanner sc) {
        try (RandomAccessFile raf = new RandomAccessFile(archivo_empleados, "rw")) {
            raf.seek(posicion);
            
            String codigo = raf.readUTF();
            String nombre = raf.readUTF();
            int departamento = raf.readInt();
            String ciudad = raf.readUTF();
            double salario = raf.readDouble();
            int estado = raf.readInt();

            if (estado == 1) {
                System.out.println("Empleado eliminado, no se puede modificar.");
                return;
            }

            
            System.out.println("Introduce nuevo nombre (o presiona Enter para mantenerlo):");
            String nuevoNombre = sc.nextLine();
            if (!nuevoNombre.isEmpty()) {
                nombre = nuevoNombre;
            }

            System.out.println("Introduce nuevo departamento (o -1 para mantenerlo):");
            int nuevoDepartamento = sc.nextInt();
            sc.nextLine();
            if (nuevoDepartamento != -1) {
                departamento = nuevoDepartamento;
            }

            System.out.println("Introduce nueva ciudad (o presiona Enter para mantenerla):");
            String nuevaCiudad = sc.nextLine();
            if (!nuevaCiudad.isEmpty()) {
                ciudad = nuevaCiudad;
            }

            System.out.println("Introduce nuevo salario (o -1 para mantenerlo):");
            double nuevoSalario = sc.nextDouble();
            sc.nextLine();
            if (nuevoSalario != -1) {
                salario = nuevoSalario;
            }

            
            raf.seek(posicion);
            raf.writeUTF(codigo);
            raf.writeUTF(nombre);
            raf.writeInt(departamento);
            raf.writeUTF(ciudad);
            raf.writeDouble(salario);
            raf.writeInt(0); 

            System.out.println("Datos modificados correctamente.");

            
            crearIndices(); 

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void consulta(Scanner sc) {
        int opcion;
        System.out.println("1. Busca por código");
        System.out.println("2. Busca por nombre");
        opcion = sc.nextInt();
        sc.nextLine();

        switch (opcion) {
            case 1:
                System.out.println("Introduce código:");
                String codigo = sc.nextLine();
                if (indiceCodigoEmpleado.containsKey(codigo)) {
                    long posicion = indiceCodigoEmpleado.get(codigo);
                    mostrarEmpleado(posicion);
                } else {
                    System.out.println("No encontrado");
                }
                break;

            case 2:
                System.out.println("Introduce nombre:");
                String nombre = sc.nextLine();
                if (indiceNombreApellidos.containsKey(nombre)) {
                    long posicion = indiceNombreApellidos.get(nombre);
                    mostrarEmpleado(posicion);
                } else {
                    System.out.println("No encontrado");
                }
                break;

            default:
                System.out.println("Opción no válida.");
                break;
        }
    }

    private static void mostrarEmpleado(long posicion) {
        try (RandomAccessFile raf = new RandomAccessFile(archivo_empleados, "r")) {
            raf.seek(posicion);
            String codigo = raf.readUTF();
            String nombre = raf.readUTF();
            int departamento = raf.readInt();
            String ciudad = raf.readUTF();
            double salario = raf.readDouble();
            int estado = raf.readInt();

            if (estado == 1) {
                System.out.println("Empleado eliminado.");
            } else {
                System.out.println("Código: " + codigo + ", Nombre: " + nombre +
                        ", Departamento: " + departamento + ", Ciudad: " + ciudad +
                        ", Salario: " + salario);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    
    private static void compactarArchivo() {
        try (RandomAccessFile raf = new RandomAccessFile(archivo_empleados, "r");
            RandomAccessFile rafCompactado = new RandomAccessFile("empleados_compactados.dat", "rw")) {
    
            while (raf.getFilePointer() < raf.length()) {
                long posicion = raf.getFilePointer();
                String codigo = raf.readUTF();
                String nombre = raf.readUTF();
                int departamento = raf.readInt();
                String ciudad = raf.readUTF();
                double salario = raf.readDouble();
                int estado = raf.readInt();
    
                if (estado == 0) { 
                    rafCompactado.writeUTF(codigo);
                    rafCompactado.writeUTF(nombre);
                    rafCompactado.writeInt(departamento);
                    rafCompactado.writeUTF(ciudad);
                    rafCompactado.writeDouble(salario);
                    rafCompactado.writeInt(0); 
                }
            }
    
        } catch (IOException e) {
            e.printStackTrace();
        }
    
        
        File archivoOriginal = new File(archivo_empleados);
        File archivoCompactado = new File("empleados_compactados.dat");
    
        
        archivoOriginal.delete();
        archivoCompactado.renameTo(archivoOriginal);
        crearIndices();
    }
    
    
}
