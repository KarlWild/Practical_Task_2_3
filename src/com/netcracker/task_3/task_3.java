package com.netcracker.task_3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class task_3 {
    /*
    * Разработайте многопоточное приложение:
 один поток считывает текст собственной программы;
 второй поток получает считанную строку, анализирует на
вхождение образца и по условию вывод строку на экран.
*/
    public static void main(String[] args) {
        AnalyzingText analyzingText = new AnalyzingText("helping Thread");
        Thread1 thread1 = new Thread1("main Thread for text", analyzingText);
        try{
            thread1.thrd.start();
            analyzingText.thrd.start();
            thread1.thrd.join();
            analyzingText.thrd.join();
        }
        catch (InterruptedException exc) {
            System.out.println("Прерывание основного потока");
        }
    }
}

/*class WorkWithText {
    public synchronized void getText() throws IOException {
        String file = "src/com/netcracker/task_3/task_3.java";
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        StringBuilder sb = new StringBuilder();
        String curLine;
        while ((curLine = bufferedReader.readLine()) != null) {
            //process the line as required
            System.out.println(curLine);
            sb.append(curLine + "\n");
        }
        bufferedReader.close();
    }

    public synchronized void analyzeText(String s, String contain) {
        if (s.contains(contain)) System.out.println(s);
    }
}*/

class Thread1 implements Runnable {
    Thread thrd;
    AnalyzingText helpingThread;

    public Thread1(String name, AnalyzingText helpingThread) {
        thrd = new Thread(this, name);
        this.helpingThread = helpingThread;
    }

    @Override
    public void run() {
        try {
            String file = "src/com/netcracker/task_3/task_3.java";
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String curLine;
            while ((curLine = bufferedReader.readLine()) != null) {
                helpingThread.analyzeText(curLine, "System", true);
            }
            bufferedReader.close();
        } catch (IOException e) {
            System.out.println("io exception");
        }

    }
}

class AnalyzingText implements Runnable {
    Thread thrd;

    public AnalyzingText(String name) {
        thrd = new Thread(this, name);
    }

    @Override
    public void run() {
        System.out.println("analyzing text works");
    }

    public synchronized void analyzeText(String s, String contain, boolean condition)  {
        try {
            if (condition == true) {
                if (s.contains(contain)) System.out.println("found: "+s);
                notify();
            } else wait();
        }
        catch (InterruptedException ex) {
            System.out.println("interrupted");
        }

    }
}