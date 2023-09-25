import java.util.Scanner;

public class UserInterface {
    int[] reference;
    public void startInterface(){
        PageSimulator pageSimulator = new PageSimulator();
        Scanner scanner = new Scanner(System.in);
        String [] validInputs = {"0", "1", "2", "3", "4", "5", "6", "7"};
        String input = "";
        String noReference = "You must create a reference with option 1 or 2 first";
        while (!input.equals("0")){
            System.out.println("Hello! Please select an option.\n0: Exit program\n1: Enter reference string\n" +
                    "2: Randomly generate reference string\n3: Display reference string\n4: FIFO\n" +
                    "5: OPT \n6: LRU \n7: LFU");
            input = scanner.nextLine();
            while (!pageSimulator.inputCheck(input, validInputs)){
                System.out.println("Invalid input. Please enter a number from 0-7");
                input = scanner.nextLine();
            }
            switch (input){
                case ("0"):
                    pageSimulator.exit();
                    break;
                case ("1"):
                    reference = pageSimulator.enterReferenceString();
                    break;
                case ("2"):
                    reference = pageSimulator.generateReferenceString();
                    break;
                case ("3"):
                    if (reference != null){
                        pageSimulator.displayReferenceString(reference);
                        break;
                    }else{ System.out.println(noReference); }
                case ("4"):
                    if (reference != null){
                        pageSimulator.firstInFirstOut(reference);
                    }else{ System.out.println(noReference);}
                    break;
                case ("5"):
                    if (reference != null){
                        pageSimulator.optimal(reference);
                    }else{ System.out.println(noReference); }
                    break;
                case ("6"):
                    if (reference != null){
                        pageSimulator.leastRecentlyUsed(reference);
                    }else{ System.out.println(noReference); }
                    break;
                case ("7"):
                    if (reference != null){
                        pageSimulator.leastFrequentlyUsed(reference);
                    }else{ System.out.println(noReference); }
                    break;
            }
        }
    }
}
