/*
CMSC 330
This class contains the main method for the program. A GUIGenerator object is created and then a scanner is created,
which calls to the GUIGenerator GetFile() method to select the file it will be reading. The scanner is then passed
as an argument to the GUIGenerator ReadInFile() method, and then the GUI is building begins.
 */

import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {

	GUIGenerator guiGenerator = new GUIGenerator();
        Scanner scanner = new Scanner(guiGenerator.GetFile());
	guiGenerator.ReadInFile(scanner);
    }
}
