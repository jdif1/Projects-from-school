/*
Jeff DiFatta
CMSC 330
This is the class for the custom error that is thrown when there are problems with the syntax of the text file
 */
public class SyntaxError extends Exception{
    String message;
    SyntaxError(String str){
        message = str;
    }
}
