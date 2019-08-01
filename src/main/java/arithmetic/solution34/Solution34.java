package arithmetic.solution34;

import java.util.Arrays;

/**
 * @author imythu
 * @date 2019/7/30 10:50
 */
class Solution34 {
    public int[] searchRange(int[] nums, int target) {
        return new int[]{findStart(nums, target), findEnd(nums, target)};
    }

    private int findStart(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        int index = -1;
        int mid;
        while (left <= right) {
            mid = (left + right) / 2;
            if (nums[mid] == target) {
                index = mid;
                right -= 1;
            } else if (nums[mid] < target){
                left += 1;
            } else {
                right -= 1;
            }
        }
        return index;
    }

    private int findEnd(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        int index = -1;
        int mid;
        while (left <= right) {
            mid = (left + right) / 2;
            if (nums[mid] == target) {
                index = mid;
                left += 1;
            } else if (nums[mid] < target){
                left += 1;
            } else {
                right -= 1;
            }
        }
        return index;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Solution34().searchRange(new int[]{5,7,7,8,8,10}, 6)));
    }
}
