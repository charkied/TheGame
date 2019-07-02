import java.util.HashSet;

public class Field {
    private boolean[][] table;
    private Move lastMove;

    public static final int DEFAULT_TABLE_SIZE = 13;

    public Field() {
        this.table = new boolean[DEFAULT_TABLE_SIZE][DEFAULT_TABLE_SIZE];
    }

    public Field(int n) {
        table = new boolean[n][n];
    }

    public Field(boolean[][] table) {
        this.table = table;
    }

    public Field(Field field) {
        int tableLengh = field.getTable().length;
        this.table = new boolean[tableLengh][tableLengh];
        for (int i = 0; i < tableLengh; i++)
            for (int j = 0; j < tableLengh; j++)
                this.table[i][j] = field.getTable()[i][j];
    }

    public int getLengh(){
        return table.length;
    }

    public Move getLastMove() {
        return lastMove;
    }

    public void setLastMove(Move lastMove) {
        this.lastMove = lastMove;
    }

    public boolean getField(int x, int y) {
        return table[x][y];
    }

    public void setTable(boolean[][] table) {
        this.table = table;
    }

    public boolean[][] getTable() {
        return table;
    }

    public void performMove(Move m) {
        this.table[m.getX1()][m.getY1()] = true;
        this.table[m.getX2()][m.getY2()] = true;
        this.lastMove = new Move(m);
    }

    public void block(int x, int y){
        this.table[x][y] = true;
    }

    public HashSet<Move> getMoveSet() {
        HashSet<Move> moveSet = new HashSet<>();
        for (int i = 0; i < table.length; i++)
            for (int j = 0; j < table.length; j++)
                if (table[i][j] == false) {
                    if (table[i][(j + 1) % table.length] == false)
                        moveSet.add(new Move(i, j, i, (j + 1) % table.length));
                    if (table[(i + 1) % table.length][j] == false)
                        moveSet.add(new Move(i, j, (i + 1) % table.length, j));
                }
        return moveSet;
    }

}
