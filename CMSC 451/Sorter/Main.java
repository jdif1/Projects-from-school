import java.io.*;
import java.text.DecimalFormat;
import java.util.*;

public class Main {
    public static void main(String[] args)
    {
        //Warmup
        for (int i = 0; i < 100000; i++){
            int [] warmupArray = new int[2];
            warmupArray[0] = 2;
            warmupArray[1] = 1;
            SelectionSort selectionSortWarmup = new SelectionSort();
            ShellSort shellSortWarmup = new ShellSort();

            try {
                selectionSortWarmup.sort(warmupArray);
                shellSortWarmup.sort(warmupArray);
            } catch (UnsortedException e)
            {
                System.out.println(e.msg);
            }
        }

        ArrayList<int[]> values = new ArrayList<>();
        int dataSetSize = 100;

        Random random = new Random();

        while (values.size() < 40) {
            int[] dataSet = new int[dataSetSize];

           for (int i = 0; i < dataSetSize; i++) {
                dataSet[i] = random.nextInt(5000);
            }
            values.add(dataSet);

            int valuesSize = values.size();
            if (valuesSize % 3 == 0 && valuesSize <= 30 ) { dataSetSize += 100; }
            if (valuesSize % 4 == 0 && valuesSize > 30) { dataSetSize += 100; }
        }

        SelectionSort selectionSort = new SelectionSort();
        ShellSort shellSort = new ShellSort();
        ArrayList<Integer> usedSizes= new ArrayList<>();

        ArrayList<Double[]> selectionCountResults = new ArrayList<>();
        ArrayList<Double[]> shellCountResults = new ArrayList<>();

        ArrayList<Double[]> selectionTimeResults = new ArrayList<>();
        ArrayList<Double[]> shellTimeResults = new ArrayList<>();

            ArrayList<Double> countsSelectionList = new ArrayList<>();
            ArrayList<Double> timesSelectionList = new ArrayList<>();
            ArrayList<Double> countsShellList= new ArrayList<>();
            ArrayList<Double> timesShellList = new ArrayList<>();

            for (int i = 0; i < values.size(); i++)
            {

                try {
                    selectionSort.sort(values.get(i));
                    shellSort.sort(values.get(i));
                }catch (UnsortedException e){
                    System.out.println(e.msg);
                }


                Integer size = values.get(i).length;
                if (i == 0){usedSizes.add(size);}

                if (! usedSizes.contains(size)){
                    usedSizes.add(size);

                    selectionCountResults.add(results(countsSelectionList));
                    selectionTimeResults.add(results(timesSelectionList));

                    shellCountResults.add(results(countsShellList));
                    shellTimeResults.add(results(timesShellList));

                    countsSelectionList.clear();
                    timesSelectionList.clear();
                    countsShellList.clear();
                    timesShellList.clear();
                }
                countsSelectionList.add((double) selectionSort.getCount());
                timesSelectionList.add((double) selectionSort.getTime());
                countsShellList.add((double) shellSort.getCount());
                timesShellList.add((double) shellSort.getTime());
            }
        String selectionOutputFileName = "selection sort.txt";
        String shellOutputFileName = "shell sort.txt";

        DecimalFormat df = new DecimalFormat("0.00");

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(selectionOutputFileName));
            BufferedWriter writer2 = new BufferedWriter(new FileWriter(shellOutputFileName));

            for (int i = 0; i < selectionCountResults.size(); i++)
            {
                writer.write(usedSizes.get(i) + " " + df.format(selectionCountResults.get(i)[0]) + " "
                    + df.format(selectionCountResults.get(i)[1] * 100)+ "%" +
                        " " + df.format(selectionTimeResults.get(i)[0]) + " " +
                        df.format(selectionTimeResults.get(i)[1]*100) + "%") ;
                writer.newLine();

                writer2.write(usedSizes.get(i) + " " + df.format(shellCountResults.get(i)[0]) + " "
                        + df.format(shellCountResults.get(i)[1]*100) + "% " +
                        df.format(shellTimeResults.get(i)[0]) + " " +
                        df.format(shellTimeResults.get(i)[1]*100) +"%");
                writer2.newLine();
            }
            writer.flush();
            writer2.flush();
            writer.close();
            writer2.close();

        }catch (IOException e)
        {
            System.out.println("Output file error");
        }

    }
    public static Double[] results(ArrayList<Double> countList)
    {
        double results = 0.0;
        double coefficientResults = 0.0;
        Double[] resultsArray = new Double[2];

        for(int k = 0; k < countList.size(); k++)
        {
            int size = countList.size();
            for (int i = 0; i < size; i++)
            {
                results += countList.get(i);
                if (i == size-1)
                {
                    results = results/size; //this provides the mean
                    for (int j = 0; j < countList.size(); j++)
                    {
                        coefficientResults = coefficientResults + (countList.get(j) - results) *
                                (countList.get(j) - results);
                        coefficientResults = Math.sqrt(coefficientResults/(countList.size()-1));
                        coefficientResults = coefficientResults/results;
                    }
                }
            }
        }
        resultsArray[0] = results;
        resultsArray[1] = coefficientResults;
        return resultsArray;
    }
}