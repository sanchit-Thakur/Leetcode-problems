import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
    private static class Pair {
        char ch;
        int count;

        Pair(char ch, int count) {
            this.ch = ch;
            this.count = count;
        }
    }

    public String removeDuplicates(String s, int k) {
        Deque<Pair> stack = new ArrayDeque<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (!stack.isEmpty() && stack.peek().ch == c) {
                stack.peek().count++;
                if (stack.peek().count == k) {
                    stack.pop();
                }
            } else {
                stack.push(new Pair(c, 1));
            }
        }

        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            Pair p = stack.removeLast(); // Read from bottom to top
            sb.append(String.valueOf(p.ch).repeat(p.count));
        }

        return sb.toString();
    }
}