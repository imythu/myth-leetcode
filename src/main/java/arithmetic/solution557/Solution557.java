package arithmetic.solution557;

/**
 * @author imythu
 * @date 2019/8/6 11:27
 * 给定一个字符串，你需要反转字符串中每个单词的字符顺序，同时仍保留空格和单词的初始顺序。
 *
 * 示例 1:
 *
 * 输入: "Let's take LeetCode contest"
 * 输出: "s'teL ekat edoCteeL tsetnoc" 
 * 注意：在字符串中，每个单词由单个空格分隔，并且字符串中不会有任何额外的空格。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/reverse-words-in-a-string-iii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Solution557 {
    public String reverseWords(String s) {
        String[] strings = s.split(" ");
        StringBuilder res = new StringBuilder();
        for (int i = 0;i < strings.length;i++) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(strings[i]).reverse();
            res.append(stringBuilder.toString()).append(" ");
        }
        return res.toString().trim();
    }

    public static void main(String[] args) {
        System.out.println(new Solution557().reverseWords("(string"));
    }
}
