import org.w3c.dom.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.Scanner;

public class GestionEmpleados {

    private static final String FILE_PATH = "empleados.xml";
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean salir = false;

        while (!salir) {
            System.out.println("\n--- Menú de Gestión de Empleados ---");
            System.out.println("1. Alta (Agregar empleado)");
            System.out.println("2. Modificación (Modificar empleado)");
            System.out.println("3. Baja (Eliminar empleado)");
            System.out.println("4. Consulta (Listar empleados)");
            System.out.println("5. Salir");
            System.out.print("Elija una opción: ");
            
            int opcion = scanner.nextInt();
            scanner.nextLine();
            
            switch (opcion) {
                case 1:
                    altaEmpleado(scanner);
                    break;
                case 2:
                    modificarEmpleado(scanner);
                    break;
                case 3:
                    bajaEmpleado(scanner);
                    break;
                case 4:
                    consultarEmpleados();
                    break;
                case 5:
                    salir = true;
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        }
    }

    private static void altaEmpleado(Scanner scanner) {
        try {
            System.out.print("Ingrese el ID del empleado: ");
            String id = scanner.nextLine();
            System.out.print("Ingrese el nombre del empleado: ");
            String nombre = scanner.nextLine();
            System.out.print("Ingrese la edad del empleado: ");
            int edad = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Ingrese el departamento del empleado: ");
            String departamento = scanner.nextLine();

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document documento = builder.parse(new File(FILE_PATH));

            Element root = documento.getDocumentElement();

            Element empleado = documento.createElement("empleado");

            Element idElem = documento.createElement("id");
            idElem.appendChild(documento.createTextNode(id));
            empleado.appendChild(idElem);

            Element nombreElem = documento.createElement("nombre");
            nombreElem.appendChild(documento.createTextNode(nombre));
            empleado.appendChild(nombreElem);

            Element edadElem = documento.createElement("edad");
            edadElem.appendChild(documento.createTextNode(String.valueOf(edad)));
            empleado.appendChild(edadElem);

            Element depElem = documento.createElement("departamento");
            depElem.appendChild(documento.createTextNode(departamento));
            empleado.appendChild(depElem);

            root.appendChild(empleado);

            guardarDocumento(documento);

            System.out.println("Empleado agregado exitosamente.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void modificarEmpleado(Scanner scanner) {
        try {
            System.out.print("Ingrese el ID del empleado a modificar: ");
            String id = scanner.nextLine();

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document documento = builder.parse(new File(FILE_PATH));

            NodeList listaEmpleados = documento.getElementsByTagName("empleado");

            boolean encontrado = false;

            for (int i = 0; i < listaEmpleados.getLength(); i++) {
                Element empleado = (Element) listaEmpleados.item(i);
                String idActual = empleado.getElementsByTagName("id").item(0).getTextContent();

                if (idActual.equals(id)) {
                    encontrado = true;
                    System.out.println("Empleado encontrado: " + idActual);
                    
                    System.out.print("Nuevo nombre (dejar en blanco para mantener el actual): ");
                    String nuevoNombre = scanner.nextLine();
                    if (!nuevoNombre.isEmpty()) {
                        empleado.getElementsByTagName("nombre").item(0).setTextContent(nuevoNombre);
                    }

                    System.out.print("Nueva edad (dejar en blanco para mantener la actual): ");
                    String nuevaEdad = scanner.nextLine();
                    if (!nuevaEdad.isEmpty()) {
                        empleado.getElementsByTagName("edad").item(0).setTextContent(nuevaEdad);
                    }

                    System.out.print("Nuevo departamento (dejar en blanco para mantener el actual): ");
                    String nuevoDepto = scanner.nextLine();
                    if (!nuevoDepto.isEmpty()) {
                        empleado.getElementsByTagName("departamento").item(0).setTextContent(nuevoDepto);
                    }

                    guardarDocumento(documento);
                    System.out.println("Empleado modificado exitosamente.");
                    break;
                }
            }

            if (!encontrado) {
                System.out.println("Empleado con ID " + id + " no encontrado.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void bajaEmpleado(Scanner scanner) {
        try {
            System.out.print("Ingrese el ID del empleado a eliminar: ");
            String id = scanner.nextLine();

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document documento = builder.parse(new File(FILE_PATH));

            NodeList listaEmpleados = documento.getElementsByTagName("empleado");

            boolean encontrado = false;

            for (int i = 0; i < listaEmpleados.getLength(); i++) {
                Element empleado = (Element) listaEmpleados.item(i);
                String idActual = empleado.getElementsByTagName("id").item(0).getTextContent();

                if (idActual.equals(id)) {
                    encontrado = true;
                    empleado.getParentNode().removeChild(empleado);
                    guardarDocumento(documento);
                    System.out.println("Empleado eliminado exitosamente.");
                    break;
                }
            }

            if (!encontrado) {
                System.out.println("Empleado con ID " + id + " no encontrado.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void consultarEmpleados() {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document documento = builder.parse(new File(FILE_PATH));

            NodeList listaEmpleados = documento.getElementsByTagName("empleado");

            System.out.println("\n--- Lista de Empleados ---");

            for (int i = 0; i < listaEmpleados.getLength(); i++) {
                Element empleado = (Element) listaEmpleados.item(i);

                String id = empleado.getElementsByTagName("id").item(0).getTextContent();
                String nombre = empleado.getElementsByTagName("nombre").item(0).getTextContent();
                String edad = empleado.getElementsByTagName("edad").item(0).getTextContent();
                String departamento = empleado.getElementsByTagName("departamento").item(0).getTextContent();

                System.out.println("ID: " + id + ", Nombre: " + nombre + ", Edad: " + edad + ", Departamento: " + departamento);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void guardarDocumento(Document documento) {
        try {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(documento);
            StreamResult result = new StreamResult(new File(FILE_PATH));
            transformer.transform(source, result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
