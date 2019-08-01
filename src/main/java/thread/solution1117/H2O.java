package thread.solution1117;

import java.util.concurrent.Semaphore;

/**
 * @author imythu
 * @date 2019/7/29 15:09
 */
class H2O {
    private Semaphore oxygenSemaphore = new Semaphore(1);
    private Semaphore hydrogenSemaphore1 = new Semaphore(0);
    private Semaphore hydrogenSemaphore2 = new Semaphore(0);
    public H2O() {

    }

    public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
        hydrogenSemaphore1.acquire();
        // releaseHydrogen.run() outputs "H". Do not change or remove this line.
        releaseHydrogen.run();
        // 此处可能出现两个 hydrogen 线程同时唤醒 oxygen 线程的情况，添加处理后超时，暂时无解决方法
    }

    public void oxygen(Runnable releaseOxygen) throws InterruptedException {
        oxygenSemaphore.acquire(1);
        // releaseOxygen.run() outputs "O". Do not change or remove this line.
        releaseOxygen.run();
        hydrogenSemaphore1.release();
        hydrogenSemaphore2.release();
    }
}