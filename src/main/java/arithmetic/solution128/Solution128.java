package arithmetic.solution128;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author imythu
 * @date 2019/7/31 10:33
 */
public class Solution128 {
    public int longestConsecutive(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        int res = 1;
        for (Integer integer : set) {
            if (!set.contains(integer - 1)) {
                int cunrentNum = integer;
                int currentStreak = 1;
                while (set.contains(cunrentNum + 1)) {
                    cunrentNum += 1;
                    currentStreak += 1;
                }
                res = Math.max(currentStreak, res);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new Solution128().longestConsecutive(new int[]{2147483646,-2147483647,0,2,2147483644,-2147483645,2147483645}));
    }
}
