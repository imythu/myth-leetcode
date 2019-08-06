package arithmetic.solution344;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author imythu
 * @date 2019/8/6 11:40
 * 编写一个函数，其作用是将输入的字符串反转过来。输入字符串以字符数组 char[] 的形式给出。
 *
 * 不要给另外的数组分配额外的空间，你必须原地修改输入数组、使用 O(1) 的额外空间解决这一问题。
 *
 * 你可以假设数组中的所有字符都是 ASCII 码表中的可打印字符。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：["h","e","l","l","o"]
 * 输出：["o","l","l","e","h"]
 * 示例 2：
 *
 * 输入：["H","a","n","n","a","h"]
 * 输出：["h","a","n","n","a","H"]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/reverse-string
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Solution344 {
    public void reverseString(char[] s) {
        for (int left = 0, right = s.length - 1; left < right;left++, right--) {
            s[left] ^= s[right];
            s[right] ^= s[left];
            s[left] ^= s[right];
        }
    }
    static class AroundAdvice implements MethodInterceptor {
        @Override
        public Object intercept(Object target, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
            System.out.println("翻转前："  + Arrays.toString((char[]) (args[0])));
            methodProxy.invokeSuper(target, args);
            System.out.println("翻转后："  + Arrays.toString((char[]) (args[0])));
            return null;
        }
    }
    static class Solution344Factory {
        public static Solution344 getSolution334() {
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(Solution344.class);
            enhancer.setCallback(new AroundAdvice());
            return (Solution344) enhancer.create();
        }
    }

    public static void main(String[] args) {
        Solution344Factory.getSolution334().reverseString(new char[]{'h','e','l','l','o'});
    }
}
