/*
Sort algorithm taken from https://www.geeksforgeeks.org/selection-sort/
 */

public class SelectionSort extends AbstractSort
{
    public void sort(int[] arr) throws UnsortedException
    {
        startSort();
        int n = arr.length;

        for (int i = 0; i < n - 1; i++)
        {
            int min_idx = i;
            for (int j = i + 1; j < n; j++)
            {
                if (arr[j] < arr[min_idx])
                {
                    min_idx = j;
                }
                incrementCount();
            }

            int temp = arr[min_idx];
            arr[min_idx] = arr[i];
            arr[i] = temp;
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