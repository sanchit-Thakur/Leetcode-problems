class Solution {
    public String removeStars(String s) {
        StringBuilder sb = new StringBuilder();
        
        for (char c : s.toCharArray()) {
            if (c == '*') {
                // Remove the closest character to the left
                sb.deleteCharAt(sb.length() - 1);
            } else {
                // Push character onto the stack
                sb.append(c);
            }
        }
        
        return sb.toString();
    }
}