package com.netcracker.task_1;

class MyThread implements Runnable {
    Thread thrd;
    GlobalVariable ttOb;

    // Конструктор нового потока
    MyThread(String name, GlobalVariable tt) {
        thrd = new Thread(this, name);
        ttOb = tt;
    }

    // Создание и запуск потока с помощью фабричного метода
    public static MyThread createAndStart(String name, GlobalVariable tt) {
        MyThread myThrd = new MyThread(name, tt);
        myThrd.thrd.start(); // запуск потока
        return myThrd;
    }

    // Точка входа для потока
    public void run() {
        if (thrd.getName().compareTo("A") == 0) {
            ttOb.tick();
        } else {
            ttOb.tock();
        }
    }

}

public class ThreadCom {
    public static void main(String args[]) {
        GlobalVariable tt = new GlobalVariable();
        MyThread mtl = MyThread.createAndStart("A", tt);
        MyThread mt2 = MyThread.createAndStart("B", tt);
        try {
            mtl.thrd.join();
            mt2.thrd.join();
        } catch (InterruptedException exc) {
            System.out.println("Прерывание основного потока");
        }
    }
}
