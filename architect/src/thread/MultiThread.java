package thread;

/**
 * Created by 刘李 on 2017/7/24.
 * 多个对象多个线程调用同一个方法
 */
public class MultiThread {

    private int num = 0;
    public static void main(String[] args) {
        final MultiThread multiThread1 = new MultiThread();
        final MultiThread multiThread2 = new MultiThread();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                multiThread1.printNum("a");
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                multiThread2.printNum("b");
            }
        });

        t1.start();
        t2.start();
    }

    private synchronized void printNum(String s) {
        if (s.equals("a")){
            num = 100;
            System.out.println("a, set num over!");
        }else {
            num = 200;
            System.out.println("b, set num over!");
        }

        System.out.println("s= " + s + "num=" + num );
    }
}
