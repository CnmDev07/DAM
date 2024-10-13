import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class PlayerHandler extends Thread {

    private int playerId;

    private boolean decided;
    private boolean idChanged;

    private Socket socket;
    private Game game;
    private Protocol protocol;

    public PlayerHandler(int id, Socket socket, Game game) {
        this.playerId = id + 1;
        this.decided = false;
        this.idChanged = true;
        this.socket = socket;
        this.game = game;
        this.protocol = new Protocol();
    }

    @Override
    public void run() {
        try {
            play();
        } catch (RightPriceException e) {
            System.out.println(e.getMessage());
        }
    }

    private void play() throws RightPriceException {
        try (DataInputStream dInput = new DataInputStream(socket.getInputStream());
                DataOutputStream dOutput = new DataOutputStream(socket.getOutputStream());) {
            String out = "";
            String in = "";
            while (true) {
                switch (protocol.getState()) {
                    case Protocol.WAITING:
                        decided = false;
                        out = game.playGame(this, null, null);
                        dOutput.writeUTF(out);
                        protocol.changeState(game.isFull(), game.isYourTurn(this), game.isRoundEnded(),
                                game.isEnd(), game.wannaPlayAgain());
                        game.waitPlayers();
                        break;

                    case Protocol.PLAYING:
                        in = dInput.readUTF();
                        out = game.playGame(this, in, null);
                        dOutput.writeUTF(out);
                        protocol.changeState(game.isFull(), game.isYourTurn(this), game.isRoundEnded(),
                                game.isEnd(), game.wannaPlayAgain());
                        break;

                    case Protocol.W_RESULT:
                        out = game.playGame(this, in, null);
                        dOutput.writeUTF(out);
                        protocol.changeState(game.isFull(), game.isYourTurn(this), game.isRoundEnded(),
                                game.isEnd(), game.wannaPlayAgain());
                        game.waitTurn(this);
                        break;

                    case Protocol.RESULT:
                        if (!game.isFirstGame() && !idChanged) {
                            changeId();
                        }
                        out = game.playGame(this, null, null);
                        dOutput.writeUTF(out);
                        protocol.changeState(game.isFull(), game.isYourTurn(this), game.isRoundEnded(),
                                game.isEnd(), game.wannaPlayAgain());
                        game.waitTurn(this);
                        break;

                    case Protocol.END:
                        idChanged = false;
                        if (!decided) {
                            in = dInput.readUTF();
                            decided = true;
                        }
                        out = game.playGame(this, null, in);
                        dOutput.writeUTF(out);
                        protocol.changeState(game.isFull(), game.isYourTurn(this), game.isRoundEnded(),
                                game.isEnd(), game.wannaPlayAgain());
                        game.waitFinalResponse(this);
                        break;

                    default:
                        break;
                }
            }

        } catch (IOException e) {
            throw new RightPriceException("No se puede conectar con el cliente.");
        } catch (InterruptedException e) {
            throw new RightPriceException(e.getMessage());
        }
    }

    public void changeId() {
        if (this.playerId < game.getMaxPlayers()) {
            this.playerId++;
        } else {
            this.playerId = 1;
        }
        idChanged = true;
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Protocol getProtocol() {
        return protocol;
    }

    public void setProtocol(Protocol protocol) {
        this.protocol = protocol;
    }

}
