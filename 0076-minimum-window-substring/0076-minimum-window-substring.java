import java.util.*;

class Solution {
    public String minWindow(String s, String t) {

        if (s.length() < t.length()) {
            return "";
        }

        HashMap<Character, Integer> required = new HashMap<>();

        for (char c : t.toCharArray()) {
            required.put(c, required.getOrDefault(c, 0) + 1);
        }

        HashMap<Character, Integer> window = new HashMap<>();

        int left = 0;
        int right = 0;

        int formed = 0;
        int requiredCharacters = required.size();

        int minLength = Integer.MAX_VALUE;
        int minLeft = 0;

        while (right < s.length()) {

            char c = s.charAt(right);

            window.put(c, window.getOrDefault(c, 0) + 1);

            if (required.containsKey(c)
                    && window.get(c).intValue() == required.get(c).intValue()) {
                formed++;
            }

            while (left <= right && formed == requiredCharacters) {

                if (right - left + 1 < minLength) {
                    minLength = right - left + 1;
                    minLeft = left;
                }

                char leftChar = s.charAt(left);

                window.put(leftChar, window.get(leftChar) - 1);

                if (required.containsKey(leftChar)
                        && window.get(leftChar) < required.get(leftChar)) {
                    formed--;
                }

                left++;
            }

            right++;
        }

        if (minLength == Integer.MAX_VALUE) {
            return "";
        }

        return s.substring(minLeft, minLeft + minLength);
    }
}