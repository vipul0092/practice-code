package code.vipul.search;

/**
 * Searching in a sorted array without knowing its size
 * Created by vgaur on 07/01/19.
 */
public class SearchWithoutSize {


    public static void solve() {

        Listy listy = Listy.getDummy();
        int value = 15;

        int ans = find(0, 2, value, listy);
        System.out.println(ans);
    }

    static int find(int start, int end, int value, Listy listy) {
        System.out.println("Iteration: " + start + " - " + end);
        if (end - start == 1) {
            if (listy.at(end) == value) {
                return end;
            } else if (listy.at(start) == value) {
                return start;
            } else return -1;
        }

        int mid = (start + end)/2;

        int startVal = listy.at(start);
        int endVal = listy.at(end);
        int midVal = listy.at(mid);

        if (startVal == -1 && endVal == -1) {
            return -1;
        }

        if (endVal != -1) {
            if (endVal < value) return find(end, 2*end, value, listy);
            else if (endVal > value) {
                if (midVal > value) return find(start, mid, value, listy);
                else return find(mid, end, value, listy);
            } else return end;
        } else {
            if (midVal == -1) return find(start, mid, value, listy);
            else if (midVal > value) return find(start, mid, value, listy);
            else if (midVal < value) return find(mid, end, value, listy);
            else return mid;
        }
    }

    static class Listy {
        final int size;
        final int[] array;

        Listy(int[] array) {
            this.size = array.length;
            this.array = array;
        }

        int at(int idx) {
            if (idx < size) return array[idx];
            return -1;
        }

        static Listy getDummy() {
            int[] array = new int[7];
            array[0] = 2;
            array[1] = 3;
            array[2] = 6;
            array[3] = 7;
            array[4] = 8;
            array[5] = 10;
            array[6] = 15;

            return new Listy(array);
        }
    }
}
