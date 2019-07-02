import java.util.*;

public class Node {
    State state;
    Node parent;
    HashSet<Node> childSet;

    public Node() {
        this.state = new State();
        this.childSet = new HashSet<>();
    }

    public Node(State state) {
        this.state = state;
        this.childSet = new HashSet<>();
    }

    public Node(State state, Node parent, HashSet<Node> childSet) {
        this.state = state;
        this.parent = parent;
        this.childSet = childSet;
    }

    public Node(Node node) {
        this.childSet = new HashSet<>();
        this.state = new State(node.getState());
        if (node.getParent() != null)
            this.parent = node.getParent();
        HashSet<Node> childSet = node.getChildSet();
        for (Node child : childSet) {
            this.childSet.add(new Node(child));
        }
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public HashSet<Node> getChildSet() {
        return childSet;
    }

    public void setChildSet(HashSet<Node> childSet) {
        this.childSet = childSet;
    }

    public boolean hasSetNext() {
        return childSet.iterator().hasNext();
    }

    public Node getChildNode() {
        return childSet.iterator().next();
    }

    public Node getMaxScoreChild() {
        return Collections.max(this.childSet, Comparator.comparing(c ->
            c.getState().getVisitCount()
        ));
    }

}
