import java.io.FileInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class Server implements Runnable {

    private int port;
    private int maxPlayers;

    private ServerSocket server;
    private List<String> productsWithPriceList;
    private List<Product> products;

    public Server() throws RightPriceException {
        try {
            Properties conf = new Properties();
            conf.load(new FileInputStream("src/config/server.properties"));
            port = Integer.parseInt(conf.getProperty("PORT"));
            maxPlayers = Integer.parseInt(conf.getProperty("MAXPLAYERS"));
            productsWithPriceList = Arrays.asList(conf.getProperty("PRODUCTS").split(";"));
            products = new ArrayList<>();
            fillProducts();
        } catch (IOException e) {
            throw new RightPriceException("No se pudo leer las propiedades del servidor");
        }
    }

    public static void main(String[] args) {
        try {
            Server s = new Server();
            Thread server = new Thread(s);
            server.start();
        } catch (RightPriceException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void run() {
        try {
            startServer();
        } catch (RightPriceException e) {
            System.out.println(e.getMessage());
            return;
        }
    }

    private void startServer() throws RightPriceException {
        while (true) {
            try (ServerSocket server = new ServerSocket(port);) {
                this.server = server;
                while (true) {
                    Game game = new Game(products, maxPlayers);
                    while (game.getPlayers().size() < maxPlayers) {
                        PlayerHandler ta = new PlayerHandler(game.getPlayers().size(), this.server.accept(), game);
                        game.addPlayer(ta);
                        ta.start();
                    }
                }
            } catch (IOException e) {
                throw new RightPriceException("No se puede escuchar en el puerto " + port);
            }
        }
    }

    private void fillProducts() {
        if (!productsWithPriceList.isEmpty()) {
            for (String s : productsWithPriceList) {
                Product p = new Product(s.split(",")[0], Double.parseDouble(s.split(",")[1]));
                products.add(p);
            }
        }
    }
}
