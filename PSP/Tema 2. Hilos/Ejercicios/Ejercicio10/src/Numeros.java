public class Numeros extends Thread{
    
    @Override
    public void run(){

        try{

            for(int i = 1; i<= 100; i++){

                System.out.println(i);
                Thread.sleep(1000);
                if(isInterrupted()){

                    if(i % 2 == 0){

                        System.out.println(i + " Es par");

                    }else{
                        System.out.println(i + " Es impar");
                    }

                }
            }

        }catch(InterruptedException e){
            interrupt();
            System.out.println("Clase Numeros");
            
        }

    }

}
