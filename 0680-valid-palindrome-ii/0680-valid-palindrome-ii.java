class Solution {
    public boolean validPalindrome(String s) {
        return isPalindrome(s, 0, s.length() - 1, false);
    }

    private boolean isPalindrome(String s, int start, int end, boolean mismatch) {
        while (start < end) {
            if (s.charAt(start) != s.charAt(end)) {
                if (mismatch) {
                    return false;
                } else {
                    mismatch = true;

                    return isPalindrome(s, start + 1, end, mismatch) || isPalindrome(s, start, end - 1, mismatch);
                }
            } else {
                start++;
                end--;
            }
        }

        return true;
    }
}