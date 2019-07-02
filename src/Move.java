public class Move {
    private int x1;
    private int y1;
    private int x2;
    private int y2;

    public Move() {
    }

    public Move(int x1, int y1, int x2, int y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    public Move(Move m) {
        this.x1 = m.getX1();
        this.y1 = m.getY1();
        this.x2 = m.getX2();
        this.y2 = m.getY2();
    }

    public int getX1() {
        return x1;
    }

    public void setX1(int x1) {
        this.x1 = x1;
    }

    public int getY1() {
        return y1;
    }

    public void setY1(int y1) {
        this.y1 = y1;
    }

    public int getX2() {
        return x2;
    }

    public void setX2(int x2) {
        this.x2 = x2;
    }

    public int getY2() {
        return y2;
    }

    public void setY2(int y2) {
        this.y2 = y2;
    }

    public boolean isOverlapping(Move m) {
        if (this.x1 == m.getX1() && this.y1 == m.getY1())
            return true;
        if (this.x1 == m.getX2() && this.y1 == m.getY2())
            return true;
        if (this.x2 == m.getX1() && this.y2 == m.getY1())
            return true;
        if (this.x2 == m.getX2() && this.y2 == m.getY2())
            return true;
        return false;

    }

    @Override
    public String toString(){
        String ret = String.format("{%d;%d},{%d;%d}", x1, y1, x2, y2);
        return ret;
    }
}
