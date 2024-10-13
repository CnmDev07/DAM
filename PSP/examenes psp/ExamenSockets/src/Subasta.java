import java.time.LocalDate;

public class Subasta {
    private String nombre;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private int precioInicial;
    private int precioFinal;
    private int precioFinalMinimo;
    private HiloCliente pujanteGanador;
    private int turno;
    private long maxPujantes;
    private long numPujantes;

    public Subasta(String nombre, int precioInicial, int precioFinalMinimo, long maxPujantes) {
        this.nombre = nombre;
        this.precioInicial = precioInicial;
        this.precioFinal = this.precioInicial;
        this.precioFinalMinimo = precioFinalMinimo;
        this.pujanteGanador = new HiloCliente();
        this.turno = 1;
        this.maxPujantes = maxPujantes;
        this.numPujantes = 0;
    }

    public synchronized String pujar(HiloCliente pujante) throws InterruptedException {

        String respuesta = "";
        String estado = pujante.getInput().split(";")[0];
        int precioPropuesto = Integer.parseInt(pujante.getInput().split(estado)[1]);

        switch (estado) {
            case "esperando":
                respuesta = "esperando que empiece la subasta del articulo " + this.nombre + ". Precio inicial: "
                        + this.precioInicial;
                break;

            case "pujando":

                while (this.turno != pujante.getId()) {
                    respuesta = "Turno del pujante: " + pujante.getNombre();
                    wait();
                }
                respuesta = "¿Cuanto desea apostar por el articulo subastado?";
                break;

            case "comprobando":
                if (precioPropuestoCorrecto(precioPropuesto)|| precioPropuesto == -1) {
                    this.precioFinal = precioPropuesto;
                    this.pujanteGanador = pujante;
                    if (this.turno != pujante.getId()) {
                        respuesta = "El pujante " + pujante.getNombre() + " aumento el precio del producto a "
                                + this.precioFinal;
                    } else {
                        respuesta = "Puja correcta. El precio de subasta del articulo actual aumento a "
                                + this.precioFinal;
                    }
                } else {
                    respuesta = "Puja incorrecta del pujante";
                }

                if (this.turno == this.numPujantes) {
                    this.turno = 1;
                } else {
                    this.turno++;
                }
                notifyAll();

                break;

            default:
                if (pujante.equals(this.pujanteGanador)) {
                    respuesta = "Felicidades adquiriste el articulo " + this.nombre + " por un precio de "
                            + this.precioFinal;
                } else {
                    respuesta = "Lo sentimos el articulo " + this.nombre + " se vendio al comprador "
                            + this.pujanteGanador + " por " + this.precioFinal;
                }
                break;
        }

        return respuesta;
    }

    private Boolean precioPropuestoCorrecto(int precioPropuesto) {
        Boolean correcto = false;

        if (precioPropuesto > this.precioFinal)
            correcto = true;

        return correcto;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPrecioInicial() {
        return precioInicial;
    }

    public void setPrecioInicial(int precioInicial) {
        this.precioInicial = precioInicial;
    }

    public int getPrecioFinal() {
        return precioFinal;
    }

    public void setPrecioFinal(int precioFinal) {
        this.precioFinal = precioFinal;
    }

    public HiloCliente getPujanteGanador() {
        return pujanteGanador;
    }

    public void setPujanteGanador(HiloCliente pujanteGanador) {
        this.pujanteGanador = pujanteGanador;
    }

    public int getTurno() {
        return turno;
    }

    public void setTurno(int turno) {
        this.turno = turno;
    }

    public long getMaxPujantes() {
        return maxPujantes;
    }

    public void setMaxPujantes(long maxPujantes) {
        this.maxPujantes = maxPujantes;
    }

    public long getNumPujantes() {
        return numPujantes;
    }

    public void setNumPujantes(long numPujantes) {
        this.numPujantes = numPujantes;
    }
}
