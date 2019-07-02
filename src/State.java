import java.util.HashSet;
import java.util.Iterator;

public class State {
    private Field field;
    private int playerNo;
    private int visitCount;
    private double score;

    public State() {
        this.field = new Field();
    }

    public State(Field field) {
        this.field = field;
    }

    public State(State state) {
        this.field = new Field(state.getField());
        this.playerNo = state.getPlayerNo();
        this.visitCount = state.getVisitCount();
        this.score = state.getScore();
    }

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }

    public int getPlayerNo() {
        return playerNo;
    }

    public void setPlayerNo(int playerNo) {
        this.playerNo = playerNo;
    }

    public int getVisitCount() {
        return visitCount;
    }

    public void setVisitCount(int visitCount) {
        this.visitCount = visitCount;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public int getOpponent() {
        return 3 - playerNo;
    }

    public void incVisit() {
        this.visitCount++;
    }

    public void addScore(double score) {
        if (this.score != Integer.MIN_VALUE)
            this.score += score;
    }

    public void tooglePlayer() {
        this.playerNo = 3 - this.playerNo;
    }

    public HashSet<State> getAllPossibleStates() {
        HashSet<State> stateSet = new HashSet<>();
        HashSet<Move> moveSet = new HashSet<>(field.getMoveSet());
        moveSet.forEach(p -> {
            State newState = new State(this.field);
            newState.setPlayerNo(3 - this.playerNo);
            newState.getField().performMove(p);
            stateSet.add(newState);
        });
        return stateSet;
    }

    public void simulatePlay() {
        HashSet<Move> moveSet = this.field.getMoveSet();
        HashSet<Move> delete;
        Iterator iterator = moveSet.iterator();
        Move m;
        while(!moveSet.isEmpty()){
            if(!iterator.hasNext())
                iterator=moveSet.iterator();
            m=(Move)iterator.next();
            field.performMove(m);
            delete = new HashSet<>();
            delete.add(new Move(m.getX1(),m.getY1(),(m.getX1()+1)%field.getLengh(),m.getY1()));
            delete.add(new Move(m.getX1(),m.getY1(),m.getX1(),(m.getY1()+1)%field.getLengh()));
            delete.add(new Move((m.getX1()-1)%field.getLengh(),m.getY1(),m.getX1(),m.getY1()));
            delete.add(new Move(m.getX1(),(m.getY1()-1)%field.getLengh(),m.getX1(),m.getY1()));
            delete.add(new Move(m.getX2(),m.getY2(),(m.getX2()+1)%field.getLengh(),m.getY2()));
            delete.add(new Move(m.getX2(),m.getY2(),m.getX2(),(m.getY2()+1)%field.getLengh()));
            delete.add(new Move((m.getX2()-1)%field.getLengh(),m.getY2(),m.getX2(),m.getY2()));
            delete.add(new Move(m.getX2(),(m.getY2()-1)%field.getLengh(),m.getX2(),m.getY2()));

            moveSet.remove(delete);
        }


    }

    public int checkStatus() {
        if (this.field.getMoveSet().isEmpty())
            return this.playerNo;
        else
            return 0;
    }


}
