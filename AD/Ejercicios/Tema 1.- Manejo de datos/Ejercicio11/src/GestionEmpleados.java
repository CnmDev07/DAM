import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GestionEmpleados {

    private static final String nombreArchivo = "Empleados.txt";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n--- Menú de Gestión de Empleados ---");
            System.out.println("1. Alta de empleado");
            System.out.println("2. Baja de empleado");
            System.out.println("3. Modificación de empleado");
            System.out.println("4. Consulta de empleados");
            System.out.println("5. Salir");
            System.out.print("Elige una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    altaEmpleado(scanner);
                    break;
                case 2:
                    bajaEmpleado(scanner);
                    break;
                case 3:
                    modificarEmpleado(scanner);
                    break;
                case 4:
                    consultarEmpleados();
                    break;
                case 5:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, elige otra opción.");
            }
        } while (opcion != 5);

        scanner.close();
    }

    private static void altaEmpleado(Scanner scanner) {
        System.out.print("Introduce el nombre del empleado: ");
        String nombre = scanner.nextLine();
        System.out.print("Introduce el primer apellido: ");
        String apellido1 = scanner.nextLine();
        System.out.print("Introduce el segundo apellido: ");
        String apellido2 = scanner.nextLine();
        System.out.print("Introduce el departamento: ");
        String departamento = scanner.nextLine();
        System.out.print("Introduce la ciudad: ");
        String ciudad = scanner.nextLine();
        System.out.print("Introduce el salario: ");
        double salario = scanner.nextDouble();
        scanner.nextLine(); 

        String empleado = String.join(",", nombre, apellido1, apellido2, departamento, ciudad, String.valueOf(salario));

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo, true))) {
            writer.write(empleado);
            writer.newLine();
            System.out.println("Empleado añadido correctamente.");
        } catch (IOException e) {
            System.out.println("Error al añadir el empleado: " + e.getMessage());
        }
    }

    private static void bajaEmpleado(Scanner scanner) {
        System.out.print("Introduce el nombre del empleado a eliminar (Nombre, Apellido1, Apellido2): ");
        String nombreCompleto = scanner.nextLine();
        List<String> empleados = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(nombreArchivo))) {
            String line;
            boolean encontrado = false;

            while ((line = reader.readLine()) != null) {
                if (!line.startsWith(nombreCompleto + ",")) {
                    empleados.add(line); 
                } else {
                    encontrado = true;
                }
            }

            if (encontrado) {
                
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo))) {
                    for (String empleado : empleados) {
                        writer.write(empleado);
                        writer.newLine();
                    }
                    System.out.println("Empleado eliminado correctamente.");
                }
            } else {
                System.out.println("Empleado no encontrado.");
            }

        } catch (IOException e) {
            System.out.println("Error al eliminar el empleado: " + e.getMessage());
        }
    }

    private static void modificarEmpleado(Scanner scanner) {
        System.out.print("Introduce el nombre del empleado a modificar (Nombre, Apellido1, Apellido2): ");
        String nombreCompleto = scanner.nextLine();
        List<String> empleados = new ArrayList<>();
        boolean encontrado = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(nombreArchivo))) {
            String line;

            while ((line = reader.readLine()) != null) {
                if (line.startsWith(nombreCompleto + ",")) {
                    encontrado = true; 
                    System.out.print("Introduce el nuevo nombre: ");
                    String nuevoNombre = scanner.nextLine();
                    System.out.print("Introduce el nuevo primer apellido: ");
                    String nuevoApellido1 = scanner.nextLine();
                    System.out.print("Introduce el nuevo segundo apellido: ");
                    String nuevoApellido2 = scanner.nextLine();
                    System.out.print("Introduce el nuevo departamento: ");
                    String nuevoDepartamento = scanner.nextLine();
                    System.out.print("Introduce la nueva ciudad: ");
                    String nuevaCiudad = scanner.nextLine();
                    System.out.print("Introduce el nuevo salario: ");
                    double nuevoSalario = scanner.nextDouble();
                    scanner.nextLine(); 

                    
                    String nuevoEmpleado = String.join(",", nuevoNombre, nuevoApellido1, nuevoApellido2, nuevoDepartamento, nuevaCiudad, String.valueOf(nuevoSalario));
                    empleados.add(nuevoEmpleado); 
                } else {
                    empleados.add(line); 
                }
            }

            if (encontrado) {
                
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo))) {
                    for (String empleado : empleados) {
                        writer.write(empleado);
                        writer.newLine();
                    }
                    System.out.println("Empleado modificado correctamente.");
                }
            } else {
                System.out.println("Empleado no encontrado.");
            }

        } catch (IOException e) {
            System.out.println("Error al modificar el empleado: " + e.getMessage());
        }
    }

    private static void consultarEmpleados() {
        try (BufferedReader reader = new BufferedReader(new FileReader(nombreArchivo))) {
            String line;

            System.out.println("\n--- Lista de Empleados ---");
            while ((line = reader.readLine()) != null) {
                System.out.println(line); 
            }
        } catch (IOException e) {
            System.out.println("Error al consultar empleados: " + e.getMessage());
        }
    }
}
