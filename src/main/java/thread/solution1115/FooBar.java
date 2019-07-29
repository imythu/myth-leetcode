package thread.solution2;

import util.thread.ThreadFactoryBuilder;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author imythu
 * @date 2019/7/16
 * createTime 15:50
 */
class FooBar {
    private int n;
    private volatile AtomicInteger sort = new AtomicInteger(1);

    public FooBar(int n) {
        this.n = n;
    }

    public void foo(Runnable printFoo) throws InterruptedException {

        for (int i = 1; i < 2 * n + 1; i+=2) {
            while (i != sort.get()) {
            }
            // printFoo.run() outputs "foo". Do not change or remove this line.
            printFoo.run();
            sort.set(sort.get() + 1);
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {

        for (int i = 2; i < 2 * n + 2; i+=2) {
            while (sort.get() != i) {

            }
            // printBar.run() outputs "bar". Do not change or remove this line.
            printBar.run();
            sort.set(sort.get() + 1);
        }
    }

    public static void main(String[] args) {
        ThreadFactory threadFactory = new ThreadFactoryBuilder().build();
        FooBar fooBar = new FooBar(5);
        Thread barThread = threadFactory.newThread(() -> {
            try {
                fooBar.bar(() -> System.out.print("bar"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread fooThread = threadFactory.newThread(() -> {
            try {
                fooBar.foo(() -> {
                    System.out.print("foo");
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        barThread.start();
        fooThread.start();
    }
}
