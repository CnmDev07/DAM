class Numeros extends Thread {
    @Override
    public void run() {
        for (int i = 1; i <= 100; i++) {
            
            if (isInterrupted()) {
                System.out.println("Hilo Numeros interrumpido en el número " + i);
                if (i % 2 == 0) {
                    System.out.println(i + " es par, el hilo termina.");
                    break;
                } else {
                    System.out.println(i + " es impar, el hilo continúa.");
                }
            }
            System.out.println(i);
            try {
                Thread.sleep(1000); 
            } catch (InterruptedException e) {
                interrupt(); 
            }
        }
    }
}