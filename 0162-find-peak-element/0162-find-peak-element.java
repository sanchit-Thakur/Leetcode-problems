class Solution {
    public int findPeakElement(int[] nums) {
        int a = 0;
        int b = nums.length - 1;
        while(a<b){
            int mid = a + ( b - a)/2;
            if(nums[mid] > nums[mid + 1]){
                b = mid;
            } else{
                a = mid + 1;

            }
        }
        return a;
    }
}