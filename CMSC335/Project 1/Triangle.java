/*
Jeff DiFatta
CMSC 335
11/2/2021
This class contains the constructor for a Triangle object
 */

public class Triangle extends TwoDimensionalShape{
    public Triangle (double leg1, double leg2){
        //This is for a right triangle
        super.area = (leg1 * leg2)/2;
    }
}
