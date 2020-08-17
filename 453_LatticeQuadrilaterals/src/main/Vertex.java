package LatticeQuadrilaterals;

public class Vertex {

    private final int x;

    private final int y;

    public Vertex(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Vertex diffVector(Vertex other) {
        int diffX = x-other.getX();
        int diffY = y-other.getY();
        return new Vertex(diffX, diffY);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
