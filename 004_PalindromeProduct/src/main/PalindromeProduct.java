public class PalindromeProduct {

    private PalindromeChecker checker = new PalindromeChecker();

    public int findLargestPalindrome(int min, int max) {
        int result = 0;
        for (int i = min; i <= max; i++) {
            for (int j = min; j <= max; j++) {
                int product = i * j;
                if (product > result) {
                    if (checker.isPalindrome(product)) {
                        result = product;
                    }
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        PalindromeProduct product = new PalindromeProduct();
        int result = product.findLargestPalindrome(100, 999);
        System.out.println(result);
    }
}
