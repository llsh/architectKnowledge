package thread;

/**
 * Created by 刘李 on 2017/7/24.
 * 不要给String对象加锁，会导致死循环
 * 因为string对象的引用始终指向同一个地址值
 */
public class StringLock {

    public void method1(){
        synchronized ("子"){
            while (true){
                System.out.println("线程开始" + Thread.currentThread().getName());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("线程结束" + Thread.currentThread().getName());
            }

        }
    }

    public static void main(String[] args) {
        StringLock stringLock = new StringLock();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                stringLock.method1();
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                stringLock.method1();
            }
        });

        t1.start();
        t2.start();
    }
}
