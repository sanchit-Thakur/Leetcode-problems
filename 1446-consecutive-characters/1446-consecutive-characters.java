class Solution {
    public int maxPower(String s) {
        int best = 1;
        int streak = 1;
        for(int i = 1; i < s.length(); i++) {
            if(s.charAt(i) == s.charAt(i-1)) {
                streak++;
                best = Math.max(best,streak);
                continue;
            }
            streak = 1;
        }
        return best;
    }
}