package com.netcracker.task_2;

public class Thread_With_Local_Variable {

    public static void main(String[] args) {
        LocalThread a = LocalThread.createAndStart("thread1");
        LocalThread b = LocalThread.createAndStart("thread2");
        //LocalThread2 a = LocalThread2.createAndStart("thread1");
        //LocalThread2 b = LocalThread2.createAndStart("thread2");
        try {
            a.thrd.join();
            b.thrd.join();
        } catch (InterruptedException e) {
            System.out.println("Прерывание основного потока");
        }
    }
}

class LocalThread implements Runnable {
    private int i;
    Thread thrd;

    public LocalThread(String name) {
        i = 0;
        thrd = new Thread(this, name);
    }

    public static LocalThread createAndStart(String name) {
        LocalThread myThrd = new LocalThread(name);
        myThrd.thrd.start(); // запуск потока
        return myThrd;
    }

    @Override
    public void run() {
        for (; i < 100; i++) {
            System.out.println("Thread name - " + thrd.getName() + " i = " + i);
        }
    }
}
class LocalThread2 implements Runnable{
    Thread thrd;
    public LocalThread2(String name){
        thrd = new Thread(this,name);
    }
    public static LocalThread2 createAndStart(String name){
        LocalThread2 myThrd = new LocalThread2(name);
        myThrd.thrd.start();
        return myThrd;
    }
    public void run(){
        for (int i = 0; i < 100; i++) {
            System.out.println("Thread name - " + thrd.getName() + " i = " + i);
        }
    }
}