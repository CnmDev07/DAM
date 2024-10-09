public class Empleado {
    private String nombre;
    private int departamento;
    private String ciudad;
    private double salario;
    
    public Empleado(String nombre, int departamento, String ciudad, double salario) {
        this.nombre = nombre;
        this.departamento = departamento;
        this.ciudad = ciudad;
        this.salario = salario;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public int getDepartamento() {
        return departamento;
    }
    public void setDepartamento(int departamento) {
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
