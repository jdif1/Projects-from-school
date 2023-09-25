/*
Project 2
9/19/2021
This class has tow overloaded checkSorted methods. The second one compares the nodes in polynomials and the first one
iterates through the polynomials and calls to the second to compare the nodes. It then returns whether or not all
the polynomials in the list are sorted.
 */

import java.util.Comparator;
import java.util.List;

public class OrderedList {

    protected static <T extends Comparable<? super T>> boolean checkSorted(List<Polynomial> list) {
        int i = 0;
        int zeroCounter = 0;

        int result = checkSorted(list, list.get(i+1).head);
        while (i < list.size() - 1 && result != 1){
            Polynomial polynomial1 = list.get(i);
            Polynomial polynomial2 = list.get(i+1);
            result = polynomial1.compareTo(polynomial2);
            i++;
            /*
            The zeroCounter ensures that if one polynomial is identical the the last but the rest is is still in
            ascending order, the program will still return a result of true since the polynomials are still in an
            ascending order.
             */
            if(result == 0){
                zeroCounter ++;
            }
            if (i == list.size() && zeroCounter == list.size()){
                result = 0;
            }
        }
        if (result == -1){
            return true;
        }else{
            return false;
        }
    }
    private static <T extends Comparator<? super T>> int checkSorted(List<Polynomial> list, Polynomial.Node node1) {
        int i = 0;
        Polynomial.Node node2 = list.get(i).head;
        int result = -1;
        int initialResult = list.get(i).compare(node1, node2);
        while (i < list.size()-1) {
            node2 = list.get(i).head;
            if (list.get(i).compare(node1, node2) == 1){
                result = 1;
            }
            if (list.get(i).compare(node1, node2) == -1) {
                result = -1;
            }
            if(result != initialResult && result != 0){result = 2;}
            i++;
            if (initialResult == 0 && initialResult != result){
                initialResult = result;
                // this prevents issues that would arise if initialResult = 0 but result did not
            }
        }
        if (result!= initialResult){result = 2;}
        return result;
    }
}
