/*
Sort algorithm taken from https://www.geeksforgeeks.org/shellsort/
 */

public class ShellSort extends AbstractSort
{

        public void sort(int[] arr) throws UnsortedException
        {
            startTime = System.nanoTime();
            int n = arr.length;

            for (int gap = n/2; gap > 0; gap /= 2)
            {
                for (int i = gap; i < n; i += 1)
                {
                    int temp = arr[i];
                    int j;

                    for (j = i; j >= gap && arr[j - gap] > temp; j -= gap)
                        arr[j] = arr[j - gap];

                    incrementCount();

                    arr[j] = temp;
                }
            }
            endSort();
            for (int i = 1; i < arr.length; i++)
            {
                if (arr[i-1] > arr[i]){
                    throw new UnsortedException();
                }
            }
        }
}