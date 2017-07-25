package thread;

/**
 * Created by 刘李 on 2017/7/24.
 * 异常释放锁
 * 可以通过异常日志记录，也可抛出运行时异常叫停
 * 但若不及时处理会对程序业务逻辑造成严重后果
 */
public class SyncException {
    private int i = 0;

    public synchronized void operation() {
        while (true){
            try{
                i++;
                Thread.sleep(200);
                System.out.println(Thread.currentThread().getName() + ", i = " + i);
                if (i == 10){
                    Integer.parseInt("a");
                }
            }catch (InterruptedException e){
                e.printStackTrace();
                System.out.println("log info i = " + i);//建立异常日志
            }
        }
    }

    public static void main(String[] args) {
        final SyncException syncException = new SyncException();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                syncException.operation();
            }
        });

        t1.start();
    }
}
