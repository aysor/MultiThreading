package ru.netology;

public class LeaderUpdater extends Thread{
    private static int maxFreq(){
        int max = 0;
        for(int s : RouteAnalyser.sizeToFreq.keySet()){
            if (s > max){
                max = s;
            }
        }
        return max;
    }

    @Override
    public void run() {
        while(!isInterrupted()) {
            try {
                synchronized (RouteAnalyser.sizeToFreq) {
                    RouteAnalyser.sizeToFreq.wait();
                }
            } catch (InterruptedException e) {
                //throw new RuntimeException(e);
                break;
            }
            int max = maxFreq();
            System.out.printf("\nСамое частое количество повторений %d (встретилось %d раз)\n", max, RouteAnalyser.sizeToFreq.get(max));
        }
    }
}
