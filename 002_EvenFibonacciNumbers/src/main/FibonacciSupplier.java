package suppliers;

import java.util.function.IntSupplier;

public class FibonacciSupplier implements IntSupplier {

    int current = 1;
    int previous = 1;

    @Override
    public int getAsInt() {
        int result = current;
        current = previous + current;
        previous = result;
        return result;
    }
}
