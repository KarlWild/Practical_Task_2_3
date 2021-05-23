package com.netcracker.task_1;

// Использование методов wait() и notify() для изменения глобальной переменной
class GlobalVariable {
    int state;

    public GlobalVariable() {
        state = 0;
    }

    synchronized void tick() {
        System.out.print("Tick ");
        for (int i = 0; i <= 20; i++) {
            if (i == 15) {
                notify();
                try {
                    while (state != 13) {
                        System.out.println(i+ "\nWaiting for B\nstate = "+state);
                        wait();// ожидать до завершения метода tock()
                    }
                } catch (InterruptedException exc) {
                    System.out.println("Прерывание потока");
                }
            } else System.out.print(i+" ");
        }
    }

    synchronized void tock() {
        System.out.println("Thread B started and state = "+state);
        while (state != 13) state++;
        System.out.println("Thread B ended and state = "+state);
        notify();
    }
}