import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Problem2 {
    public static volatile ArrayList<Integer> tempReadings = new ArrayList<>();
    public static volatile ArrayList<Integer> temps = new ArrayList<>();
    public static volatile int [] temp = new int[8];
    public static volatile int curSensor;
    public static class myrun implements Runnable {

        @Override
        public synchronized void run() {
            int curTemp = ThreadLocalRandom.current().nextInt(-100, 71);
            //int curTemp = (int) ((Math.random() * ((70) - (-100))) + (-100));
            //temp[curSensor] = curTemp;
            temps.add(curTemp);
            if (!tempReadings.contains(curTemp))
                tempReadings.add(curTemp);

           // System.out.println("sensor read");
        }
    }

    public static void main(String[] args){
        Thread [] sensors = new Thread[8];
        myrun reading = new myrun();
        ArrayList<Integer> intervals = new ArrayList<Integer>();
        Scanner sc = new Scanner(System.in);

        curSensor = 0;


        System.out.print("Report for the Hour \n");
            for (int i = 0; i < 60; i++) {
                sensors[0] = new Thread(reading);
                sensors[0].start();
                sensors[1] = new Thread(reading);
                sensors[1].start();
                sensors[2] = new Thread(reading);
                sensors[2].start();
                sensors[3] = new Thread(reading);
                sensors[3].start();
                sensors[4] = new Thread(reading);
                sensors[4].start();
                sensors[5] = new Thread(reading);
                sensors[5].start();
                sensors[6] = new Thread(reading);
                sensors[6].start();
                sensors[7] = new Thread(reading);
                sensors[7].start();

            }

            Collections.sort(tempReadings);
            System.out.println("Lowest 5 Readings of the Hour: ");
            for (int m = 0; m < 5; m++) {
                System.out.printf("%d ", tempReadings.get(m));
            }
            System.out.println();
            Collections.reverse(tempReadings);
            System.out.println("Top 5 Readings of the Hour: ");
            for (int m = 0; m < 5; m++) {
                System.out.printf("%d ", tempReadings.get(m));
            }

            System.out.println();

            int max = -1000, min = 71;
            int intervalDiff;
            int curInterval = 0;

            for (int h = 0; h < 480; h++) {
                int b = temps.get(h);
                if (h < min) min = h;
                if (max < h) max = h;
                curInterval++;
                if ((curInterval + 1) % 80 == 0) {
                    intervalDiff = max - min;
                    intervals.add(intervalDiff);
                }
            }

            int best = -1000;
            int bestIndx = 0;
            for (int v = 0; v < 6; v++) {
                if (intervals.get(v) > best) {
                    best = intervals.get(v);
                    bestIndx = v;
                }
            }
            System.out.printf("Ten minute interval with largest temperature difference: %d\n", bestIndx);
            System.out.println();

            // System.out.print(temps); making sure random values are generate each time; they are
            temps.clear();
            tempReadings.clear();
            intervals.clear();

    }

    public int getReading(){
        return (int) ((Math.random() * ((70) - (-100))) + (-100));
    }

}
