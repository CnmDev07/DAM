public class Hilo implements Runnable {
    private int id;

    public Hilo(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            System.out.println("Hola " + id);
        }
    }

    @Override
    public String toString() {
        return "Hilo [id=" + id + "]";
    }

    public int getId() {
        return id;
    }

}
