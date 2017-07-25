package thread;

/**
 * Created by 刘李 on 2017/7/24.
 * synchronized拥有锁重入的功能，就是在使用synchronized时，当一个线程得到一个对象
 * 的锁后，再次请求此对象是可以再次得到该对象的锁。
 */
public class SyncDoubbo2 {

    static class Main{
        public int i = 10;
        public synchronized void operationSup(){
            try {
                i--;
                System.out.println("Main print i = " + i);
                Thread.sleep(100);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    static class Sub extends Main{
        public synchronized void operationSub(){
            try {
                while (i > 0){
                    i--;
                    System.out.println("Sub print i = " + i);
                    Thread.sleep(100);
                    this.operationSup();
                    System.out.println("suo shi fang");
                }
            }catch(InterruptedException e){
                    e.printStackTrace();
                }
            }
        }

    public static void main(String[] args) {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                Sub sub = new Sub();
                sub.operationSub();
            }
        });

        t1.start();
    }
}
