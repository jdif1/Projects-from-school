import javax.management.remote.rmi._RMIConnection_Stub;
import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class PageSimulator {
    String validSizes = "[0-9]+";
    public void exit() {
        //Option 0
        System.out.println("Thank you, goodbye");
        System.exit(0);
    }

    public int[] enterReferenceString() {
        //Option 1
        String[] validInputs = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
        String choice;
        int size;
        Scanner scanner = new Scanner(System.in);
        System.out.println("How many elements do you want in your reference?");
        choice = scanner.nextLine();
        while (!choice.matches(validSizes)){
            System.out.println("Invalid input. Only enter numbers.");
            choice = scanner.nextLine();
        }
        size = Integer.parseInt(choice);


        int[] referenceNumbers = new int[size];
        String input;
        for (int i = 0; i < referenceNumbers.length; i++) {
            System.out.println("Please enter number (0-9) that will be at position " + i);
            input = scanner.nextLine();
            while (!inputCheck(input, validInputs)) {
                System.out.println("Please enter a single digit number from 0-9");
                input = scanner.nextLine();
            }
            referenceNumbers[i] = Integer.parseInt(input);
        }
        return referenceNumbers;
    }

    public int[] generateReferenceString() {
        //Option 2
        String choice;
        int size;
        Scanner scanner = new Scanner(System.in);
        System.out.println("How many elements do you want in your reference?");
        choice = scanner.nextLine();
        while (!choice.matches(validSizes)){
            System.out.println("Invalid input. Only enter numbers.");
            choice = scanner.nextLine();
        }
        size = Integer.parseInt(choice);
        int[] referenceNumbers = new int[size];
        for (int i = 0; i < referenceNumbers.length; i++) {
            referenceNumbers[i] = ThreadLocalRandom.current().nextInt(0, 10);
        }
        return referenceNumbers;
    }

    public void displayReferenceString(int[] reference) {
        //Option 3
        System.out.println();
        System.out.print("Your reference string is: ");
        for (int i : reference) {
            System.out.print(i + " ");
        }
        System.out.println();
        System.out.println();
    }

    public void firstInFirstOut(int[] reference) {
        //Option 4

        int physicalFrames = choosePhysicalFrameAmount();
        int[][] frameArray = new int[physicalFrames][reference.length]; //Physical frames are Y axis, reference is X axis
        int oldestFrameLocation = 0;
        int framesInUse = 0; //using this instead of checking if frames are full
        int pageFaults = 0;
        boolean referenceInUse;
        int [] victimFrames = new int[reference.length];
        valueSetter(frameArray, reference.length, physicalFrames);
        Arrays.fill(victimFrames, -1);

        for (int i = 0; i < reference.length; i++) {
            boolean faultsChanged = false;
            if (i > 0) {
                referenceInUse = usageCheck(frameArray, reference[i], i - 1, physicalFrames);
            } else {
                referenceInUse = false;
            }

            if (!referenceInUse) {
                if (physicalFrames > framesInUse) {
                    for (int j = 0; j < physicalFrames; j++) {
                        if (i > 0) {
                            frameArray[j][i] = frameArray[j][i - 1];
                        }
                    }
                    frameArray[framesInUse][i] = reference[i];
                    pageFaults++;
                    faultsChanged = true;
                    framesInUse++;
                } else {
                    for (int j = 0; j < physicalFrames; j++) {
                        if (j != oldestFrameLocation) {
                            frameArray[j][i] = frameArray[j][i - 1];
                        }
                    }
                    victimFrames[i] = oldestFrameLocation;
                    frameArray[oldestFrameLocation][i] = reference[i];
                    pageFaults++;
                    faultsChanged = true;
                    if (oldestFrameLocation == physicalFrames - 1) {
                        oldestFrameLocation = 0;
                    } else {
                        oldestFrameLocation++;
                    }
                }
            } else {
                frameArray = copyToNextStep(frameArray, physicalFrames, i);
            }

            showSimulation(reference, frameArray, physicalFrames, i);
            System.out.println();
            printVictimFrames(victimFrames, i);
            if (faultsChanged){
                System.out.println("Page faults: " + (pageFaults-1));
            } else {
                System.out.println("Page faults: " + pageFaults);
            }



            System.out.println("Press enter to continue simulation");
            try {
                System.in.read();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        showSimulation(reference, frameArray, physicalFrames, reference.length);
        printVictimFrames(victimFrames, reference.length);
        System.out.println("Page faults: " + pageFaults);
    }

    public void optimal(int[] reference) {
        //option 5
        int physicalFrames = choosePhysicalFrameAmount();
        int framesInUse = 0;
        int[][] frameArray = new int[physicalFrames][reference.length];
        boolean isFull = false;
        int pageFaults = 0;
        int [] victimFrames = new int[reference.length];
        Arrays.fill(victimFrames, -1);

        for (int i = 0; i < reference.length; i++) {
            boolean faultsChanged = false;
            if (framesInUse == physicalFrames) {
                isFull = true;
            }
            for (int j = 0; j < framesInUse; j++) { //just added
                frameArray[j][i] = frameArray[j][i - 1];
            }

            if (!isFull) {
                if (usageCheck(frameArray, reference[i], i, framesInUse)) {
                } else {
                    for (int j = 0; j < framesInUse; j++) {
                        frameArray[j][i] = frameArray[j][i - 1];
                    }
                }
                frameArray[framesInUse][framesInUse] = reference[i];
                pageFaults++;
                faultsChanged = true;
                framesInUse++;

            } else {
                if (usageCheck(frameArray, reference[i], i, framesInUse)) {
                } else {
                    faultsChanged =true;
                    pageFaults++;
                    int biggestDistance = -1;
                    int frameToReplace = 0;
                    for (int j = 0; j < physicalFrames; j++) {
                        int currentDistance = -1;
                        for (int k = i; k < reference.length; k++) {
                            currentDistance = k;
                            if (currentDistance > biggestDistance) {
                                biggestDistance = k;
                                frameToReplace = j;
                                victimFrames[i] = frameToReplace;
                            }
                            if (frameArray[j][i - 1] == reference[k]) {
                                break;
                            }
                        }
                    }
                    for (int j = 0; j < physicalFrames; j++) {
                        if (j != frameToReplace) {
                            frameArray[j][i] = frameArray[j][i - 1];
                        } else {
                            frameArray[frameToReplace][i] = reference[i];
                        }
                    }
                }
            }
            System.out.println();
            showSimulation(reference, frameArray, physicalFrames, i);
            printVictimFrames(victimFrames, i);
            if (faultsChanged){
                System.out.println("Page faults: " + (pageFaults-1));
            } else {
                System.out.println("Page faults: " + pageFaults);
            }
            System.out.println("Press enter to continue simulation");
            try {
                System.in.read();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        showSimulation(reference, frameArray, physicalFrames, reference.length);
        printVictimFrames(victimFrames, reference.length);
        System.out.println("Page faults: " + pageFaults);
    }

    public void leastRecentlyUsed(int[] reference) {
        //option 6
        int physicalFrames = choosePhysicalFrameAmount();
        int[] timeSinceUse = new int[physicalFrames]; // time since used value to keep track of when references were last used
        int framesInUse = 0;
        int[][] frameArray = new int[physicalFrames][reference.length];
        boolean isFull = false;
        int pageFaults = 0;
        int [] victimFrames = new int[reference.length];
        Arrays.fill(victimFrames, -1);

        for (int i = 0; i < reference.length; i++) {
            boolean faultsChanged = false;
            for (int j = 0; j < framesInUse; j++) {
                timeSinceUse[j]++;
            }
            if (framesInUse == physicalFrames) {
                isFull = true;
            }
            if (!isFull) {

                for (int j = 0; j < framesInUse; j++) {
                    frameArray[j][i] = frameArray[j][i - 1];
                }
                if (!usageCheck(frameArray, reference[i], i - 1, framesInUse)) {
                    for (int j = 0; j < framesInUse; j++) {
                        if (frameArray[j][i - 1] == reference[i]) {
                            timeSinceUse[j] = 0;
                        }
                    }
                    frameArray[framesInUse][framesInUse] = reference[i];
                    pageFaults++;
                    faultsChanged = true;
                    framesInUse++;
                }

            } else {
                int frameToReplace = -1;
                if (usageCheck(frameArray, reference[i], i - 1, framesInUse)) {
                    for (int j = 0; j < framesInUse; j++) {
                        if (frameArray[j][i - 1] == reference[i]) {
                            timeSinceUse[j] = 0;
                        }
                    }
                } else {
                    pageFaults++;
                    faultsChanged = true;
                    int longestTime = -1;

                    for (int j = 0; j < physicalFrames; j++) {
                        if (timeSinceUse[j] > longestTime) {
                            longestTime = timeSinceUse[j];
                            frameToReplace = j;
                            victimFrames[i] = frameToReplace;
                        }
                    }
                }
                for (int j = 0; j < physicalFrames; j++) {
                    if (j == frameToReplace) {
                        frameArray[frameToReplace][i] = reference[i];
                        timeSinceUse[j] = 0;
                    } else {
                        frameArray[j][i] = frameArray[j][i - 1];
                    }
                }
            }
            System.out.println();
            showSimulation(reference, frameArray, physicalFrames, i);
            printVictimFrames(victimFrames, i);
            if (faultsChanged){
                System.out.println("Page faults: " + (pageFaults-1));
            } else {
                System.out.println("Page faults: " + pageFaults);
            }
            System.out.println("Press enter to continue simulation");
            try {
                System.in.read();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        showSimulation(reference, frameArray, physicalFrames, reference.length);
        printVictimFrames(victimFrames, reference.length);
        System.out.println("Page faults: " + pageFaults);
    }

    public void leastFrequentlyUsed(int[] reference) {
        //option 7
        int physicalFrames = choosePhysicalFrameAmount();
        int[] frequencyOfUse = new int[physicalFrames]; // time since used value to keep track of when references were last used
        int framesInUse = 0;
        int[][] frameArray = new int[physicalFrames][reference.length];
        boolean isFull = false;
        int pageFaults = 0;
        int [] victimFrames = new int[reference.length];
        Arrays.fill(victimFrames, -1);

        for (int i = 0; i < reference.length; i++) {
            boolean faultsChanged = false;
            if (framesInUse == physicalFrames) {
                isFull = true;
            }
            if (!isFull) {

                for (int j = 0; j < framesInUse; j++) {
                    frameArray[j][i] = frameArray[j][i - 1];
                }
                if (!usageCheck(frameArray, reference[i], i - 1, framesInUse)) {
                    for (int j = 0; j < framesInUse; j++) {
                        if (frameArray[j][i - 1] == reference[i]) {
                            frequencyOfUse[j]++;
                        }
                    }
                    frameArray[framesInUse][framesInUse] = reference[i];
                    pageFaults++;
                    faultsChanged = true;
                    framesInUse++;
                }

            } else {
                int frameToReplace = -1;
                if (usageCheck(frameArray, reference[i], i - 1, framesInUse)) {
                    for (int j = 0; j < framesInUse; j++) {
                        if (frameArray[j][i - 1] == reference[i]) {
                            frequencyOfUse[j]++;
                        }
                    }
                } else {
                    pageFaults++;
                    faultsChanged = true;
                    int lowestFrequency = reference.length+1;

                    for (int j = 0; j < frequencyOfUse.length; j++) {
                        System.out.println("J:" + j + ":" + frequencyOfUse[j]);
                        if (frequencyOfUse[j] < lowestFrequency) {
                            lowestFrequency = frequencyOfUse[j];
                            frameToReplace = j;
                            victimFrames[i] = frameToReplace;
                            System.out.println(frequencyOfUse.length);
                        }
                    }
                }
                for (int j = 0; j < physicalFrames; j++) {
                    if (j == frameToReplace) {
                        frameArray[frameToReplace][i] = reference[i];
                        frequencyOfUse[j] = 0;
                    } else {
                        frameArray[j][i] = frameArray[j][i - 1];
                    }
                }
            }
            System.out.println();
            showSimulation(reference, frameArray, physicalFrames, i);
            printVictimFrames(victimFrames, i);
            if (faultsChanged){
                System.out.println("Page faults: " + (pageFaults-1));
            } else {
                System.out.println("Page faults: " + pageFaults);
            }
            System.out.println("Press enter to continue simulation");
            try {
                System.in.read();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        showSimulation(reference, frameArray, physicalFrames, reference.length);
        printVictimFrames(victimFrames, reference.length);
        System.out.println("Page faults: " + pageFaults);
    }

    public int[][] copyToNextStep(int[][] original, int physicalFrames, int referencePosition) {
        int[][] newArray = original;
        for (int j = 0; j < physicalFrames; j++) {
            newArray[j][referencePosition] = original[j][referencePosition - 1];
        }
        return newArray;
    }

    private int choosePhysicalFrameAmount() {
        String[] validInputs = {"1", "2", "3", "4", "5", "6", "7", "8"};
        String input;
        Scanner scanner = new Scanner(System.in);
        System.out.println("How many physical frames do you want? Please enter a number from 1-8");
        input = scanner.nextLine();

        while (!inputCheck(input, validInputs)) {
            System.out.println("That is not a valid input. Please enter a number from 1-8");
            input = scanner.nextLine();
        }
        return Integer.parseInt(input);
    }

    public Boolean inputCheck(String input, String[] validInputs) {
        for (int i = 0; i < validInputs.length; i++) {
            if (validInputs[i].equals(input)) {
                return true;
            }
        }
        return false;
    }

    private void showSimulation(int[] reference, int[][] physicalFrames, int yAxis, int xPos) {
        System.out.println();
        for (int i : reference) {
            System.out.print(i + "\t|\t");
        }
        System.out.println();

        for (int i = 0; i < yAxis; i++) {
            System.out.println();
            for (int j = 0; j < xPos; j++) {
                if (j < i || physicalFrames[i][j] == -1){
                    System.out.print("-" + "\t|\t");
                }else{
                    System.out.print(physicalFrames[i][j] + "\t|\t");
                }
            }
        }
        System.out.println();
    }

    private boolean usageCheck(int[][] array, int reference, int xAxis, int yAxis) {
        for (int i = 0; i < yAxis; i++) {
            if (array[i][xAxis] == reference) {
                return true;
            }
        }
        return false;
    }
    private int[][] valueSetter(int [][] array, int xSize, int ySize){
        //setting array values to -1 to indicate that they haven't been used yet to showSimulator method
        for (int i = 0; i < ySize; i++){
            for (int j = 0; j < xSize; j++){
                array[i][j] = -1;
            }
        }
        return array;
    }
    private void printVictimFrames(int[] victimFrames, int i){
        System.out.println("Victim frames:");

        for (int j = 0; j < i; j++){
            if (victimFrames[j] != -1){
                System.out.print(victimFrames[j] + "\t|\t");
            }else{
                System.out.print("-" + "\t|\t");
            }
        }
        System.out.println();

    }
}
