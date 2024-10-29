import java.io.File;
import java.util.LinkedList;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class Main {
    public static void main(String[] args) {
        try {
            File inputFile = new File("empleados.xml"); // Ruta del archivo XML
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            EmpleadoHandler handler = new EmpleadoHandler();
            saxParser.parse(inputFile, handler);

            LinkedList<Empleado> empleados = handler.getEmpleados();
            EmpleadoApp app = new EmpleadoApp(empleados);
            app.mostrarMenu();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
