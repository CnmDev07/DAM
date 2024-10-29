public class Empleado{

    private String id;
    private String nombre;
    private String departamento;
    private double salario;
    
    public Empleado(String id, String nombre, String departamento, double salario) {
        this.id = id;
        this.nombre = nombre;
        this.departamento = departamento;
        this.salario = salario;
    }
    
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getDepartamento() {
        return departamento;
    }
    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }
    public double getSalario() {
        return salario;
    }
    public void setSalario(double salario) {
        this.salario = salario;
    }
    
    @Override
    public String toString(){
        return "Empleado ID " + id + ", Nombre: " + nombre + ", Departamento: " + departamento + ", Salario: " + salario;
    }

        

}