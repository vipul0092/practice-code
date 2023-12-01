package code.vipul.aoc2019;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Grid {

    public static int ROWS = 5;
    public static int COLS = 5;

    public static void setMaxRowsCols(int maxRows, int maxCols) {
        ROWS = maxRows;
        COLS = maxCols;
    }

    private int[] layout;
    private boolean isInitialized = false;

    private static final Pos TOP_TOUCH_POS = Pos.of((ROWS/2) - 1, COLS/2);
    private static final Pos BOTTOM_TOUCH_POS = Pos.of((ROWS/2) + 1, COLS/2);
    private static final Pos RIGHT_TOUCH_POS = Pos.of(ROWS/2, (COLS/2) - 1);
    private static final Pos LEFT_TOUCH_POS = Pos.of(ROWS/2, (COLS/2) + 1);
    private static final Pos SKIP_POS = Pos.of(ROWS/2, COLS/2);

    private static final Map<Integer, Integer> POWERS_OF_2;

    static {
        POWERS_OF_2 = new HashMap<>();
        int power = 1;
        POWERS_OF_2.put(0, 1);
        for (int i = 1; i<= 31; i++) {
            power *= 2;
            POWERS_OF_2.put(i, power);
        }
    }

    private Grid() {
        layout = new int[ROWS];
        for (int i = 0; i < ROWS; i++) {
            layout[i] = 0;
        }
    }

    private Grid(int[] layout) {
        this.layout = layout;
        for (int i = 0; i < ROWS; i++) {
            if (layout[i] != 0) {
                isInitialized = true;
                break;
            }
        }
    }

    public Grid updateGrid() {
        return updateGrid(null, null);
    }

    public Grid updateGrid(Grid parent, Grid child) {
        if (!isInitialized && !parent.isInitialized() && !child.isInitialized()) {
            return this;
        }
        int[] result = new int[ROWS];
        for (int i=0; i < ROWS; i++) {
            int evaluatedRow = 0;
            for (int j = 0; j < COLS; j++) {
                Pos currentPos = Pos.of(i, j);
                if (currentPos.equals(SKIP_POS) && parent != null && child != null) {
                    continue;
                }
                List<Integer> neighbourValues = getNeighbourValues(currentPos, parent, child);
                int evaluatedCellValue = evaluateCellValue(currentPos, neighbourValues);

                if (evaluatedCellValue != 0) {
                    isInitialized = true;
                }
                evaluatedRow += (evaluatedCellValue * POWERS_OF_2.get(j));
            }
            result[i] = evaluatedRow;
        }
        return new Grid(result);
    }

    public int getOnes() {
        int count = 0;
        for (int i=0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                int value = cellValue(Pos.of(i, j));
                if (value == 1) {
                    count++;
                }
            }
        }
        return count;
    }

    public int getScore() {
        int score = 0;
        int k = 0;
        for (int i=0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                int value = cellValue(Pos.of(i, j));
                score += (value * POWERS_OF_2.get(k++));
            }
        }
        return score;
    }

    private int evaluateCellValue(Pos currentPos, List<Integer> neighbourValues) {
        int cellValue = cellValue(currentPos);
        if (cellValue == 0) { // 0 -> 1, only when there are one or two adjacent 1s
            long oneCount = neighbourValues.stream().filter(nbr -> nbr == 1).count();
            return oneCount == 2 || oneCount == 1
                    ? 1 : 0;
        } else { // 1 -> 0, only when there isnt exactly one 1 adjacent
            return neighbourValues.stream().filter(nbr -> nbr == 1).count() != 1
                    ? 0 : 1;
        }
    }

    private List<Integer> getNeighbourValues(Pos pos, Grid parent, Grid child) {
        List<Integer> neighbourValues = new ArrayList<>();

        if (!pos.isAtTopBoundary()) { // i - 1 >= 0
            neighbourValues.add(cellValue(pos.moveUp()));
        }

        if (!pos.isAtBottomBoundary()) { // i + 1 < ROWS
            neighbourValues.add(cellValue(pos.moveDown()));
        }

        if (!pos.isAtLeftBoundary()) { // j - 1 >= 0
            neighbourValues.add(cellValue(pos.moveLeft()));
        }

        if (!pos.isAtRightBoundary()) { // j + 1 < COLS
            neighbourValues.add(cellValue(pos.moveRight()));
        }

        if (pos.isAtTopBoundary() && parent != null) {
            neighbourValues.add(parent.cellValue(TOP_TOUCH_POS));
        }

        if (pos.isAtBottomBoundary() && parent != null) {
            neighbourValues.add(parent.cellValue(BOTTOM_TOUCH_POS));
        }

        if (pos.isAtRightBoundary() && parent != null) {
            neighbourValues.add(parent.cellValue(RIGHT_TOUCH_POS));
        }

        if (pos.isAtLeftBoundary() && parent != null) {
            neighbourValues.add(parent.cellValue(LEFT_TOUCH_POS));
        }

        if (pos.equals(TOP_TOUCH_POS) && child != null) {
            neighbourValues.addAll(child.getTopRowValues());
        }

        if (pos.equals(BOTTOM_TOUCH_POS) && child != null) {
            neighbourValues.addAll(child.getBottomRowValues());
        }

        if (pos.equals(RIGHT_TOUCH_POS) && child != null) {
            neighbourValues.addAll(child.getRightmostRowValues());
        }

        if (pos.equals(LEFT_TOUCH_POS) && child != null) {
            neighbourValues.addAll(child.getLeftmostRowValues());
        }

        return neighbourValues;
    }

    public List<Integer> getBottomRowValues() {
        List<Integer> values = new ArrayList<>();
        for (int j = 0; j< COLS; j++) {
            values.add(cellValue(Pos.of(ROWS - 1, j)));
        }
        return values;
    }

    public List<Integer> getTopRowValues() {
        List<Integer> values = new ArrayList<>();
        for (int j = 0; j< COLS; j++) {
            values.add(cellValue(Pos.of(0, j)));
        }
        return values;
    }

    public List<Integer> getLeftmostRowValues() {
        List<Integer> values = new ArrayList<>();
        for (int i = 0; i < ROWS; i++) {
            values.add(cellValue(Pos.of(i, 0)));
        }
        return values;
    }

    public List<Integer> getRightmostRowValues() {
        List<Integer> values = new ArrayList<>();
        for (int i = 0; i < ROWS; i++) {
            values.add(cellValue(Pos.of(i, COLS - 1)));
        }
        return values;
    }

    public boolean isInitialized() {
        return isInitialized;
    }

    public void display() {
        for (int i=0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                int value = cellValue(Pos.of(i, j));
                System.out.print(value == 0 ? '.' : '#');
            }
            System.out.println();
        }
        System.out.println();
    }

    public static Grid newGrid() {
        return new Grid();
    }

    public static Grid parseInput(List<String> rows) {
        int[] input = new int[ROWS];
        for (int i=0; i < ROWS; i++) {
            int parsedValue = 0;
            String row = rows.get(i);
            for (int j = 0; j < COLS; j++) {
                parsedValue += ((row.charAt(j) == '.' ? 0 : 1) * POWERS_OF_2.get(j));
            }
            input[i] = parsedValue;
        }
        return new Grid(input);
    }

    public int cellValue(Pos pos) {
        int row = layout[pos.i];
        return (row & (1 << pos.j)) != 0 ? 1 : 0;
    }

    public static final class Pos implements Comparable<Pos> {
        public int i() {
            return i;
        }

        public int j() {
            return j;
        }

        private final int i;
        private final int j;
        private int hash = -1;

        private Pos(int i, int j) {
            this.i = i;
            this.j = j;
        }

        public boolean isValid() {
            return this.i >= 0 && this.j >= 0 && this.i < ROWS && this.j < COLS;
        }

        public Pos copy() {
            return new Pos(this.i, this.j);
        }

        public static Pos of(int i, int j) {
            return new Pos(i, j);
        }

        public Pos moveLeft() {
            return new Pos(i, j - 1);
        }

        public Pos moveRight() {
            return new Pos(i, j + 1);
        }

        public Pos moveUp() {
            return new Pos(i - 1, j);
        }

        public Pos moveDown() {
            return new Pos(i + 1, j);
        }

        public Pos moveNE() {
            return new Pos(i - 1, j + 1);
        }

        public Pos moveNW() {
            return new Pos(i - 1, j - 1);
        }

        public Pos moveSE() {
            return new Pos(i + 1, j + 1);
        }

        public Pos moveSW() {
            return new Pos(i + 1, j - 1);
        }

        public Pos moveLeftGrid(int steps) {
            return new Pos(i, j - steps);
        }

        public Pos moveRightGrid(int steps) {
            return new Pos(i, j + steps);
        }

        public Pos moveUpGrid(int steps) {
            return new Pos(i - steps, j);
        }

        public Pos moveDownGrid(int steps) {
            return new Pos(i + steps, j);
        }

        // Functions below work wrt the Cartesian plane
        public Pos moveLeft(int steps) {
            return new Pos(i - steps, j);
        }

        public Pos moveRight(int steps) {
            return new Pos(i + steps, j);
        }

        public Pos moveUp(int steps) {
            return new Pos(i, j + steps);
        }

        public Pos moveDown(int steps) {
            return new Pos(i, j - steps);
        }

        public boolean isAtTopBoundary() {
            return i == 0;
        }

        public boolean isAtBottomBoundary() {
            return i == ROWS - 1;
        }

        public boolean isAtLeftBoundary() {
            return j == 0;
        }

        public boolean isAtRightBoundary() {
            return j == COLS - 1;
        }

        public boolean isCorner() {
            return (isAtTopBoundary() && isAtLeftBoundary())
                    || (isAtTopBoundary() && isAtRightBoundary())
                    || (isAtBottomBoundary() && isAtLeftBoundary())
                    || (isAtBottomBoundary() && isAtRightBoundary());
        }

        public boolean isAtEdge() {
            return isAtTopBoundary() || isAtBottomBoundary() || isAtLeftBoundary() || isAtRightBoundary();
        }

        @Override
        public int hashCode() {
            if (hash == -1) {
                hash = 5381;
                hash += (hash << 5) + Objects.hashCode(i);
                hash += (hash << 5) + Objects.hashCode(j);
            }
            return hash;
        }

        @Override
        public boolean equals(Object another) {
            if (this == another) return true;
            return another instanceof Pos && equalTo((Pos) another);
        }

        @Override
        public String toString() {
            return String.format("i: %s, j: %s", i, j);
        }

        private boolean equalTo(Pos another) {
            return Objects.equals(i, another.i) && Objects.equals(j, another.j);
        }

        @Override
        public int compareTo(Pos p) {
            int ret = this.i - p.i;
            // Equal i so fall back to comparing j.
            if (ret == 0) {
                ret = this.j - p.j;
            }
            return ret;
        }
    }
}
