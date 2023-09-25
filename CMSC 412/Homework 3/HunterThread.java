/*
This class has the default constructor a HunterThread as well as the vampireHunter method that checks if a number is
a vampire number. There are also three utility methods, getVampireCount() returns a count of how many vampire numbers
have been found, while stringArrayConverter() and intArrayConverter have what is necessary to convert the integers
to strings and strings to integers. Converting to string is necessary because the numbers need to be split into single
digits and then recombined in every possible permutation and converting back to integers is necessary in order to perform
the mathematical operations needed to check for vampire numbers.
 */

import java.util.ArrayList;

public class HunterThread extends Thread {
    private int vampireCount; //counter of how many vampire numbers have been found
    private int counter;
    private final ArrayList<Integer> numberList; //an ArrayList containing the set of numbers that a thread will check
    private final String name; //The name of the thread, used when identifying which thread found a vampire number

    HunterThread(ArrayList<Integer> arrayList, String str){
        name = str;
        numberList = arrayList;
        vampireCount = 0;
        counter = 0;
    }

    public void run() {
        vampireHunter();
    }

    private void vampireHunter() {

        while (counter < numberList.size()) {
            ArrayList<Integer[]> fangSets = new ArrayList<>();
            PermutationGenerator permutationGenerator = new PermutationGenerator();
            String [] str = stringArrayConverter(numberList.get(counter));
            permutationGenerator.permutationGenerator(str, str.length, str.length);
            for (int i = 0; i<permutationGenerator.permutationStrings.size(); i++){
                fangSets.add(intArrayConverter(permutationGenerator.permutationStrings.get(i)));
            }
            for (Integer[] fangs : fangSets) {
                if (fangs[0] * fangs[1] == numberList.get(counter)) {
                    vampireCount++;
                    System.out.println(name + " worker found " + numberList.get(counter));
                    break;
                }
            }
            counter++;
        }
    }


    public int getVampireCount() {
        return vampireCount;
    }

    private String[] stringArrayConverter(int startingNumber) {

        String str = Integer.toString(startingNumber);
        return str.split("(?!^)");
    }
    private Integer[] intArrayConverter(String[] str) {

        Integer[]fangArray = new Integer[2];
        String fang1 = (str[0]+str[1]+str[2]);
        String fang2 = (str[3]+str[4]+str[5]);
        fangArray[0] = Integer.parseInt(fang1);
        fangArray[1] = Integer.parseInt(fang2);
        return fangArray;
    }

}
