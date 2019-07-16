package util.thread;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author imythu
 * @date 2019/7/15
 * createTime 11:46
 * 线程创建工厂 Builder，可以自定义或使用默认配置
 * 线程名示例：
 */
public class ThreadFactoryBuilder {
    private boolean daemon;
    private int priority;
    private String poolName;
    private SimpleDateFormat simpleDateFormat;
    private AtomicInteger nextId;
    private ThreadGroup threadGroup;
    private String prefix;

    /**
     * 初始化默认值
     */
    public ThreadFactoryBuilder() {
        daemon = false;
        priority = Thread.NORM_PRIORITY;
        poolName = "default_pool";
        simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd_hh:mm:ss:SS");
        nextId = new AtomicInteger(1);
        threadGroup = System.getSecurityManager() == null ? Thread.currentThread().getThreadGroup() : System.getSecurityManager().getThreadGroup();
    }

    /**
     * 设置 ThreadGroup
     * @param threadGroup threadGroup
     * @return this
     */
    public ThreadFactoryBuilder setThreadGroup(ThreadGroup threadGroup) {
        if (threadGroup != null) {
            this.threadGroup = threadGroup;
        }
        return this;
    }

    /**
     * 设置是否为 Daemon 线程
     * @param daemon daemon
     * @return this
     */
    public ThreadFactoryBuilder setDaemon(boolean daemon) {
        this.daemon = daemon;
        return this;
    }

    /**
     * 设置线程优先级
     * @param priority priority
     * @return this
     */
    public ThreadFactoryBuilder setPriority(int priority) {
        if (priority >= Thread.MIN_PRIORITY && priority <= Thread.MAX_PRIORITY) {
            this.priority = priority;
        }
        return this;
    }

    /**
     * 设置线程池名，自动转为小驼峰
     * @param poolType 线程池类型
     * @return this
     */
    public ThreadFactoryBuilder setPoolNameByPoolType(Class<?> poolType) {
        if (poolType == null) {
            return this;
        }
        char[] chars = poolType.getSimpleName().toCharArray();
        chars[0] += 32;
        this.poolName = String.valueOf(chars);
        return this;
    }

    /**
     * 设置线程池名
     * @param poolName poolName
     * @return this
     */
    public ThreadFactoryBuilder setPoolName(String poolName) {
        if (poolName == null) {
            return this;
        }
        this.poolName = poolName;
        return this;
    }

    /**
     * 定义线程名中的日期格式
     * @param simpleDateFormat SimpleDateFormat 类实例
     * @return this，当前对象
     */
    public ThreadFactoryBuilder setSimpleDateFormat(SimpleDateFormat simpleDateFormat) {
        this.simpleDateFormat = simpleDateFormat;
        return this;
    }

    /**
     * 根据设置创建 ThreadFactory 工厂实例
     * @return ThreadFactory 实例
     */
    public ThreadFactory build() {
        prefix = poolName + "-";
        return r -> {
            Thread thread =  new Thread(threadGroup, r, prefix + nextId.getAndIncrement() + "_created_in_" + simpleDateFormat.format(new Date()));
            thread.setPriority(priority);
            thread.setDaemon(daemon);
            return thread;
        };
    }
}
