/*
Jeff DiFatta
CMSC 335
11/16/2021
This class contains the constructor for a Triangle object
 */

public class Triangle extends TwoDimensionalShape{
    public Triangle (double base, double height){
        //This is for an isosceles triangle
        super.area = (base * height)/2;
    }
}
