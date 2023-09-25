/*
Jeff DiFatta
CMSC 335
11/2/2021
This class contains the constructor for a Square object
 */

public class Square extends TwoDimensionalShape{
    public Square(double widthAndHeight){
        //The width and height of a square must be the same so one value will be sufficient
        super.area = widthAndHeight * widthAndHeight;
    }
}
