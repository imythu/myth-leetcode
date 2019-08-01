package arithmetic.solution35;

/**
 * @author imythu
 * @date 2019/7/30 11:58
 */
public class Solution35 {

    public int searchInsert(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        int mid = target;
        int insertIndex = -1;
        while (right >= left) {
            // 针对不重复数组，否则会有除 0 异常
            // mid = left + (target - nums[left]) / (nums[right] - nums[left]) * (right - left);
            mid = left + (right - left) / 2;
            if (target == nums[mid]) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
                insertIndex = mid + 1;
            } else {
                right = mid - 1;
                insertIndex = mid - 1;
            }
        }
        return insertIndex;
    }
}
