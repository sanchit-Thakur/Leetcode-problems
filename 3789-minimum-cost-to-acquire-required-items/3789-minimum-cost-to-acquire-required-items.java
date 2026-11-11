class Solution {
    public long minimumCost(int cost1, int cost2, int costBoth, int need1, int need2) {
        // Strategy 1: Buy all items separately
        long option1 = (long) need1 * cost1 + (long) need2 * cost2;
        
        // Strategy 2: Buy everything using the combined bundle
        long option2 = (long) Math.max(need1, need2) * costBoth;
        
        // Strategy 3: Use the combined bundle for the overlapping needs, then buy the rest individually
        int common = Math.min(need1, need2);
        long option3 = (long) common * costBoth 
                     + (long) (need1 - common) * cost1 
                     + (long) (need2 - common) * cost2;
        
        // Return the minimum of all three strategies
        return Math.min(option1, Math.min(option2, option3));
    }
}