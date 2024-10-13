import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Properties;
import java.util.Scanner;

public class Player {

    private String ip;
    private int port;

    public Player() throws RightPriceException {
        connect();
    }

    public static void main(String[] args) {
        try {
            Player p = new Player();
            p.play();
        } catch (RightPriceException e) {
            System.out.println(e.getMessage());
        }
    }

    private void play() throws RightPriceException {
        try (Socket cliente = new Socket(ip, port);
                DataInputStream dInput = new DataInputStream(cliente.getInputStream());
                DataOutputStream dOutput = new DataOutputStream(cliente.getOutputStream());
                Scanner sc = new Scanner(System.in)) {
            String in = "";
            String guess = "";
            boolean keepPlaying = true;
            boolean idShowed = false;
            boolean finalMsgShowed = false;
            System.out.println("Bienvenido.");
            while (keepPlaying) {
                in = dInput.readUTF();
                int state = Integer.parseInt(in.split(";")[0]);
                int id = Integer.parseInt(in.split(";")[1]);
                int turn = 0;
                boolean firstGame = true;
                switch (state) {
                    case Protocol.WAITING:
                        firstGame = Boolean.parseBoolean(in.split(";")[3]);
                        if (firstGame) {
                            int numPlayers = Integer.parseInt(in.split(";")[2]);
                            int maxPlayers = Integer.parseInt(in.split(";")[4]);
                            System.out.println("Esperando jugadores... (" + numPlayers + "/" + maxPlayers + ")");
                        }
                        break;

                    case Protocol.PLAYING:
                        System.out.println("Tu respuesta fue escuchada.");
                        break;

                    case Protocol.W_RESULT:
                        turn = Integer.parseInt(in.split(";")[2]);
                        boolean roundEnded = Boolean.parseBoolean(in.split(";")[3]);
                        if (!roundEnded) {
                            if (turn != id) {
                                System.out.println("Ronda en curso. Turno del jugador: " + turn);
                            } else {
                                String productName = in.split(";")[4];
                                System.out.println("Es tu turno.");
                                System.out.println(
                                        "Introduce cuanto supones que vale el producto(" + productName + "): ");
                                guess = sc.nextLine();
                                while (!isDouble(guess)) {
                                    System.out.println("Eso no es un precio. Prueba de nuevo: ");
                                    guess = sc.nextLine();
                                }
                                dOutput.writeUTF(guess);
                            }
                        } else {
                            System.out.println("Fin de ronda");
                        }
                        break;

                    case Protocol.RESULT:

                        boolean end = Boolean.getBoolean(in.split(";")[5]);
                        if (end) {
                            turn = Integer.parseInt(in.split(";")[6]);
                            if (!finalMsgShowed) {
                                String gameWinners = in.split(";")[7];
                                if (gameWinners.length() > 1) {
                                    System.out.println("Hubo un empate entre los jugadores: " + gameWinners);
                                } else if (gameWinners.equals("" + id)) {
                                    System.out.println("GANASTE ENHORABUENA");
                                } else {
                                    System.out.println("PERDISTE. Lo semtimos mucho");
                                }
                                finalMsgShowed = true;
                            }

                            if (turn != id) {
                                System.out
                                        .println("El jugador " + turn + " esta decidiendo si quiere volver a jugar...");

                            } else {
                                System.out.println("Quieres volver a jugar? (S/N):");
                                String decision = sc.nextLine();
                                while (!decision.equalsIgnoreCase("s") && !decision.equalsIgnoreCase("n")) {
                                    System.out.println("Tu decision no es un valor valido responde de nuevo (S/N): ");
                                    decision = sc.nextLine();
                                }
                                dOutput.writeUTF(decision);
                            }
                        } else {
                            String roundWinners = in.split(";")[2];
                            String productName = in.split(";")[3];
                            double productPrice = Double.parseDouble(in.split(";")[4]);
                            System.out.println(
                                    "El precio del producto \"" + productName + "\" era de: " + productPrice);
                            if (roundWinners.length() > 1) {
                                System.out.println("Hubo un empate entre los jugadores: " + roundWinners);
                            } else if (roundWinners.equals("" + id)) {
                                System.out.println("Ganaste esta ronda");
                            } else {
                                System.out.println("Perdiste esta ronda");
                            }
                        }
                        break;

                    case Protocol.END:
                        String finalDecision = in.split(";")[2];
                        if (finalDecision.equals("not_decided")) {
                            turn = Integer.parseInt(in.split(";")[3]);
                            System.out.println("El jugador " + turn + " esta decidiendo si quiere volver a jugar...");
                        } else if (finalDecision.equals("again")) {
                            System.out.println("Comienza una nueva partida.");
                            System.out.println("Cambio de turno...");
                            idShowed = false;
                            finalMsgShowed = false;
                        } else {
                            System.out.println("Adios. Gracias por jugar.");
                            keepPlaying = false;
                        }
                        break;

                    default:
                        break;
                }
            }

        } catch (IOException e) {
            throw new RightPriceException("No se puede conectar con el servidor.");
        }
    }

    private boolean isDouble(String playerIn) {
        boolean valid = true;
        try {
            Double.parseDouble(playerIn);
        } catch (NumberFormatException e) {
            valid = false;
        }
        return valid;
    }

    private void connect() throws RightPriceException {
        try {
            Properties conf = new Properties();
            conf.load(new FileInputStream("src/config/player.properties"));
            port = Integer.parseInt(conf.getProperty("PORT"));
            ip = conf.getProperty("IP");
        } catch (IOException e) {
            throw new RightPriceException("No se pudo leer el archivo de propiedades.");
        }
    }
}
