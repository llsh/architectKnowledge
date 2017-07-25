package thread;

/**
 * Created by 刘李 on 2017/7/24.
 * 当使用一个对象进行枷锁的时候，如果对象本身改变，锁也改变。
 * 对象不变，锁也是同步的
 */
public class ChangeLock {

    private String lock = "lock";

    private void method(){
        synchronized (lock){
            try{
                System.out.println("当前线程 ：" + Thread.currentThread().getName() + " 开始");
                lock = "newLock";
                Thread.sleep(2000);
                System.out.println("当前线程：" + Thread.currentThread().getName() + " 结束");
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        ChangeLock changeLock = new ChangeLock();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                changeLock.method();
            }
        }, "t1");

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                changeLock.method();
            }
        }, "t2");

        t1.start();
        t2.start();
    }

}
