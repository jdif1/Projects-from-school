/*
This class has what is necessary to create an ArrayList that has all the possible permutations of a given number.
 */

import java.util.ArrayList;

public class PermutationGenerator {
    public ArrayList<String[]> permutationStrings = new ArrayList<>();
    void addIntStringArray(String[] stringArray, int n)
    {
        String[] str = new String[n];
        for (int i = 0; i < n; i++){
            str[i] = stringArray[i];
        }
          permutationStrings.add(str);
    }
    void permutationGenerator(String[] stringArray, int size, int size2) //having the size twice is necessary for function to work correctly
    {
        if (size == 1){
            addIntStringArray(stringArray, size2);
        }

        for (int i = 0; i < size; i++) {
            permutationGenerator(stringArray, size - 1, size2);
            String temporaryString;

            if (size % 2 == 1) {
                temporaryString = stringArray[0];
                stringArray[0] = stringArray[size - 1];
            } else {
                temporaryString = stringArray[i];
                stringArray[i] = stringArray[size - 1];
            }
            stringArray[size - 1] = temporaryString;
        }
    }
}

