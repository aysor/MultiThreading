package ru.netology;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class RouteAnalyser extends Thread {
    public static final Map<Integer, Integer> sizeToFreq = new HashMap<>();

    public static String generateRoute(String letters, int length) {
        Random random = new Random();
        StringBuilder route = new StringBuilder();
        for (int i = 0; i < length; i++) {
            route.append(letters.charAt(random.nextInt(letters.length())));
        }
        return route.toString();
    }

    @Override
    public void run() {
        String route = generateRoute("RLRFR", 100);
        int countR = (int) route.chars().filter(c -> c == 'R').count();
        System.out.printf("%s:\t %d occurrences of 'R'\n",route, countR);

        int size = 0;
        for(int j = 0; j < route.length(); j++){
            if(route.charAt(j) == 'R'){
                size++;
            } else if (size > 0){
                synchronized (sizeToFreq) {
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    int freq = sizeToFreq.getOrDefault(size, 0);
                    sizeToFreq.put(size, freq + 1);
                    sizeToFreq.notify();
                }
                size = 0;
            }
        }
    }
}
