class Solution {
    public int findNonMinOrMax(int[] nums) {
        if(nums.length < 3){
            return -1;
        }
        int a = nums[0];
        int b = nums[1];
        int c = nums[2];
        if((a > b && a < c) || (a > c && a < b)){
            return a;
        }else if((b > a && b < c) || (b > c && b < a)){
            return b;
        }else{
            return c;
        }
    }
}