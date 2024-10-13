public class Protocolo {
    private static final int ESPERANDO = 0;
    private static final int PUJANDO = 1;
    private static final int COMPROBANDO = 2;
    private static final int FIN = 3;

    private Boolean subastaActiva;
    private int estadoActual;

    public Protocolo() {
        this.estadoActual = ESPERANDO;
        this.subastaActiva = false;
    }

    public String changeState(String entrada) {
        String estado = null;

        if (estadoActual == ESPERANDO) {
            
            estado = "esperando";
            if (subastaActiva) 
                estadoActual = PUJANDO;

        } else if (estadoActual == PUJANDO) {
            if (subastaActiva) {
                estado = "pujando";
                estadoActual = COMPROBANDO;
            } else {
                estado = "terminada";
                estadoActual = FIN;
            }

        } else if (estadoActual == COMPROBANDO) {

            if (subastaActiva) {
                estado = "comprobando";
                estadoActual = PUJANDO;
            } else {
                estado = "terminada";
                estadoActual = FIN;
            }

        } else if (estadoActual == FIN) {

            estado = "adios";
            estadoActual = ESPERANDO;

        }

        return estado;
    }

    public Boolean getSubastaActiva() {
        return subastaActiva;
    }

    public void setSubastaActiva(Boolean subastaComenzada) {
        this.subastaActiva = subastaComenzada;
    }
}
