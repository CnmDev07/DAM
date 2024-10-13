public class Protocol {
    public static final int WAITING = 0;
    public static final int PLAYING = 1;
    public static final int W_RESULT = 2;
    public static final int RESULT = 3;
    public static final int END = 4;

    private int state;

    public Protocol() {
        state = WAITING;
    }

    public void changeState(boolean gameFull, boolean yourTurn, boolean roundEnded, boolean ended, String playAgain) {
        switch (state) {
            case WAITING:
                if (gameFull)
                    if (!ended) {
                        state = W_RESULT;
                    }
                break;

            case PLAYING:
                state = W_RESULT;
                break;

            case W_RESULT:
                if (roundEnded) {
                    state = RESULT;
                } else if (yourTurn) {
                    state = PLAYING;
                }
                break;

            case RESULT:
                state = WAITING;
                if (ended) {
                    if (yourTurn) {
                        state = END;
                    }
                }
                break;

            case END:
                if (playAgain.equals("again"))
                    state = WAITING;
                break;

            default:
                break;
        }
    }

    public int getState() {
        return state;
    }
}
