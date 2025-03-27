package ru.netology;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        Random random = new Random();
        String[] texts = new String[100_000];
        for (int i = 0; i < texts.length; i++) {
            texts[i] = generateText("abc", 3 + random.nextInt(3));
        }
        /*for(String text : texts){
            System.out.println(text);
        }*/

        //System.out.println("________________________");
        Analyser analyser = new Analyser();
        ExecutorService threadPool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        threadPool.submit(() -> analyser.countPalindromes(texts));
        threadPool.submit(() -> analyser.countSameLetter(texts));
        threadPool.submit(() -> analyser.countAscOrder(texts));
        threadPool.shutdown();
        try {
            threadPool.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            System.out.println(e);
        }

        //System.out.println("_______________________");
        System.out.printf("Красивых слов с длиной 3: %d шт\n", Analyser.counter_3.get());
        System.out.printf("Красивых слов с длиной 4: %d шт\n", Analyser.counter_4.get());
        System.out.printf("Красивых слов с длиной 5: %d шт\n", Analyser.counter_5.get());
    }

    public static String generateText(String letters, int length) {
        Random random = new Random();
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < length; i++) {
            text.append(letters.charAt(random.nextInt(letters.length())));
        }
        return text.toString();
    }
}