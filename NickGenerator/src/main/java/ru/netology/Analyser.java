package ru.netology;

import java.util.concurrent.atomic.AtomicInteger;

public class Analyser {
    public static volatile AtomicInteger counter_3 = new AtomicInteger(0);
    public static volatile AtomicInteger counter_4 = new AtomicInteger(0);
    public static volatile AtomicInteger counter_5 = new AtomicInteger(0);

    private void decrCounter(String text){
        switch (text.length()) {
            case 3:
                counter_3.decrementAndGet();
                break;
            case 4:
                counter_4.decrementAndGet();
                break;
            case 5:
                counter_5.decrementAndGet();
                break;
        }
    }
    private void incrCounter(String text){
        switch (text.length()) {
            case 3:
                counter_3.incrementAndGet();
                break;
            case 4:
                counter_4.incrementAndGet();
                break;
            case 5:
                counter_5.incrementAndGet();
                break;
        }
    }

    public void countPalindromes(String[] texts) {
        for (String text : texts) {
            boolean isPalindrome = true;
            for (int i = 0; i < text.length() / 2; i++) {
                if (text.charAt(i) != text.charAt(text.length() - 1 - i)) {
                    isPalindrome = false;
                    break;
                }
            }
            if (isPalindrome) {
                incrCounter(text);
                //System.out.printf("%s is palindrome\n", text);
            }
        }
    }

    public void countSameLetter(String[] texts) {
        for (String text : texts){
            boolean isSameLetter = text.chars().allMatch(c -> c == text.charAt(0));
            if(isSameLetter){
                //  Т.к. слово, состоящее из одинаковых букв, удовлетворяет сразу трем критериям,
                //  уменьшаем счетчик, чтобы это слово не посчиталось еще два лишних раза.
                decrCounter(text);
                //System.out.printf("%s is the same letter\n", text);
            }
        }
    }

    public void countAscOrder(String[] texts) {
        for (String text : texts){
            boolean isAscOrder = true;
            char prev = text.charAt(0);
            for(int i = 1; i < text.length(); i++){
                try {
                    if (prev > text.charAt(i)) {
                        isAscOrder = false;
                        break;
                    }
                }catch (Exception e){
                    System.out.println(e);
                }
                prev = text.charAt(i);
            }

            if(isAscOrder){
                incrCounter(text);
                //System.out.printf("%s is ascOrder\n", text);
            }
        }
    }
}
