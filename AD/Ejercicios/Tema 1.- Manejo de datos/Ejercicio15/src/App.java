/*15-Realizar una aplicación en Java que permita construir  dos Indices simples sobre  sobre el fichero 'Empleados';
    los indices serán relativos a los campos:

-CódigoEmpleado

-Nombre-Apellidos

El programa debe mostrar un menú con las siguientes opciones:

1-Crear índice

2-Alta Empleado

3-Bajas

4-Modificaciones

5-Consultas

6-Reconstruir fichero de Datos */
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;
import java.util.TreeMap;

public class App {

    private static final String archivo_empleados = "empleados.dat";
    private static final String indice_empleados = "indice.dat";
    private static TreeMap<String, Long> indiceCodigoEmpleado = new TreeMap<>();
    private static TreeMap<String, Long> indiceNombreApellidos = new TreeMap<>();
    private static Scanner sc = new Scanner(System.in);
    
    public static void main(String[] args) { // Corregido el nombre del main
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
                    
                    break;
                case 4:
                    
                    break;
                case 5:
                    consulta(sc);
                    break;
                case 6:
                    
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
        try (RandomAccessFile raf = new RandomAccessFile(indice_empleados, "r")) {
            long posicion;
            indiceCodigoEmpleado.clear();
            indiceNombreApellidos.clear();

            while (raf.getFilePointer() < raf.length()) {
                posicion = raf.getFilePointer();
                String codigo = raf.readUTF();
                String nombre = raf.readUTF();
                indiceCodigoEmpleado.put(codigo, posicion);
                indiceNombreApellidos.put(nombre, posicion);

                raf.readInt();  // Leo departamento sin usarlo
                raf.readUTF();  // Leo ciudad sin usarla
                raf.readDouble();  // Leo salario sin usarlo
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void consulta(Scanner sc) {
        int opcion;
        System.out.println("1. Busca por código");
        System.out.println("2. Busca por nombre");
        opcion = sc.nextInt();
        sc.nextLine();  // Consumir el salto de línea

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

    private static void altaEmpleado(Scanner sc) {
        System.out.println("Introduce código empleado:");
        String codigo = sc.nextLine();

        System.out.println("Introduce nombre:");
        String nombre = sc.nextLine();

        System.out.println("Introduce departamento:");
        int departamento = sc.nextInt();
        sc.nextLine();  // Consumir el salto de línea

        System.out.println("Introduce ciudad:");
        String ciudad = sc.nextLine();

        System.out.println("Introduce salario:");
        double salario = sc.nextDouble();
        sc.nextLine();  // Consumir el salto de línea

        try (RandomAccessFile raf = new RandomAccessFile(archivo_empleados, "rw")) {
            long pos = raf.length();  // Posición final del archivo
            raf.seek(pos);
            raf.writeUTF(codigo);
            raf.writeUTF(nombre);
            raf.writeInt(departamento);
            raf.writeUTF(ciudad);
            raf.writeDouble(salario);
            raf.writeInt(0); //Estado :0 (Activo)

            indiceCodigoEmpleado.put(codigo, pos);
            indiceNombreApellidos.put(nombre, pos);

        } catch (IOException e) {
            e.printStackTrace();
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

            System.out.println("Código: " + codigo + ", Nombre: " + nombre +
                    ", Departamento: " + departamento + ", Ciudad: " + ciudad +
                    ", Salario: " + salario);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
