/*
CMSC 335
11/2/2021
This class contains the constructor for a Cylinder object
 */

public class Cylinder extends ThreeDimensionalShape{
    public Cylinder(double radius, double height){
        super.volume = Math.PI * Math.pow(radius, 2) * height;
    }
}
