package solution3;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 111
 */
public class Solution3 {
    public int lengthOfLongestSubstring(String s) {
        int n = s.length(), ans = 0;
        // 表示不重复的子字符串，在滑动过程中遇到第m个字符是map中存在的就把i，j置为m
        Map<Character, Integer> map = new HashMap<>(s.length());
        for (int start = 0, end = 0;start < n && end < n;end++) {
            if (map.containsKey(s.charAt(end))) {
                start = Math.max(start, map.get(s.charAt(end)));
            } else {
                map.put(s.charAt(end), end + 1);
                // test SVN update
                ans = Math.max(ans, end - start + 1);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new Solution3().lengthOfLongestSubstring("tmmzuxt"));
    }
}
