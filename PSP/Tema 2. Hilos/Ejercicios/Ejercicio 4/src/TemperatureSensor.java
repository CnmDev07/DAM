import java.util.Random;

public class TemperatureSensor implements Runnable{
    
    @Override
    public void run(){

        Random  r = new Random();
        int temperatura = 15 + (r.nextInt(21));

        if (temperatura >= 20 && temperatura <= 30){
            System.out.println("Temperatura: " + temperatura);
        }else{
            System.out.println("La temperatura no esta en un rango aceptable");
        }
    }

}
