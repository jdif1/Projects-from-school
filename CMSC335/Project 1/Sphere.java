/*
CMSC 335
11/2/2021
This class contains the constructor for a Sphere object
 */

public class Sphere extends ThreeDimensionalShape{
    public Sphere(double radius){
        super.volume = (4.0/3.0) * Math.PI * (Math.pow(radius, 3.0));
    }
}
