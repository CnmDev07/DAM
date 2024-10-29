import java.util.LinkedList;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class EmpleadoHandler extends DefaultHandler {
    private LinkedList<Empleado> empleados;
    private Empleado empleadoActual;
    private StringBuilder contenido;

    public EmpleadoHandler() {
        empleados = new LinkedList<>();
        contenido = new StringBuilder();
    }

    public LinkedList<Empleado> getEmpleados() {
        return empleados;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equals("empleado")) {
            empleadoActual = new Empleado("", "", "", 0);
        }
        contenido.setLength(0);
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        contenido.append(ch, start, length);
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        switch (qName) {
            case "id":
                empleadoActual = new Empleado(contenido.toString(), empleadoActual.getNombre(), empleadoActual.getDepartamento(), empleadoActual.getSalario());
                break;
            case "nombre":
                empleadoActual = new Empleado(empleadoActual.getId(), contenido.toString(), empleadoActual.getDepartamento(), empleadoActual.getSalario());
                break;
            case "departamento":
                empleadoActual = new Empleado(empleadoActual.getId(), empleadoActual.getNombre(), contenido.toString(), empleadoActual.getSalario());
                break;
            case "salario":
                empleadoActual = new Empleado(empleadoActual.getId(), empleadoActual.getNombre(), empleadoActual.getDepartamento(), Double.parseDouble(contenido.toString()));
                break;
            case "empleado":
                empleados.add(empleadoActual);
                break;
        }
    }
}
