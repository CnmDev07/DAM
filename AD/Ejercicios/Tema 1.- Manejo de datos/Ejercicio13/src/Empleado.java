public class Empleado {
    private int id;
    private String nombre;
    private String departamento;
    private String ciudad; 
    double salario;
    
    public Empleado(int id, String nombre, String departamento, String ciudad, double salario){
        this.id = id;
        this.nombre = nombre;
        this.departamento = departamento;
        this.ciudad = ciudad;
        this.salario = salario;
        
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

}
