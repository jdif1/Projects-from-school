import java.util.ArrayList;

public class Main {
    public static void main(String[] args){

        ArrayList<Integer> evensList = new ArrayList<>();
        ArrayList<Integer> oddsList = new ArrayList<>();
        for(int i=100000; i<=999999; i++ ){
            if (i%2 == 0){
                evensList.add(i);
            }else{
                oddsList.add(i);
            }
        }

        HunterThread firstWorker = new HunterThread(evensList, "First");
        HunterThread secondWorker = new HunterThread(oddsList, "Second");
        firstWorker.start();
        secondWorker.start();
        try {
            firstWorker.join();
            secondWorker.join();
        } catch (InterruptedException e) {
            System.out.println("InterruptedException occurred");
        }
        System.out.println("First worker found " + firstWorker.getVampireCount() + " vampire numbers.");
        System.out.println("Second worker found " + secondWorker.getVampireCount() + " vampire numbers.");
        System.out.println("Total number of vampire numbers: " + (firstWorker.getVampireCount()+secondWorker.getVampireCount()));
    }

}