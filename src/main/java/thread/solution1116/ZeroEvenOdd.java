package thread.solution1116;

import util.thread.ThreadFactoryBuilder;

import java.util.IntSummaryStatistics;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadFactory;
import java.util.function.IntConsumer;

/**
 * @author imythu
 * @date 2019/7/16
 * createTime 17:10
 */
class ZeroEvenOdd {
    private int n;
    private Semaphore zeroSemaphore;
    private Semaphore evenSemaphore;
    private Semaphore oddSemaphore;

    public ZeroEvenOdd(int n) {
        this.n = n;
        // 首先给 zero 线程一个许可证，其他两个先不给
        zeroSemaphore = new Semaphore(1);
        evenSemaphore = new Semaphore(0);
        oddSemaphore = new Semaphore(0);
    }

    /**
     * printNumber.accept(x) outputs "x", where x is an integer.
      */
    public void zero(IntConsumer printNumber) throws InterruptedException {
        for (int i = 1; i <= n; i++) {
            zeroSemaphore.acquire();
            printNumber.accept(0);
            System.err.println(0);
            // 判断应该唤醒哪一个线程，release 方法增加许可证数量
            if (i % 2 == 0) {
                evenSemaphore.release(1);
            } else {
                oddSemaphore.release(1);
            }
        }
    }

    public void even(IntConsumer printNumber) throws InterruptedException {
        for (int i = 2; i <= n; i+=2) {
            evenSemaphore.acquire();
            printNumber.accept(i);
            System.err.println(i);
            // 执行完后给 zero 线程许可证，即唤醒 zero 线程
            zeroSemaphore.release();
        }
    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
        for (int i = 1; i <= n; i+=2) {
            oddSemaphore.acquire();
            printNumber.accept(i);
            System.err.println(i);
            // 执行完后给 zero 线程许可证，即唤醒 zero 线程
            zeroSemaphore.release();
        }
    }

    public static void main(String[] args) {
        ZeroEvenOdd zeroEvenOdd = new ZeroEvenOdd(7);
        IntSummaryStatistics summaryStatisticss = new IntSummaryStatistics();
        summaryStatisticss.accept(2);
        ThreadFactory threadFactory = new ThreadFactoryBuilder().setPoolNameByPoolType(ZeroEvenOdd.class).build();
        CountDownLatch countDownLatch = new CountDownLatch(1);
        Thread threadA = threadFactory.newThread(() -> {
            IntSummaryStatistics summaryStatistics = new IntSummaryStatistics();
            try {
                countDownLatch.await();
                zeroEvenOdd.zero(summaryStatistics);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread threadB = threadFactory.newThread(() -> {
            IntSummaryStatistics summaryStatistics = new IntSummaryStatistics();
            try {
                countDownLatch.await();
                zeroEvenOdd.even(summaryStatistics);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread threadC = threadFactory.newThread(() -> {
            IntSummaryStatistics summaryStatistics = new IntSummaryStatistics();
            try {
                countDownLatch.await();
                zeroEvenOdd.odd(summaryStatistics);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        threadA.start();
        threadB.start();
        threadC.start();
        countDownLatch.countDown();
    }
}