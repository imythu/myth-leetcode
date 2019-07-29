package thread.solution3;

import java.util.IntSummaryStatistics;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.IntConsumer;

/**
 * @author imythu
 * @date 2019/7/16
 * createTime 17:10
 */
class ZeroEvenOdd {
    private int n;
    private volatile AtomicInteger i = new AtomicInteger(0);

    public ZeroEvenOdd(int n) {
        this.n = n;
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void zero(IntConsumer printNumber) throws InterruptedException {
        for (;i.get() != 0;) {
        }
        printNumber.accept(0);
        i.set(1);
    }

    public void even(IntConsumer printNumber) throws InterruptedException {

        for (;i.get() != 1;) {
        }
        printNumber.accept(n);
        i.set(2);
    }

    public void odd(IntConsumer printNumber) throws InterruptedException {

        for (;i.get() != 2;) {
        }
        printNumber.accept(n);
        i.set(0);
    }

    public static void main(String[] args) {
        ZeroEvenOdd zeroEvenOdd = new ZeroEvenOdd(2);
        for (int i = 0; i < 2; i++) {
            zeroEvenOdd.zero();
        }
    }
}