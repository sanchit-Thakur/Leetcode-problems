import java.util.Stack;

class Solution {
    public boolean isValid(String s) {
        Stack<Character> st = new Stack<>();

        for (char c : s.toCharArray()) {
            // if opening bracket, push
            if (c == '(' || c == '{' || c == '[') {
                st.push(c);
            } else {
                // closing bracket: stack must not be empty
                if (st.isEmpty()) return false;

                char top = st.pop();
                if (c == ')' && top != '(') return false;
                if (c == '}' && top != '{') return false;
                if (c == ']' && top != '[') return false;
            }
        }

        // all openings must be matched
        return st.isEmpty();
    }
}