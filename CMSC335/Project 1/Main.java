/*
Jeff DiFatta
CMSC 335
11/2/2021
This class contains the main method for the program. It prompts the user to select a type of Shape
and then prompts the user for the parameters of that shape and then displays the area or volume, depending
on whether the Shape is two or three dimensional.
 */

import java.util.*;

public class Main{

    public static void main(String[] args) {



        Scanner scanner = new Scanner(System.in);
        String input;
        boolean exitSelected = false;


        while (! exitSelected){
            try {

            System.out.println("Please select the number that corresponds to the type of shape that" +
                    " you wish to construct.\nEnter 10 if you wish to exit.");
            System.out.println("1. Circle\n2. Rectangle\n3. Square\n4. Right Triangle\n5. Sphere" +
                    "\n6. Cube\n7. Cone\n8. Cylinder\n9. Torus\n10. Exit program");
            input = scanner.next();
            while (! Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9", "10" ).contains(input)) {
                System.out.println("Invalid input. Please select a number 1-10");
                input = scanner.next();
            }

                switch (input){
                    case "1":
                        // case for circle
                        System.out.println("What is the radius of the circle?");
                        double circleRadius = scanner.nextDouble();
                        Circle circle = new Circle(circleRadius);
                        System.out.println("The area of the circle is: " + circle.toString());
                        break;

                    case "2":
                        //case for rectangle
                        System.out.println("What is the width of the rectangle");
                        double rectangleWidth = scanner.nextDouble();
                        System.out.println("What is the height of the rectangle?");
                        double rectangleHeight = scanner.nextDouble();
                        Rectangle rectangle = new Rectangle(rectangleWidth, rectangleHeight);
                        System.out.println("The area of the rectangle is: " + rectangle.toString());
                        break;

                    case "3":
                        //case for square
                        System.out.println("What is the length of one side of the square?");
                        double widthAndLength = scanner.nextDouble();
                        Square square = new Square(widthAndLength);
                        System.out.println("The area of the new square is: " + square.toString());
                        break;

                    case "4":
                        //case for triangle
                        System.out.println("What is the length of the first leg of the right triangle");
                        double leg1 = scanner.nextDouble();
                        System.out.println("What is the length of the second leg of the right triangle");
                        double leg2 = scanner.nextDouble();
                        Triangle triangle = new Triangle(leg1, leg2);
                        System.out.println("The area of the new triangle is: " + triangle.toString());
                        break;

                    case "5":
                        //case for sphere
                        System.out.println("What is the radius of the sphere?");
                        double sphereRadius = scanner.nextDouble();
                        Sphere sphere = new Sphere(sphereRadius);
                        System.out.println("The volume of the sphere is " + sphere.toString());
                        break;

                    case "6":
                        //case for cube
                        System.out.println("What is the length of the edges of the cube?");
                        double edgeLength = scanner.nextDouble();
                        Cube cube = new Cube(edgeLength);
                        System.out.println("The volume of the new cube is " + cube.toString());
                        break;

                    case "7":
                        //case for cone
                        System.out.println("What is the height of the cone?");
                        double coneHeight = scanner.nextDouble();
                        System.out.println("What is the radius of the cone?");
                        double coneRadius = scanner.nextDouble();
                        Cone cone = new Cone(coneRadius, coneHeight);
                        System.out.println("The volume of the new cone is " + cone.toString());
                        break;

                    case "8":
                        //case for cylinder
                        System.out.println("What is the radius of the cylinder?");
                        double cylinderRadius = scanner.nextDouble();
                        System.out.println("What is the height of the cylinder?");
                        double cylinderHeight = scanner.nextDouble();
                        Cylinder cylinder = new Cylinder(cylinderRadius, cylinderHeight);
                        System.out.println("The volume of the new cylinder is " + cylinder.toString());
                        break;

                    case "9":
                        //case for torus
                        System.out.println("What is the major radius of the Torus?");
                        double torusMajorRadius = scanner.nextDouble();
                        System.out.println("What is the minor radius of the Torus?");
                        double torusMinorRadius = scanner.nextDouble();
                        Torus torus = new Torus(torusMajorRadius, torusMinorRadius);
                        System.out.println("The volume of the new torus is " + torus.toString());
                        break;

                    case "10":
                        //case for exiting program
                        Date date = new Date();
                        System.out.println("Thank you. The time is " + date);
                        exitSelected = true;
                        break;

                }
            }catch (InputMismatchException e){
               System.out.println("Invalid input. Please only use numbers.");
               scanner.next();
           }
        }
    }
}

