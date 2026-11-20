class Solution {
    public int reverse(int x) {
        int rev = 0;
        int maxDiv10 = Integer.MAX_VALUE / 10; // 214748364
        int minDiv10 = Integer.MIN_VALUE / 10; // -214748364

        while (x != 0) {
            int pop = x % 10;
            x /= 10;

            // Check overflow before multiplying rev by 10
            if (rev > maxDiv10 || (rev == maxDiv10 && pop > 7)) {
                return 0;
            }
            if (rev < minDiv10 || (rev == minDiv10 && pop < -8)) {
                return 0;
            }

            rev = rev * 10 + pop;
        }

        return rev;
    }
}