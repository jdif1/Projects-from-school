/*
Project 2
9/19/2021
This File contains the Polynomial and Node classes, as well as constructors for both. It also contains an iterator,
a method for adding more nodes to a Polynomial, and a toString method that converts the numbers in the file into
a more readable format.
 */

import java.util.Scanner;
import java.util.Iterator;
import java.util.Comparator;

public class Polynomial implements Iterable<Polynomial.Node>, Comparable<Polynomial>, Comparator<Polynomial.Node>{
    Node head;

    @Override
    public int compareTo(Polynomial polynomial) {
        Node node1 = this.head;
        Node node2 = polynomial.head;

        while (node1.next != null && node2.next != null) {
            if (node1.exponent > node2.exponent) {
                return 1;
            } else if (node1.exponent < node2.exponent) {
                return -1;
            } else if (node1.coefficient > node1.coefficient) {
                return 1;
            }else if (node1.coefficient > node2.coefficient){
                return -1;
            }else{
                node1 = node1.next;
                node2 = node2.next;
            }
            }
        return 0;
    }
    @Override
    public Iterator<Node> iterator() {
        return new Iterator() {
            Node current;
            @Override
            public boolean hasNext() {
                if (current == null)
                    return head != null;
                else
                    return current.next != null;
            }
            @Override
            public Node next() {
                {
                    if (current == null)
                        current = head;
                    else
                        current = current.next;
                    return current;
                }
            }
        };
    }

    @Override
    public int compare(Node previous, Node current) {
        //Very similar to the compareTo method but this checks the order of one polynomial
        current = previous.next; //passed current as an argument to satisfy interface requirement
        int initialResult = 2;
        int result = 2;
        int zeroCounter = 0;
        int i = 0;

        while (previous.next != null && current.next != null) {

            if (previous.exponent > current.exponent) {
                result = 1;
            } else if (previous.exponent < current.exponent) {
                result = -1;
            } else if (previous.coefficient > current.coefficient) {
                result = 1;
            } else if (previous.coefficient < current.coefficient) {
                result = -1;
            }else{
                result = 0;
                zeroCounter++;
            }
            if(i == 0){
                initialResult = result;
            }
            if (initialResult == 0 && initialResult != result){
                initialResult = result;
                // this prevents issues that would arise if initialResult = 0 but result did not
            }
            previous = previous.next;
            current = current.next;
            i++;
        }
        if (result != initialResult && result != 0){
            result = 2;
        }else if (zeroCounter == i){
            result = 0;
        }
        return result;
    }

    public static class Node {
        double coefficient;
        int exponent;
        Node next;

        public Node(double coefficient, int exponent) {
            this.coefficient = coefficient;
            this.exponent = exponent;
            this.next = null;
       }
    }
    public Polynomial(String inputFromFile) throws InvalidPolynomialSyntax {
        Scanner scannerIn = new Scanner(inputFromFile);
        try {
            while (scannerIn.hasNext()) {
                addNode(scannerIn.nextDouble(), scannerIn.nextInt());
            }
       }catch (Exception e){
           throw  new InvalidPolynomialSyntax("Invalid Syntax");
        }
    }

    public void addNode(double coefficient, int exponent){

        Node currentNode = head;

        if (head == null){
            head = new Node( coefficient, exponent);

        }else{
            while (currentNode.next != null){
                currentNode = currentNode.next;
            }
            currentNode.next = new Node(coefficient, exponent);
        }
    }

    public String toString(Polynomial polynomial){

        String output = "";
        Node currentNode = polynomial.head;

        while (currentNode != null) {
                if (currentNode.coefficient == 0){
                    if (currentNode.next !=null){
                        currentNode = currentNode.next;
                    }else{
                        break;
                    }
                }
                if(currentNode.exponent == 1){
                    output += currentNode.coefficient +"x";
                }else{
                    output += currentNode.coefficient + "x^"
                            + currentNode.exponent;
                }
            if(currentNode.next != null && currentNode.next.coefficient != 0){
                output += " + ";
            }
            currentNode = currentNode.next;
        }
        return output;
    }
}
