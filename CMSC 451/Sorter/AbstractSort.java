public abstract class AbstractSort
{
    int operations = 0;
    long startTime;
    long endTime;


    public abstract void sort(int[] array) throws UnsortedException;
    protected void startSort() { startTime = System.nanoTime(); }
    protected void endSort() { endTime = System.nanoTime() - startTime; }

    protected void incrementCount() { operations += 1; }
    public int getCount() { return operations;}
    public long getTime(){ return endTime; }

}
