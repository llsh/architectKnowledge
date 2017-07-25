package thread;

/**
 * Created by 刘李 on 2017/7/24.
 * 脏读
 */
public class DirtyRead {

    private String name = "lili";
    private String password = "123";

    public synchronized void setValue(String name, String password) {
        this.name = name;
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.password = password;
        System.out.println("name = " + this.name + " password = " + this.password);
    }

    public synchronized void getValue(){
        System.out.println("name = " + this.name + " value = " + this.password);
    }

    public static void main(String[] args) throws InterruptedException {
        DirtyRead dirtyRead = new DirtyRead();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                dirtyRead.setValue("wangwu", "456");
            }
        });

        t1.start();
        Thread.sleep(1000);//如果不挂起线程，达不到观察脏读的效果
        dirtyRead.getValue();
    }
}
