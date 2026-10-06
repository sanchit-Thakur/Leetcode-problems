class Solution {
    public int subarraysDivByK(int[] nums, int k) {
        // remainderCount[r] stores how many times remainder 'r' has occurred
        int[] remainderCount = new int[k];
        
        // Base case: a prefix sum of 0 has remainder 0 once (empty prefix)
        remainderCount[0] = 1;
        
        int prefixSum = 0;
        int count = 0;
        
        for (int num : nums) {
            prefixSum += num;
            
            // Normalize remainder to stay within [0, k - 1]
            int rem = (prefixSum % k + k) % k;
            
            // Add all previous occurrences of the same remainder
            count += remainderCount[rem];
            
            // Record current remainder occurrence
            remainderCount[rem]++;
        }
        
        return count;
    }
}