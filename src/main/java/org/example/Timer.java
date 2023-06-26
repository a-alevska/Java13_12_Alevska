package org.example;

import java.time.Duration;
import java.time.Instant;

public class Timer {
    public static void main(String[] args) {
        Thread mainThread = new Thread(() -> {
            Instant startTime = Instant.now();
            while (true) {
                Instant currentTime = Instant.now();
                Duration elapsedTime = Duration.between(startTime, currentTime);
                long seconds = elapsedTime.getSeconds();
                System.out.println("Пройшло " + seconds + " секунд");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread secondThread = new Thread(() -> {
            while (true) {
                System.out.println("Минуло 5 секунд");
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        mainThread.start();
        secondThread.start();
    }
}