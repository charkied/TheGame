import java.util.HashSet;

public class MonteCarloTreeSearch {
    private static final int WIN_SCORE = 10;
    private int opponent;
    private Node rootNode;

    public MonteCarloTreeSearch() {
    }



    public Move findNextMove(Field field, int playerNo) {
        long start = System.currentTimeMillis();
        //long end = start + 60 * getMoveTime();
        long end = start + 30;

        opponent = 3 - playerNo;
        Tree tree = new Tree();
        rootNode = tree.getRoot();
        rootNode.getState().setField(new Field(field));
        rootNode.getState().setPlayerNo(opponent);

        tree = new Tree();
        Node rootNode = tree.getRoot();
        rootNode.getState().setField(new Field(field));
        rootNode.getState().setPlayerNo(opponent);

        while (System.currentTimeMillis() < end) {
            Node promisingNode = selectPromisingNode(rootNode);
            if (promisingNode.getState().checkStatus() == 0) {
                expandNode(promisingNode);
            }
            if(promisingNode.getState().getField().getMoveSet().size() > 300)
                return promisingNode.getState().getField().getLastMove();

            Node explorer = promisingNode;
            if (promisingNode.getChildSet().size() > 0) {
                explorer = promisingNode.getChildNode();
            }

            int playoutResult = simulatePlayout(explorer);
            backPropagation(explorer, playoutResult);
        }
        Node winnerNode = rootNode.getMaxScoreChild();
        tree.setRoot(winnerNode);
        //System.out.println(end-start);
        //System.out.println(winnerNode.getState().getScore());
        return winnerNode.getState().getField().getLastMove();
    }

    private Node selectPromisingNode(Node rootNode) {
        Node node = rootNode;
        while (node.getChildSet().size() > 0) {
            node = UCT.findBestNodeWithUCT(node);
        }
        return node;
    }

    private void expandNode(Node promisingNode) {
        HashSet<State> stateSet = promisingNode.getState().getAllPossibleStates();
        stateSet.forEach(state -> {
            Node newNode = new Node(state);
            newNode.setParent(promisingNode);
            newNode.getState().setPlayerNo(promisingNode.getState().getOpponent());
            promisingNode.getChildSet().add(newNode);
        });
    }

    private int simulatePlayout(Node explorer) {
        Node tempNode = new Node(explorer);
        State tempState = tempNode.getState();
        int status = tempState.checkStatus();
        if (status == this.opponent) {
            tempNode.getParent().getState().setScore(Integer.MIN_VALUE);
            return status;
        }
        while (status == 0) {
            tempState.tooglePlayer();
            tempState.simulatePlay();
            status = tempState.checkStatus();
        }
        return status;
    }

    private void backPropagation(Node explorer, int playerNo) {
        Node tempNode = explorer;
        while (tempNode != null) {
            tempNode.getState().incVisit();
            if (tempNode.getState().getPlayerNo() == playerNo)
                tempNode.getState().addScore(WIN_SCORE);
            tempNode = tempNode.getParent();
        }
    }
}

