package org.example;

import java.util.Scanner;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Task2 {
    private int n;
    private int i;

    private static final BlockingQueue<String> queue = new LinkedBlockingQueue<>();

    public void setN(int n) {
        this.n = n;
    }

    public void setI(int i) {
        this.i = i;
    }

    public synchronized void fizz(){
        while (i<=n+1){
            if (i % 3 == 0 && i % 5 != 0) {
                queue.add("fizz");
                i++;
                notifyAll();
            }else {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public synchronized void buzz(){
        while (i<=n+1){
            if (i % 5 == 0 && i % 3 != 0) {
                queue.add("buzz");
                i++;
                notifyAll();
            }else {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public synchronized void fizzbuzz(){
        while (i<=n+1){
            if (i % 3 == 0 && i % 5 == 0) {
                queue.add("fizzbuzz");
                i++;
                notifyAll();
            }else {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public synchronized void number(){
        while (i<=n+1){
            if (i % 3 != 0 && i % 5 !=0) {
                queue.add(String.valueOf(i));
                i++;
                notifyAll();
            }else {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(queue.poll());
        }
    }


    public static void main(String[] args) {
        Task2 t = new Task2();

        Scanner in = new Scanner(System.in);
        System.out.println("Введіть кількість ітерацій: ");
        int n = in.nextInt();

        t.setN(n);
        t.setI(1);

        Thread threadA = new Thread(t::fizz);
        Thread threadB = new Thread(t::buzz);
        Thread threadC = new Thread(t::fizzbuzz);
        Thread threadD = new Thread(t::number);

        threadA.start();
        threadB.start();
        threadC.start();
        threadD.start();
    }
}
