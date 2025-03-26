package ru.netology;

import java.util.concurrent.*;

public class Main {

    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        for (int i = 0; i < 10; i++) {
            threadPool.submit(new RouteAnalyser());
        }
        threadPool.shutdown();
        try {
            threadPool.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            System.out.println(e);
        }
        printStat();
    }

    private static void printStat() {
        //System.out.println("Частота подряд идущих литер 'R':");
        int max = maxFreq();
        System.out.printf("Самое частое количество повторений: %d (встретилось %d раз)\n", max, RouteAnalyser.sizeToFreq.get(max));
        System.out.println("Другие размеры:");
        for (int s : RouteAnalyser.sizeToFreq.keySet()) {
            if(s != max) {
                System.out.printf(" - %d (%d раз)\n", s, RouteAnalyser.sizeToFreq.get(s));
            }
        }
    }
    private static int maxFreq(){
        int max = 0;
        for(int s : RouteAnalyser.sizeToFreq.keySet()){
            if (s > max){
                max = s;
            }
        }
        return max;
    }

}