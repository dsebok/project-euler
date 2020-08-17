package LatticeQuadrilaterals;

public class Quadrilateral {

    private final int vertexA;
    private final int vertexB;
    private final int vertexC;
    private final int vertexD;

    public Quadrilateral(int vertexA, int vertexB, int vertexC, int vertexD) {
        this.vertexA = vertexA;
        this.vertexB = vertexB;
        this.vertexC = vertexC;
        this.vertexD = vertexD;
    }

    public boolean verticesAreInFourLines() {

        return true;
    }

    public boolean isCentroSymmetric() {
        return false;
    }
}
