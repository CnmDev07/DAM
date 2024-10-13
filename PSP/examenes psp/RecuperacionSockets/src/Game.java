import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Random;

public class Game {
    private int maxPlayers;
    private int turn;

    private String roundWinners;

    private boolean end;
    private boolean firstGame;
    private boolean reseted;
    private boolean roundEnded;

    private Product actualProduct;

    private List<Product> products;
    private List<Product> usedProducts;
    private List<PlayerHandler> winners;
    private List<PlayerHandler> players;
    private LinkedHashMap<PlayerHandler, Double> guesses;
    private LinkedHashMap<PlayerHandler, Integer> points;
    private LinkedHashMap<PlayerHandler, String> finalDecision;

    public Game(List<Product> products, int maxPlayers) {
        this.products = products;
        this.maxPlayers = maxPlayers;
        this.firstGame = true;
        this.players = new ArrayList<>();
        resetGame();
    }

    private void resetGame() {
        this.turn = 1;
        this.usedProducts = new ArrayList<>();
        this.end = false;
        this.roundEnded = false;
        this.reseted = true;
        this.roundWinners = "";
        this.actualProduct = new Product();
        this.winners = new ArrayList<>();
        this.guesses = new LinkedHashMap<>();
        this.points = new LinkedHashMap<>();
        this.finalDecision = new LinkedHashMap<>();
        startNewRound();
    }

    private void initiateMaps() {
        for (PlayerHandler p : players) {
            this.guesses.put(p, null);
        }
        if (end || firstGame)
            for (PlayerHandler p : players) {
                this.points.put(p, 0);
                finalDecision.put(p, null);
            }
    }

    public void updateTurn() {
        if (turn < maxPlayers) {
            turn++;
        } else {
            turn = 1;
        }
    }

    public synchronized String playGame(PlayerHandler p, String usrIn, String decision) {
        String response = "";
        int state = p.getProtocol().getState();
        boolean ready = true;
        switch (state) {
            case Protocol.WAITING:
                if (!firstGame) {
                    for (PlayerHandler ph : players) {
                        if (ph.getProtocol().getState() != Protocol.WAITING)
                            ready = false;
                    }

                    if (ready && reseted) {
                        startNewRound();
                    }
                    if (ready && !reseted) {
                        resetGame();
                    }
                } else if (isFull()) {
                    initiateMaps();
                }
                response += Protocol.WAITING + ";" + p.getPlayerId() + ";" + players.size() + ";" + isFirstGame() + ";"
                        + getMaxPlayers();
                notifyAll();

                break;

            case Protocol.PLAYING:
                guesses.replace(p, Double.parseDouble(usrIn));
                response += Protocol.PLAYING + ";" + p.getPlayerId();
                updateTurn();
                notifyAll();
                break;

            case Protocol.W_RESULT:
                if (!guesses.containsValue(null)) {
                    roundEnded = true;
                }
                response += Protocol.W_RESULT + ";" + p.getPlayerId() + ";" + getTurn() + ";" + isRoundEnded() + ";"
                        + getActualProduct().getName();

                break;

            case Protocol.RESULT:
                firstGame = false;

                if (roundEnded) {
                    checkRoundWinner();
                    if (products.size() == usedProducts.size()) {
                        end = true;
                    }
                }

                response += Protocol.RESULT + ";" + p.getPlayerId() + ";" + getRoundWinners() + ";"
                        + getActualProduct().getName() + ";" + actualProduct.getPrice() + ";" + isEnd();
                if (end) {
                    checkGameWinners();
                    response += ";" + getTurn() + ";" + getWinnersString();
                }
                notifyAll();
                break;

            case Protocol.END:
                reseted = false;
                fillFinalDecision(p, decision);
                response += Protocol.END + ";" + p.getPlayerId() + ";" + wannaPlayAgain() + ";" + getTurn();
                notifyAll();
                break;

            default:
                break;
        }

        return response;
    }

    public synchronized void waitPlayers() throws InterruptedException {
        if (!isFull())
            wait();
    }

    public synchronized void waitTurn(PlayerHandler p) throws InterruptedException {
        if (!isYourTurn(p))
            wait();
    }

    public synchronized void waitFinalResponse(PlayerHandler p) throws InterruptedException {
        if (!isYourTurn(p) && finalDecision.containsValue(null))
            wait();
    }

    public boolean isYourTurn(PlayerHandler p) {
        boolean yourTurn = false;
        if (turn == p.getPlayerId())
            yourTurn = true;
        return yourTurn;
    }

    public boolean isFull() {
        boolean fullGame = false;
        if (players.size() == maxPlayers)
            fullGame = true;
        return fullGame;
    }

    private void fillFinalDecision(PlayerHandler p, String decision) {
        if (isYourTurn(p) == true) {
            finalDecision.replace(p, decision);
            updateTurn();
        }
    }

    private void startNewRound() {
        boolean validProduct = false;
        roundEnded = false;
        while (!validProduct) {
            actualProduct = products.get(new Random().nextInt(products.size()));
            if (!usedProducts.contains(actualProduct)) {
                roundWinners = "";
                validProduct = true;
                usedProducts.add(actualProduct);
                initiateMaps();
            }
        }
    }

    private void checkGameWinners() {
        for (PlayerHandler p : points.keySet()) {
            if (!winners.isEmpty()) {
                if (points.get(p) > points.get(winners.get(0))) {
                    winners.clear();
                    winners.add(p);
                } else if (points.get(p) > points.get(winners.get(0))) {
                    winners.add(p);
                }
            } else {
                winners.add(p);
            }
        }
    }

    private void checkRoundWinner() {
        double closestGuess = 0;
        roundWinners = "";
        for (PlayerHandler p : guesses.keySet()) {
            double guess = guesses.get(p);
            if (closestGuess < guess && guess <= actualProduct.getPrice()) {
                closestGuess = guess;
                roundWinners = "" + p.getPlayerId();
            } else if (guesses.get(p) == closestGuess) {
                points.replace(p, points.get(p) + 1);
                roundWinners += ", " + p.getPlayerId();
            }
        }
    }

    public String wannaPlayAgain() {
        String result = "not_decided";
        if (!finalDecision.containsValue(null)) {
            result = "again";
            if (finalDecision.containsValue("N") || finalDecision.containsValue("n")) {
                result = "not_again";
            }
        }
        return result;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public int getMaxPlayers() {
        return maxPlayers;
    }

    public void setMaxPlayers(int maxPlayers) {
        this.maxPlayers = maxPlayers;
    }

    public List<PlayerHandler> getPlayers() {
        return players;
    }

    public void setPlayers(List<PlayerHandler> players) {
        this.players = players;
    }

    public void addPlayer(PlayerHandler ph) {
        players.add(ph);
    }

    public int getTurn() {
        return turn;
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }

    public boolean isEnd() {
        return end;
    }

    public void setEnd(boolean end) {
        this.end = end;
    }

    public boolean isFirstGame() {
        return firstGame;
    }

    public void setFirstGame(boolean firstGame) {
        this.firstGame = firstGame;
    }

    public boolean isReseted() {
        return reseted;
    }

    public void setReseted(boolean reseted) {
        this.reseted = reseted;
    }

    public boolean isRoundEnded() {
        return roundEnded;
    }

    public void setRoundEnded(boolean roundEnded) {
        this.roundEnded = roundEnded;
    }

    public Product getActualProduct() {
        return actualProduct;
    }

    public void setActualProduct(Product actualProduct) {
        this.actualProduct = actualProduct;
    }

    public List<Product> getUsedProducts() {
        return usedProducts;
    }

    public void setUsedProducts(List<Product> usedProducts) {
        this.usedProducts = usedProducts;
    }

    public List<PlayerHandler> getWinners() {
        return winners;
    }

    public String getWinnersString() {
        String winnersString = "";
        for (PlayerHandler playerHandler : winners) {
            if (winnersString.equals("")) {
                winnersString += playerHandler.getPlayerId();
            } else {
                winnersString += ", " + playerHandler.getPlayerId();
            }
        }
        return winnersString;
    }

    public void setWinners(List<PlayerHandler> winners) {
        this.winners = winners;
    }

    public LinkedHashMap<PlayerHandler, Double> getGuesses() {
        return guesses;
    }

    public void setGuesses(LinkedHashMap<PlayerHandler, Double> guesses) {
        this.guesses = guesses;
    }

    public LinkedHashMap<PlayerHandler, Integer> getPoints() {
        return points;
    }

    public void setPoints(LinkedHashMap<PlayerHandler, Integer> points) {
        this.points = points;
    }

    public LinkedHashMap<PlayerHandler, String> getFinalDecision() {
        return finalDecision;
    }

    public void setFinalDecision(LinkedHashMap<PlayerHandler, String> finalDecision) {
        this.finalDecision = finalDecision;
    }

    public String getRoundWinners() {
        return roundWinners;
    }

    public void setRoundWinners(String roundWinners) {
        this.roundWinners = roundWinners;
    }

}
