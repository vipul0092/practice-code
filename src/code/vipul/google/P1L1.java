package code.vipul.google;


import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

// AC
public final class P1L1 {

    public static int func(int[] x, int[] y) {
        Set<Integer> all = new HashSet<>();
        Map<Integer, Integer> xMap = new HashMap<>();
        Map<Integer, Integer> yMap = new HashMap<>();


        int answer = 0;
        for (int i : x) {
            all.add(i);
            xMap.putIfAbsent(i, 0);

            xMap.put(i, xMap.get(i) + 1);
        }

        for (int i : y) {
            all.add(i);
            yMap.putIfAbsent(i, 0);

            yMap.put(i, yMap.get(i) + 1);
        }

        for (int i : all) {
            if((xMap.containsKey(i) && !yMap.containsKey(i))
            || (!xMap.containsKey(i) && yMap.containsKey(i))) {
                answer = i;
                break;
            }
        }

        return answer;
    }
}
