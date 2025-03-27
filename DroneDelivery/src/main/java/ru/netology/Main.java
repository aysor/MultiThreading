package ru.netology;

import java.util.concurrent.*;

public class Main {

    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        LeaderUpdater updater = new LeaderUpdater();
        updater.start();
        for (int i = 0; i < 10; i++) {
            threadPool.submit(new RouteAnalyser());
        }
        threadPool.shutdown();
        try {
            threadPool.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            System.out.println(e);
        }
        updater.interrupt();
        printStat();
    }

    private static void printStat() {
        synchronized (RouteAnalyser.sizeToFreq) {
            System.out.println("\n\nЧастота подряд идущих литер 'R':");
            for (int s : RouteAnalyser.sizeToFreq.keySet()) {
                System.out.printf(" - %d (%d раз)\n", s, RouteAnalyser.sizeToFreq.get(s));
            }
        }
    }

}