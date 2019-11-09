package code.vipul;

import java.util.LinkedHashSet;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * Created by vgaur on 03/11/19.
 */
public class KthNumberWithFactors {

    public static void solve(int k) {
        Set<Integer> numbers = new LinkedHashSet<>();
        numbers.add(1);
        k--;
        Queue<Integer> three = new ArrayBlockingQueue<Integer>(30);
        Queue<Integer> five = new ArrayBlockingQueue<Integer>(30);
        Queue<Integer> seven = new ArrayBlockingQueue<Integer>(30);

        three.add(1);
        five.add(1);
        seven.add(1);

        int answer = 1;

        while (k-- > 0) {

            int curr3 = -1, curr5 = -1, curr7 = -1;
            while (curr3 == -1 || numbers.contains(curr3)) {
                if (curr3 != -1) {
                    three.remove();
                }
                int dq = three.peek();
                curr3 = dq * 3;
            }

            while (curr5 == -1 || numbers.contains(curr5)) {
                if (curr5 != -1) {
                    five.remove();
                }
                int dq = five.peek();
                curr5 = dq * 5;
            }

            while (curr7 == -1 || numbers.contains(curr7)) {
                if (curr7 != -1) {
                    seven.remove();
                }
                int dq = seven.peek();
                curr7 = dq * 7;
            }

            answer = Math.min(curr3, Math.min(curr5, curr7));

            if (answer == curr3) {
                three.remove();
            } else if (answer == curr5) {
                five.remove();
            } else {
                seven.remove();
            }

            three.add(answer);
            five.add(answer);
            seven.add(answer);

            numbers.add(answer);
        }
        System.out.println(answer);
    }
}
