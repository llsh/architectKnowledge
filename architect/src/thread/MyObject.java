package thread;

/**
 * Created by 刘李 on 2017/7/24.
 * 多个线程执行一个对象的不同方法
 */
public class MyObject {

    public synchronized void method1() {
        try {
            System.out.println(Thread.currentThread().getName());
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void method2() {
        System.out.println(Thread.currentThread().getName());
    }

    public static void main(String[] args) {
        final MyObject mo = new MyObject();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                mo.method1();
            }
        }, "t1");

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                mo.method2();
            }
        }, "t2");

        t1.start();
        t2.start();
    }

}

