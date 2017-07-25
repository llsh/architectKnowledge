package thread;

/**
 * Created by 刘李 on 2017/7/24.
 * synchronized的常见用法
 */
public class ObjectLock {

    public void method1(){
        synchronized (this){//对象锁
            System.out.println("do method1 ...");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void method2(){//类锁
        synchronized (ObjectLock.class){
            System.out.println("do method2...");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private Object lock = new Object();
    public void method3(){
        synchronized (lock){//任意对象锁
            System.out.println("do method3...");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        final ObjectLock objectLock = new ObjectLock();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                objectLock.method1();
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                objectLock.method2();
            }
        });

        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                objectLock.method3();
            }
        });

        t1.start();
        t2.start();
        t3.start();
    }
}
