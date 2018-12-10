package code.vipul;

/**
 * Implementation of a min heap using an array
 * Created by vgaur on 09/12/18.
 */
public class MinHeap {

    private int[] tree = new int[50];
    private int indexForNextInsertion = 0;

    public static void doStuff() {
        MinHeap heap = new MinHeap();

        heap.insert(10);
        heap.getMin();

        heap.insert(3);
        heap.getMin();

        heap.insert(7);
        heap.getMin();

        heap.insert(6);
        heap.getMin();

        heap.insert(1);
        heap.getMin();

        heap.popMin();
        heap.getMin();

        heap.popMin();
        heap.getMin();

        heap.popMin();
        heap.getMin();

        heap.insert(5);
        heap.getMin();

        heap.popMin();
        heap.getMin();

        heap.insert(8);
        heap.getMin();

        heap.popMin();
        heap.getMin();

    }

    public void insert(int value) {
        System.out.println("Inserting value to heap: " + value);
        tree[indexForNextInsertion] = value;
        balanceFromBottom();
        indexForNextInsertion++;
    }

    public void balanceFromBottom() {
        int index = indexForNextInsertion;

        while(index >= 0) {
            int parentIndex = (index - 1)/ 2;
            if (tree[parentIndex] > tree[index]) {
                int temp = tree[parentIndex];
                tree[parentIndex] = tree[index];
                tree[index] = temp;
                index = parentIndex;
            } else {
                break;
            }
        }
    }

    public int getMin() {
        int min = tree[0];
        System.out.println("Min: " + min);
        return min;
    }

    public int popMin() {
        int value = tree[0];
        System.out.println("Popping current minimum: " + value);
        tree[indexForNextInsertion] = 0;
        tree[0] = tree[indexForNextInsertion - 1];
        indexForNextInsertion--;
        balanceFromTop();

        return value;
    }

    public void balanceFromTop() {
        int index = 0;
        int indexLimit = (indexForNextInsertion -1)/2;
        while(index <= indexLimit) {
            int swapIndex = 0;
            int rightIndex = (index*2) + 2;
            int leftIndex = (index*2) + 1;

            if (rightIndex >= indexForNextInsertion) {
                rightIndex = indexForNextInsertion - 1;
            }

            if (leftIndex >= indexForNextInsertion) {
                rightIndex = indexForNextInsertion - 1;
            }

            if (tree[rightIndex] < tree[leftIndex]) {
                swapIndex = rightIndex;
            } else {
                swapIndex = leftIndex;
            }

            if (tree[swapIndex] < tree[index]) {
                int temp = tree[swapIndex];
                tree[swapIndex] = tree[index];
                tree[index] = temp;
                index = swapIndex;
            } else {
                break;
            }
        }
    }

}
