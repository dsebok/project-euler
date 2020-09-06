package main;

import java.time.Duration;
import java.time.Instant;

public class PrimeSummer {

    public static void main(String[] args) {
        Instant start = Instant.now();
        PrimeSupplier supplier = new PrimeSupplier();
        long sum = 0;
        long current = supplier.getAsInt();
        while (current < 2e6) {
            sum += current;
            current = supplier.getAsInt();
        }
        System.out.println(sum);
        Instant end = Instant.now();
        Duration duration = Duration.between(start, end);
        System.out.println(duration.toMillis());
    }

}
