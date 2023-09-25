/*
Jeff DiFatta
CMSC 335
11/2/2021
This class contains the constructor for a Cone object
 */

public class Cone extends ThreeDimensionalShape{
    public Cone(double radius, double height){
        super.volume = (Math.PI * Math.pow(radius, 2)) * (height/3);
    }
}
