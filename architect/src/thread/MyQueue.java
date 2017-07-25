package thread;

import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by 刘李 on 2017/7/25.
 */
public class MyQueue {

    private LinkedList<Object> list = new LinkedList<Object>();

    private AtomicInteger count = new AtomicInteger(0);

    private final int minSize = 0;

    private final int maxSize;

    public MyQueue(int num){
        this.maxSize = num;
    }

    //初始化一个对象用于加锁
    private final Object lock = new Object();

    public void put(Object obj){
        synchronized (lock){
            while (count.get() == this.maxSize){
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            list.add(obj);
            count.incrementAndGet();
            System.out.println("add new..." + obj);
            lock.notify();
        }
    }

    public Object take(){
        Object ret = null;
        synchronized (lock){
            while (count.get() == this.minSize){
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            ret = list.removeFirst();
            count.decrementAndGet();
            System.out.println("remove a obj...");
            lock.notify();
        }
        return ret;
    }

    public static void main(String[] args) {
        MyQueue myQueue = new MyQueue(5);
        myQueue.put("a");
        myQueue.put("b");
        myQueue.put("c");
        myQueue.put("d");
        myQueue.put("e");
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                myQueue.put("f");
            }
        });

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                myQueue.take();
                myQueue.take();
            }
        });
        thread1.start();
        thread.start();
    }
}
