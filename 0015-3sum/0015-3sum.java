import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length < 3) return res;

        Arrays.sort(nums);
        int n = nums.length;

        for (int i = 0; i < n - 2; i++) {
            // If the smallest available number is positive, sum cannot be zero
            if (nums[i] > 0) break;

            // Skip duplicate values for i
            if (i > 0 && nums[i] == nums[i - 1]) continue;

            // Pruning: smallest possible triplet starting with nums[i] is already > 0
            if (nums[i] + nums[i + 1] + nums[i + 2] > 0) break;

            // Pruning: largest possible triplet starting with nums[i] is still < 0
            if (nums[i] + nums[n - 2] + nums[n - 1] < 0) continue;

            int left = i + 1;
            int right = n - 1;
            int target = -nums[i];

            while (left < right) {
                int sum = nums[left] + nums[right];

                if (sum == target) {
                    res.add(List.of(nums[i], nums[left], nums[right]));

                    // Skip duplicates for both left and right
                    while (left < right && nums[left] == nums[left + 1]) left++;
                    while (left < right && nums[right] == nums[right - 1]) right--;

                    left++;
                    right--;
                } else if (sum < target) {
                    left++;
                } else {
                    right--;
                }
            }
        }

        return res;
    }
}