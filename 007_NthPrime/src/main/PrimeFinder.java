package main;

public class PrimeFinder {

    public static void main(String[] args) {
        PrimeSupplier supplier = new PrimeSupplier();
        int index = 1;
        while (index <= 10000) {
            supplier.getAsLong();
            ++index;
        }
        System.out.println(supplier.getAsLong());
    }
}
