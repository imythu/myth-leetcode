package arithmetic.solution4;

/**
 * @author imythu
 * @date 2019/7/12
 * createTime 11:00
 * 给定两个大小为 m 和 n 的有序数组 nums1 和 nums2。
 *
 * 请你找出这两个有序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。
 *
 * 你可以假设 nums1 和 nums2 不会同时为空。
 *
 * 示例 1:
 *
 * nums1 = [1, 3]
 * nums2 = [2]
 *
 * 则中位数是 2.0
 * 示例 2:
 *
 * nums1 = [1, 2]
 * nums2 = [3, 4]
 *
 * 则中位数是 (2 + 3)/2 = 2.5
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/median-of-two-sorted-arrays
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Solution4 {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int totalLength = nums1.length + nums2.length;
        int[] resultNums = new int[totalLength];

        int resultOrder = 0;
        for (int i : nums1) {
            resultNums[resultOrder++] = i;
        }
        for (int i : nums2) {
            resultNums[resultOrder++] = i;
        }

        resultNums = radixSort(resultNums);

        if (totalLength % 2 == 0) {
            return (resultNums[totalLength / 2 - 1] + resultNums[totalLength / 2]) / 2.0f;
        }
        return resultNums[totalLength / 2 ] + 0.0f;
    }

    /**
     * 基数排序
     * @param arr 需要排序的数组
     * @return 从小到大排好序的数组
     */
    private int[] radixSort(int[] arr) {
        // 基数，在循环过程中根据数的大小自动增长
        int digitNumber = 1;
        // 桶，正数和负数共20个桶
        int[][] bucket = new int[20][arr.length > 19 ? arr.length : 20];
        // i 代表当前循环的基数，如 1,10，100....
        for (int i = 1, arrOrder = 0; i <= digitNumber;arrOrder = 0) {
            // 表示本次循环中基数是否已经扩大
            boolean digitExpand = false;
            // 本次循环中 20 个桶每个桶中存的数的个数
            int[] numberAmount = new int[20];
            // 放入桶中
            for (int num : arr) {
                // digit表示 num 要放在 20 格桶中的哪一个
                int digit = (num / i) % 10;
                // 这里是加 10 ，即正数用后 10 个桶，负数用前 10 个桶
                digit += 10;
                // numberAmount[digit] 初始值为 0 ，可以直接使用
                bucket[digit][numberAmount[digit]++] = num;
                // 本次循环中遇到有以下条件时最外层循环条件需要扩大一次，即基数需要乘以10
                // 比如第一次循环时digitNumber = 1,当前 num = 2，则不需扩大
                // 若 num = 10 则需要扩大一次最外层循环
                if (num >= (digitNumber * 10) && !digitExpand) {
                    digitNumber *= 10;
                    digitExpand = true;
                }
            }
            // 从 20 个桶中取出数据，完成一次排序
            for (int j = 0; j < 20; j++) {
                for (int k = 0; k < numberAmount[j]; k++) {
                    arr[arrOrder++] = bucket[j][k];
                }
            }
            // 每循环一次 i 需要乘以 10
            i *= 10;
        }

        return arr;
    }


    public static void main(String[] args) {
        System.out.println(new Solution4().findMedianSortedArrays(new int[]{3}, new int[]{-2, -1}));
        System.out.println(new Solution4().findMedianSortedArrays(new int[]{1, 2}, new int[]{3, 4}));
    }

}
