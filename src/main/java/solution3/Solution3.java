package solution3;

import java.util.HashMap;
import java.util.Map;

/**
 * @author imythu
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 *
 * 示例 1:
 *
 * 输入: "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * 示例 2:
 *
 * 输入: "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 * 示例 3:
 *
 * 输入: "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 *      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-substring-without-repeating-characters
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Solution3 {
    public int lengthOfLongestSubstring(String s) {
        int n = s.length(), ans = 0;
        // 表示不重复的子字符串，在滑动过程中遇到第m个字符是map中存在的就把i，j置为m
        Map<Character, Integer> map = new HashMap<>(s.length());
        for (int start = 0, end = 0;start < n && end < n;end++) {
            if (map.containsKey(s.charAt(end))) {
                start = Math.max(start, map.get(s.charAt(end)));
            }
            map.put(s.charAt(end), end + 1);
            // test SVN update
            ans = Math.max(ans, end - start + 1);
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new Solution3().lengthOfLongestSubstring("tmmzuxt"));
    }
}
