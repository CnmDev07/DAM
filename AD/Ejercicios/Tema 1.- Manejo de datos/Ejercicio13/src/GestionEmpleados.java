import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GestionEmpleados {
    private final String archivoEmpleados = "empleados.dat";
    private List<Empleado> empleados = new ArrayList<>();
    private int siguienteID = 1;
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        GestionEmpleados gestion = new GestionEmpleados();
        gestion.cargarEmpleados();
        int opcion;

        do {
            gestion.menu();
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1 -> gestion.altaEmpleado();
                case 2 -> gestion.bajaEmpleado();
                case 3 -> gestion.modificarEmpleado();
                case 4 -> gestion.consultaEmpleado();
                case 5 -> gestion.listarEmpleados();
                case 6 -> gestion.guardar();
            }
        } while (opcion != 7);
    }

    private void menu() {
        System.out.println("Gestión de empleados");
        System.out.println("1. Alta de empleado");
        System.out.println("2. Baja de empleado");
        System.out.println("3. Modificación de empleado");
        System.out.println("4. Consulta empleado ");
        System.out.println("5. Lista de empleados");
        System.out.println("6. Salir");
        
    }

    private void cargarEmpleados() {
        try (DataInputStream in = new DataInputStream(new FileInputStream(archivoEmpleados))) {
            while (in.available() > 0) {
                int id = in.readInt();
                String nombre = in.readUTF();
                String departamento = in.readUTF();
                String ciudad = in.readUTF(); 
                double salario = in.readDouble();
                empleados.add(new Empleado(id, nombre, departamento, ciudad, salario));
                siguienteID = Math.max(siguienteID, id + 1); 
            }
        } catch (IOException e) {
            System.out.println("No se pudo cargar el archivo de empleados, posiblemente no existe.");
        }
    }

    private void guardar() {
        try (DataOutputStream out = new DataOutputStream(new FileOutputStream(archivoEmpleados))) {
            for (Empleado empleado : empleados) {
                out.writeInt(empleado.getId());
                out.writeUTF(empleado.getNombre());
                out.writeUTF(empleado.getDepartamento());
                out.writeUTF(empleado.getCiudad());
                out.writeDouble(empleado.getSalario());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void bajaEmpleado() {
        System.out.print("ID del empleado a eliminar: ");
        int idABorrar = sc.nextInt();
        sc.nextLine();
        boolean encontrado = empleados.removeIf(empleado -> empleado.getId() == idABorrar);
        if (encontrado) {
            System.out.println("Empleado eliminado correctamente.");
        } else {
            System.out.println("Empleado no encontrado.");
        }
    }

    private void modificarEmpleado() {
        System.out.print("ID del empleado a modificar: ");
        int idAModificar = sc.nextInt();
        sc.nextLine();
        for (Empleado empleado : empleados) {
            if (empleado.getId() == idAModificar) {
                System.out.println("Empleado encontrado. Ingrese los nuevos datos:");
                System.out.print("Nuevo departamento: ");
                empleado.setDepartamento(sc.nextLine());
                System.out.print("Nueva ciudad: ");
                empleado.setCiudad(sc.nextLine());
                System.out.print("Nuevo salario: ");
                empleado.setSalario(sc.nextDouble());
                sc.nextLine();
                System.out.println("Empleado modificado correctamente.");
                return;
            }
        }
        System.out.println("Empleado no encontrado.");
    }

    private void consultaEmpleado() {
        System.out.print("ID del empleado a consultar: ");
        int idConsulta = sc.nextInt();
        sc.nextLine();
        for (Empleado empleado : empleados) {
            if (empleado.getId() == idConsulta) {
                System.out.println("Empleado ID: " + empleado.getId());
                System.out.println("Nombre: " + empleado.getNombre());
                System.out.println("Departamento: " + empleado.getDepartamento());
                System.out.println("Ciudad: " + empleado.getCiudad());
                System.out.println("Salario: " + empleado.getSalario());
                return;
            }
        }
        System.out.println("Empleado no encontrado.");
    }

    private void listarEmpleados() {
        if (empleados.isEmpty()) {
            System.out.println("No hay empleados registrados.");
        } else {
            System.out.println("Listado de empleados:");
            for (Empleado empleado : empleados) {
                System.out.println("ID: " + empleado.getId() + ", Nombre: " + empleado.getNombre());
            }
        }
    }

    private void altaEmpleado() {
        System.out.print("Nombre del empleado: ");
        String nombre = sc.nextLine();
        System.out.print("Departamento: ");
        String departamento = sc.nextLine();
        System.out.print("Ciudad: ");
        String ciudad = sc.nextLine();
        System.out.print("Salario: ");
        double salario = sc.nextDouble();
        sc.nextLine();

        Empleado nuevoEmpleado = new Empleado(siguienteID++, nombre, departamento, ciudad, salario);
        empleados.add(nuevoEmpleado);
        guardar(); 
        System.out.println("Empleado agregado correctamente con ID: " + nuevoEmpleado.getId());
    }
}