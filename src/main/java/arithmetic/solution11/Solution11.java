package arithmetic.solution11;

/**
 * @author imythu
 * @date 2019/8/1 11:21
 */
public class Solution11 {
    public int maxArea(int[] height) {
        int maxArea = 0;
        for (int left = 0, right = height.length - 1;left != right;) {
            if (height[left] > height[right]) {
                maxArea = Math.max((right - left) * height[right], maxArea);
                right--;
            } else {
                maxArea = Math.max((right - left) * height[left], maxArea);
                left++;
            }
        }
        return maxArea;
    }

    public static void main(String[] args) {
        System.out.println(new Solution11().maxArea(new int[]{1,8,6,2,5,4,8,3,7}));
    }
}
