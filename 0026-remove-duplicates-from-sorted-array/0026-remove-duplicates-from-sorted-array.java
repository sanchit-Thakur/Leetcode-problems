class Solution {
    public int removeDuplicates(int[] nums) {
        // If array has 0 or 1 element, it's already unique
        if (nums.length <= 1) {
            return nums.length;
        }

        int k = 1; // index to place next unique element

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[k - 1]) {
                nums[k] = nums[i];
                k++;
            }
        }

        return k;
    }
}
