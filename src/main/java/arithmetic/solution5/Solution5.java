package arithmetic.solution5;

/**
 * @author imythu
 * @date 2019/7/12
 * createTime 15:18
 * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
 *
 * 示例 1：
 *
 * 输入: "babad"
 * 输出: "bab"
 * 注意: "aba" 也是一个有效答案。
 * 示例 2：
 *
 * 输入: "cbbd"
 * 输出: "bb"
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-palindromic-substring
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Solution5 {
    public String longestPalindrome(String s) {
        String result = "";

        for (int i = 0; i < s.length(); i++) {
            String maxStr1 = getMaxStr(s, i, i);
            String maxStr2 = getMaxStr(s, i, i + 1);
            maxStr1 = maxStr2.length() > maxStr1.length() ? maxStr2 : maxStr1;
            result = maxStr1.length() > result.length() ? maxStr1 : result;
        }
        return result;
    }

    private String getMaxStr(String s, int left, int right) {
        int length = s.length();
        while (right < length && left >= 0 && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return s.substring(left + 1, right);
    }

    public static void main(String[] args) {
        System.out.println(new Solution5().longestPalindrome("abbc"));
    }
}
