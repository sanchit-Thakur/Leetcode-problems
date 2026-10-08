class Solution {
    public int longestOnes(int[] nums, int k) {
        int left = 0;
        int ZeroCount = 0;
        int maxLen = 0;

        for(int right = 0; right < nums.length; right++){
            if(nums[right] == 0){
                ZeroCount++;
            }
            while (ZeroCount > k){
                if(nums[left] == 0){
                    ZeroCount--;
                }
                left++;
            }
            maxLen = Math.max(maxLen, right - left + 1);
        }
        return maxLen;

    }
}