import java.util.Vector;

public class Main {

    public static void main(String[] args) {
        InputReader reader = new InputReader();
        Vector<Integer> inputs;
        State gameState = new State();
        MonteCarloTreeSearch carlo = new MonteCarloTreeSearch();
        Move returnMove;
        //long time;
        while (true) {
            reader.getNewLine();
            //time = System.currentTimeMillis();
            if (reader.isGotNewMessage()) {
                inputs = reader.getInputs();
                if (!reader.isStarted()) {
                    if (inputs.size() == 1) {
                        gameState.setField(new Field(inputs.get(0)));
                        System.out.println("ok");
                    }
                    if (reader.isBlocked() && inputs.size() == 4) {
                        reader.setStarted(true);
                        gameState.setPlayerNo(2);

                    }

                    if (!reader.isBlocked() && inputs.size() % 2 == 0) {
                        reader.setBlocked(true);
                        for (int i = 0; i < inputs.size(); i += 2)
                            gameState.getField().block(inputs.get(i), inputs.get(i + 1));
                        System.out.println("ok");
                    }
                    if (reader.getLastMessage().equals("start")) {
                        reader.setStarted(true);
                        gameState.setPlayerNo(1);
                    }


                }
                if (reader.isStarted()) {
                        inputs = reader.getInputs();
                        if (inputs.size() == 4)
                            gameState.getField().performMove(new Move(inputs.get(0), inputs.get(1), inputs.get(2), inputs.get(3)));
                        returnMove = carlo.findNextMove(gameState.getField(), gameState.getPlayerNo());
                        gameState.getField().performMove(returnMove);
                        System.out.println(returnMove.toString());
                        //System.out.println(System.currentTimeMillis() - time);

                }
            }
        }
    }
}
