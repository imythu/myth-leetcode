package arithmetic.solution278;

/**
 * @author imythu
 * @date 2019/7/30 11:46
 */
public class VersionControl {

    private int firstBadVersion;
    protected boolean isBadVersion(int version) {
        return version == firstBadVersion;
    }
    public VersionControl(int firstBadVersion) {
        this.firstBadVersion = firstBadVersion;
    }
}
