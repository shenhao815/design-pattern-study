package com.it.thread;

// 子线程循环10次，主线程循环20次，往复50次
public class Demo01 {

    public static void main(String[] args) {
        T t = new T();
        Thread th = new Thread(t);
        th.start();

        while (true) {
            synchronized (t) {
                if (t.getCounter() > 50) {
                    break;
                }

                for (int i = 1; i <= 20; i++) {
                    System.out.print(Thread.currentThread().getName() + " : "+i);
                    System.out.println("======"+t.getCounter());
                }
                try {
                    t.notifyAll();
                    t.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

class T implements Runnable {
    private int counter = 0;

    public int getCounter() {
        return counter;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (this) {
                counter++;
                if (counter > 50) {
                    this.notifyAll();
                    break;
                }
                for (int i = 1; i <= 10; i++) {
                    System.out.print(Thread.currentThread().getName() + " : " + i);
                    System.out.println("======"+counter);
                }
                try {
                    this.notifyAll();
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
