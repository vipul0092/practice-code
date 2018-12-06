package code.vipul.string;

import java.util.Random;

/**
 * Created by vgaur on 06/12/18.
 */
public class RotateMatrix {

    private static int SIZE = 5;

    public static int[][] getDummyMatrix() {
        int[][] matrix = new int[SIZE][SIZE];
        Random random = new Random();

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                matrix[i][j] = random.nextInt(9);
            }
        }
        System.out.println("Original Matrix");
        displayMatrix(matrix);
        return matrix;
    }

    public static void rotate(int[][] matrix) {

        for (int level = 0; level <= SIZE / 2; level++) {
            for (int ctr = 0; ctr < SIZE - (level * 2) - 1; ctr++) {
                int temp = matrix[SIZE - level - 1 - ctr][level];
                matrix[SIZE - level - 1 - ctr][level] = matrix[SIZE - level - 1][SIZE - level - 1 - ctr];
                matrix[SIZE - level - 1][SIZE - level - 1 - ctr] = matrix[level + ctr][SIZE - level - 1];
                matrix[level + ctr][SIZE - level - 1] = matrix[level][level + ctr];
                matrix[level][level + ctr] = temp;
            }
        }
        System.out.println();
        System.out.println("Final Matrix");
        displayMatrix(matrix);
    }

    static void displayMatrix(int[][] matrix) {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

}
