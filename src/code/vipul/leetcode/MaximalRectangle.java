package code.vipul.leetcode;
import java.util.*;

/**
 * Created by vgaur created on 25/07/23
 * https://leetcode.com/problems/maximal-rectangle/description/
 */
public class MaximalRectangle {

    public static void solve() {
        System.out.println(new MaximalRectangle().maximalRectangle(
                new char[][]{{'1','0','1','0','0'},{'1','0','1','1','1'},{'1','1','1','1','1'},{'1','0','0','1','0'}}
        ));
    }

    public int maximalRectangle(char[][] matrix) {
        int[] h = new int[matrix[0].length];

        int max = 0;
        for (char[] row: matrix) {
            for (int i = 0; i < row.length; i++) {
                h[i] = row[i] == '1' ? h[i] + 1 : 0;
            }

            max = Math.max(maxArea(h), max);
        }
        return max;
    }

    private int maxArea(int[] h) {
        int[] width = new int[h.length];
        Stack<Integer> s = new Stack<>();

        // to left
        for (int i = 0; i < h.length; i++) {
            if (s.isEmpty()) {
                width[i] = -1;
            } else if (h[s.peek()] < h[i]) {
                width[i] = s.peek();
            } else {
                while(!s.isEmpty() && h[s.peek()] >= h[i]) {
                    s.pop();
                }

                if (s.isEmpty()) {
                    width[i] = -1;
                } else {
                    width[i] = s.peek();
                }
            }
            s.push(i);
        }

        s = new Stack<>();
        // to right
        for (int i = h.length - 1; i >= 0; i--) {
            if (s.isEmpty()) {
                width[i] = h.length - width[i] - 1;
            } else if (h[s.peek()] < h[i]) {
                width[i] = s.peek() - width[i] - 1;;
            } else {
                while(!s.isEmpty() && h[s.peek()] >= h[i]) {
                    s.pop();
                }

                if (s.isEmpty()) {
                    width[i] = h.length - width[i] - 1;
                } else {
                    width[i] = s.peek() - width[i] - 1;;
                }
            }
            s.push(i);
        }

        int max = 0;
        for (int i = 0; i < h.length; i++) {
            max = Math.max(max, h[i] * width[i]);
        }
        return max;
    }
}
