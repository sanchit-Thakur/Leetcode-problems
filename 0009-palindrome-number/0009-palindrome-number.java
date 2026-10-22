class Solution {
    public boolean isPalindrome(int x) {
        /* Negative numbers are not palindromes (e.g., -121 -> 121-).
        . Also, numbers ending in 0 (except 0 itself) cannot be palindromes. */
        if (x < 0 || (x % 10 == 0 && x != 0)) {
            return false;
        }

        int reversedHalf = 0;
      
        while (x > reversedHalf) {
            reversedHalf = reversedHalf * 10 + x % 10;
            x /= 10;
        }

        return x == reversedHalf || x == reversedHalf / 10;
    }
}