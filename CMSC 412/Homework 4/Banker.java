import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Banker {

    static ArrayList<Integer[]> max = new ArrayList<>();
    static ArrayList<Integer[]> allocated = new ArrayList<>();
    static ArrayList<Integer[]> resources = new ArrayList<>();

    static int processes;

    static int resourcesInt;

    private static void safetyCheck(boolean[] flagged, ArrayList<Integer[]> allocated,
                            int[][] need, int[] available, ArrayList<Integer> safeRoutes) {

        for (int i = 0; i < processes; i++)
        {
            if (!flagged[i] && available(i, need, available))
            {
                flagged[i] = true;

                for (int j = 0; j < resourcesInt; j++)
                {
                    available[j] += allocated.get(i)[j];
                }

                safeRoutes.add(i);
                safetyCheck(flagged, allocated, need, available, safeRoutes);
                safeRoutes.remove(safeRoutes.size() - 1);
                flagged[i] = false;
                for (int j = 0; j < resourcesInt; j++)
                {
                    available[j] -= allocated.get(i)[j];
                }
            }
        }
        if (safeRoutes.size() == processes) {
            for (int i = 0; i < processes; i++) {
                System.out.print("P" + (safeRoutes.get(i) + 1) +" ");
            }
            System.out.println();
        }
    }
    private static boolean available(int processNumber, int[][] need, int[] available) {
        boolean avail = true;
        for (int i = 0; i < resourcesInt; i++) {
            if (need[processNumber][i] > available[i]) {
                avail = false;
                break;
            }
        }
        return avail;
    }

    public static void main(String[] args) {

        readFile();

        int[] available = new int[resourcesInt];

        for (int i = 0; i < resourcesInt; i++) {
            int sum = 0;
            for (int j = 0; j < processes; j++) {
                sum += Banker.allocated.get(j)[i];
            }
            available[i] = Banker.resources.get(0)[i] - sum;
        }

        ArrayList<Integer> safe = new ArrayList<>();
        boolean[] marked = new boolean[processes];
        int[][] need = new int[processes][resourcesInt];

        for (int i = 0; i < processes; i++) {
            for (int j = 0; j < resourcesInt; j++) {
                need[i][j] = Banker.max.get(i)[j] - Banker.allocated.get(i)[j];
            }
        }
        System.out.println("Safe sequences:");
        safetyCheck(marked, Banker.allocated, need, available, safe);
    }

    private static void readFile() {
        List<String> lines = Collections.emptyList();
        try {
            lines = Files.readAllLines(Paths.get("src\\input.txt"), StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.out.println("No file found");
            e.printStackTrace();
            System.exit(0);
        }
        processes = processFinder(lines);
        if (processes > 9){
            System.out.println("There cannot be more than 9 processes");
            System.exit(0);
        }
        for (int j = 0; j < lines.size(); j++) {
            if (Objects.equals(lines.get(j), "max")) {
                for (int i = 0; i < processes; i++) {
                    Banker.max.add(stringToIntArray(lines.get(j + i + 1)));
                }
            } else if (Objects.equals(lines.get(j), "allocated")) {
                for (int i = 0; i < processes; i++) {
                    Banker.allocated.add(stringToIntArray(lines.get(j + i + 1)));
                }
            } else if (Objects.equals(lines.get(j), "resources"))
                for (int i = 0; i < 1; i++) {
                    Banker.resources.add(stringToIntArray(lines.get(j + i + 1)));
                }
        }
        resourcesInt = max.get(0).length;
        if (resourcesInt > 9){
            System.out.println("There cannot be more than 9 resources");
            System.exit(0);
        }
        System.out.print("Max:");
        for (int i = 0; i < Banker.max.size(); i++){
            System.out.println();
            for (int j = 0; j < Banker.max.get(i).length; j++ ){
                System.out.print(Banker.max.get(i)[j] + " ");
            }
        }
        System.out.println();
        System.out.print("Allocated:");
        for (int i = 0; i < Banker.allocated.size(); i++){
            System.out.println();
            for (int j = 0; j < Banker.allocated.get(i).length; j++ ){
                System.out.print(Banker.allocated.get(i)[j] + " ");
            }
        }
        System.out.println();
        System.out.print("Resources:");
        for (int i = 0; i < Banker.resources.size(); i++){
            System.out.println();
            for (int j = 0; j < Banker.resources.get(i).length; j++ ){
                System.out.print(Banker.resources.get(i)[j] + " ");
            }
        }
        System.out.println();
    }

    private static Integer[] stringToIntArray(String str) {
        String[] splitArray = str.split(" ");
        Integer[] array = new Integer[splitArray.length];

        for (int i = 0; i < splitArray.length; i++) {
            array[i] = Integer.parseInt(splitArray[i]);
        }
        return array;
    }

    private static int processFinder(List<String> lines) {
        ArrayList<Integer> processLocations = new ArrayList<>();

        for (int i = 0; i < lines.size(); i++) {
            if (Objects.equals(lines.get(i), "max")) {
                processLocations.add(i);
            } else if (Objects.equals(lines.get(i), "allocated")) {
                processLocations.add(i);
            } else if (Objects.equals(lines.get(i), "resources")) {
                processLocations.add(i);
            }
        }
        int x = processLocations.get(1) - processLocations.get(0);
        int y = processLocations.get(2) - processLocations.get(1);
        if (x < y) {
            return y-1;
        }
        return x-1;
    }
}