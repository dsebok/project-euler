public class PalindromeChecker {

    public boolean isPalindrome(Integer number) {
        StringBuilder builderNumber = new StringBuilder(number.toString());
        boolean result = true;
        while (builderNumber.length() > 1) {
            char firstChar = getAndDeleteCharAt(builderNumber, 0);
            char lastChar = getAndDeleteCharAt(builderNumber, builderNumber.length()-1);
            if (firstChar != lastChar) {
                result = false;
            }
        }
        return result;
    }

    private char getAndDeleteCharAt(StringBuilder builderNumber, int index) {
        char firstChar = builderNumber.charAt(index);
        builderNumber.deleteCharAt(index);
        return firstChar;
    }

}
