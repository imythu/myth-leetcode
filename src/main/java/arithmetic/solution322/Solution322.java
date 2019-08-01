package arithmetic.solution322;

import java.util.Arrays;

/**
 * @author imythu
 * @date 2019/7/31 15:33给定不同面额的硬币 coins 和一个总金额 amount。编写一个函数来计算可以凑成总金额所需的最少的硬币个数。如果没有任何一种硬币组合能组成总金额，返回 -1。
 *
 * 示例 1:
 *
 * 输入: coins = [1, 2, 5], amount = 11
 * 输出: 3
 * 解释: 11 = 5 + 5 + 1
 * 示例 2:
 *
 * 输入: coins = [2], amount = 3
 * 输出: -1
 * 说明:
 * 你可以认为每种硬币的数量是无限的。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/coin-change
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

public class Solution322 {
    /**
     * 动态规划。dp[i] = min{dp[i], dp[i - coin1], dp[i - coin2]......} + 1
     * eg: [1,2,5], 11
     */
    public int coinChange(int[] coins, int amount) {

        int length = amount + 1;
        int[] dp = new int[length];
        for (int i = 0; i < length; i++) {
            dp[i] = length;
        }
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {

            for (int j = 0; j < coins.length; j++) {
                if (i >= coins[j]) {
                    dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
                }
            }
        }

        return dp[amount] > amount ? -1 : dp[amount];
    }

    public static void main(String[] args) {
        System.out.println(new Solution322().coinChange(new int[]{2}, 3));
    }
}
