import java.util.HashSet;
import java.util.Scanner;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Problem01 {
    public static volatile ConcurrentLinkedQueue<present> LinkedList = new ConcurrentLinkedQueue<>();
    public static HashSet <present> presents = new HashSet<>();
    public static volatile int currentId = 0;
    public static volatile int numThankYous;
    public static volatile int altTask;
//    public static present curPresent;
//    public int ID;
//    public static int hasThankYou;

    public static class myrun implements Runnable {

        @Override
        public synchronized void run() {
            // System.out.println("entering thread");
            present temp = new present(currentId);
            if (altTask==1){ // adding gifts to the ordered chain

                if(presents.contains( temp )){
                    LinkedList.add(temp);
                    presents.remove(temp);
                //    System.out.print(LinkedList);

                }
                altTask = 2;
            }

            if (altTask==2){ // writing “Thank you” cards
                if(!LinkedList.isEmpty()){
                    LinkedList.remove(temp);
                    numThankYous++;
                    currentId++;
               //     System.out.print(LinkedList);
                }
                altTask = 1;
            }

            }
        }

    public static void main(String[] args) throws InterruptedException {
        myrun tasks = new myrun();
        Thread [] servants = new Thread[4];
        Scanner sc = new Scanner(System.in);


        // present curPresent;
        int n;
        int t = 0;
        altTask = 1;
        numThankYous = 0;

        System.out.println("How many Presents?\n");
        n = sc.nextInt(); // number of Guests


        for (int i=0; i<n; i++){
            presents.add(new present(currentId));
            currentId++;
        }
//        for (int y =0; y<4; y++){
//            servants[y] = new Thread(tasks);
//            servants[y].start();
//        }
        System.out.println("Preparation done(: Servants are on it!");
        final long startTime = System.currentTimeMillis();

        currentId = 0;
        while (!presents.isEmpty()){
            servants[t] = new Thread(tasks);
            servants[t].start();
            servants[t].join();

            if (t==3) t=0;
            else t++;
        }



        long endTime = System.currentTimeMillis();
        System.out.println("Servants done! (: wanna know how well they did?");
        System.out.println("Here's whats left in our bag of unordered presents and chain of ordered presents");
        System.out.print(presents);
        System.out.print(LinkedList);
        System.out.println();
        System.out.printf("Time taken: %f seconds\n", (float)(endTime-startTime)/1000);
        System.out.printf("Number of Thank you card written: %d\n", numThankYous);



    }
    public static class present {
        int ID;
        int hasThankYou = 0;

        public present(int id){
            this.ID = id;
        }

        @Override
        public int hashCode(){
            int hash = 22;
            hash = 32 * hash + this.ID;
            return hash;
        }

        @Override
        public boolean equals( Object obj){
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            present other = (present) obj;
            if (hasThankYou != other.hasThankYou)
                return false;
            return ID == other.ID;
        }

        public String toString(){
            return "id: "+ID;
    }

}
}
