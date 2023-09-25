/*
CMSC 335
11/2/2021
The TwoDimensionalShape class has the area field used by its children
as well as an overridden toString method to display the value of area with two decimal places
 */
public class TwoDimensionalShape extends Shape{
    protected double area;

    @Override
    public String toString(){
        return String.format("%.2f", area);
    }

}
