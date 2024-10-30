/*18- Escribir un programa que permita guardar información sobre empleados en un archivo XML.
    El programa deberá leer un fichero XML con los datos 
    de los empleados y construir una LinkedList en memoria, 
    utilizando el estándar SAX. 
    
19- Ampliar el ejercicio anterior para mostrar un menú que permita altas, bajas, mostrar todos los empleados recorriendo la lista del primero al último y mostrar todos los empleados del último al primero. */
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
