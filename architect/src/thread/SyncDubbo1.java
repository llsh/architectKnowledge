package thread;

/**
 * Created by 刘李 on 2017/7/24.
 * synchronized拥有锁重入的功能，就是在使用synchronized时，当一个线程得到一个对象
 * 的锁后，再次请求此对象是可以再次得到该对象的锁。
 */
public class SyncDubbo1 {

    public synchronized void method1(){
        System.out.println("method1...");
        method2();
    }

    private synchronized void method2() {
        System.out.println("method2...");
        method3();
    }

    private synchronized void method3() {
        System.out.println("method3...");
    }

    public static void main(String[] args) {
        final SyncDubbo1 syncDubbo1 = new SyncDubbo1();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                syncDubbo1.method1();
            }
        });

        t1.start();
    }
}
