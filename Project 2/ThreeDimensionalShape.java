/*
Jeff DiFatta
CMSC 335
11/16/2021
The ThreeDimensionalShape class has the volume field used by its children
as well as an overridden toString method to display the value of volume with two decimal places
 */

public class ThreeDimensionalShape extends Shape{
    protected double volume;

    @Override
    public String toString(){
        return String.format("%.2f", volume);
    }

}
