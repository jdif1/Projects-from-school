/*
Jeff DiFatta
Project 2
9/19/2021
This file contains the main method for the program. In addition it contains the readInList method,
which reads in the list of polynomials from a file that the user selects using JFileChooser and catches any errors
from reading the files. It then displays a warning for those errors in a JOPtionPane window. It also contains a
the checkWeak and checkStrong methods to check the sorting of the lists. There is also a named lambda method that
checks the sorting of only the exponents.
 */

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.List;
import java.util.Comparator;

public class Main extends Component {

    public Main() {
    }
    public static void main(String[] args) throws InvalidPolynomialSyntax {
        readInList();
    }

    private static List<Polynomial> polynomialList = new ArrayList<>();

    private static ArrayList<String> inputFromFile() {

        ArrayList<String> numbersList = new ArrayList<>();
        JFileChooser jFileChooser = new JFileChooser();
        jFileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        jFileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        jFileChooser.showOpenDialog(null);
        File file = jFileChooser.getSelectedFile();

            try {
                Scanner scannerIn = new Scanner(file);

                if (file.isFile()) {
                    while (scannerIn.hasNextLine()){
                        String expression = scannerIn.nextLine();
                        if (expression.trim().length() == 0){continue;} //skipping over blank lines
                        numbersList.add(expression);
                    }
                }
            } catch (FileNotFoundException e) {
                JOptionPane.showMessageDialog(null, "File not found");
            } catch (NoSuchElementException e) {
                JOptionPane.showMessageDialog(null, "Nothing in file");
            }
        return numbersList;
    }

    private static void readInList(){
        try {
            ArrayList<String> elements = inputFromFile();
            for(String element:elements){
                Polynomial polynomial = new Polynomial(element);
                polynomialList.add(polynomial);
        }
        for(int i = 0; i < polynomialList.size(); i++){
            System.out.println(polynomialList.get(i).toString(polynomialList.get(i)));
            if ( i > 0 && i < polynomialList.size()){
            }
            }
            if(polynomialList.size() <= 1){ //there needs to be at least two polynomials in order to compare them
                throw new InvalidPolynomialSyntax("Not enough polynomials in file");
        }
        ArrayList<Integer> results = new ArrayList<>();
            Comparator<Polynomial.Node> compareExponents = (node1, node2) -> node1.exponent - node2.exponent;

            for (int i = 0; i < polynomialList.size()-1; i++) {
                Polynomial.Node node1 = polynomialList.get(i).head;
                Polynomial.Node node2 = polynomialList.get(i + 1).head;
                while (node1 != null && node2 != null) {
                    results.add(compareExponents.compare(node1, node2));
                    node1 = node1.next;
                    node2 = node2.next;
                }
            }
            int zeroCounter = 0;
            boolean inOrder = true;
            if (results.get(0) >= 0){
                for (int i = 0; i < results.size(); i++){
                    if (results.get(i) == 0){ zeroCounter++; }
                    if (zeroCounter == results.size()){
                        break;
                    }
                    if (results.get(i) < 0){
                        System.out.println("Exponents are not in any order");
                        inOrder = false;
                        break;
                    }
                }
                if (zeroCounter == results.size()){
                    System.out.println("All exponents are equal");
                }else if(inOrder){
                    System.out.println("Exponents are in descending order");
                }
            }
            if (results.get(0) < 0){
                for (int i = 0; i < results.size(); i++){
                    if (results.get(i) > 0){
                        inOrder = false;
                        break;
                    }
                }
                if (inOrder){
                    System.out.println("Exponents are in ascending order");
                }else{
                    System.out.println("Exponents are not in any order");
                }
            }

            System.out.println("Ascending order: " + OrderedList.checkSorted(polynomialList));
            System.out.println("Strong ordered: " + checkStrong(polynomialList));
            System.out.println("Weak ordered: " + checkWeak(polynomialList));
        }catch (IndexOutOfBoundsException e){
        }catch (InvalidPolynomialSyntax e){
            JOptionPane.showMessageDialog(null, "Not enough polynomials in file");
        }
        }
     private static boolean checkStrong(List<Polynomial> list){
        int result;
        int i = 0;
        int zeroCounter = 0;
        while(i < list.size() -1){
            result = list.get(i).compareTo(list.get(i+1));
            if (result != 1 && result != 0){
                return false;
            }
            if(result == 0){zeroCounter++;}
            if (i == list.size() && zeroCounter == list.size()){return false;}
            i++;
        }
        return true;
     }
    private static boolean checkWeak(List<Polynomial> list){
        int result;
        int i = 0;
        int zeroCounter = 0;
        while(i < list.size() -1){
            result = list.get(i).compareTo(list.get(i+1));
            if (result != -1 && result != 0){
                return false;
            }
            if(result == 0){zeroCounter++;}
            if (i == list.size() && zeroCounter == list.size()){return false;}
            i++;
        }
        return true;
    }
    }

