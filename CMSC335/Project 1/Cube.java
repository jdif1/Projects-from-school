/*
CMSC 335
11/2/2021
This class contains the constructor for a Cube object
 */

public class Cube extends ThreeDimensionalShape{
    public Cube(double edgeLength){
        super.volume = Math.pow(edgeLength, 3);
    }
}
