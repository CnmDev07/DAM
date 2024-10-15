import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class App {

    private static final String archivo_empleados = "empleados.dat";
    private static final String archivo_indices_codigo = "indice_codigo.dat";
    private static final String archivo_indices_nombre = "indice_nombre.dat";
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        crearIndices();
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
                    System.out.println("Adiós");
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
        System.out.println("6. Compactar archivo");
        System.out.println("0. Salir");
        System.out.print("Elija una opción: ");
    }

    private static void crearIndices() {
        try (RandomAccessFile rafDatos = new RandomAccessFile(archivo_empleados, "r");
            RandomAccessFile rafIndiceCodigo = new RandomAccessFile(archivo_indices_codigo, "rw");
            RandomAccessFile rafIndiceNombre = new RandomAccessFile(archivo_indices_nombre, "rw")) {

            long posicion;
            
            rafIndiceCodigo.setLength(0);
            rafIndiceNombre.setLength(0);

            while (rafDatos.getFilePointer() < rafDatos.length()) {
                posicion = rafDatos.getFilePointer();
                String codigo = rafDatos.readUTF();
                String nombre = rafDatos.readUTF();
                rafDatos.readInt();  
                rafDatos.readUTF();  
                rafDatos.readDouble();  
                rafDatos.readInt();  

                
                rafIndiceCodigo.writeUTF(codigo);
                rafIndiceCodigo.writeLong(posicion);

                
                rafIndiceNombre.writeUTF(nombre);
                rafIndiceNombre.writeLong(posicion);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static long buscarPorCodigo(String codigoBuscado) {
        try (RandomAccessFile rafIndice = new RandomAccessFile(archivo_indices_codigo, "r")) {
            while (rafIndice.getFilePointer() < rafIndice.length()) {
                String codigo = rafIndice.readUTF();
                long posicion = rafIndice.readLong();

                if (codigo.equals(codigoBuscado)) {
                    return posicion;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return -1;
    }

    private static long buscarPorNombre(String nombreBuscado) {
        try (RandomAccessFile rafIndice = new RandomAccessFile(archivo_indices_nombre, "r")) {
            while (rafIndice.getFilePointer() < rafIndice.length()) {
                String nombre = rafIndice.readUTF();
                long posicion = rafIndice.readLong();

                if (nombre.equals(nombreBuscado)) {
                    return posicion;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return -1;
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
                long posicionCodigo = buscarPorCodigo(codigo);
                if (posicionCodigo != -1) {
                    mostrarEmpleado(posicionCodigo);
                } else {
                    System.out.println("No encontrado");
                }
                break;

            case 2:
                System.out.println("Introduce nombre:");
                String nombre = sc.nextLine();
                long posicionNombre = buscarPorNombre(nombre);
                if (posicionNombre != -1) {
                    mostrarEmpleado(posicionNombre);
                } else {
                    System.out.println("No encontrado");
                }
                break;

            default:
                System.out.println("Opción no válida.");
                break;
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

        try (RandomAccessFile rafDatos = new RandomAccessFile(archivo_empleados, "rw");
            RandomAccessFile rafIndiceCodigo = new RandomAccessFile(archivo_indices_codigo, "rw");
            RandomAccessFile rafIndiceNombre = new RandomAccessFile(archivo_indices_nombre, "rw")) {

            long pos = rafDatos.length();  
            rafDatos.seek(pos);
            rafDatos.writeUTF(codigo);
            rafDatos.writeUTF(nombre);
            rafDatos.writeInt(departamento);
            rafDatos.writeUTF(ciudad);
            rafDatos.writeDouble(salario);
            rafDatos.writeInt(0); // Estado: 0 significa activo

            
            rafIndiceCodigo.seek(rafIndiceCodigo.length());
            rafIndiceCodigo.writeUTF(codigo);
            rafIndiceCodigo.writeLong(pos);

            rafIndiceNombre.seek(rafIndiceNombre.length());
            rafIndiceNombre.writeUTF(nombre);
            rafIndiceNombre.writeLong(pos);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void bajaEmpleado(Scanner sc) {
        System.out.println("Introduce código del empleado a eliminar:");
        String codigo = sc.nextLine();
        long posicion = buscarPorCodigo(codigo);

        if (posicion != -1) {
            try (RandomAccessFile rafDatos = new RandomAccessFile(archivo_empleados, "rw")) {
                rafDatos.seek(posicion);
                rafDatos.readUTF();  
                rafDatos.readUTF();  
                rafDatos.readInt();  
                rafDatos.readUTF();  
                rafDatos.readDouble();  
                rafDatos.writeInt(1);  

                System.out.println("Empleado marcado como eliminado.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Empleado no encontrado.");
        }
    }

    private static void modificarEmpleado(Scanner sc) {
        System.out.println("Introduce código del empleado a modificar:");
        String codigo = sc.nextLine();
        long posicion = buscarPorCodigo(codigo);

        if (posicion != -1) {
            try (RandomAccessFile rafDatos = new RandomAccessFile(archivo_empleados, "rw")) {
                rafDatos.seek(posicion);
                rafDatos.readUTF();  
                System.out.println("Introduce nuevo nombre:");
                String nombre = sc.nextLine();
                rafDatos.writeUTF(nombre);
                

                System.out.println("Empleado modificado exitosamente.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Empleado no encontrado.");
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

            if (estado == 0) {
                System.out.println("Código: " + codigo + ", Nombre: " + nombre +
                        ", Departamento: " + departamento + ", Ciudad: " + ciudad +
                        ", Salario: " + salario);
            } else {
                System.out.println("Este empleado ha sido eliminado.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void compactarArchivo() {
        String archivoTemporal = "empleados_compactado.dat";

        try (RandomAccessFile rafOriginal = new RandomAccessFile(archivo_empleados, "r");
            RandomAccessFile rafNuevo = new RandomAccessFile(archivoTemporal, "rw");
            RandomAccessFile rafIndiceCodigo = new RandomAccessFile(archivo_indices_codigo, "rw");
            RandomAccessFile rafIndiceNombre = new RandomAccessFile(archivo_indices_nombre, "rw")) {

            long nuevaPosicion;
            
            rafIndiceCodigo.setLength(0);
            rafIndiceNombre.setLength(0);

            while (rafOriginal.getFilePointer() < rafOriginal.length()) {
                long posicionOriginal = rafOriginal.getFilePointer();
                String codigo = rafOriginal.readUTF();
                String nombre = rafOriginal.readUTF();
                int departamento = rafOriginal.readInt();
                String ciudad = rafOriginal.readUTF();
                double salario = rafOriginal.readDouble();
                int estado = rafOriginal.readInt();

                if (estado == 0) { 
                    nuevaPosicion = rafNuevo.getFilePointer();
                    rafNuevo.writeUTF(codigo);
                    rafNuevo.writeUTF(nombre);
                    rafNuevo.writeInt(departamento);
                    rafNuevo.writeUTF(ciudad);
                    rafNuevo.writeDouble(salario);
                    rafNuevo.writeInt(estado);

                    
                    rafIndiceCodigo.writeUTF(codigo);
                    rafIndiceCodigo.writeLong(nuevaPosicion);

                    rafIndiceNombre.writeUTF(nombre);
                    rafIndiceNombre.writeLong(nuevaPosicion);
                }
            }

            
            rafNuevo.close();
            rafOriginal.close();

            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
