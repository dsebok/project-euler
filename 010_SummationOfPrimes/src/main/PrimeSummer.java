package main;

public class PrimeSummer {

    public static void main(String[] args) {
        PrimeSupplier supplier = new PrimeSupplier();
        long sum = 0;
        long current = supplier.getAsLong();
        while (current < 2e6) {
            sum += current;
            current = supplier.getAsLong();
        }
        System.out.println(sum);
    }

}
