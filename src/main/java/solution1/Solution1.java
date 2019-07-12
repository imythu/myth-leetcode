package solution1;

import java.util.*;

/**
 * @author imythu
 * @date 2019/7/12
 * createTime 10:26
 */
public class Solution1 {

    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>(nums.length);
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                return new int[]{i, map.get(target - nums[i])};
            }
            map.put(nums[i], i);
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Solution1().twoSum(new int[]{2, 7, 11, 15}, 9)));
    }
}
