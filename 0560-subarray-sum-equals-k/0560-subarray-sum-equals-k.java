import java.util.HashMap;
import java.util.Map;

class Solution {
    public int subarraySum(int[] nums, int k) {
        int count = 0;
        int prefixSum = 0;
        
        // Map stores: prefixSum -> frequency
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1); // Base case for subarray starting at index 0
        
        for (int num : nums) {
            prefixSum += num;
            
            // If (prefixSum - k) exists, a valid subarray ending here was found
            if (map.containsKey(prefixSum - k)) {
                count += map.get(prefixSum - k);
            }
            
            // Record current prefixSum in map
            map.put(prefixSum, map.getOrDefault(prefixSum, 0) + 1);
        }
        
        return count;
    }
}