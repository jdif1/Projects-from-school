/*
CMSC 335
11/16/2021
This class contains the constructor for a Torus object
 */

public class Torus extends ThreeDimensionalShape{
    public Torus(double majorRadius, double minorRadius){
        super.volume = (Math.PI * Math.pow(minorRadius, 2)) * (2 * Math.PI * majorRadius);
    }
}
