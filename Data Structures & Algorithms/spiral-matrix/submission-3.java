class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> values = new ArrayList<>();

        int minRow = 0, maxRow = matrix.length - 1, minCol = 0, maxCol = matrix[0].length - 1;

        while(minRow <= maxRow && minCol <= maxCol) {

            boolean added = false;
            // top edge
            for (int j = minCol; j <= maxCol; j++) {
                values.add(matrix[minRow][j]);
                added = true;
            }
            if (!added) continue;
            minRow++;

            
            added = false;
            // Right edge
            for (int i = minRow; i <= maxRow; i++) {
                values.add(matrix[i][maxCol]);
                added = true;
            }
            if (!added) continue;
            maxCol--;

            added = false;
            // Bottom edge
            for (int j = maxCol; j >= minCol; j--) {
                values.add(matrix[maxRow][j]);
                added = true;
            }
            if (!added) continue;
            maxRow--;

            added = false;
            // Left edge
            for (int i = maxRow; i >= minRow; i--) {
                values.add(matrix[i][minCol]);
                added = true;
            }
            if (!added) continue;
            minCol++;
        }

        return values;
    }
}