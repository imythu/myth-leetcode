package arithmetic.solution34;

import java.util.Arrays;

/**
 * @author imythu
 * @date 2019/7/30 10:50
 */
class Solution {
    public int[] searchRange(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;
        int[] result = new int[]{-1, -1};
        int i = 0;
        while (start < end) {
            int index = (start + end) / 2;
            if (nums[index] == target) {
                result[i++] = index;
                start = index;
            } else if (nums[index] > target) {
                end = index;
            } else {
                start = index;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Solution().searchRange(new int[]{5, 7, 7, 8, 8, 10}, 8)));
    }
}
