import java.util.LinkedList;
import java.util.Scanner;

public class EmpleadoApp {
    private LinkedList<Empleado> empleados;
    private Scanner scanner;

    public EmpleadoApp(LinkedList<Empleado> empleados) {
        this.empleados = empleados;
        this.scanner = new Scanner(System.in);
    }

    public void mostrarMenu() {
        while (true) {
            System.out.println("\n1. Alta de empleado");
            System.out.println("2. Baja de empleado");
            System.out.println("3. Mostrar todos los empleados (de primero a último)");
            System.out.println("4. Mostrar todos los empleados (de último a primero)");
            System.out.println("5. Salir");
            System.out.print("Selecciona una opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine();  

            switch (opcion) {
                case 1:
                    altaEmpleado();
                    break;
                case 2:
                    bajaEmpleado();
                    break;
                case 3:
                    mostrarEmpleadosDePrimeroAUltimo();
                    break;
                case 4:
                    mostrarEmpleadosDeUltimoAPrimero();
                    break;
                case 5:
                    System.out.println("Saliendo...");
                    return;
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }

    private void altaEmpleado() {
        System.out.print("ID: ");
        String id = scanner.nextLine();
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Departamento: ");
        String departamento = scanner.nextLine();
        System.out.print("Salario: ");
        double salario = scanner.nextDouble();
        empleados.add(new Empleado(id, nombre, departamento, salario));
        System.out.println("Empleado añadido.");
    }

    private void bajaEmpleado() {
        System.out.print("Introduce el ID del empleado a eliminar: ");
        String id = scanner.nextLine();
        boolean encontrado = false;

        for (Empleado empleado : empleados) {
            if (empleado.getId().equals(id)) {
                empleados.remove(empleado);
                encontrado = true;
                System.out.println("Empleado eliminado.");
                break;
            }
        }

        if (!encontrado) {
            System.out.println("Empleado no encontrado.");
        }
    }

    private void mostrarEmpleadosDePrimeroAUltimo() {
        if (empleados.isEmpty()) {
            System.out.println("No hay empleados.");
            return;
        }
        for (Empleado empleado : empleados) {
            System.out.println(empleado);
        }
    }

    private void mostrarEmpleadosDeUltimoAPrimero() {
        if (empleados.isEmpty()) {
            System.out.println("No hay empleados.");
            return;
        }
        for (int i = empleados.size() - 1; i >= 0; i--) {
            System.out.println(empleados.get(i));
        }
    }
}
