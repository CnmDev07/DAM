import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class TimerTaskTest extends Thread{
    public static void main(String[] args) throws ParseException, InterruptedException {

        /** Con delay long */
        LocalTime ahora = LocalTime.of(13, 40);
        LocalTime aLasDoce = LocalTime.of(13, 41);
        Duration duration = Duration.between(ahora, aLasDoce);
        long delay = duration.toMillis();

        /* Con un objeto date */
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = df.parse("2024-03-07 11:56:00");

        List<Thread> workers = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            Hilo h = new Hilo(i);
            Thread thread = new Thread(h);
            workers.add(thread);
        }

        TimerTaskTest tj = new TimerTaskTest();
        tj.start();
        tj.wait(delay);

        /*
         * Ejemplo de uso de la calse Timer y TimerTask
         * Previamente se han lanzado 4 hilos y luego de
         * transurrido un tiempo se interrumplen. Esto se hace
         * cuando "salta" el temporizador
         */
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                for (Thread thread : workers)
                    thread.start();
                for (Thread h : workers) {
                    System.out.println("Interrumpendo " + h);
                    h.interrupt();
                }
            }
        };

        // Con objeto date
        timer.schedule(timerTask,date);

        for (Thread thread : workers)
            thread.join();

        timer.cancel();
        timer.purge();
    }
}
