package code.vipul;

import java.util.Random;
import java.util.Stack;

/**
 * doWork() Sorts elements in a stack so that the elements get smaller towards the top
 * Created by vgaur on 08/12/18.
 */
public class SortStack {

    public static void doWork(Stack<Integer> original) {

        Stack<Integer> temp = new Stack<>();

        temp.push(original.pop());

        while (!original.isEmpty()) {
            int poppedFromOriginal = original.pop();
            int elementsPoppedFromTemp = 0;

            while (!temp.isEmpty() && temp.peek() > poppedFromOriginal) {
                original.push(temp.pop());
                elementsPoppedFromTemp++;
            }

            temp.push(poppedFromOriginal);
            while (elementsPoppedFromTemp-- > 0) {
                temp.push(original.pop());
            }
        }

        //Answer is reverse of temp
        print(reverse(temp));
    }

    public static Stack<Integer> getRandomStack() {
        int size = 10;
        Stack<Integer> temp = new Stack<>();
        Random random = new Random();
        while (size-- > 0) {
            temp.push(random.nextInt(9));
        }
        return temp;
    }

    public static Stack<Integer> print(Stack<Integer> original) {
        Stack<Integer> temp = new Stack<>();
        while (!original.isEmpty()) {
            temp.push(original.pop());
            System.out.println(temp.peek() + " ");
        }
        System.out.println();
        return reverse(temp);
    }

    private static Stack<Integer> reverse(Stack<Integer> original) {
        Stack<Integer> temp = new Stack<>();
        while (!original.isEmpty()) {
            temp.push(original.pop());
        }
        return temp;
    }


}
