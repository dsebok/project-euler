package main;

import java.util.function.LongSupplier;

public class TriangularSupplier implements LongSupplier {

    private long natural = 1;
    private long triangular = 0;

    @Override
    public long getAsLong() {
        triangular += natural++;
        return triangular;
    }
}
