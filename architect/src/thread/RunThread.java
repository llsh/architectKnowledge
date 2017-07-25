package thread;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by 刘李 on 2017/7/25.
 * volatile使变量在多个线程间可见
 * volatile的作用就是强制线程到主内存（共享内存）里读取变量，
 * 而不去线程工作内存区里去读取
 */
public class RunThread extends Thread {

    private volatile boolean isRunning = true;//有无volatile导致不同的情况

    public void setRunning(boolean running) {
        isRunning = running;
    }

    @Override
    public void run() {
        System.out.println("into run method...");
        while (isRunning == true){
        }
        System.out.println("end the Thread...");
    }

    public static void main(String[] args) throws InterruptedException {
        RunThread runThread = new RunThread();
        runThread.start();
        Thread.sleep(3000);
        runThread.setRunning(false);
        System.out.println(runThread.isRunning);
    }
}
