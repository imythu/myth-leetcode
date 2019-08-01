package arithmetic.solution278;

/**
 * @author imythu
 * @date 2019/7/30 11:33
 */
public class Solution278 extends VersionControl {
    Solution278(int firstBadVersion) {
        super(firstBadVersion);
    }

    public int firstBadVersion(int n) {
        int left = 0;
        int right = n;
        int mid;
        int result = n;
        while (left < right) {
            // 避免溢出
            mid = (right - left) / 2 + left;
            if (isBadVersion(mid)) {
                right = mid;
                result = mid;
            } else {
                left = mid + 1;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        System.out.println(new Solution278(1702766719).firstBadVersion(2126753390));
        System.out.println((System.currentTimeMillis() - start) / 1000);
    }
}
